import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeNet implements Comparable<NodeNet> {
	int idx;
	int cost;
	NodeNet(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeNet o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static List<List<NodeNet>> graph = new ArrayList<List<NodeNet>>();
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken() )- 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new NodeNet(to, cost));
			graph.get(to).add(new NodeNet(start, cost));
		}
		
		System.out.println(prim());
	}
	
	static int prim() {
		PriorityQueue<NodeNet> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		
		PQ.offer(new NodeNet(0, 0));
		
		int count = 0;
		int totalCost = 0;
		
		while(!PQ.isEmpty()) {
			NodeNet node = PQ.poll();
			
			if (visited[node.idx]) continue;
			
			count++;
			totalCost += node.cost;
			visited[node.idx] = true;
			
			if (count == N) break;
			
			for (NodeNet nextNode : graph.get(node.idx)) {
				if (visited[nextNode.idx]) continue;
				PQ.offer(nextNode);
			}
		}
		return totalCost; 
	}
}
