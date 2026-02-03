import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point3 {
	int r;
	int c;
	int jump;
	int cnt;
	
	Point3(int r, int c, int jump, int cnt) {
		this.r = r;
		this.c = c;
		this.jump = jump;
		this.cnt = cnt;
	}
}

public class Main {
	
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[] drHorse = {-2,-1,1,2,2,1,-1,-2};
	static int[] dcHorse = {1,2,2,1,-1,-2,-2,-1};
	static int W,H;
	static int min = Integer.MAX_VALUE;
	static boolean isPoss = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		for (int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs(H, W, k);
		
		if (!isPoss || map[0][0] == 1 || map[H-1][W-1] == 1) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}
	
	static void bfs(int H, int W, int k) {
		boolean[][][] visited = new boolean[H][W][k+1];
		Queue<Point3> Q = new ArrayDeque<>();
		Q.offer(new Point3(0,0,0,0));
		
		while(!Q.isEmpty()) {
			Point3 p = Q.poll();
			
			if (p.r == H-1 && p.c == W-1) {
				isPoss = true;
				min = Math.min(min, p.cnt);
			}
			
			for (int i=0; i<4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if (isIn(nr,nc) && !visited[nr][nc][p.jump]) {
					visited[nr][nc][p.jump] = true;
					Q.offer(new Point3(nr, nc, p.jump, p.cnt+1));
				}
			}
			
			for (int i=0; i<8; i++) {
				int nrHorse = p.r + drHorse[i];
				int ncHorse = p.c + dcHorse[i];
				
				if (isIn(nrHorse,ncHorse) && p.jump < k && !visited[nrHorse][ncHorse][p.jump+1]) {
					visited[nrHorse][ncHorse][p.jump+1] = true;
					Q.offer(new Point3(nrHorse, ncHorse, p.jump+1, p.cnt+1));
					
				}
			}
			

		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W && map[r][c] == 0;
	}
}
