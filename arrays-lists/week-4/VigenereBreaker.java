import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        HashMap<Character,Integer> charCounts = new HashMap<>();
        for(String s : dictionary)
        {
            for(char c : s.toLowerCase().toCharArray())
            {
                if(!charCounts.containsKey(c))
                    charCounts.put(c,1);
                else
                    charCounts.put(c,charCounts.get(c)+1);
            }
        }
        int max = 0;
        char mostCommonChar = '0';
        for(char c : charCounts.keySet())
        {
            if(charCounts.get(c) > max)
            {
                max = charCounts.get(c);
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }

    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages)
    {
        String ans="",lang="";
        int count = 0;
        for(String language : languages.keySet())
        {
            HashSet<String> dictionary = languages.get(language);
            String currDecrypted = breakForLanguage(encrypted,dictionary);
            int currCount = countWords(currDecrypted,dictionary);
            if(currCount > count)
            {
                count = currCount;
                ans = currDecrypted;
                lang = language;
            }
        }

        System.out.println("Decrypted Message :\n"+ans);
        System.out.println("\nLanguage used : "+lang);
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> words = new HashSet<>();
        for(String line : fr.lines())
            words.add(line.toLowerCase());
        return words;
    }

    public int countWords(String message, HashSet<String> dictionary)
    {
        String[] words = message.split("\\W+");
        int count = 0;
        for(String word : words)
            if(dictionary.contains(word.toLowerCase()))
                count++;
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        int key[];
        VigenereCipher vc;
        char mostCommon = mostCommonCharIn(dictionary);
        String ans = "";
        int max = 0;
        for(int  i=1;i<=100;i++)
        {
            key = tryKeyLength(encrypted,i,mostCommon);
            vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted,dictionary);
            if(max < count)
            {
                max = count;
                ans = decrypted;
            }
        }
        return ans;
    }
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder st = new StringBuilder();
        for(int i = whichSlice;i<message.length();i+=totalSlices)
        {
            st.append(message.charAt(i));
        }
        return st.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++)
            key[i] = cc.getKey(sliceString(encrypted,i,klength));
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String data = fr.asString();
        DirectoryResource dir = new DirectoryResource();
        HashMap<String,HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for(File f : dir.selectedFiles())
        {
            System.out.println("Reading words in "+f.getName());
            languages.put(f.getName(),readDictionary(new FileResource(f)));
        }
        breakForAllLangs(data,languages);
    }
    public static void main(String args[])
    {
      VigenereBreaker vb = new VigenereBreaker();
      vb.breakVigenere();
    }
}
