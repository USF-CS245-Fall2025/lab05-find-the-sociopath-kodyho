import java.util.ArrayList;

    public class Node {
        public final int vertex;
        ArrayList<Integer> next; // the number of edges is variable so use a list which can grow easily
        int incomingEdges;

        public int getOutgoing() {
            return next.size();
        }

        public Node(int vertex) {
            this.vertex = vertex;
            this.next = new ArrayList<Integer>();
            this.incomingEdges = 0;
        }
    }