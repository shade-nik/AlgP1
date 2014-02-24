public class MySampleUnionFind extends AbstractUnionFind
{
   protected int[] matrix;
   protected int top;
   protected int bottom;

   public MySampleUnionFind(int n)
   {
      matrix = new int[(n * n + 2)];

      top = n * n + 1;
      bottom = n * n + 2;

      matrix[n * n + 1] = n * n + 1;
      matrix[n * n + 2] = n * n + 2;
   }

   @Override
   public boolean connected(int i, int j)
   {
      if (matrix[i] != 0 && matrix[i] == matrix[j])
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
      if (matrix[i] > matrix[j])
      {
         maxCCId = matrix[i];
         minCCId = matrix[j];
      }
      else
      {
         maxCCId = matrix[j];
         minCCId = matrix[i];
      }

      // change ids of merging connected components to value of maximum
      for (int k = 0; k < matrix.length; k++)
      {
         if (matrix[k] == minCCId)
         {
            matrix[k] = maxCCId;
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
      else if ((matrix[i] == top && matrix[j] == bottom) || (matrix[i] == bottom && matrix[j] == top))
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
