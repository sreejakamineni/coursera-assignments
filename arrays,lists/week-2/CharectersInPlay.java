
import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> nameCounts;

    CharactersInPlay()
    {
        names = new ArrayList<>();
        nameCounts = new ArrayList<>();
    }

    public void update(String person)
    {
        if(names.contains(person))
        {
            int index = names.indexOf(person);
            int value = nameCounts.get(index);
            nameCounts.set(index,value+1);
        }
        else
        {
            names.add(person);
            nameCounts.add(1);
        }
    }

    public void findAllCharacters()
    {
        FileResource fr = new FileResource();
        for(String line : fr.lines())
        {
            int index = line.indexOf('.');
            if(index!=-1) {
                String person = line.substring(0, index).trim();
                update(person);
            }
        }
    }

    public void tester()
    {
        findAllCharacters();
        int speakingParts = 3;
        for(int i=0;i<names.size();i++)
            if(nameCounts.get(i)==speakingParts)
                System.out.println(names.get(i) + " " + speakingParts);

        charactersWithNumParts(10,15);
        characterWithMostSpeakingParts();
    }

    public void charactersWithNumParts(int num1,int num2)
    {
        System.out.println("Characters with speaking parts between ["+num1+","+num2+"] : ");
        for(int i=0;i<names.size();i++)
        {
            if(nameCounts.get(i)>=num1 && nameCounts.get(i)<=num2)
                System.out.println(names.get(i));
        }
    }

    public void characterWithMostSpeakingParts()
    {
        int idx = 0;
        for(int i=0;i<names.size();i++)
            if(nameCounts.get(i) > nameCounts.get(idx))
                idx = i;
        System.out.println(names.get(idx)+" "+ nameCounts.get(idx));
    }
    public static void main(String args[])
    {
        CharactersInPlay obj = new CharactersInPlay();
        obj.tester();
    }

}
