import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Part1 {

    public void totalBirths(FileResource fr)
    {
        int totalBirths=0,numberOfBoys=0,numberOfGirls=0;
        for(CSVRecord record: fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if(record.get(1).equals("F"))
                numberOfGirls++;
            else
                numberOfBoys++;

        }
        System.out.println("Total births: "+totalBirths);
        System.out.println("No. of girls names: "+numberOfGirls);
        System.out.println("No. of boys names: "+numberOfBoys);
        System.out.println("Total Names: "+(numberOfBoys+numberOfGirls));
    }

    public void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender)
    {
        FileResource fr = new FileResource("us_babynames_test/yob"+year+"short.csv");
        int rank = 0;
        for(CSVRecord record:fr.getCSVParser(false))
        {
            if(record.get(0).equals(name) && record.get(1).equals(gender))
                return ++rank;
            if(record.get(1).equals(gender))
                rank++;
        }
        return -1;
    }

    public String getName(int year,int rank,String gender)
    {
        FileResource fr = new FileResource("us_babynames_test/yob"+year+"short.csv");
        int count=1;
        for(CSVRecord record:fr.getCSVParser(false))
        {
            if(count==rank && record.get(1).equals(gender))
                return record.get(0);
            if(record.get(1).equals(gender))
                count++;
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        String word;
        if(gender.equals("F"))
            word = "she";
        else
            word = "he";
        System.out.println(name+" born in "+year+" would be "+getName(newYear,getRank(year,name,gender),gender)+" if "+word+" was born in "+newYear);
    }

    public int yearOfHighestRank(String name,String gender)
    {
        DirectoryResource dir = new DirectoryResource();
        int yearOfHighestRank = -1,highestRank = Integer.MAX_VALUE;
        for(File f:dir.selectedFiles())
        {
            int currentRank = getRank(Integer.parseInt(f.getName().substring(3,7)),name,gender);
            if(highestRank>currentRank)
            {
                highestRank = currentRank;
                yearOfHighestRank = Integer.parseInt(f.getName().substring(3,7));
            }
        }
        return yearOfHighestRank;
    }

    public double getAverageRank(String name,String gender)
    {
        DirectoryResource dir = new DirectoryResource();
        int sumRank = 0,count=0;
        for(File f: dir.selectedFiles())
        {
            int currentRank = getRank(Integer.parseInt(f.getName().substring(3,7)),name,gender);
            sumRank += currentRank;
            count++;
        }
        if(sumRank<0)
            return -1.0;
        return (double)sumRank/count;
    }

    public int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        FileResource fr = new FileResource("us_babynames_test/yob"+year+"short.csv");
        int totalBirths = 0;
        int rank = getRank(year,name,gender);
        for(CSVRecord record:fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                if (rank == 1)
                    break;
                totalBirths += Integer.parseInt(record.get(2));
                rank--;
            }
        }
        return totalBirths;
    }
    public static void main(String args[])
    {
        Part1 obj = new Part1();
        System.out.println(obj.getRank(2013,"Mason","M"));
        System.out.println(obj.getName(2012,1,"M"));
        obj.whatIsNameInYear("Isabella",2012,2014,"F");
        //System.out.println(obj.yearOfHighestRank("Mason","M"));
        //System.out.println(obj.getAverageRank("Jacob","M"));
        System.out.println(obj.getTotalBirthsRankedHigher(2012,"Ethan","M"));
    }
}

