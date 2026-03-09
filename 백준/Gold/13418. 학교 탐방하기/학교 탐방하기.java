import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeGood implements Comparable<NodeGood> {
	int idx;
	int cost;
	NodeGood(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeGood o) {
		return Integer.compare(this.cost, o.cost);
	}
	
}

class NodeBad implements Comparable<NodeBad> {
	int idx;
	int cost;
	NodeBad(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeBad o) {
		return Integer.compare(o.cost, this.cost);
	}
	
}

public class Main {
	static List<List<NodeGood>> graphG = new ArrayList<>();
	static List<List<NodeBad>> graphB = new ArrayList<>();
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			graphG.add(new ArrayList<>());
			graphB.add(new ArrayList<>());
		}
		
		for (int i=0; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			if (cost == 1) {
				cost = 0;
			} else if (cost == 0) {
				cost = 1;
			}
			
			graphG.get(start).add(new NodeGood(to, cost));
			graphG.get(to).add(new NodeGood(start, cost));
			graphB.get(start).add(new NodeBad(to, cost));
			graphB.get(to).add(new NodeBad(start, cost));
		}
		
		int up = primB();
		int down = primG();
		
		System.out.println((int)Math.pow(up, 2) - (int)Math.pow(down, 2));
	}
	
	static int primG() {
		PriorityQueue<NodeGood> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		
		PQ.offer(new NodeGood(0, 0));
		int count = 0;
		int totalCost = 0;
		
		while(!PQ.isEmpty()) {
			NodeGood node = PQ.poll();
			
			if (visited[node.idx]) continue;
			
			count++;
			totalCost += node.cost;
			visited[node.idx] = true;
			
			if (count == N) break;
			
			for (NodeGood nextNode : graphG.get(node.idx)) {
				if (visited[nextNode.idx]) continue;
				PQ.offer(nextNode);
			}
			
		}
		
		return totalCost;
	}
	
	static int primB() {
		PriorityQueue<NodeBad> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		
		PQ.offer(new NodeBad(0, 0));
		int count = 0;
		int totalCost = 0;
		
		while(!PQ.isEmpty()) {
			NodeBad node = PQ.poll();
			
			if (visited[node.idx]) continue;
			
			count++;
			totalCost += node.cost;
			visited[node.idx] = true;
			
			if (count == N) break;
			
			for (NodeBad nextNode : graphB.get(node.idx)) {
				if (visited[nextNode.idx]) continue;
				PQ.offer(nextNode);
			}
			
		}
		
		return totalCost;
	}
}
