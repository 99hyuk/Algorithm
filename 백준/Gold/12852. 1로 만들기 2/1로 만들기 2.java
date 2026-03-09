import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] dp = new int[1000001];
		int[] pre = new int[1000001];
		
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		pre[2] = 1;
		pre[3] = 1;
		
		for (int i=4; i<=n; i++) {
			dp[i] = dp[i-1] + 1;
			pre[i] = i - 1;
			
			if (i % 2 == 0) {
				if (dp[i] > dp[i/2]) {
					dp[i] = dp[i/2] + 1;
					pre[i] = i/2;
				}
			}
				
			if (i % 3 == 0) {
				if (dp[i] > dp[i/3]) {
					dp[i] = dp[i/3] + 1;
					pre[i] = i/3;
				}
			}
		}
		
		System.out.println(dp[n]);
		
		while (n != 1) {
			System.out.print(n + " ");
			n = pre[n];
		}
		
		System.out.println(1);
	}
}
