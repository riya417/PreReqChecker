package prereqchecker;
import java.util.*;
public class Hash {
    
    private HashMap<String, ArrayList<String>> preReq;
    private HashMap<String, Boolean> mark; 
    

    public Hash(HashMap<String, ArrayList<String>> preReq)
    {
        this.preReq = preReq; 
    }

    public Hash(HashMap<String, ArrayList<String>> preReq, HashMap<String, Boolean> mark)
    {
        this.preReq = preReq; 
        this.mark = mark; 
    }

    public void inputCourse(String id, HashMap<String, ArrayList<String>> course)
    {
        course.put(id, new ArrayList<String>()); 
    }

    public void inputCourse(String id, HashMap<String, ArrayList<String>> course, HashMap<String, Boolean> mark)
    {
        course.put(id, new ArrayList<String>());
        mark.put(id, false); 
    }

    public void addpQ(String id, String pq, HashMap<String, ArrayList<String>> hash)
    {
        ArrayList<String> arrayList = hash.get(id);
        arrayList.add(pq);
    }

    public String validpq(String id, String preR, HashMap<String, Boolean> mark)
    {
        HashSet<String> please = new HashSet<>();
        dfs(preR, mark, please); 
        if(please.contains(id) || preR.equals(id))
        {
            return "NO";
        }
        else
        {
            return "YES"; 
        }
    }

    public HashSet<String> coursesNeeded(String target,HashMap<String, ArrayList<String>> courseMap, HashSet<String> neededCourses,
           HashSet<String> courseSet){
       for (String s : courseMap.get(target))
        {
           if (!courseSet.contains(s)) 
           {
                neededCourses.add(s);
                coursesNeeded(s, courseMap, neededCourses, courseSet);
           }
       }
       return neededCourses;
   }
   public void allPreReqs(String course, HashSet<String> courseSet, HashMap<String, ArrayList<String>> courseMap)
   {
       courseSet.add(course);
       for(String preReq: courseMap.get(course))
       {
           if(!courseSet.contains(preReq))
           {
               allPreReqs(preReq, courseSet, courseMap);
           }
       }
   }

    public void dfs(String courseID, HashMap<String,Boolean> marked, HashSet<String> x)
    {
       for(String i : preReq.get(courseID))
       {
           if(marked.get(i)!=true)
           {
               x.add(i);
               dfs(i, marked, x);
           }
       }   
       mark.put(courseID,true);    
       
    }

    
  public HashMap<Integer, HashSet<String>> webreg(String course, HashSet<String> courseSet, Hash hashMaps,
   HashMap<String, ArrayList<String>> courseMap, HashSet<String> mustTake,HashSet<String> setOfCourses)
   {
    HashSet<String> needCourse = hashMaps.coursesNeeded(course, courseMap, mustTake, setOfCourses);
    //StdOut.println(needCourse);
    HashMap<Integer, HashSet<String>> schedule = new HashMap<>();
    int count = 1;
    HashSet<String> holdCourses = new HashSet<>();
    while(!needCourse.isEmpty()){
       
        for (String nc : needCourse) {
            HashSet<String> allPreReq = new HashSet<>();
           
            HashSet<String> another = new HashSet<>();
            HashSet<String> anotherOne = new HashSet<>();
           
            hashMaps.allPreReqs(course, another, courseMap);
            HashSet<String> nCPreReq = hashMaps.coursesNeeded(nc, courseMap, anotherOne, setOfCourses);
            allPreReq.addAll(nCPreReq);
            //StdOut.println(nc+" "+nCPreReq);
            if(allPreReq.isEmpty()){
                holdCourses.add(nc);
                //StdOut.println(holdCourses);
            }
            else if(courseMap.get(nc).size()>0){
                for (String s: courseMap.get(nc)) {
                    if(mark.get(s)==true){
                         holdCourses.add(nc);
                        //StdOut.println(holdCourses+" "+run);
                       // isMarked.put(s,true);
                    }
                    else{
                        break;
                    }
                }
            }
           
        }
        if(!holdCourses.isEmpty()){
            HashSet<String> newSet = new HashSet<>();
            for (String s : holdCourses) {
                 mark.put(s, true);
            }
            newSet.addAll(holdCourses);
            //StdOut.print(newSet);
            schedule.put(count, newSet);
            count++;
            needCourse.removeAll(newSet);
            courseSet.addAll(newSet);
            holdCourses.clear();
           
        }
    }
   
    return schedule;
}
   
    
    
    
}
