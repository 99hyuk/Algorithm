import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] time = new int[n+1];
		int[] cost = new int[n+1];
		int[] dp = new int[n+1];
		
		for (int i=1; i<=n; i++) {
			time[i] = sc.nextInt();
			cost[i] = sc.nextInt();
			if(i+time[i] <= n+1) {
				dp[i] = cost[i];
			}
		}
		
		for (int i=1; i<=n; i++) {
			for (int j=1; j<i; j++) {
				if(j + time[j] -1 < i && i+time[i] <= n+1) {
					dp[i] = Math.max(dp[j] + cost[i], dp[i]);
				}
			}
		}
		
		int max = dp[0];
		for (int i=1; i<=n; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
