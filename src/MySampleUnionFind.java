public class MySampleUnionFind extends AbstractUnionFind
{
   protected int[] connectionMatrix;
   protected boolean[] openMatrix;
   protected int top;
   protected int bottom;

   public MySampleUnionFind(int n)
   {
      connectionMatrix = new int[n];
      openMatrix = new boolean[n];
   }

   public MySampleUnionFind(int[] matrix, boolean[] openMatrix)
   {
      this.connectionMatrix = matrix;
      this.openMatrix = openMatrix;
      top = matrix[matrix.length - 2];
      bottom = matrix[matrix.length - 1];
   }

   @Override
   public boolean connected(int i, int j)
   {
      if (openMatrix[i] && openMatrix[j] && connectionMatrix[i] == connectionMatrix[j])
      {
         return true;
      }
      return false;
   }

   @Override
   public void union(int i, int j)
   {
      if (isTouched(i, j))
      {
         return;
      }

      int maxCCId;
      int minCCId;

      // choose maximal value as a value for merge
      if (!openMatrix[j])
      {
         connectionMatrix[j] = j;
      }

      if (connectionMatrix[i] > connectionMatrix[j])
      {
         maxCCId = connectionMatrix[i];
         minCCId = connectionMatrix[j];
      }
      else
      {
         maxCCId = connectionMatrix[j];
         minCCId = connectionMatrix[i];
      }
      // change ids of merging connected components to value of maximum
      for (int k = 0; k < connectionMatrix.length; k++)
      {
         if (connectionMatrix[k] == minCCId)
         {
            connectionMatrix[k] = maxCCId;
         }
      }
   }

   private boolean isTouched(int i, int j)
   {
      if (i == j)
      {
         return true;
      }
      else if (connected(i, j))
      {
         return true;
      }
      else if ((connectionMatrix[i] == top && connectionMatrix[j] == bottom) || (connectionMatrix[i] == bottom && connectionMatrix[j] == top))
      {
         return true;
      }
      return false;
   }

   @Override
   public int find(int p)
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int count()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public void unionWithMatrix(int to1d, int[] neighbors2d)
   {
      if (neighbors2d[2] < 0)
      {
         union(top, to1d);
      }
      else
      {
         union(neighbors2d[2], to1d);
         count++;
      }
      if (neighbors2d[3] > n * n)
      {
         union(bottom, to1d);
      }
      else
      {
         union(neighbors2d[3], to1d);
         count++;
      }
      union(neighbors2d[0], to1d);
      union(neighbors2d[1], to1d);
   }

}
