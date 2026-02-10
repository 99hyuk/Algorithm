import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class PointB {
	int r;
	int c;
	PointB(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int N, M, map[][], max;
	static int[] dc = {1,-1,0,0};
	static int[] dr = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		
		dfs(0,0,0);
		
		System.out.println(max);
	}
	
	static void bfs() {
		Queue<PointB> Q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (map[r][c] == 2) {
					Q.offer(new PointB(r, c));
					visited[r][c] = true;
				}
			}
		}
		
		while(!Q.isEmpty()) {
			PointB p = Q.poll();
			for (int ro=0; ro<4; ro++) {
				int nr = p.r + dr[ro];
				int nc = p.c + dc[ro];
				
				if (nr<0 || N<=nr || nc<0 || M<=nc) continue;
				if (map[nr][nc] == 1 || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				Q.offer(new PointB(nr,nc));
			}
		}
		
		int cnt = 0;
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (map[r][c] == 0 && !visited[r][c]) {
					cnt++;
				}
			}
		}
		max = Math.max(cnt, max);
	}
	
	static void dfs(int idx, int startI, int startJ) {
		if (idx==3) { 
			bfs();
			return;
		}
		
		for (int r=startI; r<N; r++) {
			startJ = r > startI ? 0 : startJ;
			for (int c=startJ; c<M; c++) {
				if (map[r][c] == 0) {
					map[r][c] = 1;
					dfs(idx+1,r,c);
					map[r][c] = 0;
				}
			}
		}
	}
}
