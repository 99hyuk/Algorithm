import java.util.Scanner;

public class Main {
	
	static int cnt, n;
	static int[][] map;
	static boolean[] visitedRow, diagUp, diagDown;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cnt = 0;
		n = sc.nextInt();
		map = new int[n][n];
		visitedRow = new boolean[n];
		diagUp = new boolean[2*n];
		diagDown = new boolean[2*n];
		
		dfs(0);
		
		System.out.println(cnt);
	}
	
	static void dfs(int idx) {
		if (idx == n) {
			cnt++;
			return;
		}
		
		for (int j=0; j<n; j++) {
			if(!visitedRow[j] && !diagUp[(n-1) - idx+j] && !diagDown[idx+j]) {
				visitedRow[j] = diagUp[(n-1) - idx+j] = diagDown[idx+j] = true;
				dfs(idx+1);
				visitedRow[j] = diagUp[(n-1) - idx+j] = diagDown[idx+j] = false;
			}
		}
	}
}
