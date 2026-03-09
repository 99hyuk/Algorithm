import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		p = new int[n+1];
		Arrays.fill(p, -1);
		
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		
		int[] truePeople = new int[k];
		for (int i=0; i<k; i++) {
			truePeople[i] = Integer.parseInt(st.nextToken());
		}
		
		List<List<Integer>> list = new ArrayList<>();
		for (int i=0; i<m; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int many = Integer.parseInt(st.nextToken());
			
			int u = Integer.parseInt(st.nextToken());
			list.get(i).add(u);
			for (int j=0; j<many-1; j++) {
				int v = Integer.parseInt(st.nextToken());
				list.get(i).add(v);
				union(u, v);
			}
		}
		
		int cnt = 0;
		for (List<Integer> list2 : list) {
			boolean check = true;
			for (int i=0; i<k; i++) {
				if (find(list2.get(0)) == find(truePeople[i])) {
					check = false;
					break;
				}
			}
			
			if(check) {
				cnt++;
			}
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
		
		if (u==v) return false;
		
		p[v] = u;
		return true;
	}
}
