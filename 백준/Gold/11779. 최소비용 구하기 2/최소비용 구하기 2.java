import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class NodeBus2 implements Comparable<NodeBus2> {
	int idx;
	int cost;
	public NodeBus2(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeBus2 o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static final int INF = 100_000_000;
	static ArrayList<ArrayList<NodeBus2>> graph = new ArrayList<>();
	static int N, M;
	static int[] dist;
	static int[] parentCity;
	
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
			
			graph.get(start).add(new NodeBus2(to, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int to = Integer.parseInt(st.nextToken()) - 1;
		
		parentCity = new int[N];
		
		System.out.println(dijkstra(start, to));
		
		Stack<Integer> stack = new Stack<>();
		int curr = to;
		while(curr != start) {
			stack.push(curr);
			curr = parentCity[curr];
		}
		stack.push(start);
		
		System.out.println(stack.size());
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+1 + " ");
		}
	}
	
	static int dijkstra(int start, int to) {
		PriorityQueue<NodeBus2> PQ = new PriorityQueue<>();
		dist = new int[N];
		Arrays.fill(dist, INF);
		
		PQ.offer(new NodeBus2(start, 0));
		dist[start] = 0;
		
		while(!PQ.isEmpty()) {
			NodeBus2 node = PQ.poll();
			int cutIdx = node.idx;
			int cutCost = node.cost;
			
			if (dist[cutIdx] < cutCost) continue;
			
			for (NodeBus2 nextNode : graph.get(cutIdx)) {
				int cost = cutCost + nextNode.cost;
				
				if (cost < dist[nextNode.idx]) {
					dist[nextNode.idx] = cost;
					parentCity[nextNode.idx] = cutIdx;
					PQ.offer(new NodeBus2(nextNode.idx, cost));
				}
			}
		}
		
		return dist[to];
	}
}
