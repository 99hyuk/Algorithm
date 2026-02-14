import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NodeM implements Comparable<NodeM> {
	int r, c, cost;
	
	NodeM(int r, int c, int cost) {
		this.r = r;
		this.c = c;
		this.cost = cost;
	}		

	@Override
	public int compareTo(NodeM o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N, M;
	
	static int dist[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dist = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				dist[i][j] = INF;
			}
		}
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		dijkstra();
		
		System.out.println(dist[N-1][M-1]);
	}
	
	static void dijkstra() {
		PriorityQueue<NodeM> PQ = new PriorityQueue<>();
		PQ.offer(new NodeM(0,0,0));
		dist[0][0] = 0;
		
		while(!PQ.isEmpty()) {
			NodeM node = PQ.poll();
			int cutR = node.r;
			int cutC = node.c;
			int cutCost = node.cost;
			
			if (dist[cutR][cutC] < cutCost) continue;
			
			for (int i=0; i<4; i++) {
				int nr = cutR + dr[i];
				int nc = cutC + dc[i];
					
				if(!isIn(nr,nc)) continue;
				int cost = cutCost + map[nr][nc];
				
				if (cost < dist[nr][nc]) {
					dist[nr][nc] = cost;
					PQ.offer(new NodeM(nr, nc, cost));
				}
				
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
