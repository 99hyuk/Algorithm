import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeHs implements Comparable<NodeHs> {
	int idx;
	long cost;
	NodeHs (int idx, long cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeHs o) {
		return Long.compare(this.cost, o.cost);
	}
	
	
}

public class Main {
	
	static final long INF = 1_000_000_000_000_000L;
	static ArrayList<ArrayList<NodeHs>> graph = new ArrayList<>();
	static int N, M, A, B;
	static long C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken()) - 1;
		B = Integer.parseInt(st.nextToken()) - 1;
		C = Long.parseLong(st.nextToken());
		
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		ArrayList<Integer> costList = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new NodeHs(to, cost));
            graph.get(to).add(new NodeHs(start, cost));
			costList.add(cost);
		}
		
		Collections.sort(costList);
		
		int low = 0;
		int high = costList.size()-1;
		int maxCost = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (dijkstra(costList.get(mid))) {
				high = mid - 1;
				maxCost = costList.get(mid);
			} else {
				low = mid + 1;
			}
			
		}
		
		System.out.println(maxCost);
		
	}
	// 더 큰 값이 있는 길이 더 쌀 때가 문제? 작은값으로 가야 수치심이 적은데
	static boolean dijkstra(int limit) {
		PriorityQueue<NodeHs> PQ = new PriorityQueue<>();
		PQ.offer(new NodeHs(A, 0));
		long dist[] = new long[N];
		Arrays.fill(dist, INF);
		dist[A] = 0;
		
		while(!PQ.isEmpty()) {
			NodeHs node = PQ.poll();
			
			int cutIdx = node.idx;
			long cutCost = node.cost;
			
			if (dist[cutIdx] < cutCost) continue;
			
			for (NodeHs nextNode : graph.get(cutIdx)) {
				long cost = cutCost + nextNode.cost;
				if (nextNode.cost > limit) continue;
				if (cost > C) continue;
				
				if (cost < dist[nextNode.idx]) {
					dist[nextNode.idx] = cost;
					PQ.offer(new NodeHs(nextNode.idx, cost));
				}
			}
		}
		
		if (dist[B] == INF) return false;
		else return true;
	}
}
