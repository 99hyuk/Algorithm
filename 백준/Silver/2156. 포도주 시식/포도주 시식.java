import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] dp = new int[n+1];
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			dp[i] = arr[i];
		}
		
		for (int i=1; i<n; i++) {
			if(i == 1) {
				dp[1] += dp[0];
			} else if(i == 2) {
				dp[2] = Math.max(dp[1], Math.max(arr[1] + arr[2], arr[0] + arr[2]));
			} else {
				dp[i] = Math.max(dp[i-1], Math.max(dp[i-3] + arr[i-1] + arr[i], dp[i-2] + arr[i]));
			}
		}
		
		if (n==1) {
			System.out.println(dp[0]);
		} else if(n==2) {
			System.out.println(dp[1]);
		} else {
			System.out.println(dp[n-1]);
		}
	}
}