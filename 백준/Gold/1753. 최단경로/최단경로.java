import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int idx;
	int distance;
	
	Node(int idx, int distance) {
		this.idx = idx;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.distance, o.distance);
	}
}

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int V, E, K;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		dist = new int[V];
		Arrays.fill(dist, INF);
		
		for (int i=0; i<V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new Node(to, w));
		}
		
		dijkstra(K-1);
		
		for (int i=0; i<V; i++) {
			if (dist[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}
		
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!PQ.isEmpty()) {
			Node node = PQ.poll();
			int curIdx = node.idx;
			int curDist = node.distance;
			
			if (dist[curIdx] < curDist) continue;
			
			for (Node nextNode : graph.get(curIdx)) {
				int cost = curDist + nextNode.distance;
				
				if (cost < dist[nextNode.idx]) {
					dist[nextNode.idx] = cost;
					PQ.offer(new Node(nextNode.idx, cost));
				}
			}
		}
	}
}
