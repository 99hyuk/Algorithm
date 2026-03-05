import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] coin = new int[n];
		
		for (int i=0; i<n; i++) {
			coin[i] = sc.nextInt();
		}

		// 상태: 합   출력 : 경우의 수 (경우의 수를 메모이제이션)
		// 동전이 다 목표 합보다 크다면? 애초에 dp 범위를 k 최대로?
		
		int[] dp = new int[k+1];
		
		// 아무것도 안 뽑는 경우 (합계 0)
		dp[0] = 1;
		
		// 순열이 아닌 조합이기 때문에
		// 각각의 동전이 들어가는 경우를 먼저 누적
		// 자기 동전은 한 개 뽑고 누적
		
		for (int i=0; i<n; i++) {
			for (int j=coin[i]; j<=k; j++) {
				dp[j] += dp[j-coin[i]];
			}
		}
	
		System.out.println(dp[k]);
	}
}
