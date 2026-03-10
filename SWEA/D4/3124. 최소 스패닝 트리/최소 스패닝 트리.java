import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int u;
	int v;
	int weight;
	Edge(int u, int v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
}

public class Solution {
	
	static int p[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			p = new int[V+1];
			Arrays.fill(p, -1);
			List<Edge> list = new ArrayList<>();
			
			for (int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				list.add(new Edge(start, to, weight));
			}
			
			Collections.sort(list);
			
			int totalCnt = 0;
			long totalWeight = 0;
			for (Edge edge : list) {
				if (union(edge.u, edge.v)) {
					totalCnt++;
					totalWeight += edge.weight;
				}
				
				if(totalCnt == V-1) {
					sb.append("#" + t + " " + totalWeight + "\n");
					break;
				}
			}
		}
		
		System.out.println(sb);
	}
	
	static int find(int x) {
		if (p[x] < 0) {
			return x;
		}
		return p[x] = find(p[x]);
	}
	
	static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u == v) return false;
		
		p[v] = u;
		return true;
	}
}
