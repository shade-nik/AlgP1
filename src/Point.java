import java.util.Comparator;

public class Point implements Comparable<Point>
{
   // compare points by slope

   // public final Comparator<Point> SLOPE_ORDER;
   protected final int x; // x coordinate
   protected final int y; // y coordinate

   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   // plot this point to standard drawing

   public void draw()
   {
      StdDraw.point(x, y);

   }

   // draw line between this point and that point to standard drawing

   public void drawTo(Point that)
   {
      StdDraw.line(this.x, this.y, that.x, that.y);

   }

   // slope between this point and that point

   public double slopeTo(Point that)
   { /* YOUR CODE HERE */
      if ((that.x - this.x) == 0) {
         return 1;
      }
      return (that.y - this.y)/(that.x - this.x);
   }

   // is this point lexicographically smaller than that one?
   // comparing y-coordinates and breaking ties by x-coordinates
   @Override
   public int compareTo(Point o)
   { /* YOUR CODE HERE */

      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public String toString()
   {
      return "(" + x + ", " + y + ")";

   }

   public static void main(String[] args)
   {

   }

}
