import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] tree = new int[T+1];
		
		for (int i=1; i<=T; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] dp = new int[T+1][W+1];
		
		if (tree[1]%2 == 1) {
			dp[1][0] = 1;
			dp[1][1] = 0;
		} else {
			dp[1][0] = 0;
			dp[1][1] = 1;
		}
		
		for (int t=2; t<=T; t++) {
			for (int w=0; w<=W; w++) {
				if ((w+1)%2 == tree[t]%2) {
					dp[t][w] = Math.max(dp[t-1][w] + 1, dp[t][w]);
				} else {
					dp[t][w] = Math.max(dp[t-1][w], dp[t][w]);
					if (w < W) {
						dp[t][w+1] = Math.max(dp[t-1][w] + 1, dp[t][w+1]);
					}
				}
			}
		}
		
		int max = 0;
		for (int w=0; w<=W; w++) {
			max = Math.max(max, dp[T][w]);
		}
		
		System.out.println(max);
	}
}
