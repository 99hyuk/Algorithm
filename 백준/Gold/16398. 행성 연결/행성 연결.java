import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodePlanet implements Comparable<NodePlanet> {
	int idx;
	long cost;
	public NodePlanet(int idx, long cost) {
		this.idx = idx;
		this.cost = cost;
	}
	@Override
	public int compareTo(NodePlanet o) {
		return Long.compare(this.cost, o.cost);
	}
}

public class Main {
	static int graph[][];
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(prim());
	}
	
	static long prim() {
		PriorityQueue<NodePlanet> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		
		PQ.offer(new NodePlanet(0, 0));
		
		int count = 0;
		Long totalCost = 0L;
		
		while(!PQ.isEmpty()) {
			NodePlanet node = PQ.poll();
			if (visited[node.idx]) continue;
			
			count++;
			totalCost += node.cost;
			visited[node.idx] = true;
			if (count == N) break;
			
			for (int i=0; i<N; i++) {
				if (visited[i]) continue;
				PQ.offer(new NodePlanet(i, graph[node.idx][i]));
			}
		}
		
		return totalCost;
	}
}
