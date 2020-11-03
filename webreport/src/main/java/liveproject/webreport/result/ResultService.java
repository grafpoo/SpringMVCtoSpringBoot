package liveproject.webreport.result;

import liveproject.webreport.season.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ResultService {
	
	@Autowired
	private ResultRepository resultRepository;

	@Transactional
	public Result save(Result result) {
		resultRepository.save(result);
		return result;
	}

	public Season aggregateSeason(int year) {
		int homeWins = 0;
		int awayWins = 0;
		int draws = 0;
		int goallessDraws = 0;
		Map<String, Integer> teamPoints = new HashMap<>();
		Map<String, Integer> teamGoalDiff = new HashMap<>();
		for ( Result result: resultRepository.findAll() ) {
			addResults(teamPoints, teamGoalDiff, result.getAwayTeam(), result.getAwayGoalsFullTime()-result.getHomeGoalsFullTime());
			addResults(teamPoints, teamGoalDiff, result.getHomeTeam(), result.getHomeGoalsFullTime()-result.getAwayGoalsFullTime());
			if (result.getHomeGoalsFullTime() > result.getAwayGoalsFullTime()) homeWins++;
			if (result.getHomeGoalsFullTime() < result.getAwayGoalsFullTime()) awayWins++;
			if (result.getHomeGoalsFullTime() == result.getAwayGoalsFullTime()) {
				draws++;
				if (result.getHomeGoalsFullTime() == 0) goallessDraws++;
			}
		}
		// figure out results. this is a pain because ties on point go to goal difference
		List<Season.TeamResult> teamResults = new ArrayList<>();
		while (!teamPoints.isEmpty()) {
			String topTeam = getTopTeam(teamPoints, teamGoalDiff);
			teamResults.add(new Season.TeamResult(topTeam, teamPoints.get(topTeam), teamGoalDiff.get(topTeam)));
			teamPoints.remove(topTeam);
			teamGoalDiff.remove(topTeam);
		}

		// stick stuff in map
		Season season = new Season(teamResults, homeWins, awayWins, draws, goallessDraws);
		return season;
	}

	private String getTopTeam(Map<String, Integer> points, Map<String, Integer> goalDiff) {
		Integer maxPoints = points.values()
				.stream()
				.mapToInt(v -> v)
				.max().orElseThrow(NoSuchElementException::new);
		String topTeamSoFar = "XX";
		Integer bestGoalDiffSoFar = Integer.MIN_VALUE;
		for (Map.Entry<String, Integer> team : points.entrySet()) {
			if (team.getValue() == maxPoints) {
				String teamName = team.getKey();
				if (goalDiff.get(teamName) > bestGoalDiffSoFar) {
					topTeamSoFar = teamName;
					bestGoalDiffSoFar = goalDiff.get(teamName);
				}
			}
		}
		return topTeamSoFar;
	}
	private void addResults(Map<String, Integer> points, Map<String, Integer> goalDiff, String team, int diff) {
		// verify team exists in maps
		if (!points.containsKey(team)) {
			points.put(team, 0);
		}
		if (!goalDiff.containsKey(team)) {
			goalDiff.put(team, 0);
		}
		if (diff < 0) {
			// no points for you
		} else if (diff > 0) {
			// a win
			points.put(team, 3+points.get(team));
		} else {
			// a draw
			points.put(team, 1+points.get(team));
		}
		goalDiff.put(team, diff+goalDiff.get(team));
	}

}
