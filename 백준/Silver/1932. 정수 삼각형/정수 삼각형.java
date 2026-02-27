import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[][] triangle = new int[501][501];
		
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=i; j++) {
				triangle[i][j] = sc.nextInt();
			}
		}
		
		int[][] dp = new int[501][501];
		
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=i; j++) {
				dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
			}
		}
		
		int max = 0;
		for (int i=1; i<=n; i++) {
			max = Math.max(max, dp[n][i]);
		}
		
		System.out.println(max);
	}
}
