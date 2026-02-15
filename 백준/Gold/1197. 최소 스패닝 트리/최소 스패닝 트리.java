import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeSt implements Comparable<NodeSt> {
	int idx;
	int cost;
	NodeSt(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	@Override
	public int compareTo(NodeSt o) {
		return Integer.compare(this.cost, o.cost);
	}
	
}

public class Main {
	static int V, E;
	static List<List<NodeSt>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			graph.get(start).add(new NodeSt(to, cost));
			graph.get(to).add(new NodeSt(start, cost));
		}
		
		System.out.println(prim());
	}
	
	static int prim() {
		PriorityQueue<NodeSt> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[V];
		
		PQ.offer(new NodeSt(0, 0));
		int totalCost = 0;
		int count = 0;
		
		while(!PQ.isEmpty()) {
			NodeSt node = PQ.poll();
			
			if(visited[node.idx]) continue;
			
			count++;
			totalCost += node.cost;
			visited[node.idx] = true;
			
			if (count == V) break;
			
			for (NodeSt nextNode : graph.get(node.idx)) {
				if (visited[nextNode.idx]) continue;
				PQ.offer(nextNode);
			}
		}
		
		return totalCost;
	}
}
