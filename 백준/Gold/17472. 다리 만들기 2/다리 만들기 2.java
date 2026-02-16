import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class PointIsland {
	int r;
	int c;
	public PointIsland(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class NodeBridge implements Comparable<NodeBridge> {
	int idx;
	int cost;
	public NodeBridge(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(NodeBridge o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static int N, M;
	static int[][] map, islandMap;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int idx;
	static List<List<NodeBridge>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		islandMap = new int[N][M];
		idx = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 1 && islandMap[i][j] == 0) {
					bfs(i, j, ++idx);
				}
			}
		}
		
		for (int i=0; i<=idx; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (islandMap[r][c] != 0) {
					makeBridge(r, c);
				}
			}
		}
		
		System.out.println(prim());
	}
	
	static void makeBridge(int r, int c) {
		int nr = r;
		int nc = c;
		int cnt = 0;
		while (isIn(nr,nc)) {
			nr = nr + 1;
			if (isIn(nr,nc) && islandMap[nr][nc] == islandMap[r][c]) {
				break;
			}
			
			if (isIn(nr,nc) && islandMap[nr][nc] == 0) {
				cnt++;
			}
			
			if (isIn(nr,nc) && islandMap[nr][nc] != islandMap[r][c] && islandMap[nr][nc] != 0) {
				if (cnt == 1) break;
				graph.get(islandMap[r][c]).add(new NodeBridge(islandMap[nr][nc], cnt));
				graph.get(islandMap[nr][nc]).add(new NodeBridge(islandMap[r][c], cnt));
				break;
			}
		}
		
		nr = r;
		nc = c;
		cnt = 0;
		while (isIn(nr,nc)) {
			nr = nr - 1;
			if (isIn(nr,nc) && islandMap[nr][nc] == islandMap[r][c]) {
				break;
			}
			
			if (isIn(nr,nc) && islandMap[nr][nc] == 0) {
				cnt++;
			}
			
			if (isIn(nr,nc) && islandMap[nr][nc] != islandMap[r][c] && islandMap[nr][nc] != 0) {
				if (cnt == 1) break;
				graph.get(islandMap[r][c]).add(new NodeBridge(islandMap[nr][nc], cnt));
				graph.get(islandMap[nr][nc]).add(new NodeBridge(islandMap[r][c], cnt));
				break;
			}
		}
		
		nr = r;
		nc = c;
		cnt = 0;
		while (isIn(nr,nc)) {
			nc = nc + 1;
			if (isIn(nr,nc) && islandMap[nr][nc] == islandMap[r][c]) {
				break;
			}
			
			if (isIn(nr,nc) && islandMap[nr][nc] == 0) {
				cnt++;
			}
			
			if (isIn(nr,nc) && islandMap[nr][nc] != islandMap[r][c] && islandMap[nr][nc] != 0) {
				if (cnt == 1) break;
				graph.get(islandMap[r][c]).add(new NodeBridge(islandMap[nr][nc], cnt));
				graph.get(islandMap[nr][nc]).add(new NodeBridge(islandMap[r][c], cnt));
				break;
			}
		}
		
		nr = r;
		nc = c;
		cnt = 0;
		while (isIn(nr,nc)) {
			nc = nc - 1;
			if (isIn(nr,nc) && islandMap[nr][nc] == islandMap[r][c]) {
				break;
			}
			
			if (isIn(nr,nc) && islandMap[nr][nc] == 0) {
				cnt++;
			}
			
			if (isIn(nr,nc) && islandMap[nr][nc] != islandMap[r][c] && islandMap[nr][nc] != 0) {
				if (cnt == 1) break;
				graph.get(islandMap[r][c]).add(new NodeBridge(islandMap[nr][nc], cnt));
				graph.get(islandMap[nr][nc]).add(new NodeBridge(islandMap[r][c], cnt));
				break;
			}
		}
	}
	
	static int prim() {
		PriorityQueue<NodeBridge> PQ = new PriorityQueue<>();
		boolean[] visitied = new boolean[idx+1];
		
		PQ.offer(new NodeBridge(1, 0));
		int count = 0;
		int totalCost = 0;

		while(!PQ.isEmpty()) {
			NodeBridge node = PQ.poll();
			
			if(visitied[node.idx]) continue;
			
			count++;
			totalCost += node.cost;
			visitied[node.idx] = true;
			
			if (count == idx) break;
			for (NodeBridge nextNode : graph.get(node.idx)) {
				if (visitied[nextNode.idx]) continue;
				PQ.offer(nextNode);
			}
		}
		
		if (count == idx) return totalCost;
		else return -1;
	}
	
	static void bfs(int r, int c, int idx) {
		Queue<PointIsland> Q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		Q.offer(new PointIsland(r, c));
		visited[r][c] = true;
		islandMap[r][c] = idx;
		
		while(!Q.isEmpty()) {
			PointIsland p = Q.poll();
			
			for (int i=0; i<4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr,nc)) continue;
				if(map[nr][nc] != 1) continue;
				if(visited[nr][nc]) continue;
				
				Q.offer(new PointIsland(nr, nc));
				visited[nr][nc] = true;
				islandMap[nr][nc] = idx;
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
