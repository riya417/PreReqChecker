package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {
        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }
 
        StdIn.setFile(args[0]);
        int nCourses = StdIn.readInt();
        StdOut.setFile(args[2]);
        HashMap<String,ArrayList<String>> cMap = new HashMap<>();
        HashMap<String,Boolean> isVisited = new HashMap<>();
        Hash holdHashMaps = new Hash(cMap,isVisited);
        for(int i = 0; i < nCourses;i++)
        {
            String cName = StdIn.readString();
            holdHashMaps.inputCourse(cName,cMap,isVisited);

        }
        int cNums = StdIn.readInt();
        for(int i = 0; i < cNums;i++)
        {
            String course = StdIn.readString();
            String preReq = StdIn.readString();
            holdHashMaps.addpQ(course,preReq,cMap);
        }
        StdIn.setFile(args[1]);
        HashSet<String> cSet = new HashSet<>();
        HashSet<String> eligible = new HashSet<>();
        int numOfCourses = StdIn.readInt();
        Hash holdHashMaps2 = new Hash(cMap, isVisited);
       
        for(int i = 0; i < numOfCourses;i++)
        {
            String course = StdIn.readString();
            holdHashMaps2.allPreReqs(course, cSet, cMap);
  
        }
       
        Boolean isEligible = true;
        for( String s : cMap.keySet())
        {
            isEligible = true;
            if(!cSet.contains(s))
            {
                for(String prereq: cMap.get(s))
                {
                    if(!cSet.contains(prereq))
                    {
                         isEligible = false;
                    }
                }
            }
           
            if(isEligible && !cSet.contains(s))
            {
                eligible.add(s);
  
            }
           
        }
        for(String course: eligible)
        {
            StdOut.println(course);
        }
  
    }
}