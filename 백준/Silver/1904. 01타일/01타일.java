import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] dp = new int[n+1];
		dp[1] = 1;
		
		for (int i=2; i<=n; i++) {
			if (i==2) {
				dp[2] = 2;
				continue;
			}
			
			dp[i] = (dp[i-2] + dp[i-1]) % 15746;
		}
		
		System.out.println(dp[n]);
	}
}
