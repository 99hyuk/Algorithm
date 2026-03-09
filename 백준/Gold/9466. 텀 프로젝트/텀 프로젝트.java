import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, arr[];
	static boolean[] visited;
	static boolean[] finished;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n+1];
			visited = new boolean[n+1];
			finished = new boolean[n+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			cnt = 0;
			for (int i=1; i<=n; i++) {
				if(finished[i]) continue;
				dfs(i);
			}
			
			sb.append(n-cnt+"\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int now) {
		visited[now] = true;
		int next = arr[now];
		
		if(!visited[next]) {
			dfs(next);
		} else if (!finished[next]){
			cnt++;
			int temp = next;
			while (temp != now) { 
                cnt++;
                temp = arr[temp]; 
            }
		}
		
		finished[now] = true;
	}
}
