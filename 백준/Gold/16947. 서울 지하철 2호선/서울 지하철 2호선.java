import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static List<List<Integer>> graph;
	static StringBuilder sb = new StringBuilder();
	static boolean[] rotateList;
	static int[] parents;
	static boolean[] visited;
	static boolean isCycleFound = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(to);
			graph.get(to).add(start);
		}
		
		rotateList = new boolean[N+1];
		parents = new int[N+1];
		visited = new boolean[N+1];
		
		dfs(1, 0);
		bfs();
		
	}
	
	static void dfs(int idx, int prev) {
		if (isCycleFound) return;
		
		visited[idx] = true;
		
		for (int next : graph.get(idx)) {
			
			if (next==prev) continue;
			
			if (visited[next]) {
				isCycleFound = true;
				
				rotateList[idx] = true;
	            int temp = idx;
	            while(temp != next) {
	                temp = parents[temp];
	                rotateList[temp] = true;
	            }
	            rotateList[next] = true;
	            return;
	        }
			
			parents[next] = idx;
			dfs(next, idx);
			
			if (isCycleFound) return;

		}
	}
	
	static void bfs() {
		Queue<Integer> Q = new ArrayDeque<>();
		int[] distance = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		for (int i=1; i<=N; i++) {
			if(rotateList[i]) {
				Q.offer(i);
				distance[i] = 0;
				visited[i] = true;
			}
		}
		
		int cnt = 1;
		
		while(!Q.isEmpty()) {
			
			int size = Q.size();
			for (int i=0; i<size; i++) {
				int curIdx = Q.poll();
				
				for (int next : graph.get(curIdx)) {
					if(rotateList[next]) continue;
					if(visited[next]) continue;
					
					visited[next] = true;
					distance[next] = cnt;
					Q.offer(next);
				}
				
			}
			
			cnt++;
			
		}
		
		for (int i=1; i<=N; i++) {
			sb.append(distance[i] + " ");
		}
		System.out.println(sb);
	}
}
