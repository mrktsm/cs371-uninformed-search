import java.util.ArrayList;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class SuitYourselfSoloNode extends SearchNode {
    private int[] pileSize;
    private int[] topSuit;

    private int[][] suitBelow;  // suitBelow[col][n] = suit when n cards remain
    private int numColumns;

    public SuitYourselfSoloNode(int numSuits, int numRanks, int numColumns, long seed) {
        ArrayList<Stack<Integer>> generatedColumns = SuitYourselfSoloGenerator.generate(numSuits, numRanks, numColumns, seed);

        this.numColumns = numColumns;
        this.pileSize = new int[numColumns];
        this.topSuit = new int[numColumns];
        this.suitBelow = new int[numColumns][];

        for (int col = 0; col < numColumns; col++) {
            Stack<Integer> stack = generatedColumns.get(col);
            pileSize[col] = stack.size();

            suitBelow[col] = new int[pileSize[col] + 1];
            suitBelow[col][0] = -1; // Empty pile has no suit
            for (int n = 1; n <= pileSize[col]; n++) {
                suitBelow[col][n] = stack.get(n - 1);
            }

            topSuit[col] = pileSize[col] > 0 ? suitBelow[col][pileSize[col]] : -1; 
        }
    }

    public boolean isGoal() {
        for (int size : pileSize) {
            if (size > 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<SearchNode> expand() {
        ArrayList<SearchNode> children = new ArrayList<SearchNode>();
        
        // Find all valid suits (cards on top of non-empty piles)
        Set<Integer> validSuits = new HashSet<Integer>();
        for (int col = 0; col < numColumns; col++) {
            if (topSuit[col] != -1) {
                validSuits.add(topSuit[col]);
            }
        }

        for (int suit : validSuits) {
            // Create a new state node for each suit
            SuitYourselfSoloNode child = (SuitYourselfSoloNode) childClone();
            
            for (int col = 0; col < numColumns; col++) {
                while (child.topSuit[col] == suit) {
                    child.pileSize[col]--;
                    child.topSuit[col] = 
                        (child.pileSize[col] > 0) ? child.suitBelow[col][child.pileSize[col]] : -1;
                }
            }
            children.add(child);
        }
        
        return children;
    }

    @Override
    public Object clone() {
        SuitYourselfSoloNode copy = (SuitYourselfSoloNode) super.clone();
        copy.pileSize = this.pileSize.clone();
        copy.topSuit = this.topSuit.clone();

        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuitYourselfSoloNode that = (SuitYourselfSoloNode) o;
        return Arrays.equals(pileSize, that.pileSize) &&
               Arrays.equals(topSuit, that.topSuit);
    }
        

}
