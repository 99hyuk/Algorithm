import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] indegree;
	static List<List<Integer>> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		indegree = new int[N+1];
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(start).add(to);
			indegree[to]++;
		}
		
		topologicalSort();
	}
	
	static void topologicalSort() {
		PriorityQueue<Integer> PQ = new PriorityQueue<>();
		
		for (int i=1; i<=N; i++) {
			if (indegree[i] == 0) {
				PQ.offer(i);
			}
		}
		
		List<Integer> result = new ArrayList<>();
		
		while(!PQ.isEmpty()) {
			int curIdx = PQ.poll();
			result.add(curIdx);
			
			for (int next : graph.get(curIdx)) {
				indegree[next]--;
				if(indegree[next] == 0) {
					PQ.offer(next);
				}
			}
		}
		
		for (Integer integer : result) {
			System.out.print(integer + " ");
		}
	}
}
