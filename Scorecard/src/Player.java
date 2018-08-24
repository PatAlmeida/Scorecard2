public class Player {

    private String name, position;

    public Player(String myName, String myPosition) {
        name = myName;
        position = myPosition;
    }

    public String toString() {
        return name + " - " + position;
    }

}
