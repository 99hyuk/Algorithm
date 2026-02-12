import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class PointGam {
	int r,c, num;
	public PointGam(int r, int c, int num) {
		this.r = r;
		this.c = c;
		this.num = num;
	}
}

public class Main {
	
	static int N,M;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	
	static int[] dc = {1,0,-1,0};
	static int[] dr = {0,-1,0,1};
	static int[][] cctvDir = new int[][]{{},{0},{0,2},{0,1},{0,1,2},{0,1,2,3}};
	
	static int[] visited = new int[8];
	static List<PointGam> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) {
					list.add(new PointGam(i, j, map[i][j]));
				}
			}
		}
		
		dfs(0, list.size());
		
		System.out.println(min);

		
	}
	
	static void dfs(int idx, int size) {
		
		if (idx == size) {			
			int[][] copyMap = new int[N][M];
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					copyMap[i][j] = map[i][j];
				}
			}
			
			for (int i=0; i<size; i++) {
				PointGam p = list.get(i);
				for (int dir=0; dir<cctvDir[p.num].length; dir++) {
					fill(copyMap, (cctvDir[p.num][dir] + visited[i]) % 4, p.r, p.c);
				}
			}
			
			int cnt = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (copyMap[i][j] == 0) {
						cnt++;
					}
				}
			}
			
			min = Math.min(min, cnt);
			return;
		}
		
		for (int ro=0; ro<4; ro++) {
			visited[idx] = ro;
			dfs(idx+1, size);
		}
	}
	
	
	static void fill(int[][] map, int idx, int r, int c) {
		while (true) {
			r = r + dr[idx];
			c = c + dc[idx];
			if (!(0<=r && r<N && 0<=c && c<M)) break;
			if (map[r][c] == 6) break;
			if (map[r][c] == 0) map[r][c] = 10;
		}
	}
}