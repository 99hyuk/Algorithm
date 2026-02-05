import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[][] arr;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		arr = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		for (int i=1; i<=m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[r][c] = 1;
			arr[c][r] = 1;
		}
		
		visited[1] = true;
		dfs(1);
		
		int cnt=0;
		for (int i=2; i<=n; i++) {
			if (visited[i]) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	static void dfs(int pos) {
		
		
		for (int idx=2; idx<=n; idx++) {
			if (arr[pos][idx] == 1 && !visited[idx]) {
				visited[idx] = true;
				dfs(idx);
			}
		}
	}
}
