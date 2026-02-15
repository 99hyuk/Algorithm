import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeX implements Comparable<NodeX>{
	int idx;
	int x;
	int y;
	int z;
	NodeX (int idx, int x, int y, int z) {
		this.idx = idx;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public int compareTo(NodeX o) {
		return Integer.compare(this.x, o.x);
	}
}

class NodeY implements Comparable<NodeY>{
	int idx;
	int x;
	int y;
	int z;
	NodeY (int idx, int x, int y, int z) {
		this.idx = idx;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public int compareTo(NodeY o) {
		return Integer.compare(this.y, o.y);
	}
}

class NodeZ implements Comparable<NodeZ>{
	int idx;
	int x;
	int y;
	int z;
	NodeZ (int idx, int x, int y, int z) {
		this.idx = idx;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public int compareTo(NodeZ o) {
		return Integer.compare(this.z, o.z);
	}
}

class NodeP implements Comparable<NodeP> {
	int idx;
	long distance;
	NodeP (int idx, long distance) {
		this.idx = idx;
		this.distance = distance;
	}
	
	@Override
	public int compareTo(NodeP o) {
		return Long.compare(this.distance, o.distance);
	}
}

public class Main {
	static List<List<NodeP>> graph = new ArrayList<>();
	static List<NodeX> listX = new ArrayList<>();
	static List<NodeY> listY = new ArrayList<>();
	static List<NodeZ> listZ = new ArrayList<>();
	static int N;
	static final long INF = 20_000_000_000L;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			listX.add(new NodeX(i, x, y, z));
			listY.add(new NodeY(i, x, y, z));
			listZ.add(new NodeZ(i, x, y, z));
		}
		
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		Collections.sort(listX);
		for (int i=0; i<listX.size() - 1; i++) {
			graph.get(listX.get(i).idx).add(new NodeP(listX.get(i+1).idx, Math.abs(listX.get(i+1).x - listX.get(i).x)));
			graph.get(listX.get(i+1).idx).add(new NodeP(listX.get(i).idx, Math.abs(listX.get(i+1).x - listX.get(i).x)));
		}
		
		Collections.sort(listY);
		for (int i=0; i<listX.size() - 1; i++) {
			graph.get(listY.get(i).idx).add(new NodeP(listY.get(i+1).idx, Math.abs(listY.get(i+1).y - listY.get(i).y)));
			graph.get(listY.get(i+1).idx).add(new NodeP(listY.get(i).idx, Math.abs(listY.get(i+1).y - listY.get(i).y)));
		}
		
		Collections.sort(listZ);
		for (int i=0; i<listX.size() - 1; i++) {
			graph.get(listZ.get(i).idx).add(new NodeP(listZ.get(i+1).idx, Math.abs(listZ.get(i+1).z - listZ.get(i).z)));
			graph.get(listZ.get(i+1).idx).add(new NodeP(listZ.get(i).idx, Math.abs(listZ.get(i+1).z - listZ.get(i).z)));
		}
		
		System.out.println(prim());
	}
	
	static long prim() {
		PriorityQueue<NodeP> PQ = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		long[] minDist = new long[N];
		Arrays.fill(minDist, INF);
		
		PQ.offer(new NodeP(0, 0));
		minDist[0] = 0;
		int count = 0;
		long totalCost = 0;
		
		while(!PQ.isEmpty()) {
			NodeP node = PQ.poll();
			
			if (visited[node.idx]) continue;
			
			count++;
			totalCost += node.distance;
			visited[node.idx] = true;
			
			if (count == N) break;
			
			for (NodeP nextNode : graph.get(node.idx)) {
				if (visited[nextNode.idx]) continue;
				if (minDist[nextNode.idx] < nextNode.distance) continue;
				minDist[nextNode.idx] = nextNode.distance;
				PQ.offer(nextNode);
			}
		}
		
		return totalCost;
	}
}
