public class WordPlay {
    public boolean isVowel(char ch)
    {
        ch = Character.toLowerCase(ch);
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
            return true;
        return false;
    }

    public String replaceVowels(String phrase,char ch)
    {
        StringBuilder newPhrase = new StringBuilder();
        for(int i=0;i<phrase.length();i++) {
            if (isVowel(phrase.charAt(i)))
                newPhrase.append(ch);
            else
                newPhrase.append(phrase.charAt(i));
        }
        return newPhrase.toString();
    }

    public String emphasize(String phrase,char ch)
    {
        char upCh = Character.toUpperCase(ch);
        StringBuilder newPhrase = new StringBuilder();
        for(int i=0;i<phrase.length();i++)
        {
            if(phrase.charAt(i)==ch || phrase.charAt(i)==upCh)
                if(i%2!=0)
                    newPhrase.append('+');
                else
                    newPhrase.append('*');
            else
                newPhrase.append(phrase.charAt(i));
        }
        return newPhrase.toString();
    }
    public static void main(String args[])
    {
        WordPlay obj = new WordPlay();
        System.out.println(obj.isVowel('F'));
        System.out.println(obj.replaceVowels("HelloWorld",'*'));
        System.out.println(obj.emphasize("dna ctgaaactga",'a'));
        System.out.println(obj.emphasize("Mary Bella Abracadabra",'a'));
    }
}
