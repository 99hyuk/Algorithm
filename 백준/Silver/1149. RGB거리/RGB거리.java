import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] costR = new int[1001];
		int[] costG = new int[1001];
		int[] costB = new int[1001];
		int[][] dp = new int[1001][3];
		
		for (int i=1; i<=n; i++) {
			costR[i] = sc.nextInt();
			costG[i] = sc.nextInt();
			costB[i] = sc.nextInt();
		}
		
		dp[1][0] = costR[1];
		dp[1][1] = costG[1];
		dp[1][2] = costB[1];
		
		for (int i=2; i<=n; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costR[i];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costG[i];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costB[i];
		}
		
		int min;
		
		if (dp[n][0] <= dp[n][1] && dp[n][0] <= dp[n][2]) {
			min = dp[n][0];
		} else if (dp[n][1] <= dp[n][0] && dp[n][1] <= dp[n][2]) {
			min = dp[n][1];
		} else {
			min = dp[n][2];
		}
		
		System.out.println(min);
	}
}
