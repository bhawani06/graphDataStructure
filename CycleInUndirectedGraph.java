package graphs;

import java.util.LinkedList;

public class CycleInUndirectedGraph {

	int v;
	LinkedList<Integer> adj[];
	
	public CycleInUndirectedGraph(int c)
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
		adj[d].add(s);
		
	}

	public boolean detectCycle(int s)
	{
		boolean[] vis = new boolean[v];
		var res = dfsUtil(s,vis,-1);
		return res;
	}
	
	public boolean dfsUtil(int s , boolean[] vis,int parent)
	{
		vis[s]=true;
		for(int n : adj[s])
		{
			if(n==parent)
				continue;
			
			if(vis[n]== true)
				return true;
			else
			{
				if(dfsUtil(n, vis,s))
					return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int size =4;
        Graph1 g = new Graph1(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
      //  g.addEdge(1, 2);
      //  g.addEdge(2, 0);
        g.addEdge(2, 3);
      //  g.addEdge(3, 3);
        
        
        boolean found = false;
        for(int i=1;i<size;++i)
        {
        	if(g.detectCycle(i))
        	{	found = true;
        		System.out.println("Cycle Detected from"+i); 
        		break;
        	}
        }
        
        if(!found)
        	System.out.println("No Cycle");

	}

}
