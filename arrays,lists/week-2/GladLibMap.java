import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private ArrayList<String> trackingList;
    private ArrayList<String> trackingCategoryList;
    private HashMap<String,ArrayList<String>> mapOfLists;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        mapOfLists = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source){
        mapOfLists = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String words[] = {"adjective","noun","color","country",
                           "name","animal","timeframe","verb",
                            "fruit"};
        for(String s : words)
            mapOfLists.put(s , readIt(source + "/" + s + ".txt"));
        trackingList = new ArrayList<>();
        trackingCategoryList = new ArrayList<>();
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {

        if (label.equals("number"))
            return ""+myRandom.nextInt(50)+5;

        if(mapOfLists.containsKey(label)) {
            if(!trackingCategoryList.contains(label))
                    trackingCategoryList.add(label);
            return randomFrom(mapOfLists.get(label));
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(!trackingList.isEmpty() && trackingList.contains(sub))
            sub = getSubstitute(w.substring(first+1,last));
        trackingList.add(sub);
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public int totalWordsInMap()
    {
        int ans = 0;
        for(ArrayList<String> words : mapOfLists.values())
            ans += words.size();
        return ans;
    }

    public int totalWordsConsidered()
    {
        int ans = 0;
        for(int i=0;i<trackingCategoryList.size();i++)
        {
            String s = trackingCategoryList.get(i);
            //System.out.println(s);
            if(mapOfLists.containsKey(s))
                ans += mapOfLists.get(s).size();
        }
        return ans;
    }
    public void makeStory(){
        trackingList.clear();
        trackingCategoryList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nNo. of words that were replaced is "+trackingList.size());
    }


    public static void main(String args[])
    {
        GladLibMap glm = new GladLibMap();
        glm.makeStory();
        System.out.println("Total words to pick out from = "+glm.totalWordsInMap());
        System.out.println("Total words considered = "+glm.totalWordsConsidered());
    }

}
