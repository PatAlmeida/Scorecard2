import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ScorecardPane extends VBox {

    private static final int NUM_PLAYERS = 9;
    private static final int NUM_INNINGS = 9;
    private static final int PLAYERS_ON_SCREEN = 5;
    private static final int INNINGS_ON_SCREEN = 5;
    private static final Font TITLE_FONT = Font.font("Times New Roman", FontWeight.BOLD, 28);

    private Scorecard scorecard;
    private int startingPlayerIndex;
    private int startingInningIndex;
    private GridPane diamondGrid;

    public ScorecardPane(Scorecard score) {

        scorecard = score;
        startingPlayerIndex = 0;
        startingInningIndex = 0;

        setAlignment(Pos.CENTER);

        Label titleLabel = new Label(scorecard.getTeamName());
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setPadding(new Insets(10));
        getChildren().add(titleLabel);

        GridPane diamondGrid = new GridPane();
        diamondGrid.setAlignment(Pos.CENTER);
        diamondGrid.setHgap(10);
        diamondGrid.setPadding(new Insets(10));
        diamondGrid.setVgap(10);

        for (int i = 0; i < PLAYERS_ON_SCREEN; i++) {
            for (int j = 0; j < INNINGS_ON_SCREEN; j++) {
                int pIndex = (i + startingPlayerIndex) % NUM_PLAYERS;
                int iIndex = (j + startingInningIndex) % NUM_INNINGS;
                DiamondPane pane = scorecard.getDiamondPane(pIndex, iIndex);
                diamondGrid.add(pane, j, i);
            }
        }
        getChildren().add(diamondGrid);

        StackPane root = new StackPane();
        root.getChildren().add(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Scorecard - " + scorecard.getTeamName());
        stage.show();

    }

}
