import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[][] dp = new int[n][10];
		
		for (int i=1; i<10; i++) {
			dp[0][i] = 1;
		}
		
		for (int i=1; i<n; i++) {
			for (int j=1; j<9; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1_000_000_000;
			}
			
			dp[i][9] = dp[i-1][8] % 1_000_000_000;
			dp[i][0] = dp[i-1][1] % 1_000_000_000;
		}
		
		int sum = 0;
		for (int i=0; i<10; i++) {
			sum = (sum + dp[n-1][i]) % 1_000_000_000;
		}
		
		System.out.println(sum);
	}
}
