import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			int n = Integer.parseInt(br.readLine());
			int k = Integer.parseInt(br.readLine());
			
			p = new int[n];
			Arrays.fill(p, -1);
			
			for (int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			
			sb.append("Scenario " + t + ":\n");
			
			int m = Integer.parseInt(br.readLine());
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if (find(u) == find(v)) {
					sb.append(1 + "\n");
				} else {
					sb.append(0 + "\n");
				}
			}
			
			sb.append("\n");
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
		
		if(p[u] < p[v]) {
			p[v] = u;
		} else if(p[u] > p[v]) {
			p[u] = v;
		} else {
			p[v] = u;
			p[u]--;
		}
		
		return true;
	}
}
