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
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n+1];
			indegree = new int[n+1];
			visited = new boolean[n+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				indegree[arr[i]]++;
			}

			topological();
			
			int cnt = 0;
			for (int i=1; i<=n; i++) {
				if(visited[i]) cnt++;
			}
			sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int num) {
		
	}
	
	static void topological() {
		Queue<Integer> Q = new ArrayDeque<>();
		for (int i=1; i<=n; i++) {
			if(indegree[i] == 0) {
				Q.offer(i);
			}
		}
		
		while(!Q.isEmpty()) {
			int curIdx = Q.poll();
			visited[curIdx] = true;
			
			indegree[arr[curIdx]]--;
			if(indegree[arr[curIdx]] == 0) {
				Q.offer(arr[curIdx]);
			}
		}
	}
}
