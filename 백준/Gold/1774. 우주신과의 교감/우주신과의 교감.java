import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeSpace implements Comparable<NodeSpace> {
	int idx;
	double distance;
	public NodeSpace(int idx, double distance) {
		this.idx = idx;
		this.distance = distance;
	}
	
	@Override
	public int compareTo(NodeSpace o) {
		return Double.compare(this.distance, o.distance);
	}
}

class NodeXY {
	int idx;
	int x;
	int y;
	NodeXY(int idx, int x, int y) {
		this.idx = idx;
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static List<List<NodeSpace>> graph = new ArrayList<>();
	static List<NodeXY> coor = new ArrayList<>();
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			coor.add(new NodeXY(i, x, y));
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			graph.get(start).add(new NodeSpace(to, 0));
			graph.get(to).add(new NodeSpace(start, 0));
		}
		
		System.out.printf("%.2f", prim());
	}
	
	static double prim() {
		PriorityQueue<NodeSpace> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		
		PQ.offer(new NodeSpace(0, 0));
		int count = 0;
		double totalCost = 0;
		
		while(!PQ.isEmpty()) {
			NodeSpace node = PQ.poll();
			
			if (visited[node.idx]) continue;
			
			count++;
			totalCost += node.distance;
			visited[node.idx] = true;
			
			if (count == N) break;
			
			for (NodeSpace nextNode : graph.get(node.idx)) {
				if(visited[nextNode.idx]) continue;
				PQ.offer(nextNode);
			}
			
			for (int i=0; i<N; i++) {
				if(visited[i]) continue;
				int cutX = coor.get(node.idx).x;
				int cutY = coor.get(node.idx).y;
				int nextX = coor.get(i).x;
				int nextY = coor.get(i).y;
				double distance = Math.sqrt(Math.pow(nextY-cutY, 2) + Math.pow(nextX-cutX, 2));
		
				PQ.offer(new NodeSpace(i, distance));
			}
		}
		return totalCost;
	}
}
