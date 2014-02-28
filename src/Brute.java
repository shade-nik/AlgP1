public class Brute
{
   // int numPoints;

   Point[] points;
   int numPoints;

   public Brute(int parseInt)
   {
      this.numPoints = parseInt;
      points = new Point[numPoints];
      for (int i = 0; i < parseInt; i++)
      {
         int x = StdRandom.uniform(1, 300);
         int y = StdRandom.uniform(1, 300);
         points[i] = new Point(x, y);
      }
   }

   public Brute(In in)
   {
      this.numPoints = in.readInt();
      if (numPoints < 0)
         throw new IllegalArgumentException("Number of edges must be nonnegative");
      points = new Point[numPoints];
      for (int i = 0; i < numPoints; i++)
      {
         int x = in.readInt();
         int y = in.readInt();
         points[i] = new Point(x, y);
      }
   }

   public void findCollinear()
   {
      for (int i = 0; i < numPoints; i++)
      {

         for (int j = 0; j < numPoints; j++)
         {

            for (int k = 0; k < numPoints; k++)
            {

               for (int l = 0; l < numPoints; l++)
               {

                  if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[k]) == points[i].slopeTo(points[l]))
                  {

                     StdDraw.line(points[i].x, points[i].y, points[j].x, points[j].y);
                     StdDraw.line(points[j].x, points[j].y, points[k].x, points[k].y);
                     StdDraw.line(points[k].x, points[k].y, points[l].x, points[l].y);

                  }
               }
            }
         }
      }

   }

   public static void main(String[] args)
   {

//      In in = new In(args[0]);
//      Brute brute = new Brute(in);

         Brute br = new Brute(400);
         br.findCollinear();
         
         
   }
}
