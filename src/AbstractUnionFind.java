public abstract class AbstractUnionFind implements UnionFindInterface
{

   protected int n;
   protected int count;

   public AbstractUnionFind()
   {
      // TODO Auto-generated constructor stub
   }
   public AbstractUnionFind(int n)
   {
      this.n = n;
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
