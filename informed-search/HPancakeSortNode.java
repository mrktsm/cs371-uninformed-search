import java.util.ArrayList;
import java.util.Arrays;

public class HPancakeSortNode extends HSearchNode {

    public int[] pancake;
    public int lastFlip;
    public int cost;

    public HPancakeSortNode(int size, int numShuffleFlips) {
        pancake = new int[size];

        for (int i = 0; i < size; i++) {
            pancake[i] = i;
        }

        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < numShuffleFlips; i++) {
            // Randomly select a flip size between 2 and size
            int flip = rand.nextInt(size - 1) + 2;
            flip(flip);
        }

        lastFlip = 0;
        cost = 0;
    }

    public HPancakeSortNode(int[] pancake) {
        this.pancake = pancake;
        lastFlip = 0;
        cost = 0;
    }

    public void flip(int n) {
        for (int i = 0; i < n / 2; i++) {
            int temp = pancake[i];
            pancake[i] = pancake[n - 1 - i];
            pancake[n - 1 - i] = temp;
        }
        cost += n;
        lastFlip = n;
    }

    @Override
    public boolean isGoal() {
        for (int i = 0; i < pancake.length; i++) {
            if (pancake[i] != i) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<SearchNode> expand() {
        ArrayList<SearchNode> children = new ArrayList<SearchNode>();
        for (int i = 2; i <= pancake.length; i++) {
            HPancakeSortNode child = (HPancakeSortNode) childClone();
            child.flip(i);
            children.add(child);
        }
        return children;
    }

    public int getLastFlip() {
        return lastFlip;
    }

    @Override
    public double getG() {
        return cost;
    }

    @Override
    public double getH() {
        return (isGoal()) ? 0 : 2;
    }

    public static HPancakeSortNode getGoalNode(int[] pancake) {
        HPancakeSortNode root = new HPancakeSortNode(pancake);
        BestFirstSearcher searcher = new BestFirstSearcher(new AStarComparator());
        searcher.search(root);
        return (HPancakeSortNode) searcher.getGoalNode();
    }

    @Override
    public HPancakeSortNode clone() {
        HPancakeSortNode copy = (HPancakeSortNode) super.clone();
        copy.pancake = pancake.clone();
        return copy;
    }

    @Override
    public String toString() {
        return "HPancakeSortNode{" + "pancake=" + Arrays.toString(pancake) + ", lastFlip=" + lastFlip + ", cost="
                + cost + '}';
    }

}
