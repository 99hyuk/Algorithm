import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			if (K == 0) {
				break;
			} 
			
			arr = new int[K];
			for (int i=0; i<K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new boolean[K];
			
			sb = new StringBuilder();
			dfs(0,0,K);
			System.out.println(sb);
			
		}
	}
	
	static void dfs(int idx, int start, int K) {
		if (idx==6) {
			for (int i=0; i<K; i++) {
				if (visited[i]) {
					sb.append(arr[i] + " ");
				}
			}
			sb.append("\n");
			return;
		}
		
		for (int i=start; i<K ; i++) {
			visited[i] = true;
			dfs(idx+1, i+1, K);
			visited[i] = false;
		}
	}
}
