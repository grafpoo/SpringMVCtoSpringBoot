package liveproject.webreport.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByHomeTeam(String team);
    List<Match> findByAwayTeam(String team);
    List<Match> findByHomeTeamAndAwayTeamAndDate(String homeTeam, String awayTeam, Date date);

    @Query("SELECT m.homeTeam, count(m.fullTimeResult) from Match m where m.season = :season and m.fullTimeResult = 'H' group by m.homeTeam")
    List<Map<String, Integer>> homeWinsByTeam(@Param("season") String season);
    @Query("SELECT m.homeTeam, m.season, count(m.fullTimeResult) from Match m where m.fullTimeResult = 'H' group by m.homeTeam, m.season")
    List homeWinsByTeamBySeason();

    @Query("SELECT m.awayTeam, count(m.fullTimeResult) from Match m where m.season = :season and m.fullTimeResult = 'H' group by m.awayTeam")
    List<Map<String, Integer>> awayWinsByTeam(@Param("season") String season);
    @Query("SELECT m.awayTeam, m.season, count(m.fullTimeResult) from Match m where m.fullTimeResult = 'H' group by m.awayTeam, m.season")
    List awayWinsByTeamBySeason();
}
