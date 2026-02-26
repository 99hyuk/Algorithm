import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int idx;
	int cost;
}

public class Main {
	static int N, M;
	static List<List<Integer>> graph;
	static int[] indegree;
	static int[][] Idt;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		indegree = new int[N+1];
		Idt = new int[N+1][N+1];
		
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			graph.get(X).add(Y);
			indegree[Y]++;
			Idt[X][Y] = K;
		}
		
		topologicalSort();
	}
	
	static void topologicalSort() {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(N);
		
		boolean[] basic = new boolean[N+1];
		int[] result = new int[N+1];
		result[N] = 1;
		
		while(!Q.isEmpty()) {
			int curIdx = Q.poll();
			
			if(graph.get(curIdx).size() == 0) {
				basic[curIdx] = true;
			}
			
			for (int next : graph.get(curIdx)) {
				indegree[next]--;
				result[next] += Idt[curIdx][next]*result[curIdx];
				
				if(indegree[next] == 0) {
					Q.offer(next);
				}
			}
		}
		
		for (int i=0; i<=N; i++) {
			if(basic[i]) {
				sb.append(i + " " + result[i] + "\n");
			}
		}
		System.out.println(sb);
	}
}
