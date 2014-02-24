public interface UnionFindInterface
{
   // find if i and j are connected
   boolean connected(int i, int j);

   // connect i and j
   void union(int i, int j);

   // connect i and j
   int find(int p);

   // number of components
   int count();
}
