import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeD implements Comparable<NodeD> {
	int idx;
	int distance;
	NodeD(int idx, int distance) {
		this.idx = idx;
		this.distance = distance;
	}
	
	@Override
	public int compareTo (NodeD o) {
		return Integer.compare(this.distance, o.distance);
	}
}

public class Main {
	
	static final int INF = 200_000_000;
	static int N, E;
	static ArrayList<ArrayList<NodeD>> graph = new ArrayList<>();
	static int v1, v2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int distance = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new NodeD(to, distance));
			graph.get(to).add(new NodeD(start, distance));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken()) - 1;
		v2 = Integer.parseInt(st.nextToken()) - 1;
		
		int minDistance = -1;
		int distance1 = dijkstra(0, v1) + dijkstra(v1, v2) + dijkstra(v2, N-1);
		int distance2 = dijkstra(0, v2) + dijkstra(v2, v1) + dijkstra(v1, N-1);
		if (distance1 >= INF && distance2 >= INF) {
			minDistance = -1;
		} else {
			minDistance = Math.min(distance1, distance2);
		}
		
		System.out.println(minDistance);
	}
	
	static int dijkstra (int start, int to) {
		PriorityQueue<NodeD> PQ = new PriorityQueue<>();
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		
		PQ.offer(new NodeD(start, 0));
		dist[start] = 0;
		
		while (!PQ.isEmpty()) {
			NodeD node = PQ.poll();
			int cutIdx = node.idx;
			int cutDist = node.distance;
			
			if (dist[cutIdx] < cutDist) continue;
			
			for (NodeD nextNode : graph.get(cutIdx)) {
				int cost = cutDist + nextNode.distance;
				
				if (cost < dist[nextNode.idx]) {
					dist[nextNode.idx] = cost;
					PQ.offer(new NodeD(nextNode.idx, cost));
				}
			}
		}
		
		return dist[to];
	}
}
