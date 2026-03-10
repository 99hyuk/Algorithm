import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int n,m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for (int r=0; r<n; r++) {
			String str = br.readLine();
			for (int c=0; c<m; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		
		Queue<int[]> Q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[n][m][k+1];
		
		Q.offer(new int[] {0,0,0,1});
		visited[0][0][0] = true;
		
		while(!Q.isEmpty()) {
			int[] curPos = Q.poll();
			int curR = curPos[0];
			int curC = curPos[1];
			int curK = curPos[2];
			int curTime = curPos[3];
			
			if(curR == n-1 && curC == m-1) {
				System.out.println(curTime);
				return;
			}
			
			for (int ro=0; ro<4; ro++) {
				int nr = curR + dr[ro];
				int nc = curC + dc[ro];
				
				if(!isIn(nr,nc)) continue;
				
				if(map[nr][nc] == 0) {
					if(visited[nr][nc][curK]) continue;
					visited[nr][nc][curK] = true;
					Q.offer(new int[] {nr,nc,curK,curTime+1});
				} else {
					if(curK+1 > k) continue;
					if(visited[nr][nc][curK+1]) continue;
					visited[nr][nc][curK+1] = true;
					Q.offer(new int[] {nr,nc,curK+1,curTime+1});
				}
			}
		}
		
		System.out.println(-1);
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<n && 0<=c && c<m;
	}
}
