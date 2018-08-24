import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ScorecardPane extends VBox {

    private static final int NUM_PLAYERS = 9;
    private static final int NUM_INNINGS = 9;
    private static final Font TITLE_FONT = Font.font("Times New Roman", FontWeight.BOLD, 28);

    private Scorecard scorecard;

    public ScorecardPane(Scorecard score) {

        scorecard = score;

        setAlignment(Pos.CENTER);

        Label titleLabel = new Label(scorecard.getTeamName());
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setPadding(new Insets(10));
        getChildren().add(titleLabel);

        Canvas canvas = new Canvas(100, 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 100, 100);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.strokeRect(0, 0, 100, 100);
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(7);
        gc.strokeLine(50, 85, 85, 50);
        gc.strokeLine(85, 50, 50, 15);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeLine(50, 15, 15, 50);
        gc.strokeLine(15, 50, 50, 85);
        getChildren().add(canvas);

        StackPane root = new StackPane();
        root.getChildren().add(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Scorecard - " + scorecard.getTeamName());
        stage.show();

    }

}
