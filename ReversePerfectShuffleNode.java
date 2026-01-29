import java.util.ArrayList;
import java.util.Arrays;

public class ReversePerfectShuffleNode extends SearchNode {

    private int[] state;

    public ReversePerfectShuffleNode() {
        state = new int[52];
        for (int i = 0; i < state.length; i++) {
            state[i] = i;
        }
    }

    public boolean isGoal() {
        return state[4] == 0 && state[9] == 1
                && state[14] == 2 && state[19] == 3;
    }

    public ArrayList<SearchNode> expand() {
        ArrayList<SearchNode> children = new ArrayList<SearchNode>();

        // Reverse OUT-shuffle (Take all the even ones put them on top of the odd ones)
        int[] newState = new int[52];
        for (int i = 0; i < 26; i++) {
            newState[i] = state[i * 2];
            newState[i + 26] = state[i * 2 + 1];
        }

        ReversePerfectShuffleNode child = (ReversePerfectShuffleNode) this.childClone();
        child.state = newState;
        children.add(child);

        // Reverse IN-shuffle (Take all the odd ones put them on top of the even ones)
        newState = new int[52];
        for (int i = 0; i < 26; i++) {
            newState[i] = state[i * 2 + 1];
            newState[i + 26] = state[i * 2];
        }

        child = (ReversePerfectShuffleNode) this.childClone();
        child.state = newState;
        children.add(child);

        return children;
    }

    public Object clone() {
        ReversePerfectShuffleNode clone = (ReversePerfectShuffleNode) super.clone();
        clone.state = state.clone();
        return clone;
    }

    public String toString() {
        return Arrays.toString(state).replace("[", "").replace("]", "");
    }

    public static void main(String[] args) {
        Searcher searcher = new BreadthFirstSearcher();
        if (searcher.search(new ReversePerfectShuffleNode())) {
            searcher.printGoalPath();
        } else {
            System.out.println("Goal not found.");
        }
    }
}