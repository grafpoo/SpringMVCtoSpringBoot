package liveproject.webreport.season;

import liveproject.webreport.result.ResultService;

import java.util.List;

public class Season {
    private final List<TeamResult> teamResults;
    private final Integer homeWins;
    private final Integer awayWins;
    private final Integer draws;
    private final Integer goallessDraws;

    public Season(List<TeamResult> teamResults, Integer homeWins, Integer awayWins, Integer draws, Integer goallessDraws) {
        this.teamResults = teamResults;
        this.homeWins = homeWins;
        this.awayWins = awayWins;
        this.draws = draws;
        this.goallessDraws = goallessDraws;
    }

    public List<TeamResult> getTeamResults() {
        return teamResults;
    }

    public Integer getHomeWins() {
        return homeWins;
    }

    public Integer getAwayWins() {
        return awayWins;
    }

    public Integer getDraws() {
        return draws;
    }

    public Integer getGoallessDraws() {
        return goallessDraws;
    }

    public static class TeamResult {
        private final String teamName;
        private final Integer points;
        private final Integer goalDiff;

        public TeamResult(String teamName, Integer points, Integer goalDiff) {
            this.teamName = teamName;
            this.points = points;
            this.goalDiff = goalDiff;
        }

        public String getTeamName() {
            return teamName;
        }

        public Integer getPoints() {
            return points;
        }

        public Integer getGoalDiff() {
            return goalDiff;
        }
    }

}
