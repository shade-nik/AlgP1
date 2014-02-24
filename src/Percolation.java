public class Percolation
{
   private int n;

   private int matrix[];
   int[] neighbors2D;

   private int countOpen;

   private int top;
   private int bottom;

   private double pTreshold;

   private MySampleUnionFind uf1D;

   /**
    * Percolation constructor. Here we create inital (starting) matrix actually cc[][]... and two virtual node
    * Инициализирую массив для протекания с 2 виртуальными вершинами
    */
   public Percolation(int n)
   {
      this.n = n; // NxN - matrix dimension
      countOpen = 0;
      neighbors2D = new int[4];
      matrix = new int[n];

      uf1D = new MySampleUnionFind(n);
   }

   private double computePercolation()
   {
      while (!percolates())
      {
         testOutMatrix();

         openRandomNode();

      }
      pTreshold = countOpen / (n * n);
      return pTreshold;
   }

   private void openRandomNode()
   {
      int i = StdRandom.uniform(1, n);
      int j = StdRandom.uniform(1, n);
      if (!isOpen(i, j))
      {
         open(i, j);
         countOpen++;
      }

   }

   /* Check if site with [i,j] is connected to open site at the top */
   private boolean isFull(int i, int j)
   {
      // Actually need union find there
      if (uf1D.connected(i, j))
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
      return (getBy2D(i, j) != 0) ? true : false;
   }

   /**
    * open node|site... whatever, if not already open Отрыть - соединить с 2D соседями
    */
   private void open(int i, int j)
   {
      // Вычисляем id для linear UF
      int linearUnionId = i * n + j;

      // open2Dneighbors(linearUnionId);
      calculateNeighbors(linearUnionId, i, j);
      unionWithNeighbors(linearUnionId);
   }

   private static boolean percolates()
   {
      return false;
   }

   public static void main(String... args)
   {
      // создаем подтекатель
      Percolation perco = new Percolation(Integer.parseInt(args[0]));
      // вычисляем коэффициент протекания =)
      double percolationTreshold = perco.computePercolation();
      // Выводим
      System.out.println("Computed treshold: " + percolationTreshold);
   }

   private int getBy2D(int i, int j)
   {
      return matrix[i * n + j];
   }

   private void unionWithNeighbors(int linear)
   {
      // Объединения с соседями хм будем 4 раза проходить весь массив
      // left
      if (neighbors2D[0] != 0)
      {
         uf1D.union(linear, neighbors2D[0]);
      }
      // right
      if (neighbors2D[1] != 0)
      {
         uf1D.union(linear, neighbors2D[1]);
      }
      // top
      if (neighbors2D[2] != 0)
      {
         uf1D.union(linear, neighbors2D[2]);
      }
      else
      {
         uf1D.union(linear, top);
      }
      // bottom
      if (neighbors2D[3] != 0)
      {
         uf1D.union(linear, neighbors2D[3]);
      }
      else
      {
         uf1D.union(linear, bottom);
      }
      // uf1D.unionWithMatrix(linear, neighbors2D); //TODO вытащить метод в этот класс
   }

   private void calculateNeighbors(int linear, int i, int j)
   {
      /**
       * [-] [i-1,j] [-] [i, j-1] [i,j] [i, j+1] [-] [i+1,j] [-] соединяем ноды так если мы получаем меньше нуля сверху
       * - соединяем с виртуальным topId если больше n^2 (n*n) - соединяем с виртуальным bottomId
       */
      /*
       * left neighbor if (j==0) (leftmost node, no node to the left) set to 0
       */
      if (j == 0)
      {
         neighbors2D[0] = 0;
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
         neighbors2D[1] = 0;
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
         neighbors2D[2] = 0; // ?
      }
      else
      {
         neighbors2D[2] = linear - n;
      }
      if (i == n - 1)
      {
         neighbors2D[2] = 0; // ?
      }
      else
      {
         neighbors2D[2] = linear + n;
      }
   }

   private void testOutMatrix()
   {
      System.out.println("Sites opened:" + countOpen);
      for (int i : matrix)
      {
         System.out.print("[" + i + "]");
         if (i % n == 0)
         {
            System.out.println();
         }
      }
   }
}
