import java.util.List;

public class Sociopath {

	public int findTheSociopath (int groupSize, List<int []> likeList) {
		AdjList graph = new AdjList(groupSize+1); // +1 because 0 is not a person
		for (int [] like : likeList) {
			graph.addEdge(like[0], like[1]);
		}
		Node[] nodes = graph.getNodes();
		for (Node node : nodes) {
			if (node.vertex != 0 && node.getOutgoing() == 0 && node.incomingEdges == groupSize - 1) {
				return node.vertex;
			}
		}
		return -1;
	}

	// public static void main(String[] args) { // test case
	// 	int groupSize = 4;
	// 	ArrayList<int []> likes = new ArrayList<int []>();
	// 	likes.add(new int [] {1, 4});
	// 	likes.add(new int [] {2, 4});
	// 	likes.add(new int [] {3, 4});
	// 	int sociopath = findTheSociopath(groupSize, likes);
	// 	System.out.println(sociopath > 0 ? "The sociopath is person: " + sociopath : "There is no sociopath in this group");
	// }
}
