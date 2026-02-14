import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeSb implements Comparable<NodeSb> {
	int idx;
	long distance;
	public NodeSb(int idx, long distance) {
		this.idx = idx;
		this.distance = distance;
	}
	@Override
	public int compareTo(NodeSb o) {
		return Long.compare(this.distance, o.distance);
	}
}

public class Main {
	static ArrayList<ArrayList<NodeSb>> graph = new ArrayList<>();
	static int N, M, K;
	static long[] dist;
	static final long INF = 10_000_000_000L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		dist = new long[N];
		Arrays.fill(dist, INF);
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int distance = Integer.parseInt(st.nextToken());
			
			graph.get(to).add(new NodeSb(start, distance));
		}
		
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0; i<K; i++) {
			list.add(Integer.parseInt(st.nextToken())-1);
		}
		
		dijkstra(list);
		
		int maxIdx = -1;
		long maxDistance = -1;
		for (int i=0; i<N; i++) {
			if (dist[i] > maxDistance) {
				maxIdx = i+1;
				maxDistance = dist[i];
			}
		}
		
		System.out.println(maxIdx + "\n" + maxDistance);
		
	}
	
	static void dijkstra(ArrayList<Integer> list) {
		PriorityQueue<NodeSb> PQ = new PriorityQueue<>();
		for (int start : list) {
			PQ.offer(new NodeSb(start, 0));
			dist[start] = 0;
		}
		
		while(!PQ.isEmpty()) {
			NodeSb node = PQ.poll();
			int cutIdx = node.idx;
			long cutDist = node.distance;
			
			if (dist[cutIdx] < cutDist) continue;
			
			for (NodeSb nextNode : graph.get(cutIdx)) {
				long cost = cutDist + nextNode.distance;
				
				if (cost < dist[nextNode.idx]) {
					dist[nextNode.idx] = cost;
					PQ.offer(new NodeSb(nextNode.idx, cost));
				}
			}
		}
	}
}
