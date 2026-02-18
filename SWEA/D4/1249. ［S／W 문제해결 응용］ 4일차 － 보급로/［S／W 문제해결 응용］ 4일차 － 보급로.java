import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class NodeBo implements Comparable<NodeBo> {
	int r;
	int c;
	int cost;
	
	NodeBo (int r, int c, int cost) {
		this.r = r;
		this.c = c;
		this.cost = cost;
	}

	@Override
	public int compareTo(NodeBo o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Solution {
	
	static int N, map[][];
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i=0; i<N; i++) {
				String str = br.readLine();
				for (int j=0; j<N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			System.out.println("#" + test_case + " " + dijkstra());
			
		}
	}
	
	static int dijkstra() {
		PriorityQueue<NodeBo> PQ = new PriorityQueue<>();
		int[][] dist = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				dist[i][j] = INF;
			}
		}
		
		dist[0][0] = 0;
		PQ.offer(new NodeBo(0, 0, 0));
		
		while(!PQ.isEmpty()) {
			NodeBo node = PQ.poll();
			int curR = node.r;
			int curC = node.c;
			int curCost = node.cost;
			
			if (dist[curR][curC] < curCost) continue;
			
			for(int i=0; i<4; i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				
				if(nr<0 || N<=nr || nc<0 || N<=nc) continue;
				
				int cost = curCost + map[nr][nc];
				
				if (cost < dist[nr][nc]) {
					dist[nr][nc] = cost;
					PQ.offer(new NodeBo(nr, nc, cost));
				}
			}
		}
		
		return dist[N-1][N-1];
	}
}
