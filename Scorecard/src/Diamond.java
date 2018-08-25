public class Diamond {

    private static final int NUM_BASES = 4;

    private Player player;
    private int inningNum;
    private String centerText;
    private Baseline[] baselines;

    private DiamondPane diamondPane;

    public Diamond(Player play, int inning) {

        player = play;
        inningNum = inning;

        centerText = "";

        baselines = new Baseline[NUM_BASES];
        for (int i = 0; i < NUM_BASES; i++) {
            baselines[i] = new Baseline(i);
        }

        diamondPane = new DiamondPane(this);

    }

    public String getPlayerName() { return player.getName(); }
    public int getInningNum() { return inningNum; }
    public Baseline getBaseline(int i) { return baselines[i]; }
    public DiamondPane getPane() { return diamondPane; }
    public String getCenterText() { return centerText; }
    public void updateBaselineState(int i) { baselines[i].updateState(); }

    public void addToBaselineText(int i, String str) {
        baselines[i].addToText(str);
    }

    public void removeFinalCharOfBaseline(int i) {
        baselines[i].removeFinalChar();
    }

    public void addToCenterText(String str) {
        centerText += str;
    }

    public void removeFinalCharOfCenterText() {
        if (centerText.length() > 0) {
            centerText = centerText.substring(0, centerText.length() - 1);
        }
    }

}
