import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int[] arr0 = new int[n];
			int[] arr1 = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<n; i++) {
				arr0[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<n; i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
			}
			
			int[][] dp = new int[n][2];
			
			dp[0][0] = arr0[0];
			dp[0][1] = arr1[0];
			
			for (int i=1; i<n; i++) {
				if (i == 1) {
					dp[1][0] = arr0[1] + arr1[0];
					dp[1][1] = arr1[1] + arr0[0];
					continue;
				}
				dp[i][0] = Math.max(Math.max(dp[i-2][1], dp[i-1][1]) + arr0[i], dp[i][0]);
				dp[i][1] = Math.max(Math.max(dp[i-2][0], dp[i-1][0]) + arr1[i], dp[i][1]);
			}
			
			System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));
		}
	}
}
