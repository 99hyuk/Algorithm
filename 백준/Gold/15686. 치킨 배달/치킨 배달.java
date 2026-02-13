import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Home {
		int r; int c;
		Home (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Chicken {
		int r; int c;
		Chicken (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N,M;
	static int minDistance = Integer.MAX_VALUE;
	static int[][] map;
	static List<Home> homeList;
	static List<Chicken> chList;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		homeList = new ArrayList<>();
		chList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					homeList.add(new Home(i, j));
				}
				if (map[i][j] == 2) {
					chList.add(new Chicken(i, j));
				}
			}
		}
		
		visited = new boolean[chList.size()];
		dfs(0,0);
		System.out.println(minDistance);
	}
	
	static void calculate() {
		ArrayList<Chicken> selected = new ArrayList<>();
		for (int i=0; i<chList.size(); i++) {
			if (visited[i]) {
				selected.add(chList.get(i));
			}
		}
		
		int sumD = 0;
		for (Home home : homeList) {
			int minD = Integer.MAX_VALUE;
			for (Chicken chicken : selected) {
				int d = Math.abs(chicken.r - home.r) + Math.abs(chicken.c - home.c);
				minD = Math.min(minD, d);
			}
			sumD += minD;
		}
		
		minDistance = Math.min(minDistance, sumD);
	}
	
	static void dfs(int idx, int start) {
		if (idx == M) {
			calculate();
			return;
		}
		
		for (int i=start; i<chList.size(); i++) {
			visited[i] = true;
			dfs(idx+1, i+1);
			visited[i] = false;
		}
	}
}
