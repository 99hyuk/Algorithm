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
		
		int n = Integer.parseInt(br.readLine());
		p = new int[1000001];
		Arrays.fill(p, -1);
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			
			if (cmd == 'I') {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				union(u, v);
				
			} else if (cmd == 'Q') {
				int u = Integer.parseInt(st.nextToken());
				sb.append(-p[find(u)]+"\n");
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
		
		if (u==v) return false;
		
		p[u] += p[v];
		p[v] = u;
		
		return true;
	}
}
