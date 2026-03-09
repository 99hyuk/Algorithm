import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int r;
	int c;
	Pos (int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		int[][] distMap = new int[n][m];
		int startR = -1;
		int startC = -1;
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					startR = i;
					startC = j;
				}
			}
		}
		
		Queue<Pos> Q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		
		Q.offer(new Pos(startR,startC));
		visited[startR][startC] = true;
		int dist = 1;
		
		while(!Q.isEmpty()) {
			
			int size = Q.size();
			for (int i=0; i<size; i++) {
				Pos p = Q.poll();
				
				for (int ro=0; ro<4; ro++) {
					int nr = p.r + dr[ro];
					int nc = p.c + dc[ro];
					
					if(nr<0 || n<=nr || nc<0 || m<=nc) continue;
					if(map[nr][nc] == 0) continue;
					if(visited[nr][nc]) continue;

					visited[nr][nc] = true;
					distMap[nr][nc] = dist;
					Q.offer(new Pos(nr, nc));
				}
			}
			dist++;
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					distMap[i][j] = -1;
				}
			}
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				sb.append(distMap[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
