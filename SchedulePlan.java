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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    StdIn.setFile(args[0]);
    HashMap<String, ArrayList<String>> map = Methods.makeAdjList();
    StdIn.setFile(args[1]);
    StdOut.setFile(args[2]);

    String target = StdIn.readString();
    int numtaken = StdIn.readInt();

    HashSet<String> takenCourses = new HashSet<>();
    
    for(int i = 0; i<numtaken; i++){
        String course = StdIn.readString();
        takenCourses = Methods.prereqList(map, takenCourses, course);
        takenCourses.add(course);
    }

    HashSet<String> pretarget = new HashSet<>();
    pretarget = Methods.prereqList(map, pretarget, target);

    HashMap<Integer, ArrayList<String>> semlist = new HashMap<>();

    int count = 0;

    HashSet<String> checking = new HashSet<>();
    
    for(String key : pretarget){
        if(!takenCourses.contains(key)){
            checking.add(key);
        }
    }
    
    while(checking.size() != 0){
        
      ArrayList<String> list = new ArrayList<String>();
      for(String key : checking){
        if(Methods.isEligible(map, takenCourses, key) == true){
            list.add(key);
            //takenCourses.add(key);
        }
      }
      for(int j = 0; j<list.size(); j++){
          takenCourses.add(list.get(j));
      }
      for(int i = 0; i<list.size(); i++){
          checking.remove(list.get(i));
      }
      semlist.put(count+1,list);
      count++;
     }

     StdOut.println(count);
     for(int course: semlist.keySet()){
        
        ArrayList<String> prereqs = semlist.get(course);
        for(int i = 0; i<prereqs.size(); i++){
            StdOut.print(prereqs.get(i) + " ");
        }
        StdOut.println();
    }
    }
}
