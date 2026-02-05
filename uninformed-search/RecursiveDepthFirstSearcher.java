public class RecursiveDepthFirstSearcher extends Searcher {

    public boolean search(SearchNode rootNode) {
        nodeCount = 0;
        return searchHelper(rootNode);
    }

    private boolean searchHelper(SearchNode node) {
        nodeCount++;
        if (node.isGoal()) {
            goalNode = node;
            return true;
        }
        for (SearchNode child : node.expand()) {
            if (searchHelper(child)) {
                return true;
            }
        }
        return false;
    }
}