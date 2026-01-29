public class IterativeDeepeningDepthFirstSearcher extends Searcher {

	/**
	 * <code>search</code> - given an initial node, perform
	 * iterative-deepening depth-first search (IDDFS).
	 *
	 * @param rootNode a <code>SearchNode</code> value - the initial node
	 * @return a <code>boolean</code> value - whether or not goal node
	 * was found */
	public boolean search(SearchNode rootNode) {
		nodeCount = 0;
		int currentLimit = 0;

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
