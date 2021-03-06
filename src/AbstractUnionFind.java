public abstract class AbstractUnionFind implements UnionFindInterface
{

   protected int n;
   protected int count;
   protected int matrix[];

   public AbstractUnionFind()
   {
   }
   public AbstractUnionFind(int n)
   {
      this.n = n;
      matrix =new int[n];
      for(int i = 0; i < n; i++) {
         matrix[i] = i;
      }
   }

   public AbstractUnionFind(int[] cc, int n)
   {
   }

   @Override
   public abstract boolean connected(int i, int j);

   @Override
   public abstract void union(int i, int j);

   @Override
   public abstract int find(int p);

   @Override
   public abstract int count();
}
