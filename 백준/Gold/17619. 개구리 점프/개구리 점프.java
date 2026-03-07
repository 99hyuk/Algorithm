import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree>{
	int x1;
	int x2;
	int id;
	Tree(int x1, int x2, int id) {
		this.x1 = x1;
		this.x2 = x2;
		this.id = id;
	}
	
	@Override
	public int compareTo(Tree o) {
		return Integer.compare(this.x1, o.x1);
	}
}

public class Main {
	
	static int[] p;
	static int max2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		p = new int[n+1];
		Arrays.fill(p, -1);
		
		Tree[] trees = new Tree[n];
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			trees[i-1] = new Tree(x1, x2 , i);
		}
		
		Arrays.sort(trees);
		
		max2 = trees[0].x2;
		for (int i=1; i<=n-1; i++) {
			if (trees[i].x1 <= max2) {
				union(trees[i].id, trees[i-1].id);
				max2 = Math.max(max2, trees[i].x2);
			} else {
				max2 = trees[i].x2;
			}
		}
		
		for (int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int tree1 = Integer.parseInt(st.nextToken());
			int tree2 = Integer.parseInt(st.nextToken());
			
			if (find(tree1) == find(tree2)) {
				sb.append(1 + "\n");
			} else {
				sb.append(0 + "\n");
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
		
		if (u == v) return false;
		
		p[v] = u;
		return true;
	}
}
