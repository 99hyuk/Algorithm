import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			int[] price = new int[4];
			st = new StringTokenizer(br.readLine());
			
			for (int i=0; i<4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] calander = new int[13];
			
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=12; i++) {
				calander[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dp = new int[13];
			
			for (int i=1; i<=12; i++) {
				int monthPrice = calander[i] * price[0];
				dp[i] = dp[i-1] + monthPrice;
				
				if (dp[i-1] + price[1] < dp[i]) {
					dp[i] = dp[i-1] + price[1];
				}
				
				if (3<=i && dp[i-3] + price[2] < dp[i]) {
					dp[i] = dp[i-3] + price[2];
				}
			}
			
			if (price[3] < dp[12]) {
				dp[12] = price[3];
			}
			
			sb.append("#").append(t).append(" ").append(dp[12]).append("\n");
		}
		System.out.println(sb);
	}
}
