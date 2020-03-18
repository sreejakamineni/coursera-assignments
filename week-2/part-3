public class Part3 {

    public int findStopCodon(String dna,int startIndex,String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);
        while (currIndex != -1)
        {
            if ((currIndex - startIndex) % 3 == 0)
                return currIndex+3;
            currIndex = dna.indexOf(stopCodon,currIndex+1);
        }
        return dna.length();
    }

    public String findGene(String dna,int where)
    {
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex==-1)
            return "";
        int taaIndex = findStopCodon(dna,startIndex+3,"TAA");
        int tagIndex = findStopCodon(dna,startIndex+3,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex+3,"TGA");
        int stopIndex = Math.min(taaIndex,Math.min(tgaIndex,tagIndex));
        if(stopIndex==dna.length())
            return "";
        return dna.substring(startIndex,stopIndex);
    }
    public int countGenes(String dna)
    {
        int startIndex = 0,count=0;
        while(true)
        {
            String currGene = findGene(dna,startIndex);
            if(currGene.isEmpty())
                break;
            count++;
            startIndex = dna.indexOf(currGene,startIndex)+currGene.length();
        }
        return count;
    }
    public void testCountGenes()
    {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }
    public static void main(String args[])
    {
        Part3 obj = new Part3();
        obj.testCountGenes();
    }
}
