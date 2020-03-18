import java.util.Random;

public class RandomRolls {

    public void simulate(int rolls)
    {
        Random rand = new Random();
        int count[] = new int[13];
        for(int i=0;i<rolls;i++)
        {
            int d1 = rand.nextInt(6)+1;
            int d2 = rand.nextInt(6)+1;
            //System.out.println(d1+" + "+d2+" = "+(d1+d2));
            count[(d1+d2)] += 1;
        }
        for(int i=2;i<13;i++)
        {
            System.out.println(i+"\t"+count[i]+"\t"+((double)count[i]/rolls)*100);
        }
    }

    public static void main(String args[])
    {
        RandomRolls obj = new RandomRolls();
        obj.simulate(10);
    }

}
