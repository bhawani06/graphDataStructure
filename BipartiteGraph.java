package graphs;

import java.util.LinkedList;
import java.util.Queue;

/*
We have three color values with us:-
0 => uncolored
1 => our color 1 (lets say blue)
-1 => our color 2 (lets say green)

we check following conditions for every neighbor of a particular node:-

if its color is 0 then color it with whatever it's parent is not colored with i.e (multiply -1))
then we check if the rest can is bipartite or not
else if we found that color of the neighbor is same as color of parent node we return false
Time complexity - O(v + e) 
Space complexity - O(v)

*/

public class BipartiteGraph {

	 public static boolean isBipartite(int[][] graph) {
	        Queue<Integer> q = new LinkedList<>();
	        int col[]= new int[graph.length];
	        for(int v=0;v<graph.length;++v)
	        {
	            if(col[v]==0)
	            {
	                q.add(v);
	                col[v]=1;

	                while(!q.isEmpty() )
	                {
	                    int t = q.remove();
	                    for(int n :graph[t])
	                    {
	                        if(col[n]==0)
	                        {
	                            col[n] = -1*col[t];
	                            q.add(n);
	                        }
	                        else if(col[n]==col[t])
	                        {
	                            return false;
	                        }
	                    }
	                }
	                
	            }

	            
	        }
	        
	        return true;
	} 
	 
	 public static int makeTreeBipartite(int[][] graph) {
        Queue<Integer> q = new LinkedList<>();
        int col[]= new int[graph.length];
        int red=0;
        int blue=0;
        int totEdges= 0;
        for(int i=0;i<graph.length;++i)
        {
        	totEdges+=graph[i].length;
        }
        totEdges=totEdges/2;
        for(int v=0;v<graph.length;++v)
        {
            if(col[v]==0)
            {
                q.add(v);
                col[v]=1;
                ++red;

                while(!q.isEmpty() )
                {
                    int t = q.remove();
                    for(int n :graph[t])
                    {
                        if(col[n]==0)
                        {
                            col[n] = -1*col[t];
                            if(col[n]==1)
                            	++red;
                            else
                            	++blue;
                            q.add(n);
                        }
                    }
                }
                
            }
        }
        return red*blue-totEdges;
}
	
public static void main(String[] args)
{
	int[][] g = new int [][]
			{
			{1,3},{0,2},{1,3},{0,2}
			};
	int[][] g1 = new int [][]
			{
			{1,2,3},{0,2},{0,1,3},{0,2}
			};
	System.out.println(isBipartite(g));
	System.out.println(isBipartite(g1));

	int[][] tree1 = new int [][]
			{
			{1,2},{0,3},{0,4},{1},{2}
			};
	//https://www.geeksforgeeks.org/maximum-number-edges-added-tree-stays-bipartite-graph/		
	System.out.println(makeTreeBipartite(tree1)+"more edges will make this tree a biparted graph");
}
}
