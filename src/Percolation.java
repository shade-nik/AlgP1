public class Percolation
{
   private boolean openMatrix[];
   
   private int n;
   private int top;
   private int bottom;

   int[] neighbors2D;

   private int countOpen;

   private double pTreshold;

   private AbstractUnionFind uf1D;

   public Percolation(int n)
   {
      this.n = n;
      top = n * n;
      bottom = top + 1;

      countOpen = 0;
      neighbors2D = new int[4];
      openMatrix = new boolean[top + 2];

      openMatrix[top] = true;
      openMatrix[bottom] = true;
      
      uf1D = new QuickUF(top + 2);
   }

   private double computePercolation()
   {
      while (!percolates())
      {
         openRandomNode();
      }
      pTreshold = (double) countOpen / (n * n);
      return pTreshold;
   }

   private void openRandomNode()
   {
      int i = StdRandom.uniform(0, n);
      int j = StdRandom.uniform(0, n);
      if (!isOpen(i, j))
      {
         open(i, j);
         countOpen++;
      }

   }

   private boolean isFull(int i, int j)
   {
      if (uf1D.connected(convert2DtoLinear(i, j), top))
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   private boolean isOpen(int i, int j)
   {
      return openMatrix[convert2DtoLinear(i, j)];
   }

   private void open(int i, int j)
   {
      int linearUnionId = convert2DtoLinear(i, j);
      openMatrix[linearUnionId] = true;
      calculateNeighbors(linearUnionId, i, j);
      unionWithNeighbors(linearUnionId, i, j);
   }

   private boolean percolates()
   {
      for (int i = 1; i < n; i++)
      {
         if (openMatrix[top-i] && isFull(n-1, i))
         {
            return true;
         }
      }
      return false;
   }

   public static void main(String... args)
   {
      Percolation perco = new Percolation(Integer.parseInt(args[0]));
      double percolationTreshold = perco.computePercolation();
      System.out.println("Computed treshold: " + percolationTreshold);
   }

   private int convert2DtoLinear(int i, int j)
   {
      return i * n + j;
   }

   private void unionWithNeighbors(int linear, int i, int j)
   {
      if (openMatrix[neighbors2D[0]])
      {
         uf1D.union(linear, neighbors2D[0]);
      }
      if (openMatrix[neighbors2D[1]])
      {
         uf1D.union(linear, neighbors2D[1]);
      }
      if (openMatrix[neighbors2D[2]])
      {
         uf1D.union(linear, neighbors2D[2]);
      }
      if (openMatrix[neighbors2D[3]])
      {
         uf1D.union(linear, neighbors2D[3]);
      }
   }

   private void calculateNeighbors(int linear, int i, int j)
   {
      if (j == 0)
      {
         neighbors2D[0] = linear;
      }
      else
      {
         neighbors2D[0] = linear - 1;
      }
      if (j == n - 1)
      {
         neighbors2D[1] = linear;
      }
      else
      {
         neighbors2D[1] = linear + 1;
      }
      if (i == 0)
      {
         neighbors2D[2] = top; // ?
      }
      else
      {
         neighbors2D[2] = linear - n;
      }

      if (i == n - 1)
      {
         neighbors2D[3] = bottom; // ?
      }
      else
      {
         neighbors2D[3] = linear + n;
      }
   }
}
