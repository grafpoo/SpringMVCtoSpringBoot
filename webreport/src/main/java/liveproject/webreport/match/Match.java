package liveproject.webreport.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "EPL" )
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Match implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Result {
        HOME_WIN('H'), AWAY_WIN('A'), DRAW ('D'), UNK('X');

//        @JsonValue
        private char value;

        Result(char value) { this.value = value; }

        public char getValue() { return value; }

        public static Result parse(char id) {
            Result result = null; // Default
            for (Result item : Result.values()) {
                if (item.getValue()==id) {
                    result = item;
                    break;
                }
            }
            return result;
        }

    };

    @Id
    @GeneratedValue
    private Long id;

    private String division;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy") private Date date;
    private String homeTeam;
    private String awayTeam;
    private Integer fullTimeHomeGoals;
    private Integer fullTimeAwayGoals;
    private Result fullTimeResult;
    private Integer halfTimeHomeGoals;
    private Integer halfTimeAwayGoals;
    private Result halfTimeResult;
    private String referee;
    private Integer homeTeamShots;
    private Integer awayTeamShots;
    private Integer homeTeamShotsOnTarget;
    private Integer awayTeamShotsOnTarget;
    private Integer homeTeamFouls;
    private Integer awayTeamFouls;
    private Integer homeTeamCorners;
    private Integer awayTeamCorners;
    private Integer homeTeamYellowCards;
    private Integer awayTeamYellowCards;
    private Integer homeTeamRedCards;
    private Integer awayTeamRedCards;
    private String bet365HomeWinOdds;
    private String bet365DrawOdds;
    private String bet365AwayWinOdds;
    private String blueSquareHomeWinOdds;
    private String blueSquareDrawOdds;
    private String blueSquareAwayWinOdds;
    private String interwettenHomeWinOdds;
    private String interwettenDrawOdds;
    private String interwettenAwayWinOdds;
    private String ladbrokesHomeWinOdds;
    private String ladbrokesDrawOdds;
    private String ladbrokesAwayWinOdds;
    private String pinnacleHomeWinOdds;
    private String pinnacleDrawOdds;
    private String pinnacleAwayWinOdds;
    private String williamHillHomeWinOdds;
    private String williamHillDrawOdds;
    private String williamHillAwayWinOdds;
    private String vcBetHomeWinOdds;
    private String vcBetDrawOdds;
    private String vcBetAwayWinOdds;
    private Integer bb1X2;
    private String bbMxH;
    private String bbAvH;
    private String bbMxD;
    private String bbAvD;
    private String bbMxA;
    private String bbAvA;
    private Integer bbOU;
    private String bbMxGT25;
    private String bbAvGT25;
    private String bbMxLT25;
    private String bbAvLT25;
    private Integer bbAH;
    private String bbAHh;
    private String bbMxAHH;
    private String bbAvAHH;
    private String bbMxAHA;
    private String bbAvAHA;
    private String pinnacleClosingHomeWinOdds;
    private String pinnacleClosingDrawOdds;
    private String pinnacleClosingAwayWinOdds;;
    private String attendance;
    private Integer homeTeamHitWoodwork;
    private Integer awayTeamHitWoodwork;
    private Integer homeTeamOffsides;
    private Integer awayTeamOffsides;
    private Integer homeTeamBookingPoints;
    private Integer awayTeamBookingPoints;
    private String gameBookersHomeWinOdds;
    private String gameBookersDrawOdds;
    private String gameBookersAwayWinOdds;
    private String sportingBetHomeWinOdds;
    private String sportingBetOdds;
    private String sportingBetAwayWinOdds;
    private String sportingBetDrawOdds;
    private String gbGT25;
    private String gbLT25;
    private String b365GT25;
    private String b365LT25;
    private String gameBookersAsianHandicapHomeWinOdds;
    private String gameBookersAsianHandicapAwayWinOdds;
    private String ladbrokesAsianHandicapHomeWinOdds;
    private String ladbrokesAsianHandicapAwayWinOdds;
    private String ladbrokesAsianHandicapSize;
    private String betting365AsianHandicapHomeWinOdds;
    private String betting365AsianHandicapAwayWinOdds;
    private String betting365AsianHandicapSize;
    private String season;
    private String matchDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        if (date == null || homeTeam == null || awayTeam == null) return false;
        return date.equals(match.date) &&
                homeTeam.equals(match.homeTeam) &&
                awayTeam.equals(match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, homeTeam, awayTeam);
    }
}

