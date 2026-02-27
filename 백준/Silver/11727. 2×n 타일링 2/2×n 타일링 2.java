import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] dp = new int[1001];
		
		dp[1] = 1;
		dp[2] = 3; //dp[3] = 5 dp[4] = 1+3+3+2=9
		
		for (int i=3; i<=1000; i++) {
			dp[i] = (dp[i-2] * 2 + dp[i-1]) % 10007;
		}
		
		System.out.println(dp[n]);
	}
}
