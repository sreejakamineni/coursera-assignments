import edu.duke.*;

public class PerimeterRunner {
    public int getNumPoints(Shape s){
        int count=0;
        for(Point currpt:s.getPoints()){
              count++;
            }
        return count;
    }
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public double getAverageLength(Shape s) {
        double average=(getPerimeter(s))/(getNumPoints(s));
        return average;
    }
    
    public double getLargestSide(Shape s) {
        double largest = 0;
        Point prevpt = s.getLastPoint();
        for(Point currpt : s.getPoints()){
            double currdist= prevpt.distance(currpt);
            if(currdist>largest) largest=currdist;
            prevpt = currpt;
        }
        return largest;
    }
    
    public double getLargestX(Shape s){
        double largestX = 0;
        for(Point currpt : s.getPoints()){
            double currX = currpt.getX();
            if(currX>largestX) largestX=currX;
        }
        return largestX;
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double average = getAverageLength(s);
        double longestSide = getLargestSide(s);
        double largeX= getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = "+ numPoints);
        System.out.println("Average of sides = "+ average);
        System.out.println("Longest side = "+longestSide);
        System.out.println("Largest x = "+largeX);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
