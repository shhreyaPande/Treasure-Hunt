public class Level {
    private int levelNumber;
    private int rows;
    private int cols;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        setLevelProperties();
    }

    private void setLevelProperties() {
        switch (levelNumber) {
            case 1:
                rows = 3;
                cols = 3;
                break;
            case 2:
                rows = 5;
                cols = 5;
                break;
            case 3:
                rows = 7;
                cols = 7;
                break;
            default:
                rows = 3;
                cols = 3;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
