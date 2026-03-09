import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static List<List<Integer>> graph;
	static int[] indegree;
	static int[] workList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		indegree = new int[N+1];
		workList = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			workList[i] = Integer.parseInt(st.nextToken());
			
			int recursive = Integer.parseInt(st.nextToken());
			
			for (int j=0; j<recursive; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph.get(to).add(i);
				indegree[i]++;
			}
		}
		
		topological();
	}
	
	static void topological() {
		Queue<Integer> Q = new ArrayDeque<>();
		int[] result = new int[N+1];
		for (int i=1; i<=N; i++) {
			if (indegree[i] == 0) {
				Q.offer(i);
				result[i] = workList[i];
			}
		}
		
		while(!Q.isEmpty()) {
			int curIdx = Q.poll();
			
			for (int next : graph.get(curIdx)) {
				result[next] = Math.max(result[next], result[curIdx]);
				indegree[next]--;
				if(indegree[next] == 0) {
					result[next] += workList[next];
					Q.offer(next);
				}
			}
		}
		
		int ans = 0;
		for (int i=1; i<=N; i++) {
			ans = Math.max(ans, result[i]);
		}
		
		System.out.println(ans);
	}
}
