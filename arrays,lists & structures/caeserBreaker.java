public class CaesarBreaker {
    public int[] countLetters(String input)
    {
        //                 01234567890123456789012345
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int count[] = new int[26];
        for(int i=0;i<input.length();i++)
        {
            int index = alphabet.indexOf(Character.toLowerCase(input.charAt(i)));
            if(index!=-1)
                count[index] += 1;
        }
        return count;
    }

    public int maxIndex(int[] count)
    {
        int index = 0;
        for(int i=0;i<count.length;i++)
            if(count[index] < count[i])
                index = i;
        return index;
    }

    public String decrypt(String encrypted)
    {
        CaesarCipher cc = new CaesarCipher();
        int dkey = getKey(encrypted);
        return  cc.encrypt(encrypted,26-dkey);
    }

    public void testDecrypt()
    {
        System.out.println(decrypt("Bmkl s lwkl kljafy oalz dglk gx wwwwwwwwwwwwwwwwwk"));
    }

    public String halfOfString(String message, int start)
    {
        StringBuilder halfString = new StringBuilder();
        for(int i=start;i<message.length();i+=2)
            halfString.append(message.charAt(i));
        return halfString.toString();
    }

    public int getKey(String s)
    {
        int count[] = countLetters(s);
        int maxDex = maxIndex(count);
        int dkey = maxDex - 4;
        if(maxDex < 4)
            dkey = 26 - (4 - maxDex);
        return 26-dkey;
    }

    public String decryptTwoKeys(String encrypted)
    {
        String s1 = halfOfString(encrypted,0);
        String s2 = halfOfString(encrypted,1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("Key1 : "+key1+" Key2 : "+key2);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted,key1,key2);
    }
    public static void main(String args[])
    {
        CaesarBreaker csb = new CaesarBreaker();
        //csb.testDecrypt();
        //System.out.println(csb.halfOfString("Qbkm Zgis", 1));
        //System.out.println(csb.decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu\n"));
        //System.out.println(csb.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        //System.out.println(csb.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));

    }


}
