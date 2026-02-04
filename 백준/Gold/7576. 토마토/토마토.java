import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class PointT {
	int r;
	int c;
	PointT(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int N;
	static int M;
	static int cnt;
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		cnt = -1;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j] != -1 && visited[i][j] == false) {
					cnt = -1;
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	static void bfs() {
		Queue<PointT> Q = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 1) {
					Q.offer(new PointT(i,j));
					visited[i][j] = true;
				}
			}
		}
		
		while(!Q.isEmpty()) {
			int recur = Q.size();
			for (int i=0; i<recur; i++) {
				PointT p = Q.poll();
				
				for (int j=0; j<4; j++) {
					int nr = p.r + dr[j];
					int nc = p.c + dc[j];
					
					if (isIn(nr,nc) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						Q.offer(new PointT(nr, nc));
					}
				}
				
			}
			cnt++;
			
		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M && map[r][c] != -1;
	}
}
