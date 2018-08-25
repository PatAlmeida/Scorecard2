import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Lineup {

    private static final int NUM_PLAYERS = 9;

    private LineupPane lineupPane;

    private String teamName;
    private Player[] players;

    public Lineup(Stage stage) {
        lineupPane = new LineupPane(this, stage);
    }

    public String getTeamName() { return teamName; }
    public Player getPlayer(int i) { return players[i]; }

    public void setInfo(String team, String[] names, String[] positions) {

        teamName = team;
        players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players[i] = new Player(i, names[i], positions[i]);
        }

        Scorecard scorecard = new Scorecard(this);

    }

}
