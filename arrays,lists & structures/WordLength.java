import edu.duke.FileResource;

public class WordLength {
    public void countWordLengths(FileResource resource,int[] counts)
    {
        for(String word : resource.words())
        {
            if(word.length()>=counts.length)
                counts[counts.length-1] += 1;
            if(Character.isLetter(word.charAt(0)) && !Character.isLetter(word.charAt(word.length()-1)))
                counts[word.length()-1] += 1;
            else
                counts[word.length()] += 1;
        }
    }

    public void testCountWordLengths()
    {
        FileResource resource = new FileResource();
        int counts[] = new int[31];
        countWordLengths(resource,counts);
        System.out.println("Length\tCount");
        for(int i=0;i<counts.length;i++)
        {
            System.out.println("  "+i+"\t\t"+counts[i]);
        }

        System.out.println("Most common word length in file = "+indexOfMax(counts));
    }

    public int indexOfMax(int[] values)
    {
        int idx = 0;
        for(int i=0;i<values.length;i++)
        {
            if(values[idx]<values[i])
                idx = i;
        }
        return idx;
    }

    public static void main(String args[])
    {
        WordLength obj = new WordLength();
        obj.testCountWordLengths();
    }
}
