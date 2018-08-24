public class Scorecard {

    private ScorecardPane scorecardPane;

    private Lineup lineup;

    public Scorecard(Lineup line) {

        lineup = line;

        scorecardPane = new ScorecardPane(this);

    }

    public String getTeamName() { return lineup.getTeamName(); }

}
