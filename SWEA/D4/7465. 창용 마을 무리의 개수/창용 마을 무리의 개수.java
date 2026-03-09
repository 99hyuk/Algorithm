import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			p = new int[n+1];
			Arrays.fill(p, -1);
			
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				union(u, v);
			}
			
			int cnt = 0;
			for (int i=1; i<=n; i++) {
				if(p[i] == -1) {
					cnt++;
				}
			}
			
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}
	
	static int find(int x) {
		if(p[x] < 0) {
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
