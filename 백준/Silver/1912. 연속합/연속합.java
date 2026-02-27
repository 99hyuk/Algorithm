import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[100001];
		int[] dp = new int[100001];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
		}

		int max = Integer.MIN_VALUE;
		for (int i=1; i<=n; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
