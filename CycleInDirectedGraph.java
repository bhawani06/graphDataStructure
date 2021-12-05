package graphs;

import java.util.LinkedList;
import java.util.List;

class Graph1 {
	int v;
	LinkedList<Integer> adj[];
	
	public Graph1(int c)
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
	
	public boolean detectCycle(int s)
	{
        // Mark all the vertices as not visited and
        // not part of recursion stack
        boolean[] visited = new boolean[v];
        boolean[] recStack = new boolean[v];
       if (isCyclicUtil(s, visited, recStack))
                return true;
  
        return false;
	}
	
	 private boolean isCyclicUtil(int i, boolean[] visited,
             boolean[] recStack) 
	 {
	
			// Mark the current node as visited and
			// part of this recursion 
			if (recStack[i])
				return true;
			
			if (visited[i])
				return false;
			
			visited[i] = true;
			recStack[i] = true;
			
			for (Integer c: adj[i])
				if (isCyclicUtil(c, visited, recStack))
				{
					adj[i].removeFirstOccurrence(c);
					return true;
				}
			//when u exit this func all remove it from this call stack
			recStack[i] = false;
			
			return false;
		}
	 }
public class CycleInDirectedGraph 
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int size =4;
        Graph1 g = new Graph1(5);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 0);
        
        
        boolean found = false;
        for(int i=0;i<size;++i)
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
