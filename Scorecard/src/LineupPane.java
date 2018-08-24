import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LineupPane extends VBox {

    // Press Enter on team name TextField to quickly advance
    private static final boolean QUICK = true;

    private static final int NUM_PLAYERS = 9;
    private static final Font TITLE_FONT = Font.font("Times New Roman", FontWeight.BOLD, 28);
    private static final Font HEADER_FONT = Font.font("Times New Roman", FontWeight.NORMAL, 24);
    private static final Font FIELD_FONT = Font.font("Times New Roman", FontWeight.NORMAL, 18);

    private Lineup lineup;

    private TextField teamField;
    private TextField[] nameFields;
    private TextField[] positionFields;

    public LineupPane(Lineup line, Stage stage) {

        lineup = line;

        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Set Your Lineup");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setPadding(new Insets(10));
        getChildren().add(titleLabel);

        Label teamLabel = new Label("Team:");
        teamLabel.setFont(HEADER_FONT);
        teamField = new TextField();
        teamField.setAlignment(Pos.CENTER);
        teamField.setFont(HEADER_FONT);
        if (QUICK) {
            teamField.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    submit();
                    stage.close();
                }
            });
        }
        teamField.setPrefWidth(250);

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.getChildren().addAll(teamLabel, teamField);
        getChildren().add(hbox);

        Label nameLabel = new Label("Name");
        GridPane.setHalignment(nameLabel, HPos.CENTER);
        nameLabel.setFont(HEADER_FONT);
        Label positionLabel = new Label("Position");
        positionLabel.setFont(HEADER_FONT);

        Label[] numLabels = new Label[NUM_PLAYERS];
        nameFields = new TextField[NUM_PLAYERS];
        positionFields = new TextField[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            numLabels[i] = new Label(Integer.toString(i+1));
            numLabels[i].setFont(FIELD_FONT);
            nameFields[i] = new TextField();
            nameFields[i].setAlignment(Pos.CENTER);
            nameFields[i].setFont(FIELD_FONT);
            nameFields[i].setPrefWidth(150);
            positionFields[i] = new TextField();
            positionFields[i].setAlignment(Pos.CENTER);
            positionFields[i].setFont(FIELD_FONT);
            positionFields[i].setPrefWidth(50);
        }

        GridPane lineupGrid = new GridPane();
        lineupGrid.setAlignment(Pos.CENTER);
        lineupGrid.setHgap(10);
        lineupGrid.setPadding(new Insets(10));
        lineupGrid.setVgap(10);

        lineupGrid.add(nameLabel, 1, 0);
        lineupGrid.add(positionLabel, 2, 0);
        for (int i = 0; i < NUM_PLAYERS; i++) {
            lineupGrid.add(numLabels[i], 0, i+1);
            lineupGrid.add(nameFields[i], 1, i+1);
            lineupGrid.add(positionFields[i], 2, i+1);
        }
        getChildren().add(lineupGrid);

        Button submitButton = new Button("Submit Lineup");
        submitButton.setFont(HEADER_FONT);
        submitButton.setOnAction(e -> {
            submit();
            stage.close();
        });
        submitButton.setPrefWidth(320);

        StackPane buttonPane = new StackPane();
        buttonPane.setPadding(new Insets(10));
        buttonPane.getChildren().add(submitButton);
        getChildren().add(buttonPane);

        StackPane root = new StackPane();
        root.getChildren().add(this);
        stage.setScene(new Scene(root));
        stage.setTitle("Set Your Lineup");
        stage.show();

    }

    private void submit() {
        String team = teamField.getText();
        String[] names = new String[NUM_PLAYERS];
        String[] positions = new String[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            names[i] = nameFields[i].getText();
            positions[i] = positionFields[i].getText();
        }
        lineup.setInfo(team, names, positions);
    }

}
