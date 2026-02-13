import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static boolean[] arr;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		arr = new boolean[N];
		arr[0] = true;
		dfs(1,1);
		
		System.out.println(min);
	}
	
	static void dfs(int idx, int start) {
		if (idx == N/2) {
			int score1 = 0;
			int score2 = 0;
			
			for (int i=0; i<N; i++) {
				if (arr[i]) {
					for (int r=0; r<N; r++) {
						if (arr[r]) {
							score1 += map[i][r];
						}
					}
				} else {
					for (int r=0; r<N; r++) {
						if (!arr[r]) {
							score2 += map[i][r];
						}
					}
				}
			}
			
			int minus = Math.abs(score1 - score2);
            min = Math.min(min, minus);
			
            
            
			return;
		}
		
		for (int i=start; i<N; i++) {
            if(arr[i]) continue;
			arr[i] = true;
			dfs(idx+1, i+1);
			arr[i] = false;
		}
	}
}
