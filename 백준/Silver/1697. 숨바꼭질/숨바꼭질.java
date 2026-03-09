import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class NodeTimeS implements Comparable<NodeTimeS> {
	int idx;
	int time;
	public NodeTimeS(int idx, int time) {
		this.idx = idx;
		this.time = time;
	}
	
	@Override
	public int compareTo(NodeTimeS o) {
		return Integer.compare(this.time, o.time);
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] dist = new int[100001];
		
		final int INF = 100001;
		Arrays.fill(dist, INF);
		
		dist[n] = 0;
		
		PriorityQueue<NodeTimeS> PQ = new PriorityQueue<>();
		PQ.offer(new NodeTimeS(n, 0));
		
		while(!PQ.isEmpty()) {
			NodeTimeS node = PQ.poll();
			int curIdx = node.idx;
			int curTime = node.time;
			
			if (curTime > dist[curIdx]) continue;
			
			int next = curIdx + 1;
			if ((0<=next && next<=100000) && curTime+1 < dist[next]) {
				dist[next] = curTime+1;
				PQ.offer(new NodeTimeS(next, curTime+1));
			}
			
			next = curIdx-1;
			if ((0<=next && next<=100000) && curTime+1 < dist[next]) {
				dist[next] = curTime+1;
				PQ.offer(new NodeTimeS(next, curTime+1));
			}
			
			next = curIdx*2;
			if ((0<=next && next<=100000) && curTime+1 < dist[next]) {
				dist[next] = curTime+1;
				PQ.offer(new NodeTimeS(next, curTime+1));
			}
		}
		
		System.out.println(dist[k]);
	}
}
