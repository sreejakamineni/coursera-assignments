import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;

public class countShakespeare {

    public String[] getCommon()
    {
        FileResource fr = new FileResource("data/common.txt");
        String common[] = new String[20];
        int index = 0;
        for(String s: fr.words())
        {
            common[index] = s;
            index++;
        }
        return common;
    }

    public void countWords(FileResource fr,String[] common,int[] counts)
    {
        for(String s: fr.words())
        {
            s = s.toLowerCase();
            int index = indexOf(common,s);
            if(index!=-1)
                counts[index] += 1;
        }
    }

    public int indexOf(String[] list,String word)
    {
        for(int i=0;i<list.length;i++)
        {
            if(list[i].equals(word))
                return i;
        }
        return -1;
    }

    public void countShakespeare()
    {
        DirectoryResource dir = new DirectoryResource();
        String common[] = getCommon();
        int counts[] = new int[common.length];
        for(File f : dir.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            countWords(fr,common,counts);
        }
        for(int i=0;i<common.length;i++)
            System.out.println(common[i] + "\t" + counts[i]);
    }

    public static void main(String args[])
    {
        countShakespeare cs = new countShakespeare();
        cs.countShakespeare();
    }
}
