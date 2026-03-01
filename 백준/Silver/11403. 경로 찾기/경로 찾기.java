import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] graph;
	static boolean[] visited;
	static int[][] finalMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		
		StringTokenizer st;
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[n];
		finalMap = new int[n][n];
		
		for (int i=0; i<n; i++) {
			bfs(i);
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.print(finalMap[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void bfs(int idx) {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(idx);
		
		boolean[] selected = new boolean[n];
		
		while(!Q.isEmpty()) {
			int curIdx = Q.poll();
			
			for(int i=0; i<n; i++) {
				if (graph[curIdx][i] == 1 && !selected[i]) {
					selected[i] = true;
					Q.offer(i);
				}
			}
		}
		
		for (int i=0; i<n; i++) {
			if (selected[i]) {
				finalMap[idx][i] = 1;
			}
		}
	}
}
