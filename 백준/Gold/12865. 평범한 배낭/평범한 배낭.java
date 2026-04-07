import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] bag = new int[n+1][2];
		
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			bag[i][0] = W;
			bag[i][1] = V;
		}
		
		// 최대 무게 이내 최대 가치
		// 현재 무게, 포함된 물건의 최대 인덱스 => 최대 가치합
		// dp[i][j] = dp[i-1][j-bag[i][0]] + bag[i][1], dp[i-1][j]
		int[][] dp = new int[n+1][k+1];
		
		for (int i=1; i<=n; i++) {
			
			if (bag[i][0] <= k) {
				dp[i][bag[i][0]] = Math.max(dp[i][bag[i][0]], bag[i][1]);
			}
			
			for (int j=0; j<=k; j++) {
				if (j-bag[i][0] >= 0) {
					dp[i][j] = Math.max(dp[i-1][j-bag[i][0]] + bag[i][1], dp[i-1][j]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[n][k]);
	}
}
