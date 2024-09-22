package prereqchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Methods {
    

    public static HashMap<String, ArrayList<String>> makeAdjList(){

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        int numCourses = StdIn.readInt();
        for(int i = 0; i<numCourses; i++){
            String course = StdIn.readString();
            ArrayList<String> preqs = new ArrayList<>();
            map.put(course, preqs);
        }

        int numPreqs = StdIn.readInt();
        for(int i = 0; i<numPreqs; i++){
            String course = StdIn.readString();
            ArrayList<String> prereqs = map.get(course);
            String prev = StdIn.readString();
            prereqs.add(prev);
        }

        return map;
        
    }

    public static void printMap(HashMap<String, ArrayList<String>> map){
        for(String course: map.keySet()){
            StdOut.print(course + " ");
            ArrayList<String> preqs = map.get(course);
            for(int i = 0; i < preqs.size(); i++){
                StdOut.print(preqs.get(i) + " ");
            }
            StdOut.println();
        }
    }

    public static HashSet<String> prereqList(HashMap<String, ArrayList<String>> map,HashSet<String> onlyCourses, String course){

        ArrayList<String> prereqs = map.get(course);

        if(prereqs.size() != 0){
            for(int i = 0; i<prereqs.size(); i++){
                onlyCourses.add(prereqs.get(i));
                prereqList(map, onlyCourses, prereqs.get(i));
            }
            return onlyCourses;
        }    
        return onlyCourses;
    }

    public static Boolean isEligible(HashMap<String, ArrayList<String>> map, HashSet<String> onlyCourses, String course ){

        ArrayList<String> prereqs = map.get(course);
        boolean truth = true;

        if(onlyCourses.contains(course)){
            truth = false;
        }
        
        if(!onlyCourses.contains(course)){
            for(int i = 0; i<prereqs.size(); i++){
                if(!onlyCourses.contains(prereqs.get(i))){
                    truth = false;
            }
          }
        }
        return truth;
    }

    

    
}
