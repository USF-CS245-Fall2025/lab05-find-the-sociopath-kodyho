/**
 * Sociopath class to find the sociopath in a group based on likes.
 * A sociopath is defined as someone who is liked by everyone else but likes no one.
 * 
 * This program has all the necessary methods to determine the sociopath in a group but requires the TestCases class to run. i.e compile TestCases.java and run that.
 * 
 * @author Kody Ho
 */

public class Sociopath {

	private final int groupSize; // the simplest approach as discussed in class
    private final int[] inDegree;
    private final int[] outDegree;

	/**
	 * A small helper class to add edges to the 'graph'.
	 * 
	 * @param from The node that likes another node.
	 * @param to The node that is liked by another node.
	 * @throws IllegalArgumentException if either node is out of bounds.
	 */
	private void add(int from, int to) {
		if (from < 1 || from > groupSize || to < 1 || to > groupSize) {
			return; // ignore invalid edges
		}
		outDegree[from - 1]++; // we subtract 1 to convert to 0-based index
		inDegree[to - 1]++;
	}

	/**
	 * Finds the sociopath in the group.
	 * @return The index of the sociopath if found, otherwise -1.
	 */
	public int findTheSociopath () {
		for (int i = 0; i < groupSize; i++) {
			if (inDegree[i] == groupSize - 1 && outDegree[i] == 0) {
				return i+1;
			}
		}
		return -1; // no sociopath found
	}

	/**
	 * Constructor for Sociopath class.
	 */
	public Sociopath(int groupSize, int[][] edges) {
		this.groupSize = groupSize;
		inDegree = new int[groupSize];
		outDegree = new int[groupSize];
		for (int[] edge : edges) { 
			add(edge[0], edge[1]);
		}
	}

}
