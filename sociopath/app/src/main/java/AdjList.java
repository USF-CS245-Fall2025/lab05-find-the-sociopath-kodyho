import java.util.ArrayList;

public class AdjList {
    // represent an adjacency list for a graph.

    private Node[] adjLists;

    public void addEdge(int fromVertex, int toVertex) {
        Node fromNode = adjLists[fromVertex];
        Node toNode = adjLists[toVertex];
        if (toNode == null || fromNode == null) {
            throw new IllegalArgumentException(": Vertex does not exist");
        }
        if (toVertex <= 0) {
            return; // do not add edges to vertex 0, stand in for null
        }
        adjLists[fromVertex].next.add(toVertex);
        adjLists[toVertex].incomingEdges += 1;
    }

    public Node[] getNodes() {
        return adjLists;
    }

    public AdjList(int numVertices) {
        // 0 is not a person in this case
        adjLists = new Node[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjLists[i] = new Node(i);
        }
    }

}