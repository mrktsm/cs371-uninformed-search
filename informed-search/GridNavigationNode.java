import java.util.ArrayList;

public class GridNavigationNode extends HSearchNode {

    private int row;
    private int col;
    private int goalRow;
    private int goalCol;
    private boolean[][] isBlocked;
    private double cost;
    private static boolean[][] visited;

    public GridNavigationNode(int startRow,
            int startCol,
            int goalRow,
            int goalCol,
            boolean[][] isBlocked) {
        this.row = startRow;
        this.col = startCol;
        this.goalRow = goalRow;
        this.goalCol = goalCol;
        this.isBlocked = isBlocked;
        this.cost = 0;
        visited = new boolean[isBlocked.length][isBlocked[0].length];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getG() {
        return cost;
    }

    public ArrayList<SearchNode> expand() {
        ArrayList<SearchNode> children = new ArrayList<SearchNode>();

        // If we've already expanded this state, don't do it again (Closed List logic)
        if (visited[row][col]) {
            return children;
        }
        visited[row][col] = true;

        int[] dRow = { -1, 1, 0, 0, -1, -1, 1, 1 };
        int[] dCol = { 0, 0, -1, 1, -1, 1, -1, 1 };

        for (int i = 0; i < 8; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (newRow >= 0 && newRow < isBlocked.length && newCol >= 0 && newCol < isBlocked[0].length
                    && !isBlocked[newRow][newCol] && !visited[newRow][newCol]) {

                GridNavigationNode child = (GridNavigationNode) childClone();
                child.row = newRow;
                child.col = newCol;
                child.cost = this.cost + ((i < 4) ? 1 : Math.sqrt(2)); // first 4 are straight, last 4 are diagonal
                children.add(child);
            }
        }
        return children;
    }

    public double getH() {
        return Math.sqrt(Math.pow(row - goalRow, 2) + Math.pow(col - goalCol, 2));
    }

    public boolean isGoal() {
        return row == goalRow && col == goalCol;
    }

    @Override
    public String toString() {
        return "GridNavigationNode[row=" + row + ", col=" + col + ", g=" + cost + "]";
    }

}
