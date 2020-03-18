public class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    CaesarCipherTwo(int key1,int key2)
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0;i<encrypted.length();i++)
        {
            char currentChar = encrypted.charAt(i);
            boolean flag = false;
            if(Character.isLowerCase(currentChar)) {
                flag = true;
                currentChar = Character.toUpperCase(currentChar);
            }

            int idx = alphabet.indexOf(currentChar);

            if(idx!=-1)
            {
                if (i % 2 == 0) {
                    if (flag)
                        encrypted.setCharAt(i, Character.toLowerCase(shiftedAlphabet1.charAt(idx)));
                    else
                        encrypted.setCharAt(i, shiftedAlphabet1.charAt(idx));
                } else {
                    if (flag)
                        encrypted.setCharAt(i, Character.toLowerCase(shiftedAlphabet2.charAt(idx)));
                    else
                        encrypted.setCharAt(i, shiftedAlphabet2.charAt(idx));
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input)
    {
        int key1 = 26 - mainKey1;
        int key2 = 26 - mainKey2;
        System.out.println("Key1 : "+key1+" Key2 : "+key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1,key2);
        return cc.encrypt(input);
    }

}
