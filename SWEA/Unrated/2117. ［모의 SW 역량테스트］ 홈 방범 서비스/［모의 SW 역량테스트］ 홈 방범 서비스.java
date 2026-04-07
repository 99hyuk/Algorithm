import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Broden {
	int r;
	int c;
	public Broden(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Solution {
	
	static int[][] map;
	static int N,M;
	static int[] securityCost;
	static int maxHomeCnt;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		securityCost = new int[41];
		for (int i=1; i<=40; i++) {
			securityCost[i] = i*i + (i-1)*(i-1);
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxHomeCnt = Integer.MIN_VALUE;
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					bfs(i,j);
				}
			}
			
			sb.append("#").append(t).append(" ").append(maxHomeCnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int r, int c) {
		Queue<Broden> Q = new ArrayDeque<>();
		Q.offer(new Broden(r, c));
		
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		int homeCount = 0;
		int time = 1;
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			
			for (int i=0; i<size; i++) {
				Broden node = Q.poll();
				
				if (map[node.r][node.c] == 1) homeCount++;
				
				for (int dir=0; dir<4; dir++) {
					int nr = node.r + dr[dir];
					int nc = node.c + dc[dir];
					
					if(!isIn(nr,nc)) continue;
					if(visited[nr][nc]) continue;
					
					Q.offer(new Broden(nr, nc));
					visited[nr][nc] = true;
				}
			}
			
			if (homeCount*M >= securityCost[time++]) {
				maxHomeCnt = Math.max(maxHomeCnt, homeCount);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
}