/*
Div = League Division
Date = Match Date (dd/mm/yy)
HomeTeam = Home Team
AwayTeam = Away Team
FTHG and HG = Full Time Home Team Goals
FTAG and AG = Full Time Away Team Goals
FTR and Res = Full Time Match (H=Home Win, D=Draw, A=Away Win)
HTHG = Half Time Home Team Goals
HTAG = Half Time Away Team Goals
HTR = Half Time Match (H=Home Win, D=Draw, A=Away Win)

Match Statistics (where available)
Attendance = Crowd Attendance
Referee = Match Referee
HS = Home Team Shots
AS = Away Team Shots
HST = Home Team Shots on Target
AST = Away Team Shots on Target
HHW = Home Team Hit Woodwork
AHW = Away Team Hit Woodwork
HC = Home Team Corners
AC = Away Team Corners
HF = Home Team Fouls Committed
AF = Away Team Fouls Committed
HFKC = Home Team Free Kicks Conceded
AFKC = Away Team Free Kicks Conceded
HO = Home Team Offsides
AO = Away Team Offsides
HY = Home Team Yellow Cards
AY = Away Team Yellow Cards
HR = Home Team Red Cards
AR = Away Team Red Cards
HBP = Home Team Bookings Points (10 = yellow, 25 = red)
ABP = Away Team Bookings Points (10 = yellow, 25 = red)

Note that Free Kicks Conceeded includes fouls, offsides and any other offense commmitted and will always be equal to or higher than the number of fouls. Fouls make up the vast majority of Free Kicks Conceded. Free Kicks Conceded are shown when specific data on Fouls are not available (France 2nd, Belgium 1st and Greece 1st divisions).

Note also that English and Scottish yellow cards do not include the initial yellow card when a second is shown to a player converting it into a red, but this is included as a yellow (plus red) for European games.


Key to 1X2 (match) betting odds data:

B365H = Bet365 home win odds
B365D = Bet365 draw odds
B365A = Bet365 away win odds
BSH = Blue Square home win odds
BSD = Blue Square draw odds
BSA = Blue Square away win odds
BWH = Bet&Win home win odds
BWD = Bet&Win draw odds
BWA = Bet&Win away win odds
GBH = Gamebookers home win odds
GBD = Gamebookers draw odds
GBA = Gamebookers away win odds
IWH = Interwetten home win odds
IWD = Interwetten draw odds
IWA = Interwetten away win odds
LBH = Ladbrokes home win odds
LBD = Ladbrokes draw odds
LBA = Ladbrokes away win odds
PSH and PH = Pinnacle home win odds
PSD and PD = Pinnacle draw odds
PSA and PA = Pinnacle away win odds
SOH = Sporting Odds home win odds
SOD = Sporting Odds draw odds
SOA = Sporting Odds away win odds
SBH = Sportingbet home win odds
SBD = Sportingbet draw odds
SBA = Sportingbet away win odds
SJH = Stan James home win odds
SJD = Stan James draw odds
SJA = Stan James away win odds
SYH = Stanleybet home win odds
SYD = Stanleybet draw odds
SYA = Stanleybet away win odds
VCH = VC Bet home win odds
VCD = VC Bet draw odds
VCA = VC Bet away win odds
WHH = William Hill home win odds
WHD = William Hill draw odds
WHA = William Hill away win odds

Bb1X2 = Number of BetBrain bookmakers used to calculate match odds averages and maximums
BbMxH = Betbrain maximum home win odds
BbAvH = Betbrain average home win odds
BbMxD = Betbrain maximum draw odds
BbAvD = Betbrain average draw win odds
BbMxA = Betbrain maximum away win odds
BbAvA = Betbrain average away win odds

MaxH = Oddsportal maximum home win odds
MaxD = Oddsportal maximum draw win odds
MaxA = Oddsportal maximum away win odds
AvgH = Oddsportal average home win odds
AvgD = Oddsportal average draw win odds
AvgA = Oddsportal average away win odds



Key to total goals betting odds:

BbOU = Number of BetBrain bookmakers used to calculate over/under 2.5 goals (total goals) averages and maximums
BbMx>2.5 = Betbrain maximum over 2.5 goals
BbAv>2.5 = Betbrain average over 2.5 goals
BbMx<2.5 = Betbrain maximum under 2.5 goals
BbAv<2.5 = Betbrain average under 2.5 goals

GB>2.5 = Gamebookers over 2.5 goals
GB<2.5 = Gamebookers under 2.5 goals
B365>2.5 = Bet365 over 2.5 goals
B365<2.5 = Bet365 under 2.5 goals


Key to Asian handicap betting odds:

BbAH = Number of BetBrain bookmakers used to Asian handicap averages and maximums
BbAHh = Betbrain size of handicap (home team)
BbMxAHH = Betbrain maximum Asian handicap home team odds
BbAvAHH = Betbrain average Asian handicap home team odds
BbMxAHA = Betbrain maximum Asian handicap away team odds
BbAvAHA = Betbrain average Asian handicap away team odds

GBAHH = Gamebookers Asian handicap home team odds
GBAHA = Gamebookers Asian handicap away team odds
GBAH = Gamebookers size of handicap (home team)
LBAHH = Ladbrokes Asian handicap home team odds
LBAHA = Ladbrokes Asian handicap away team odds
LBAH = Ladbrokes size of handicap (home team)
B365AHH = Bet365 Asian handicap home team odds
B365AHA = Bet365 Asian handicap away team odds
B365AH = Bet365 size of handicap (home team)


Closing odds (last odds before match starts)

PSCH = Pinnacle closing home win odds
PSCD = Pinnacle closing draw odds
PSCA = Pinnacle closing away win odds

 */

/*
Div = League Division
Date = Match Date (dd/mm/yy)
HomeTeam = Home Team
AwayTeam = Away Team
FTHG and HG = Full Time Home Team Goals
FTAG and AG = Full Time Away Team Goals
FTR and Res = Full Time Match (H=Home Win, D=Draw, A=Away Win)
HTHG = Half Time Home Team Goals
HTAG = Half Time Away Team Goals
HTR = Half Time Match (H=Home Win, D=Draw, A=Away Win)

Match Statistics (where available)
Attendance = Crowd Attendance
Referee = Match Referee
HS = Home Team Shots
AS = Away Team Shots
HST = Home Team Shots on Target
AST = Away Team Shots on Target
HHW = Home Team Hit Woodwork
AHW = Away Team Hit Woodwork
HC = Home Team Corners
AC = Away Team Corners
HF = Home Team Fouls Committed
AF = Away Team Fouls Committed
HFKC = Home Team Free Kicks Conceded
AFKC = Away Team Free Kicks Conceded
HO = Home Team Offsides
AO = Away Team Offsides
HY = Home Team Yellow Cards
AY = Away Team Yellow Cards
HR = Home Team Red Cards
AR = Away Team Red Cards
HBP = Home Team Bookings Points (10 = yellow, 25 = red)
ABP = Away Team Bookings Points (10 = yellow, 25 = red)
*/