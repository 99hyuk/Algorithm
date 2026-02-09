import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int[][] map;
	static int coreMax;
	static int distanceSumMin;
	static boolean visited[][];
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreMax = 0;
			distanceSumMin = Integer.MAX_VALUE;
			
			for (int r=0; r<N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[N][N];
			
			cnt = 0;
			
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					if (map[r][c] == 1) {
						cnt++;
					}
				}
			}
			
			dfs(0, 0, 0, 0);
			
			System.out.println("#" + test_case + " " + distanceSumMin);
		}
	}
	
	static void dfs(int idx, int distance, int connect, int start) {
		
		if (coreMax < connect) {
			coreMax = connect;
			distanceSumMin = distance;
		} else if (coreMax == connect) {
			if (distance < distanceSumMin) {
				distanceSumMin = distance;
			}
		}
		
		if (idx==cnt) {
			return;
		}
		
		for (int i=start/N; i<N; i++) {
			int startJ = (i == start / N) ? (start % N) : 0;
			for (int j=startJ; j<N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					if (i==0 || i==N-1 || j==0 || j==N-1) continue;
					
//					if (upCheck(i,j)) {
//						int dis = upCal(i, j);
//						dfs(idx+1, distance+dis, connect+1);
//						upOri(i, j);
//					}
//					
//					if (downCheck(i, j)) {
//						int dis = downCal(i, j);
//						dfs(idx+1, distance+dis, connect+1);
//						downOri(i, j);
//					}
//					
//					if (leftCheck(i, j)) {
//						int dis = leftCal(i, j);
//						dfs(idx+1, distance+dis, connect+1);
//						leftOri(i, j);
//					}
//					
//					if (rightCheck(i, j)) {
//						int dis = rightCal(i, j);
//						dfs(idx+1, distance+dis, connect+1);
//						rightOri(i, j);
//					}
					
					if (upCheck(i,j)) {
						int dis = upCal(i, j);
						dfs(idx+1, distance+dis, connect+1, i*N+j);
						upOri(i, j);
					}
					
					if (downCheck(i, j)) {
						int dis = downCal(i, j);
						dfs(idx+1, distance+dis, connect+1, i*N+j);
						downOri(i, j);
					}
					
					if (leftCheck(i, j)) {
						int dis = leftCal(i, j);
						dfs(idx+1, distance+dis, connect+1, i*N+j);
						leftOri(i, j);
					}
					
					if (rightCheck(i, j)) {
						int dis = rightCal(i, j);
						dfs(idx+1, distance+dis, connect+1, i*N+j);
						rightOri(i, j);
					}
					
					dfs(idx+1, distance, connect, i*N+j);
					
					visited[i][j] = false;
                    return;
				}
			}
		}
	}
	
	static boolean upCheck(int r, int c) {
		for (int up=1; up<=r; up++) {
			if (map[r-up][c] == 1 || visited[r-up][c]) {
				return false;
			}
		}
		return true;
	}
	
	static boolean downCheck(int r, int c) {
		for (int down=1; r+down<N; down++) {
			if (map[r+down][c] == 1 || visited[r+down][c]) {
				return false;
			}
		}
		return true;
	}
	
	static boolean leftCheck(int r, int c) {
		for (int left=1; c-left>=0; left++) {
			if (map[r][c-left] == 1 || visited[r][c-left]) {
				return false;
			}
		}
		return true;
	}
	
	static boolean rightCheck(int r, int c) {
		for (int right=1; c+right<N; right++) {
			if (map[r][c+right] == 1 || visited[r][c+right]) {
				return false;
			}
		}
		return true;
	}
	
	static int upCal(int r, int c) {
		int distance = 0;
		for (int up=1; up<=r; up++) {
			visited[r-up][c] = true;
			distance++;
		}
		return distance;
	}
	
	static int downCal(int r, int c) {
		int distance = 0;
		for (int down=1; r+down<N; down++) {
			visited[r+down][c] = true;
			distance++;
		}
		return distance;
	}
	
	static int leftCal(int r, int c) {
		int distance = 0;
		for (int left=1; c-left>=0; left++) {
			visited[r][c-left] = true;
			distance++;
		}
		return distance;
	}
	
	static int rightCal(int r, int c) {
		int distance = 0;
		for (int right=1; c+right<N; right++) {
			visited[r][c+right] = true;
			distance++;
		}
		return distance;
	}
	
	static void upOri (int r, int c) {
		for (int up=1; up<=r; up++) {
			visited[r-up][c] = false;
		}
	}
	
	static void downOri(int r, int c) {
		for (int down=1; r+down<N; down++) {
			visited[r+down][c] = false;
		}
	}
	
	static void leftOri(int r, int c) {
		for (int left=1; c-left>=0; left++) {
			visited[r][c-left] = false;
		}
	}
	
	static void rightOri(int r, int c) {
		for (int right=1; c+right<N; right++) {
			visited[r][c+right] = false;
		}
	}
}
