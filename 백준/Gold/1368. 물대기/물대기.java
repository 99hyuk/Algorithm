import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeWater implements Comparable<NodeWater> {
	int idx;
	int cost;

	public NodeWater(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(NodeWater o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static int[][] graph;
	static int[] waterCost;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		waterCost = new int[N+1];

		for (int i = 1; i <= N; i++) {
			waterCost[i] = Integer.parseInt(br.readLine());
			graph[0][i] = waterCost[i];
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			graph[i][0] = waterCost[i];
			for (int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		System.out.println(prim(0));
	}

	static int prim(int start) {
		PriorityQueue<NodeWater> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		PQ.offer(new NodeWater(start, 0));

		int count = 0;
		int totalCost = 0;

		while(!PQ.isEmpty()) {
			NodeWater node = PQ.poll();
			
			if (visited[node.idx]) continue;
			
			count++;
			totalCost += node.cost;
			visited[node.idx] = true;
			
			if (count == N+1) break;
			
			for (int i=0; i<=N; i++) {
				if (visited[i]) continue;
				PQ.offer(new NodeWater(i, graph[node.idx][i]));
			}
		}
		return totalCost;
	}
}
