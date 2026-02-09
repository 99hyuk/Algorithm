import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int maxStart;
	static int maxCount;
	static int map[][];
	static int[] dc = {1,-1,0,0};
	static int[] dr = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for (int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxCount = 0;
			maxStart = 0;
			
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					dfs(r,c,1,map[r][c]);
				}
			}
			
			System.out.println("#" + test_case + " " + maxStart + " " + maxCount);
		}
	}
	
	static void dfs(int r, int c, int count, int start) {
		
		if (maxCount < count) {
			maxCount = count;
			maxStart = start;
		} else if (maxCount == count) {
			if (start < maxStart) {
				maxStart = start;
			}
		} 
		
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || N <= nr || nc < 0 || N<=nc) continue;
			if (map[nr][nc] == map[r][c] + 1) {
				dfs(nr, nc, count+1, start);
			}
		}
	}
}
