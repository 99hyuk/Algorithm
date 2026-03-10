import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] map;
	static int N,M;
	static int cnt = 0;
	static boolean[][] visited;
	
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
		
		while (true) {
			int islandCnt = checkIslandCnt();
			if (islandCnt == 0) {
				System.out.println(0);
				break;
			} else if (islandCnt >= 2) {
				System.out.println(-cnt);
				break;
			}
			
			for (int r=0; r<N; r++) {
				for (int c=0; c<M; c++) {
					if(map[r][c] == cnt) {
						SearchWaterBfs(r, c, cnt-1);
					}
				}
			}
			cnt--;
		}
	}
	
	static void SearchWaterBfs(int r, int c, int cnt) {
		Queue<int[]> Q = new ArrayDeque<>();
		Q.offer(new int[]{r,c});
		map[r][c] = cnt;
		
		while(!Q.isEmpty()) {
			int[] curPos = Q.poll();
			int curR = curPos[0];
			int curC = curPos[1];
			
			for (int ro=0; ro<4; ro++) {
				int nr = curR + dr[ro];
				int nc = curC + dc[ro];
				
				if(!isIn(nr,nc)) continue;
				if(map[nr][nc] == cnt) continue;
				
				if(map[nr][nc] > 0) {
					map[nr][nc]--;
					if(map[nr][nc] == 0) {
						map[nr][nc] = cnt;
					}
				} else {
					Q.offer(new int[]{nr,nc});
					map[nr][nc] = cnt;
				}
			}
		}
	}
	
	static int checkIslandCnt() {
		visited = new boolean[N][M];
		int islandCnt = 0;
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (map[r][c] > 0 && !visited[r][c]) {
					checkIslandCntBfs(r,c);
					islandCnt++;
					if (islandCnt>=2) return islandCnt;
				}
			}
		}
		
		return islandCnt;
	}
	
	static void checkIslandCntBfs(int r, int c) {
		visited[r][c] = true;
		
		Queue<int[]> Q = new ArrayDeque<>();
		Q.offer(new int[]{r,c});
		
		while(!Q.isEmpty()) {
			int[] curPos = Q.poll();
			int curR = curPos[0];
			int curC = curPos[1];
			
			for (int ro=0; ro<4; ro++) {
				int nr = curR + dr[ro];
				int nc = curC + dc[ro];
				
				if(!isIn(nr,nc)) continue;
				if(map[nr][nc] <= 0) continue;
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				Q.offer(new int[]{nr,nc});
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
