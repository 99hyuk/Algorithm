import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static List<List<Integer>> graphUp = new ArrayList<>();
	static List<List<Integer>> graphDown = new ArrayList<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			graphUp.add(new ArrayList<>());
			graphDown.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			graphUp.get(start).add(to);
			graphDown.get(to).add(start);
		}
		
		visited = new boolean[N];
		int knownStudent = 0;
		
		for (int i=0; i<N; i++) {
			int cnt = 0;
			dfsUp(i);
			for (int j=0; j<N; j++) {
				if (visited[j]) cnt++;
			}
			Arrays.fill(visited, false);
			dfsDown(i);
			for (int j=0; j<N; j++) {
				if (visited[j]) cnt++;
			}
			Arrays.fill(visited, false);
			
			if (cnt == N-1) {
				knownStudent++;
			}
		}
		
		System.out.println(knownStudent);
	}
	
	static void dfsUp(int idx) {
		
		if (graphUp.get(idx).size() == 0) {
			return;
		}
		
		for (int next : graphUp.get(idx)) {
            if(visited[next]) continue;
			dfsUp(next);
			visited[next] = true;
		}
	}
	
	static void dfsDown(int idx) {
		
		if (graphDown.get(idx).size() == 0) {
			return;
		}
		
		for (int next : graphDown.get(idx)) {
            if(visited[next]) continue;
			dfsDown(next);
			visited[next] = true;
		}
	}
}
