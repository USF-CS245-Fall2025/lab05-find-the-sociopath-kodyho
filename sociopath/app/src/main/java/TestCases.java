
import  java.util.ArrayList;

public class TestCases {
    
    public boolean  runTest(Sociopath sociopathFinder, int groupSize) {
        boolean passed = true;
        ArrayList<int []> likes = new ArrayList<int []>();
        for (int i = 1; i < groupSize; i++) {
            likes.add(new int [] {i, groupSize});
        }
        return passed;
    }
    

	public static void main(String[] args) {
        Sociopath sociopathFinder = new Sociopath();


        
	}

}