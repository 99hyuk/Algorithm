import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	static int[][] ladder;
	static int[] dc = {1,-1,0,0};
	static int[] dr = {0,0,1,1};
	static int cnt = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ladder = new int[H+1][N*2]; // 0번 인덱스 안 씀. 1부터 시작이다.
		
		for (int i=1; i<=H; i++) {
			for (int j=1; j<N*2; j+=2) {
				ladder[i][j] = 1;
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][(b*2)] = 1;
		}
		
//		for (int i=1; i<=H; i++) {
//			for (int j=1; j<N*2; j++) {
//				System.out.print(ladder[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		if (dfs(0,0,1,2)) {
			cnt = 0;
		} else if (dfs(0,1,1,2)) {
			cnt = 1;
		} else if (dfs(0,2,1,2)) {
			cnt = 2;
		} else if (dfs(0,3,1,2)) {
			cnt = 3;
		} else {
			cnt = -1;
		}
		
		System.out.println(cnt);
	}
	
	static boolean check(int r, int c, int init) {
		
		while (true) {
			if (r == H+1) {
				if (c == init) {
					return true;
				} else {
					return false;
				}
			}
			
			if (isIn(r,c+1) && ladder[r][c+1] == 1) {
				c = c+2;
				r++;
				continue;
			}
			
			if (isIn(r,c-1) && ladder[r][c-1] == 1) {
				c = c-2;
				r++;
				continue;
			}
			
			r++;
		}
	}
	
	static boolean checkAll() {
		for (int i=1; i<=2*N-1; i+=2) {
			if(!check(1, i, i)) {
				return false;
			}
		}
		return true;
	}
	
	static boolean dfs(int idx, int k, int startR, int startI) {
		if (idx==k) {
			if (checkAll()) {
				return true;
			} else {
				return false;
			}
		}
		
		for (int r=startR; r<=H; r++) {
			startI = r > startR ? startI=0 : startI;
			for (int c=startI; c<N*2; c+=2) {
				if(ladder[r][c] == 1) continue;
				ladder[r][c] = 1;
				if (dfs(idx+1, k, r, c+4)) return true;
				ladder[r][c] = 0;
			}
		}
		
		return false;
	}
	
	static boolean isIn(int r, int c) {
		return r>=1 && H+1 > r && c >= 1 && N*2 > c;
	}
}




//	static void test(int start) {
//		
//		// 조합 
//		int R;
//		int C;
//		
//		for (int i = start; i < R*C; i++) {
//			int r = i / R;
//			int c = i % R;
//		}
//		
//		
//		test(start + 1);
//	}