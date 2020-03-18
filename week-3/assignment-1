import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part1 {

    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser,"Germany"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"gold","diamonds");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"gold"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$400,000,000");
    }
    public String countryInfo(CSVParser parser,String countryOfInterest)
    {
        for(CSVRecord record:parser)
        {
            String country = record.get("Country");
            if(country.contains(countryOfInterest))
                return countryOfInterest+": "+record.get("Exports")+": "+record.get("Value (dollars)");
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser,String exportitem1,String exportitem2)
    {
        for(CSVRecord record:parser)
        {
            String item = record.get("Exports");
            if(item.contains(exportitem1) && item.contains(exportitem2))
                System.out.println(record.get("Country"));

        }
    }
    public int numberOfExporters(CSVParser parser,String exportitem)
    {
        int count=0;
        for(CSVRecord record:parser)
        {
            String item = record.get("Exports");
            if(item.contains(exportitem))
                count++;
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount)
    {
        for(CSVRecord record:parser)
        {
            String value = record.get("Value (dollars)");
            if(value.length()>amount.length())
                System.out.println(record.get("Country")+" "+value);
        }
    }
    public static void main(String args[])
    {
        Part1 obj = new Part1();
        obj.tester();
    }
}
