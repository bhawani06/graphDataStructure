package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MinimumTestCaseGen {

	int vertexCount; //total vertex's in input
	HashSet<Integer>[] children; // list of outgoing node for each vertex
	HashSet<Integer>[] parent; //list of incoming nodes for a vertex
	HashSet<Integer> vertexSet; //set of unique vertex in input.
	int completedVertex =0;
	
	MinimumTestCaseGen(int capacity)
	{
		vertexCount=capacity;
		children = new HashSet[vertexCount];
		parent = new HashSet[vertexCount];
		vertexSet = new HashSet<>();
		for(int i =0;i<vertexCount;++i)
		{
			children[i] = new HashSet<Integer>();
			parent[i] = new HashSet<Integer>();
		}	
	}
	

	public void addEdge(int source, int destination)
	{
		vertexSet.add(source);
		vertexSet.add(destination);
		children[source].add(destination);
		parent[destination].add(source);
	}
	
	public List<String> getMinimumPaths()
	{
		int indegree[] = new int[vertexCount];
		//Calculate indegree for all vertex
		for(int i=0;i<vertexCount;++i)
		{
			for(int n:children[i])
			{
				indegree[n]+=1;
			}
		}
		
		//initially all nodes are not complete; 
		boolean[] isComplete = new boolean[vertexCount];
		boolean[] isVisited = new boolean[vertexCount];
		
		List<String> result = new ArrayList<String>();
		
		while(completedVertex<vertexSet.size())
		{
			for(int v=0;v<vertexCount;++v)
			{
				List<Character> pathChars = new ArrayList<Character>();
				//Call DFS for any node with indegree 0,that is still incompete && has outward edges
				if(indegree[v]==0 && isComplete[v]==false && children[v].size()>0)
				{
					 getMinimumPathsUtil(indegree,v,pathChars, isComplete,isVisited);
					 String path="";
					 for(Character c : pathChars)
					 {
						 path+=c.toString();
					 }
					 
					 result.add(path);
				}
			}
		}
		return result;
	}
	
	private boolean getMinimumPathsUtil(int[] indegree, 
			int vertex, 
			List<Character> pathChars, 
			boolean[] isComplete,
			boolean[] isVisited) 
	{
		isVisited[vertex] = true;
		
		//else add current node to result path after converting it back to Char
		pathChars.add((char)(vertex+65));
		
		//if this is the leaf node - mark it as completed. ie it is not in any other path
		if(children[vertex].size() ==0 )  
		{
			isComplete[vertex]=true;
			//update the information parent Node
			updateParent(vertex,isComplete,isVisited);
			++completedVertex;
			return true;
		}
		
		boolean checkChild = false;
		for(int child:children[vertex])
		{
			//for every child of current node, if it is not complete call dfs
			if(isComplete[child] == false) 
			{
				checkChild = true;
				if(getMinimumPathsUtil(indegree, child, pathChars, isComplete,isVisited))
				{	
					return true;
				}
			}
		}
		
		//edge case where there is an unvisited indegree0 node but all its children are visited
		if(checkChild==false)
		{	isComplete[vertex]= true;
			getMinimumPathsUtil(indegree, children[vertex].iterator().next(), pathChars, isComplete,isVisited);
		}
		return true;
	}

	/*
	 * recursively update all parent node ifd and only if
	 * 	-all children node is included in some testcase and
	 *  - parent node is also included in some test case
	 *  eg- graph - a->c, b->c
	 *  when we traverse A to C, we should not mark B as completed.
	 */
	private void updateParent(int vertex, boolean[] isComplete,boolean[] isVisited) {
		// TODO Auto-generated method stub
		if(parent[vertex].size()==0 || isVisited[vertex]==false)
			return;
		
		for(int p:parent[vertex])
		{
			boolean isChildrenComplete = true;
			for(int n1:children[p])
			{
				isChildrenComplete = true;
				if(isComplete[n1]==false)
				{	isChildrenComplete = false;
					break;
				}
			}
			if( isChildrenComplete == true &&isVisited[p])
			{
				//if all children is completed, current node is completed. 
				++completedVertex;
				isComplete[p]=true;
				updateParent(p,isComplete,isVisited);
			}
		}
	}


	public static void main(String[] args) {
		
        /*
         *   - Assume fully connected graph
         *   - Assume all these are directed edges
         *   - Assume there are no cycles in the graph
         *   - Assume there are no two paths from any node 1 to node 2; 
         *   For example, you won't have B -> C -> D and B -> P -> D - since that would create two paths from B to D
         */
        List<String> input = new ArrayList<String>();
        input.add("ABCDKL") ;
        input.add("MBCN");
        input.add("QBCD");
        input.add("KL");
        input.add("DKX");
        input.add("DKZ");
        input.add("YQB");
        
        
        MinimumTestCaseGen minTc = generateEdges(input);
        
		 List<String> result= minTc.getMinimumPaths();
		 System.out.println(result.toString());
	}


	private static MinimumTestCaseGen generateEdges(List<String> input) {
		// TODO Auto-generated method stub

		MinimumTestCaseGen  minTc = new MinimumTestCaseGen(26);
		
		 for(String s: input)
	     {
	        	s=s.toUpperCase(); //ensure all input path is in uppercase to  handle 'a' &'A' as same nodes.
	        	for(int i=0;i<s.length()-1;++i)
	        	{
	        		minTc.addEdge(s.charAt(i)-'A', s.charAt(i+1)-'A'); //for input "ABC" add 2 edges A->B,B->c
	        	}
	     }
		 return minTc;
	}
	

}
