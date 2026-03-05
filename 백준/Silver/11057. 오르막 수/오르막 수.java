import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		// 자리 수(상태, 인자) -> 오르막 개수 (출력)
		// 자리 수 및 마지막 숫자를 상태로. (마지막을 알아야 다음 거 정할 수 있음)
		
		int[][] dp = new int[n][10];
		
		for (int i=0; i<10; i++) {
			dp[0][i] = 1;
		}
		
		for (int i=1; i<n; i++) {
			for (int j=0; j<10; j++) {
				for (int k=0; k<=j; k++) {
					dp[i][j] += dp[i-1][k] % 10007;
				}
			}
		}
		
		int sum = 0;
		for (int i=0; i<10; i++) {
			sum += dp[n-1][i] % 10007;
		}
		
		System.out.println(sum % 10007);
	}
}
