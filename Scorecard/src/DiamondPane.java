import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DiamondPane extends StackPane {

    private static final boolean INFO = false;
    private static final int NUM_BASES = 4;

    private Diamond diamond;

    private Canvas canvas;
    private double mx, my;

    public DiamondPane(Diamond dia) {

        diamond = dia;

        setOnKeyPressed(e -> {

            // Update center text if close to (50, 50)
            double dist = Math.hypot(50 - mx, 50 - my);

            if (dist < 10) {

                KeyCode code = e.getCode();
                if (code == KeyCode.BACK_SPACE) {
                    diamond.removeFinalCharOfCenterText();
                } else {
                    String text = e.getText();
                    if (!text.equals("")) {
                        if (e.isShiftDown()) {
                            diamond.addToCenterText(text.toUpperCase());
                        } else {
                            diamond.addToCenterText(text);
                        }
                    }
                }

            } else {

                int baselineNum = -1;
                if (mx >= 50 && mx < 100 && my >= 50 && my < 100) baselineNum = 0;
                if (mx >= 50 && mx < 100 && my >= 0 && my < 50) baselineNum = 1;
                if (mx >= 0 && mx < 50 && my >= 0 && my < 50) baselineNum = 2;
                if (mx >= 0 && mx < 50 && my >= 50 && my < 100) baselineNum = 3;

                KeyCode code = e.getCode();
                if (code == KeyCode.BACK_SPACE) {
                    diamond.removeFinalCharOfBaseline(baselineNum);
                } else {
                    String text = e.getText();
                    if (!text.equals("")) {
                        if (e.isShiftDown()) {
                            diamond.addToBaselineText(baselineNum, text.toUpperCase());
                        } else {
                            diamond.addToBaselineText(baselineNum, text);
                        }
                    }
                }

            }

            draw();

        });

        setOnMouseMoved(e -> {
            setFocusTraversable(true);
            requestFocus();
            mx = e.getX();
            my = e.getY();
        });

        setOnMouseClicked(e -> {

            int baselineNum = -1;
            if (mx >= 50 && mx < 100 && my >= 50 && my < 100) baselineNum = 0;
            if (mx >= 50 && mx < 100 && my >= 0 && my < 50) baselineNum = 1;
            if (mx >= 0 && mx < 50 && my >= 0 && my < 50) baselineNum = 2;
            if (mx >= 0 && mx < 50 && my >= 50 && my < 100) baselineNum = 3;

            diamond.updateBaselineState(baselineNum);

            draw();

        });

        canvas = new Canvas(100, 100);
        draw();
        getChildren().add(canvas);

    }

    private void draw() {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw background
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 100, 100);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.strokeRect(0, 0, 100, 100);

        // Draw baselines
        for (int i = 0; i < NUM_BASES; i++) {

            Baseline baseline = diamond.getBaseline(i);

            // Set the color
            switch (baseline.getState()) {
                case EMPTY: gc.setStroke(Color.LIGHTGREY); break;
                case FILLED: case CUTOFF:
                    gc.setStroke(Color.rgb(255, 255, 60)); break;
            }

            // Draw the line
            gc.setLineWidth(7);
            int baselineNum = baseline.getNum();
            switch (baselineNum) {
                case 0: gc.strokeLine(50, 85, 85, 50); break;
                case 1: gc.strokeLine(85, 50, 50, 15); break;
                case 2: gc.strokeLine(50, 15, 15, 50); break;
                case 3: gc.strokeLine(15, 50, 50, 85); break;
            }

        }

        // Draw baseline text after lines
        for (int i = 0; i < NUM_BASES; i++) {

            Baseline baseline = diamond.getBaseline(i);
            int baselineNum = baseline.getNum();

            // Get text info
            String str = baseline.getText();
            Text text = new Text(str);
            double offset = text.getLayoutBounds().getWidth() / 2;

            // Draw text
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            switch (baselineNum) {
                case 0: gc.strokeText(str, 67.5 - offset, 70); break;
                case 1: gc.strokeText(str, 67.5 - offset, 35); break;
                case 2: gc.strokeText(str, 32.5 - offset, 35); break;
                case 3: gc.strokeText(str, 32.5 - offset, 70); break;
            }

        }

        // Draw center text
        String str = diamond.getCenterText();
        Text text = new Text(str);
        double offset = text.getLayoutBounds().getWidth() / 2;
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeText(str, 50 - offset, 55);

        // Draw Player info text - For testing purposes
        if (INFO) {
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            String infoText = diamond.getPlayerName() + " - " + diamond.getInningNum();
            gc.strokeText(infoText, 10, 20);
        }

    }

}
