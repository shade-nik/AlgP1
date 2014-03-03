public class CopyOfPercolation
{
   private boolean openMatrix[];
   private boolean perc;
   
   private int n;
   private int top;
   private int bottom;

   int[] neighbors2D;

   private int countOpen;

   private double pTreshold;

   private AbstractUnionFind uf1D;

   public CopyOfPercolation(int n)
   {
      // NxN - matrix dimension
      this.n = n;
      top = n * n;
      bottom = top + 1;

      // open node count
      countOpen = 0;
      // optional neighbors array for 2d matrix
      neighbors2D = new int[4];
      // nodes state(open/closed) storage =)
      openMatrix = new boolean[top + 2];

      openMatrix[top] = true;
      openMatrix[bottom] = true;
      perc = false;
      
      uf1D = new QuickUF(top + 2);
   }

   private double computePercolation()
   {
      while (!percolates())
      {

         openRandomNode();
//         testOutOpenMatrix();

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
         if(isFull(i, j)) {
            perc = true;
         }
      }

   }

   /* Check if site with [i,j] is connected to open site at the top */
   private boolean isFull(int i, int j)
   {
      // Actually need union find there
      if (uf1D.connected(convert2DtoLinear(i, j), top))
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   /**
    * Check if site with [i,j] is opened Реализация - 0 это закрытая нода, если туда наступили - значение не 0
    */
   private boolean isOpen(int i, int j)
   {
      return openMatrix[convert2DtoLinear(i, j)];
   }

   /**
    * open node|site... whatever, if not already open Отрыть - соединить с 2D соседями
    */
   private void open(int i, int j)
   {
      // Вычисляем id для linear UF
      int linearUnionId = convert2DtoLinear(i, j);
      openMatrix[linearUnionId] = true;
      // open2Dneighbors(linearUnionId);
      calculateNeighbors(linearUnionId, i, j);
      unionWithNeighbors(linearUnionId, i, j);
   }

   private boolean percolates()
   {
      // Определим что такое есть протекать, получается что это когда
      // виртуальный топ и виртуальный bottom в одном компоненте
      for (int i = 1; i < n; i++)
      {
         if (openMatrix[top-i] && uf1D.connected(top - i, top))
         {
            return true;
         }
      }
      // if (uf1D.connected(top, bottom))
      // {
      // return true;
      // }
      return false;
   }

   public static void main(String... args)
   {
      // создаем подтекатель
      CopyOfPercolation perco = new CopyOfPercolation(Integer.parseInt(args[0]));
      // вычисляем коэффициент протекания =)
      double percolationTreshold = perco.computePercolation();
      // Выводим
      System.out.println("Computed treshold: " + percolationTreshold);
   }

   private int convert2DtoLinear(int i, int j)
   {
      return i * n + j;
   }

   private void unionWithNeighbors(int linear, int i, int j)
   {
      // Объединения с соседями хм будем 4 раза проходить весь массив
      // left
      if (openMatrix[neighbors2D[0]])
      {
         uf1D.union(linear, neighbors2D[0]);
      }
      // right
      if (openMatrix[neighbors2D[1]])
      {
         uf1D.union(linear, neighbors2D[1]);
      }
      // top
      if (openMatrix[neighbors2D[2]])
      {
         uf1D.union(linear, neighbors2D[2]);
      }
      // bottom
      if (openMatrix[neighbors2D[3]])
      {

         uf1D.union(linear, neighbors2D[3]);
      }
   }

   private void calculateNeighbors(int linear, int i, int j)
   {
      /**
       * соединяем ноды так если мы получаем меньше нуля сверху - соединяем с виртуальным topId если больше n^2 (n*n)
       * снизу - соединяем с виртуальным bottomId
       */

      if (j == 0)
      {
         neighbors2D[0] = linear;
      }
      else
      {
         neighbors2D[0] = linear - 1;
      }
      /*
       * right neighbor if (j==n) doesn't exist (no node to the right) than set to 0
       */
      if (j == n - 1)
      {
         neighbors2D[1] = linear;
      }
      else
      {
         neighbors2D[1] = linear + 1;
      }
      /*
       * i == 0) set to 0
       */
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

   private void testOutOpenMatrix()
   {
      System.out.println("Sites opened:" + countOpen);
      for (int i = 1; i < n * n + 1; i++)
      {
         System.out.print("[" + ((openMatrix[i - 1]) ? "*" : " ") + "]");

         if (i % n == 0)
         {
            System.out.println();
         }
      }
   }

}
