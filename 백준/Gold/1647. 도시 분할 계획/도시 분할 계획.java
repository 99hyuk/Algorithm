import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeCity implements Comparable<NodeCity> {
	int idx;
	int cost;
	NodeCity(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeCity o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static List<List<NodeCity>> graph = new ArrayList<>();
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new NodeCity(to, cost));
			graph.get(to).add(new NodeCity(start, cost));
		}
		
		System.out.println(prim());
		
		
	}
	
	static int prim() {
		PriorityQueue<NodeCity> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		
		PQ.offer(new NodeCity(0, 0));
		int count=0;
		int totalCost=0;
		int maxCost = Integer.MIN_VALUE;
		
		while(!PQ.isEmpty()) {
			NodeCity node = PQ.poll();
			
			if (visited[node.idx]) continue;
			
			count++;
			totalCost += node.cost;
			visited[node.idx] = true;
			maxCost = Math.max(maxCost, node.cost);
			
			if (count == N) break;
			
			for (NodeCity nextNode : graph.get(node.idx)) {
				if (visited[nextNode.idx]) continue;
				PQ.offer(nextNode);
			}
		}
		
		return totalCost - maxCost;
	}
}
