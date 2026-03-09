import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		p = new int[n+1];
		Arrays.fill(p, -1);
		
		StringTokenizer st;
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=n; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					union(i,j);
				}
			}
		}
		
		int temp = -1;
		boolean poss = true;
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=m; i++) {
			int travel = Integer.parseInt(st.nextToken());
			
			if(i==1) {
				temp = find(travel);
				continue;
			}
			
			if (find(travel) != temp) {
				System.out.println("NO");
				poss = false;
				break;
			}
		}
		
		if (poss) System.out.println("YES");
		
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
		
		p[v] = u;
		return true;
	}
}
