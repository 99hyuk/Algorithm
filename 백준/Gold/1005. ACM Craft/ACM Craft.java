import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static List<List<Integer>> graph;
	static int[] indegree;
	static int[] buildTime;
	static int W;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			indegree = new int[N+1];
			buildTime = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i=0; i<=N; i++) {
				graph.add(new ArrayList<>());
			}
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph.get(start).add(to);
				indegree[to]++;
			}
			
			W = Integer.parseInt(br.readLine());
			
			topologicalSort();
		}
		
		System.out.println(sb);
	}
	
	static void topologicalSort() {
		Queue<Integer> Q = new ArrayDeque<>();
		int[] totalTime = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			if (indegree[i] == 0) {
				Q.offer(i);
				totalTime[i] = buildTime[i];
			}
		}
		
		
		while(!Q.isEmpty()) {
			int curIdx = Q.poll();
			
			
			for (int next : graph.get(curIdx)) {
				if (totalTime[next] < totalTime[curIdx] + buildTime[next]) {
					totalTime[next] = totalTime[curIdx] + buildTime[next];
				}
				indegree[next]--;
				if (indegree[next] == 0) {
					Q.offer(next);
				}
			}
		}
		
		sb.append(totalTime[W] + "\n");
	}
}
