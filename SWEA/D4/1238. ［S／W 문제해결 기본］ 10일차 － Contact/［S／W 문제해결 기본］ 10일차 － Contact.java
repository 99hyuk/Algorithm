import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int start;
	static int max;
	static List<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case=1; test_case<=10; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			max = start;
			
			list = new ArrayList[101];
			for (int i=0; i<101; i++) {
				list[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			
			for (int i=0; i<N/2; i++) {
				list[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}
			
			bfs(start);
			
			System.out.println("#" + test_case + " " + max);
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> Q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[101];
		
		Q.offer(start);
		visited[start] = true;
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			max = 0;
			for (int i=0; i<size; i++) {
				int p = Q.poll();
				max = Math.max(p,max);
				
				for (Integer n : list[p]) {
					if (visited[n]) continue;
					Q.offer(n);
					visited[n] = true;
				
				}
			}
		}
	}
}
