import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] time = new int[n+1];
		int[] cost = new int[n+1];
		int[] dp = new int[n+2];
		
		StringTokenizer st;
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st.nextToken());
			
			if (i + time[i] <= n+1) {
				dp[i + time[i]] = cost[i];
			}
		}

		int max = 0; 
        for (int i = 1; i <= n+1; i++) {
            
        	max = Math.max(max, dp[i]);
        	
        	if (i > n) break;
        	
            int nextDay = i + time[i];
            if (nextDay-1 <= n) {
                dp[nextDay] = Math.max(dp[nextDay], max + cost[i]);
            }
            
        }
        
        System.out.println(max);
	}
}
