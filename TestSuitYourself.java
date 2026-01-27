
public class TestSuitYourself {
    public static void main(String[] args) {
        // Test case from the user's prompt:
        // "new SuitYourselfSoloNode(4, 13, 5, 0L);"
        
        SuitYourselfSoloNode node = new SuitYourselfSoloNode(4, 13, 5, 0L);
        System.out.println("Searching for solution for SuitYourselfSoloNode(4, 13, 5, 0L)...");
        
        BreadthFirstSearcher searcher = new BreadthFirstSearcher();
        if (searcher.search(node)) {
            System.out.println("Solution found!");
            System.out.println("Goal node found in " + searcher.nodeCount + " nodes.");
            
            // Reconstruct path
            java.util.List<SearchNode> path = new java.util.ArrayList<SearchNode>();
            SearchNode current = searcher.goalNode;
            while (current != null) {
                path.add(0, current);
                current = current.parent;
            }
            
            System.out.println("Solution path length (moves): " + (path.size() - 1));
            // System.out.println("Path:\n" + searcher.goalNode.toString()); 
        } else {
            System.out.println("No solution found.");
        }
    }
}
