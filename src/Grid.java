import static java.lang.Math.abs;
public class Grid {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";


    private int rows;
    private int cols;
    private int treasureRow;
    private int treasureCol;
    private int playerRow;
    private int playerCol;
    private int bug1row;
    private int bug1column;
    private int bug2row;
    private int bug2column;


    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        generateTreasureAndBugs();
        playerRow = 0;
        playerCol = 0;
    }

    private void generateTreasureAndBugs() {
        treasureRow = treasureCol = -1;
        bug1row = bug1column = -1;
        bug2row = bug2column = -1;

        do {
            treasureRow = (int) (Math.random() * rows);
            treasureCol = (int) (Math.random() * cols);
        } while (treasureRow == 0 && treasureCol == 0);

        do {
            bug1row = (int) (Math.random() * rows);
            bug1column = (int) (Math.random() * cols);
        } while ((bug1row == 0 && bug1column == 0) ||
                (bug1row == treasureRow && bug1column == treasureCol));

        do {
            bug2row = (int) (Math.random() * rows);
            bug2column = (int) (Math.random() * cols);
        } while ((bug2row == 0 && bug2column == 0) ||
                (bug2row == treasureRow && bug2column == treasureCol) ||
                (bug2row == bug1row && bug2column == bug1column));
    }

    public boolean isTreasureFound() {
        return playerRow == treasureRow && playerCol == treasureCol;
    }

    public void movePlayer(String direction) {
        switch (direction.toLowerCase()) {
            case "u":
                if (playerRow > 0) playerRow--;
                else System.out.println("Invalid direction");
                break;
            case "d":
                if (playerRow < rows - 1) playerRow++;
                else System.out.println("Invalid direction");
                break;
            case "l":
                if (playerCol > 0) playerCol--;
                else System.out.println("Invalid direction");
                break;
            case "r":
                if (playerCol < cols - 1) playerCol++;
                else System.out.println("Invalid direction");
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }
    private boolean isSamePosition(int row1, int col1, int row2, int col2) {
        return row1 == row2 && col1 == col2;
    }

    public boolean isfindbug(){
        if(isSamePosition(bug1row, bug1column, playerRow, playerCol) ||
                isSamePosition(bug2row, bug2column, playerRow, playerCol)){
            System.out.println(ANSI_RED + "💀 You hit a bug! Game Over." + ANSI_RESET);
            return true;
        }
        return false;

    }

    public String getTreasureHint() {
        int row = Math.abs(treasureRow-playerRow);
        int col = Math.abs(treasureCol-playerCol);
        int hint = (int) Math.sqrt((row * row) + (col * col));
        if(hint <= 1){
            return "You are close to the treasure";
        }
        else{
            return "";
        }
    }

    public String getBugHint(){
        int row1=Math.abs(bug1row-playerRow);
        int col1=Math.abs(bug1column-playerCol);
        int hint1 = (int) Math.sqrt((row1 * row1) + (col1 * col1));
        int row2=Math.abs(bug2row-playerRow);
        int col2=Math.abs(bug2column-playerCol);
        int hint2 = (int) Math.sqrt((row2 * row2) + (col2 * col2));
        if(hint2 <= 1 || hint1 <=1){
            return "😨 a BUG is detected!!!...";
        }
        else{
            return "";
        }
    }


    public void displayGrid() {
        if(isTreasureFound()){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == treasureRow && j == treasureCol) {
                        System.out.print(ANSI_GREEN+"T "+ANSI_RESET);
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }
        }
        else{
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == playerRow && j == playerCol) {
                        System.out.print(ANSI_YELLOW + "P " + ANSI_RESET);
                    }
//                    else if ((i == bug1row && j == bug1column) || (i == bug2row && j == bug2column)) {
//                        System.out.print(ANSI_RED + "B " + ANSI_RESET);
//                    } else if (i == treasureRow && j == treasureCol) {
//                        System.out.print(ANSI_GREEN + "T " + ANSI_RESET);
//                    }
                    else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }

        }
    }
}

