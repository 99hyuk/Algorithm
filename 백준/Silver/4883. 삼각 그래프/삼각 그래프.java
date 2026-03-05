import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int idx = 1;
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n==0) {
				break;
			}
			
			int[][] graph = new int[n][3];
			
			StringTokenizer st;
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int a0 = Integer.parseInt(st.nextToken());
				int a1 = Integer.parseInt(st.nextToken());
				int a2 = Integer.parseInt(st.nextToken());
				graph[i][0] = a0;
				graph[i][1] = a1;
				graph[i][2] = a2;
			}
			
			int[][] dp = new int[n][3];
			int[] arr1 = new int[4];
			int[] arr2 = new int[3];
			
			dp[0][1] = graph[0][1];
			dp[0][2] = graph[0][1] + graph[0][2];
			dp[1][0] = graph[0][1] + graph[1][0];

			arr1[0] = Integer.MAX_VALUE;
			arr1[1] = dp[0][1];
			arr1[2] = dp[0][2];
			arr1[3] = dp[1][0];
			Arrays.sort(arr1);
			dp[1][1] = arr1[0] + graph[1][1];
			
			arr2[0] = dp[0][1];
			arr2[1] = dp[0][2];
			arr2[2] = dp[1][1];
			Arrays.sort(arr2);
			dp[1][2] = arr2[0] + graph[1][2];
			
			for (int i=2; i<n; i++) {
				dp[i][0] += Math.min(dp[i-1][0], dp[i-1][1]) + graph[i][0];
				
				arr1[0] = dp[i-1][0];
				arr1[1] = dp[i-1][1];
				arr1[2] = dp[i-1][2];
				arr1[3] = dp[i][0];
				Arrays.sort(arr1);
				dp[i][1] += arr1[0] + graph[i][1];
				
				arr2[0] = dp[i-1][1];
				arr2[1] = dp[i-1][2];
				arr2[2] = dp[i][1];
				Arrays.sort(arr2);
				dp[i][2] += arr2[0] + graph[i][2];
			}
			
			sb.append(idx + ". " + dp[n-1][1] + "\n");
			idx++;
		}
		
		System.out.println(sb);
	}
}
