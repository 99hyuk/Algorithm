import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class NodeClub implements Comparable<NodeClub> {
	int x;
	int y;
	int id;
	public NodeClub(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(NodeClub o) {
		return Integer.compare(this.x, o.x);
	}
}

public class Main {
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		p = new int[n+1];
		Arrays.fill(p, -1);
		
		NodeClub[] clubs = new NodeClub[m];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			clubs[i] = new NodeClub(x, y);
		}
		
		Arrays.sort(clubs);
		
		int maxY = -1;
		for (int i=0; i<m; i++) {
			int x = clubs[i].x;
			int y = clubs[i].y;
			
			if (i==0) {
				maxY = clubs[0].y;
				for (int j=x; j<y; j++) {
					union(j,j+1);
				}
				continue;
			}
			
			if (x <= maxY) {
				for (int j=maxY; j<y; j++) {
					union(j,j+1);
				}
				maxY = Math.max(maxY, y);
			} else {
				for (int j=x; j<y; j++) {
					union(j,j+1);
				}
				maxY = y;
			}
		}

		int cnt = 0;
		for (int i=1; i<=n; i++) {
			if(p[i] == -1) cnt++;
		}
		
		System.out.println(cnt);
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
		
		if(u==v) return false;
		
		p[v] = u;
		return true;
	}
}
