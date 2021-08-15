import java.io.*;
import java.util.*;
//Longest Path
public class lonngestpathtopo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //Read in number of vertices
		int M = sc.nextInt(); //Read in number of edges
		ArrayList<Integer> graph[] = new ArrayList[N+1]; //Adjacency list
		int indgr[] = new int[N+1]; //Indegree (for our topological sort)
		for(int i = 1; i<=N; i++){ 
			graph[i] = new ArrayList<>(); //Initializing the graph 
		}
		for(int i = 1; i<=M; i++){
			int a = sc.nextInt(), b = sc.nextInt(); //Adding in the edge from (a, b)
			graph[a].add(b); //In the adjacency list
			indgr[b]++; //That means indegree of b is increased by 1
		}
		//First step is to perform a topological order
		LinkedList<Integer> ll = new LinkedList<>(); //Queue
		for(int i= 1; i<=N; i++){
			if(indgr[i] == 0){ //If the indegree is intially 0
				ll.add(i); //Add it to the queue
			}
		}
		ArrayList<Integer> toposort = new ArrayList<>(); //Stores the topological order
		while(!ll.isEmpty()){ //While there are still nodes
			int n = ll.poll(); //Get the next one from the front of the queue
			toposort.add(n); //Add it to end of the topological sort
			for(int e : graph[n]){ //Loop through outgoing edges (n, e) from n
				indgr[e]--; //Remove the edge which means indegree of e we decrease by 1
				if(indgr[e] == 0){ //If there are no incoming edges
					ll.add(e); //Add to the queue
				}
			}
		}
		//Second step do the dp
		int dp[] = new int[N+1]; //dp[n] = the longest path ending at 'n' so far
		for(int n : toposort){ //Go through the nodes in topological order
			for(int e : graph[n]){ //Loop through all outgoing edges from e
				dp[e] = Math.max(dp[e], dp[n] + 1); //Update if going from 'n' to 'e' is better than its current path 
			}
		}
		int ans = 0; //Storing answer
		for(int i = 1; i<=N; i++){
			ans = Math.max(ans, dp[i]); //Take the maximum longest path from each node i 
		}
		System.out.println(ans);//Print out longest path
	}
}
