import java.util.Scanner;
public class TreasureHunt implements Game{
    private Player player;
    private Grid grid;
    private Level level;
    private int currentLevel;

    public TreasureHunt(Player player) {
        this.player = player;
        this.currentLevel = 1;
        this.level = new Level(currentLevel);
        this.grid = new Grid(level.getRows(), level.getCols());
    }

    @Override
    public void startGame() {
        System.out.println("Welcome to the Treasure Hunt, " + player.getName());
        Scanner scanner = new Scanner(System.in);
        boolean isGameOver = false;

        while (!isGameOver) {
            grid.displayGrid();
            System.out.println(grid.getTreasureHint());

            System.out.println(grid.getBugHint());

            System.out.println("Your move (up, down, left, right): ");
            String move = scanner.nextLine();
            player.makeMove(grid, move);

            if (grid.isfindbug()) {
                isGameOver = true; // optional: use this or just exit
                System.exit(0); // instantly terminate the game

            }

            if (checkWin(player)) {
                grid.displayGrid();
                System.out.println("Congratulations, " + player.getName() + "! You found the treasure in " + player.getMoves() + " moves.");
                isGameOver = true;
                nextLevel();
            }
        }
    }

    @Override
    public boolean checkWin(Player player) {
        return grid.isTreasureFound();
    }

    @Override
    public void nextLevel() {
        currentLevel++;
        if (currentLevel <= 3) {
            level = new Level(currentLevel);
            grid = new Grid(level.getRows(), level.getCols());
            System.out.println("Moving to level " + currentLevel);
            startGame();
        } else {
            System.out.println("You completed all levels! Game Over.");
        }
    }
    public static void main(String [] args) {
        String name;
        System.out.println("Please enter your name: ");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        Game game = new TreasureHunt(new Player(name));
        game.startGame();
    }
}
