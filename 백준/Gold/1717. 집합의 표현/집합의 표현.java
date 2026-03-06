import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		p = new int[n+1];
		Arrays.fill(p, -1);
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int input = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if(input == 0) {
				union(u, v);
			} else {
				if (find(u) != find(v)) {
					sb.append("NO\n");
				} else {
					sb.append("YES\n");
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
		
		if (p[u] < p[v]) {
			p[v] = u;
		} else if (p[u] > p[v]){
			p[u] = v;
		} else {
			p[v] = u;
			p[u]--;
		}
		
		return true;
	}
}
