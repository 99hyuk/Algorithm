import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, K;
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int max;
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			max = 0;
			int higher = 0;
			
			for (int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					higher = Math.max(higher, map[r][c]);
				}
			}
			
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					if (map[r][c] == higher) {
						isVisited = new boolean[N][N];
                        isVisited[r][c] = true;
						dfs(r,c,1,0,map[r][c]);
					}
				}
			}
			
			System.out.println("#" + test_case + " " + max);
		}
	}
	
	static void dfs(int r, int c, int cnt, int dream, int prev) {
		
		max = Math.max(max, cnt);
		
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr<0 || N<=nr || nc<0 || N<=nc) continue;
			
			if (map[nr][nc] >= prev) {
				if (map[nr][nc] - K < map[r][c] && dream == 0) {
					if (!isVisited[nr][nc]) {
						isVisited[nr][nc] = true;
						dfs(nr,nc,cnt+1,dream+1, map[r][c]-1);
						isVisited[nr][nc] = false;
					}
				}
			} else {
				if (!isVisited[nr][nc]) {
					isVisited[nr][nc] = true;
					dfs(nr,nc,cnt+1,dream,map[nr][nc]);
					isVisited[nr][nc] = false;
				}
			}	
		}
	}
}
