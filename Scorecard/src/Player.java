public class Player {

    private int orderNum;
    private String name, position;

    public Player(int order, String myName, String pos) {
        orderNum = order;
        name = myName;
        position = pos;
    }

    public int getOrderNum() { return orderNum; }
    public String getName() { return name; }

    public String toString() {
        return name + " - " + position;
    }

}
