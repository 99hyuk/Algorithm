import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] dp = new int[n+1];
		boolean[] vip = new boolean[n+1];
		
		for (int i=0; i<m; i++) {
			vip[sc.nextInt()] = true;
		}
		
		int sum = 1;		
		dp[0] = 1;

		for (int i=1; i<=n; i++) {
			if (i == 1 && !vip[i]) {
				dp[i] = 1;
				continue;
			}
			
			if(vip[i]) {
				sum *= dp[i-1];
				dp[i] = 1;
				continue;
			}
			
			if(vip[i-1]) {
				dp[i] = 1;
				continue;
			}
			
			dp[i] = dp[i-1] + dp[i-2];
			
			if (i==n) {
				sum *= dp[i];
			}
		}
		
		System.out.println(sum);
	}
}
