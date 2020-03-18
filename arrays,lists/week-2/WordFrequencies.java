import edu.duke.FileResource;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    WordFrequencies()
    {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void findUnique()
    {
        myFreqs.clear();
        myWords.clear();

        FileResource fr = new FileResource();
        for(String word : fr.words())
        {
            word = word.toLowerCase();
            if(myWords.contains(word))
            {
                int index = myWords.indexOf(word);
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
            else
            {
                myWords.add(word);
                myFreqs.add(1);
            }
        }
    }

    public void tester()
    {
        findUnique();
        System.out.println("No. of unique words : "+myWords.size());
        for(int i=0;i<myWords.size();i++)
            System.out.println(myFreqs.get(i)+" "+myWords.get(i));
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are : "+myWords.get(maxIndex)+" "+myFreqs.get(maxIndex));
    }

    public int findIndexOfMax()
    {
        int x = 0;
        for(int i=0;i<myFreqs.size();i++)
            if(myFreqs.get(i) > myFreqs.get(x))
                x = i;
        return x;
    }

    public static void main(String args[])
    {
        WordFrequencies obj = new WordFrequencies();
        obj.tester();
    }
}
