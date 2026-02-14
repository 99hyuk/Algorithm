import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeBus implements Comparable<NodeBus> {
	int idx;
	int cost;
	public NodeBus(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeBus o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static final int INF = 100_000_000;
	static ArrayList<ArrayList<NodeBus>> graph = new ArrayList<>();
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new NodeBus(to, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int to = Integer.parseInt(st.nextToken()) - 1;
		
		System.out.println(dijkstra(start, to));
	}
	
	static int dijkstra(int start, int to) {
		PriorityQueue<NodeBus> PQ = new PriorityQueue<>();
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		
		PQ.offer(new NodeBus(start, 0));
		dist[start] = 0;
		
		while(!PQ.isEmpty()) {
			NodeBus node = PQ.poll();
			int cutIdx = node.idx;
			int cutCost = node.cost;
			
			if (dist[cutIdx] < cutCost) continue;
			
			for (NodeBus nextNode : graph.get(cutIdx)) {
				int cost = cutCost + nextNode.cost;
				
				if (cost < dist[nextNode.idx]) {
					dist[nextNode.idx] = cost;
					PQ.offer(new NodeBus(nextNode.idx, cost));
				}
			}
		}
		
		return dist[to];
	}
}
