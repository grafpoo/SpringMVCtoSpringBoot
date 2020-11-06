package liveproject.webreport.match;

import liveproject.webreport.match.Match;
import liveproject.webreport.match.MatchRepository;
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
public class MatchService {
	
	@Autowired
	private MatchRepository matchRepository;

	@Transactional
	public Match save(Match match) {
		matchRepository.save(match);
		return match;
	}

	public Season aggregateSeason(String seasonStr) {
		int homeWins = 0;
		int awayWins = 0;
		int draws = 0;
		int goallessDraws = 0;
		Map<String, Integer> teamPoints = new HashMap<>();
		Map<String, Integer> teamGoalDiff = new HashMap<>();
		for ( Match match : matchRepository.findBySeason(seasonStr) ) {
			addResults(teamPoints, teamGoalDiff, match.getAwayTeam(), match.getFullTimeAwayGoals()- match.getFullTimeHomeGoals());
			addResults(teamPoints, teamGoalDiff, match.getHomeTeam(), match.getFullTimeHomeGoals()- match.getFullTimeAwayGoals());
			if (match.getFullTimeHomeGoals() > match.getFullTimeAwayGoals()) homeWins++;
			if (match.getFullTimeHomeGoals() < match.getFullTimeAwayGoals()) awayWins++;
			if (match.getFullTimeHomeGoals() == match.getFullTimeAwayGoals()) {
				draws++;
				if (match.getFullTimeHomeGoals() == 0) goallessDraws++;
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
		Season season = new Season(teamResults, homeWins, awayWins, draws, goallessDraws, seasonStr);
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
