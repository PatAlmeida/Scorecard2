public class Scorecard {

    private static final int NUM_PLAYERS = 9;
    private static final int NUM_INNINGS = 9;

    private ScorecardPane scorecardPane;

    private Lineup lineup;
    private Diamond[][] diamonds;

    public Scorecard(Lineup line) {

        lineup = line;

        diamonds = new Diamond[NUM_PLAYERS][NUM_INNINGS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            Player player = lineup.getPlayer(i);
            for (int j = 0; j < NUM_INNINGS; j++) {
                diamonds[i][j] = new Diamond(player, j);
            }
        }

        scorecardPane = new ScorecardPane(this);

    }

    public String getTeamName() { return lineup.getTeamName(); }

    public DiamondPane getDiamondPane(int pIndex, int iIndex) {
        return diamonds[pIndex][iIndex].getPane();
    }

}
