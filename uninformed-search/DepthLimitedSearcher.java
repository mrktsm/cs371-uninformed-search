import java.util.Stack;

public class DepthLimitedSearcher extends Searcher {

    private int limit;

    public DepthLimitedSearcher(int limit) {
        this.limit = limit;
    }

    /**
     * <code>search</code> - given an initial node, perform
     * depth-limited search (DLS). This particular implementation of
     * DLS is iterative.
     *
     * @param rootNode a <code>SearchNode</code> value - the initial node
     * @return a <code>boolean</code> value - whether or not goal node
     *         was found
     */
    public boolean search(SearchNode rootNode) {
        // Initialize search variables.
        Stack<SearchNode> stack = new Stack<SearchNode>();
        stack.add(rootNode);
        nodeCount = 0;

        // Main search loop.
        while (true) {

            // If the search stack is empty, return with failure
            // (false).
            if (stack.isEmpty()) {
                return false;
            }

            // Otherwise pop the next search node from the top of
            // the stack.
            SearchNode node = stack.pop();
            nodeCount++;
            // If the search node is a goal node, store it and return
            // with success (true).
            if (node.isGoal()) {
                goalNode = node;
                return true;
            }

            // Otherwise, expand the node and push each of its
            // children into the stack.
            if (node.depth < limit) {
                for (SearchNode child : node.expand()) {
                    stack.push(child);
                }
            }
        }

    }

}// DepthLimitedSearcher
