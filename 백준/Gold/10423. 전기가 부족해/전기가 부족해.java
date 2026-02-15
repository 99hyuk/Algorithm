import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeElec implements Comparable<NodeElec> {
	int idx;
	int cost;
	NodeElec(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeElec o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static int N, M, K;
	static List<List<NodeElec>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int elecCity = Integer.parseInt(st.nextToken());
			graph.get(0).add(new NodeElec(elecCity, 0));
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new NodeElec(to, cost));
			graph.get(to).add(new NodeElec(start, cost));
		}
		
		System.out.println(prim());
	}
	
	static int prim() {
		PriorityQueue<NodeElec> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		
		PQ.offer(new NodeElec(0, 0));
		int count = 0;
		int totalCost = 0;
		
		while(!PQ.isEmpty()) {
			NodeElec node = PQ.poll();
			
			if (visited[node.idx]) continue;
			count++;
			totalCost += node.cost;
			visited[node.idx] = true;
			
			if (count == N+1) break;
			
			for (NodeElec nextNode : graph.get(node.idx)) {
				if (visited[nextNode.idx]) continue;
				PQ.offer(nextNode);
			}
		}
		
		return totalCost;
	}
}
