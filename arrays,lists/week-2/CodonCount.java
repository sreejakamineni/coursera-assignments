import edu.duke.FileResource;

import java.io.File;
import java.util.HashMap;

public class CodonCount {

    private HashMap<String,Integer> dnaCount;
    CodonCount()
    {
        dnaCount = new HashMap<>();
    }

    public void buildCodonMap(int start,String dna)
    {
        dnaCount.clear();
        for(int i=start;i<dna.length()-2;i+=3) {
            String subDna = dna.substring(i,i+3);
            if (dnaCount.containsKey(subDna))
                dnaCount.put(subDna,dnaCount.get(subDna)+1);
            else
                dnaCount.put(subDna,1);
        }
    }

    public String getMostCommonCodon()
    {
        int count = -1;
        String ans = "";
        for(String s : dnaCount.keySet())
        {
            if(dnaCount.get(s)>count) {
                count = dnaCount.get(s);
                ans = s;
            }
        }
        return ans;
    }

    public void printCodonCounts(int start,int end)
    {
        for(String s : dnaCount.keySet())
            if(dnaCount.get(s)>=start && dnaCount.get(s)<=end)
                System.out.println(s + " " + dnaCount.get(s));
    }

    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        for(int i=0;i<3;i++)
        {
            buildCodonMap(i,dna);
            System.out.println("No. of unique codons in the reading frame : "+dnaCount.size());
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("Most common codon and its count is : "+mostCommonCodon+" "+dnaCount.get(mostCommonCodon));
            printCodonCounts(1,5);
        }
    }

    public static void main(String args[])
    {
        CodonCount obj = new CodonCount();
        obj.tester();
    }
}
