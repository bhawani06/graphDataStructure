package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	int v;
	LinkedList<Integer> adj[];
	
	public Graph(int c)
	{
		v = c;
		adj = new LinkedList[v];  //IMP!! 
		for(int i =0 ; i<v; ++i)
		{
			adj[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int s, int d)
	{
		adj[s].add(d);
		
	}
	
	void DFS(int s)
    {
        // Mark all the vertices as 
        // not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[v];
  
        // Call the recursive helper 
        // function to print DFS
        // traversal
        DFSUtil(s, visited);
    }
	
	void DFSUtil(int s, boolean vis[])
	{/*
	Time complexity: O(V + E), where V is the number of vertices and E is the number of edges 
	in the graph.
	Space Complexity: O(V). 
	*/
		vis[s] = true;
		System.out.println(s+ ",");
		
		for (int n : adj[s]) 
		{
			if(vis[n]==false)
			{
				 DFSUtil(n,vis);
			}

		}
	}
	
	void BFS(int s)
	{//Time Complexity: O(V+E) where V is
		//a number of vertices in the graph and E is a number of edges in the graph
		boolean[] vis = new boolean[v];
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(s);
		
		while(!q.isEmpty())
		{
			int t = q.remove();
			vis[t] = true;
			
			System.out.println(t+",");
			for(int neigh : adj[t])
			{
				if(vis[neigh] == false)
				{
					q.add(neigh);
				}
			}
		}
		
	}

	
	boolean isConnected(int s,int d)
	{//Time Complexity: O(V+E) where V is
		//a number of vertices in the graph and E is a number of edges in the graph
		boolean[] vis = new boolean[v];
		
		vis[s] = true;
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(s);
		
		while(!q.isEmpty())
		{
			int t = q.remove();
		//	System.out.println(t+",");
			for(int neigh : adj[t])
			{
				if(neigh==d)
					return true;
				if(vis[neigh] == false)
				{
					vis[neigh] = true;
					q.add(neigh);
				}
			}
		}
		
		return false;
		
	}
	
	int minEdges(int s,int d)
	{//Time Complexity: O(V+E) where V is
		//a number of vertices in the graph and E is a number of edges in the graph
		boolean[] vis = new boolean[v];
		
		vis[s] = true;
		int[] dist = new int[v];
		dist[s]=0;
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(s);
		
		while(!q.isEmpty())
		{
			int t = q.remove();
			System.out.println(t+",");
			for(int neigh : adj[t])
			{
				if(vis[neigh] == false)
				{
					dist[neigh] =dist[t]+1;
					vis[neigh] = true;
					q.add(neigh);
				}
			}
		}
		
		return dist[d];
		
	}
	

    // The function to do DFS traversal. It uses recursive
    // DFSUtil()
    void DFS()
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[v];
  
        // Call the recursive helper function to print DFS
        // traversal starting from all vertices one by one
        for (int i = 0; i < v; ++i)
            if (visited[i] == false)
                DFSUtil(i, visited);
    }
	
	public static void main(String[] args) {

        Graph g = new Graph(5);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 3);
 
        System.out.println("Following is Breadth First Traversal "+
                          "(starting from vertex 2)");
 
        g.BFS(2);
        
        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex 2)");
        g.DFS(2);

        System.out.println("Next: " + g.isConnected(2,4)); // is 2 connected to 4

        System.out.println("distnace"+ g.minEdges(0,4)); // is 0 edges btw 3&4
        
        //Disconnected graph
        Graph g1 = new Graph(5);

        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(3, 3);
        g1.addEdge(4, 4);

        
        System.out.println("Disconnected graph DFS");
        g1.DFS();
        
	}
}

