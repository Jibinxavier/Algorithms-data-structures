import java.util.Stack;

/*****************************************************************************
 * CountiesMap
 *
 * @version 1.0
 * @author Jibin Xavier
 * 
 *         The constructor of this class loads a MapImage object. The
 *         constructor then computes the regions with the same colour on the
 *         map. We call these regions <b>counties</b>.
 * 
 *         Once the counties have been computed, their size can be obtained by
 *         calling the method <code>getCountySize</code>.
 *
 *         This implementation is based on the data-structure/algorithm:  Union find
 *         This data structure is the most apt for this assignment .As the it is theoretically the most efficient.
 *         It keeps track of pixels that are of the same colour . 
 *         Each connection is stored in a tree like structure. And each connected pixel holds a reference to its parent node.
 *         The pixels that can be connected are those which can be connected vertically and horizontally 
 *         Union find has two arrays, id array and size array.id array keeps track of the connections. 
 *         The sz stores the area. This is done by counting number of objects in the tree is connected to the root.
 *         This implementation uses weighting and path compression. Since the program is weighting it links root of smaller tree to root of larger tree ,
 *         which creates a balanced tree the running time of would be O (log n) .This is also improves running time when traversing the tree in union. 
 *         It also uses the path compression this is done in find(). 
 *         The find() is used to find the root of the passed in value. Each time it loops to find the root ,it makes very other node in the path to 
 *         point to its grandparent.This makes the tree almost flat and gives a running time of O(lg*N) for union() which uses the find().The find () has a cost of O(lgn).
 *       
 *       
 *         The program uses two for loops going through each pixel in the map which takes O(n) .For each pixel it  checks for one pixel to 
 *         the top ,right ,left and bottom if they are same it unions them. This creates a tree like structure.As it checks in four directions ,4nLgn
 *         But the running time of the constructor of  counties map  is O ( nLgn) .Where n is the number of pixels.
 *         
 *       
 *         But initialisation of union find which has two arrays takes 2n,which is O(n).
 *         The getCountysize method  takes O(lgn).This is because it has to move up to the root.
 *          And it is called M times. Where M is the number of counties and therefore O(mlgn).
 *         
 *          Then the  running time would be  O [ 2n+mlgn+ 4nlgn/m], therefore  time isO(nLgn+mLgn).
 *          
 *          The worst case is where the image has one colour and would need to union all the pixels. 
 *         The worst case running for the constructor will be  O (nLogn)  ,O( n) is for the initialisation  of union find in asymptotic time.
 *         Since the union method which takes  Lgn ,is called n times it will have a cost of O(nLogn) .
 *          Therefore the worst case is  O (nLogn).
 *         The worst case running time of getcountysize() is O(mlgn).As it is called m times. 
 *         
 *         Therefore the worst case running time is O(mlgn + nLgn +n)  , O(nLgn+mlgn) in asymptotic time.
 *         
 *        The memory taken by the methods is O(1).But the memory taken by union find is O(N) because it has two arrays that store
 *        the connections and size.  
 *         
 *****************************************************************************/
public class CountiesMap {
	boolean[] check;
	private final MapImage map;
	WeightedQuickUnion t;
	int count=0;
	/**
	 * The constructor does all the map calculations. The parameter of the class
	 * contains a map of counties of a country. There is no text on the map. It
	 * has only single-colour regions. Some of these single-colour regions
	 * represent counties (you don't know which ones). There might be other
	 * regions on the map such as lakes, oceans, islands etc.
	 *
	 * @param map
	 *            this is a MapImage object
	 */
	public CountiesMap(MapImage map) {
		this.map = map;

		 t = new WeightedQuickUnion(map.getHeight() * map.getWidth());
		int x, y;
		for (y = 0; y < map.getHeight(); y++) {
			for (x = 0; x < map.getWidth(); x++) {
				int c = map.getRGB(x, y);
				if (checkB(x + 1, y ,c) ) {
					int i = getIndex(x, y);
					int m = getIndex(x + 1, y);
					 
						t.union(i, m);
						 count++;
				}
				if (checkB(x - 1, y,c ) ) {
					int i = getIndex(x, y);
					int m = getIndex(x-1, y);
					 
						t.union(i, m);
					   

					 

				}
				if (checkB(x, y + 1,c ) ) {
					int i = getIndex(x, y);
					int m = getIndex(x, y + 1);
					 
						t.union(i, m);
						 

				}
				if (checkB(x, y - 1,c ) ) {
					int i = getIndex(x, y);
					int m = getIndex(x, y - 1);
					 
						t.union(i, m);  

				}
				
			}
		}
	 
	}

	/**
	 * This method returns the size in pixels of the region which includes the
	 * point (x,y).
	 *
	 * @param x
	 *            the x-coordinate of the point in the region.
	 * @param y
	 *            the y-coordinate of the point in the region.
	 * @return the size of the region in pixels.
	 */
	public int getCountySize(int x, int y) {
		int n = getIndex(x, y); 
		return  t.getSize(n);
	}

	/**
	 * This method can be used to convert the map's (x,y) coordinates to a
	 * unique linear index. Suppose we want to store all pixels of the map in a
	 * one-dimentional array. Then the array will have to have size
	 * (map.getHeight() * map.getWidth()). Pixel (x,y) will be at position
	 * getIndex(x,y) in the array.
	 *
	 * @param x
	 *            the x-coordinate of the pixel.
	 * @param y
	 *            the y-coordinate of the pixel.
	 * @return the index in a 1-dimentional array corresponding to pixel (x,y).
	 */
	private int getIndex(int x, int y) {
		return y * map.getWidth() + x;
	}

	private class WeightedQuickUnion {
		int[] sz;
		int[] id;
        int count=0;
		public WeightedQuickUnion(int N) {
			id = new int[N];
			sz = new int[N];
			check = new boolean[N];
			for (int i = 0; i < N; i++) {
				id[i] = i;
				sz[i] = 1;
			}

		}

		public int find(int i)
		{
		 while (i != id[i])
		 {
		 id[i] = id[id[i]];// path compression occurs here
		 i = id[i];
		 }
		 return i;
		}

		public void union(int p, int q) {
			int i = find(p);
			int j = find(q);
			if (i == j)
				return;
			if (sz[i] < sz[j]) {//It always adds small tree to  a larger tree.Which makes the tree balanced
				id[i] = j;
				sz[j] += sz[i];
			} else {
				id[j] = i;
				sz[i] += sz[j];
			}
			count++;
		}

		public int getSize(int i) {
			 
			return sz[id[i]];
		}
	}

	public boolean checkB(int x, int y,int c ) {
		if (y < map.getHeight() && y >= 0 && x >= 0 && x < map.getWidth()&& map.getRGB(x, y) == c) { 
				return true; 
		} else
			return false;
	}
	 
}
