public class Player {
    private String name;
    private int moves;

    public Player(String name) {
        this.name = name;
        this.moves = 0;
    }

    public void makeMove(Grid grid, String direction) {
        grid.movePlayer(direction);
        moves++;
    }

    public int getMoves() {
        return moves;
    }

    public String getName() {
        return name;
    }
}
