public class Baseline {
    
    private int num;
    private BaselineState state;
    private String text;

    public Baseline(int i) {
        num = i;
        state = BaselineState.EMPTY;
        text = "";
    }

    public int getNum() { return num; }
    public BaselineState getState() { return state; }
    public String getText() { return text; }

    public void updateState() {
        if (state == BaselineState.EMPTY) state = BaselineState.FILLED;
        else if (state == BaselineState.FILLED) state = BaselineState.EMPTY;
    }

    public void addToText(String str) {
        text += str;
    }

    public void removeFinalChar() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

}
