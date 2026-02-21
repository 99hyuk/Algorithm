import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static Map<String, Integer> map;
	static String[] names;
	static List<List<Integer>> graph;
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		names = new String[N];
		graph = new ArrayList<>();
		indegree = new int[N];
		map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			names[i] = st.nextToken();
		}
		Arrays.sort(names);
		
		for (int i=0; i<N; i++) {
			map.put(names[i], i);
			graph.add(new ArrayList<>());
		}
		
		M = Integer.parseInt(br.readLine());
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String start = st.nextToken();
			String to = st.nextToken();
			
			graph.get(map.get(to)).add(map.get(start));
			indegree[map.get(start)]++;
		}
		
		topologicalSort();
		
	}
	
	static void topologicalSort() {
		Queue<Integer> Q = new ArrayDeque<>();
		List<Integer> parents = new ArrayList<>();
		for (int i=0; i<N; i++) {
			if(indegree[i] == 0) {
				Q.offer(i);
				parents.add(i);
			}
		}
		
		List<Integer> visitedList = new ArrayList<>();
		List<List<Integer>> children = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			children.add(new ArrayList<>());
		}
		
		while(!Q.isEmpty()) {
			int curIdx = Q.poll();
			visitedList.add(curIdx);
			
			for (int next : graph.get(curIdx)) {
				indegree[next]--;
				if (indegree[next] == 0) {
					Q.offer(next);
					children.get(curIdx).add(next);
				}
			}
		}
		
		sb.append(parents.size() + "\n");
		for (int i=0; i<parents.size(); i++) {
			sb.append(names[parents.get(i)] + " ");
		}
		
		sb.append("\n");
		
		for (int i=0; i<children.size(); i++) {
			sb.append(names[i] + " " + children.get(map.get(names[i])).size() + " ");
			for (int j=0; j<children.get(map.get(names[i])).size(); j++) {
				Collections.sort(children.get(map.get(names[i])));
				sb.append(names[children.get(map.get(names[i])).get(j)] + " ");
			}
			sb.append("\n");
		}
	
		System.out.println(sb);
	}
}
