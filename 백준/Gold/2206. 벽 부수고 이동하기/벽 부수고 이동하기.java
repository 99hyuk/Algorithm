import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point2 {
	int r;
	int c;
	int cnt;
	int destroy;
	Point2(int r, int c, int cnt, int destroy) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
		this.destroy = destroy;
	}
}

public class Main {
	
	static int N,M;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static boolean possible = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		bfs();

		
		if (!possible) {
			min = -1;
		}
		
		System.out.println(min);
	}
	
	static void bfs() {
		boolean[][][] visited = new boolean[N][M][2];
		Queue<Point2> Q = new ArrayDeque<>();

		visited[0][0][0] = true;
		Q.offer(new Point2(0, 0, 1, 0));
		
		while(!Q.isEmpty()) {
			Point2 p = Q.poll();
			if (p.r == N-1 && p.c == M-1) {
				possible = true;
				min = Math.min(min, p.cnt);
				break;
			}
			
			for (int i=0; i<4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr,nc)) {
					if (map[nr][nc] == 0) {
						if (!visited[nr][nc][p.destroy]) {
							visited[nr][nc][p.destroy] = true;
							Q.offer(new Point2(nr, nc, p.cnt+1, p.destroy));
						}
					} else {
						if (p.destroy == 0 && !visited[nr][nc][1]) {
							visited[nr][nc][1] = true;
							Q.offer(new Point2(nr, nc, p.cnt+1, 1));
						}
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}