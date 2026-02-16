import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] population;
	static int totalHuman = 0;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalHuman += population[i];
		}
		
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			for (int j=0; j<count; j++) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				graph.get(i).add(to);
				graph.get(to).add(i);
			}
		}
		
		visited = new boolean[N];
		
		dfs(0);
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void dfs(int idx) {
		
		if (idx == N) {
			List<Integer> A = new ArrayList<>();
			List<Integer> B = new ArrayList<>();
			int sum1 = 0;
			int sum2 = 0;
			for (int i=0; i<N; i++) {
				if (visited[i]) {
					A.add(i);
					sum1 += population[i];
				} else {
					B.add(i);
					sum2 += population[i];
				}
			}
			
			if (A.size() == 0 || B.size() == 0) return;
			
			boolean[] visitedA = new boolean[N];
			boolean[] visitedB = new boolean[N];
//			visitedA[A.get(0)] = true;
//			visitedA[B.get(0)] = true;
			
			check(A.get(0), visitedA, A);
			check(B.get(0), visitedB, B);
			
			for (int i=0; i<N; i++) {
				if(A.contains(i)) {
					if (!visitedA[i]) {
						return;
					}
				}
				
				if(B.contains(i)) {
					if (!visitedB[i]) {
						return;
					}
				}
			}
			
			min = Math.min(min, Math.abs((sum2 - sum1)));
			
			return;
		}
		
		visited[idx] = true;
		dfs(idx+1);
		visited[idx] = false;
		dfs(idx+1);
	}

	static void check(int start, boolean[] visited, List<Integer> list) {
		visited[start] = true;

		for (int next : graph.get(start)) {
			if (visited[next]) continue;
			if (list.contains(next)) {
				check(next, visited, list);
			}
		}
	}
}
