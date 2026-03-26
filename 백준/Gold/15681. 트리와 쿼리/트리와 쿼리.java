import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, R, Q;
	static List<List<Integer>> graph;
	static List<List<Integer>> tree;
	static int[] parents;
	static int[] countSubtree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		tree = new ArrayList<>();
		parents = new int[N+1];
		countSubtree = new int[N+1];
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
			tree.add(new ArrayList<>());
		}
		
		for (int i=0; i<=N-2; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		makeTree(R, -1);
		countSubtreeNodes(R);
		
		for (int i=0; i<Q; i++) {
			int q = Integer.parseInt(br.readLine());
			sb.append(countSubtree[q]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void makeTree(int curNode, int parent) {
		
		for (int node : graph.get(curNode)) {
			if (node == parent) continue;
			tree.get(curNode).add(node);
			parents[node] = curNode;
			makeTree(node, curNode);
		}
	}
	
	static void countSubtreeNodes(int curNode) {
		countSubtree[curNode]++;
		
		for (int node : tree.get(curNode)) {
			countSubtreeNodes(node);
			countSubtree[curNode] += countSubtree[node];
		}
	}
}
