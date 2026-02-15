import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class NodeSb implements Comparable<NodeSb> {
	int idx;
	int cost;
	
	NodeSb(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeSb o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	
	static final int INF = 200_000;
	static int N, K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		System.out.println(dijkstra(N));
	}
	
	static int dijkstra (int start) {
		PriorityQueue<NodeSb> PQ = new PriorityQueue<>();
		int[] dist = new int[100001];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		PQ.offer(new NodeSb(start, 0));
		
		
		while(!PQ.isEmpty()) {
			NodeSb node = PQ.poll();
			
			int cutIdx = node.idx;
			int cutDist = node.cost;
			
			if (dist[cutIdx] < cutDist) continue;
			
			int nextIdx = cutIdx - 1;
			int nextCost = cutDist + 1;
			
			if (isIn(nextIdx) && nextCost < dist[nextIdx]) {
				dist[nextIdx] = nextCost;
				PQ.offer(new NodeSb(nextIdx, nextCost));
			}
			
			nextIdx = cutIdx + 1;
			nextCost = cutDist + 1;
			
			if (isIn(nextIdx) && nextCost < dist[nextIdx]) {
				dist[nextIdx] = nextCost;
				PQ.offer(new NodeSb(nextIdx, nextCost));
			}
			
			nextIdx = cutIdx * 2;
			nextCost = cutDist;
			
			if (isIn(nextIdx) && nextCost < dist[nextIdx]) {
				dist[nextIdx] = nextCost;
				PQ.offer(new NodeSb(nextIdx, nextCost));
			}
		}
		
		return dist[K];
	}
	
	static boolean isIn(int idx) {
		return 0<=idx && idx <= 100000;
	}
}