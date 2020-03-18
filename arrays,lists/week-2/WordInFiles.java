import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsInFiles;

    WordsInFiles()
    {
        wordsInFiles = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        String file = f.getName();
        for(String s : fr.words())
        {
            if(wordsInFiles.containsKey(s))
            {
                ArrayList<String> temp = wordsInFiles.get(s);
                if(!temp.contains(file)) {
                    temp.add(file);
                    wordsInFiles.put(s, temp);
                }
            }
            else
            {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(file);
                wordsInFiles.put(s,temp);
            }
        }
    }

    public void buildWordFileMap()
    {
        wordsInFiles.clear();
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }

    private int maxNumber()
    {
        int max = 0;
        for(ArrayList<String> x : wordsInFiles.values())
        {
            if(max < x.size())
                max = x.size();
        }
        return max;
    }

    private ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> temp = new ArrayList<>();
        for(String word : wordsInFiles.keySet())
        {
            if(number == wordsInFiles.get(word).size())
                temp.add(word);
        }
        return temp;
    }

    private void printFilesIn(String word)
    {
        ArrayList<String> temp = wordsInFiles.get(word);
        for(String s : temp)
            System.out.print(s+" ");
        System.out.println("\b");
    }

    public void tester()
    {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Maximum number of files any word is in : "+max);
        ArrayList<String> temp = wordsInNumFiles(max);
        System.out.println("The words that are in max number of files are : ");
        for(String s : temp)
        {
            System.out.print(s+"  ");
            printFilesIn(s);
        }
    }

    public static void main(String args[])
    {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }
}
