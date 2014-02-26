
public class QuickUF extends AbstractUnionFind
{
   
   public QuickUF(int n)
   {
      super(n);
   }
   
   @Override
   public boolean connected(int i, int j)
   {
      return matrix[i] == matrix[j];
      
   }

   //Немного другая реализация чтобы при слиянии присваивался больший 
   // id хм.... хотя нафига
   @Override
   public void union(int i, int j)
   {
//      int max;
//      int min;
//
//      if(matrix[i] > matrix[j]) {
//         max = matrix[i];
//         min = matrix[j];
//      } else {
//         max = matrix[j];
//         min = matrix[i];
//      }
    int max = matrix[i];
    int min = matrix[j];
      
      for (int q = 0; q < matrix.length; q ++) {
         if(matrix[q] == min) {
            matrix[q] = max;
         }
      }
      
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

}
