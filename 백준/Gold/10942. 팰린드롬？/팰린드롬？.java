import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		boolean[][] dp = new boolean[N+2][N+2];
		for (int i=1; i<=N; i++) {
			dp[i][i] = true;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] == arr[i-1]) {
				dp[i-1][i] = true;
			}
		}
		
		for (int dist=2; dist<=N-1; dist++) {
			for (int i=1, j=i+dist; i<=N && j<=N; i++, j++) {
				dp[i][j] = arr[i] == arr[j] && dp[i+1][j-1];
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			sb.append(dp[S][E] ? 1 : 0).append("\n");
		}
		
		System.out.println(sb);
	}
}
