public class IterativeDeepeningDepthFirstSearcher extends Searcher {

    public boolean search(SearchNode rootNode) {
        nodeCount = 0;
        int currentLimit = 1;

        while (true) {
            DepthLimitedSearcher dls = new DepthLimitedSearcher(currentLimit);
            boolean success = dls.search(rootNode);
            nodeCount += dls.getNodeCount();
            if (success) {
                goalNode = dls.getGoalNode();
                return true;
            }
            currentLimit++;
        }
    }

}
