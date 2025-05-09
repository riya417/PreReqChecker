package prereqchecker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE

    StdIn.setFile(args[0]);
        int numCourses = StdIn.readInt();
        StdOut.setFile(args[2]);
        HashMap<String,ArrayList<String>> idcMap = new HashMap<>();
        HashMap<String,Boolean> idcCheck = new HashMap<>();
        Hash hashMaps = new Hash(idcMap,idcCheck);
        for(int i = 0; i < numCourses;i++)
        {
            String cName = StdIn.readString();
            hashMaps.inputCourse(cName,idcMap,idcCheck);

        }
        int courseNums = StdIn.readInt();
        for(int i = 0; i < courseNums;i++)
        {
            String course = StdIn.readString();
            String preReq = StdIn.readString();
            hashMaps.addpQ(course,preReq,idcMap);
        }
        
        StdIn.setFile(args[1]);
        HashSet<String> add = new HashSet<>();
        HashSet<String> need = new HashSet<>();
        String target = StdIn.readString();
        int count = StdIn.readInt();
        Hash hashMaps2 = new Hash(idcMap, idcCheck);

        for(int i = 0; i < count; i++)
        {
            String course = StdIn.readString();
            hashMaps2.allPreReqs(course, add, idcMap);
        }

        HashSet<String> remove = hashMaps2.coursesNeeded(target, idcMap, need, add);
        for(String strs : remove)
        {
            StdOut.println(strs);
        }
    }
}
