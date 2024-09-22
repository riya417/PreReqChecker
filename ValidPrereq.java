package prereqchecker;
import java.util.*; 
import java.util.HashMap;
import java.util.HashSet;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }

        //adjList
        // INPUT LOLLLLLLL
        StdIn.setFile(args[0]);
        StdOut.setFile(args[2]);
        
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        HashMap<String, Boolean> check = new HashMap<>();
        Hash id = new Hash(map);

        int n = StdIn.readInt(); 

        for(int i = 0; i < n; i++)
        {
            String str = StdIn.readString(); 
            id.inputCourse(str, map, check); 
        }

        int x = StdIn.readInt(); 

        int y = 0;
        while(y < x)
        {
            
            String key = StdIn.readString(); 
            String pre = StdIn.readString();
            id.addpQ(key, pre, map);
            y++;
        }

       
        //validpq
        StdIn.setFile(args[1]);
        String str1 = StdIn.readString();
        String str2 = StdIn.readString();
        Hash h2 = new Hash(map, check);
        StdOut.println(h2.validpq(str1, str2, check));
    }
}
