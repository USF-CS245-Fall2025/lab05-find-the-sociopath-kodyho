
import  java.util.ArrayList;
import java.util.Random;

public class TestCases {
    
    protected static Random rnd;
    protected static Sociopath sociopathFinder;
    
    public static boolean  runRandomTest(int groupSize) {
        ArrayList<int []> likes = new ArrayList<int []>();
        int expected = rnd.nextInt(groupSize) + 1;
        for (int i = 1; i < groupSize; i++) {
            if (i != expected) {
                likes.add(new int [] {i, expected});
                int rndbuf = rnd.nextInt(groupSize)+1; // addiitional edges
                for (int j = 0; j < rndbuf; j++) {
                    likes.add(new int [] {i, rnd.nextInt(groupSize)+1});
                }
            } 
        }

        // chance for no sociopath
        int nosp = rnd.nextInt(4);
        if (nosp == 1) { 
            int liketarget = expected + 1 > groupSize ? expected - 1 : expected + 1;
            likes.add(new int[] {expected, liketarget});
            System.out.println("TEST: NO SOCIOPATH");
            expected = -1;
        } else if(nosp == 2) {
            likes.add(new int[] {expected, 0});
            System.out.println("TEST: LIKE 0");
            expected = -1;
        }

        int sociopath = sociopathFinder.findTheSociopath(groupSize, likes);
        return expected == sociopath;
    }

    public static boolean baseTest() {
        ArrayList<int []> likes = new ArrayList<int []>();
        likes.add(new int[] {1,2}); // expect 2
        int sociopath = sociopathFinder.findTheSociopath(2, likes);
        return sociopath == 2;
    }
    
	public static void main(String[] args) {
        rnd = new Random();
        sociopathFinder = new Sociopath();
        boolean success = baseTest();
        if (!success) {
            System.out.println("Failed");
            return;
        }
        System.out.println("Success");
        
	}

}