import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class PointR {
		int r,c;
		PointR(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class PointR2 {
		int r,c,color,width;
		PointR2(int r, int c, int color, int width) {
			this.r = r;
			this.c = c;
			this.color = color;
			this.width = width;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] colorMap;
	static int roomCnt = 0;
	static int maxWidth = Integer.MIN_VALUE;
	static List<PointR2> list;
	static int maxWidthWithBroken = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		colorMap = new int[N][M];
		list = new ArrayList<>();
		int color = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(colorMap[i][j] < color && colorMap[i][j] != 0) continue;
				bfs1(i,j,color++); roomCnt++;
			}
		}
		
		for (PointR2 p : list) {
			bfs2(p.r, p.c, p.color);
		}
		
		System.out.println(roomCnt);
		System.out.println(maxWidth);
		System.out.println(maxWidthWithBroken);

	}
	
	static void bfs1(int r, int c, int color) {
		Queue<PointR> Q = new ArrayDeque<>();
		Q.offer(new PointR(r, c));
		colorMap[r][c] = color;
		int width = 0;
		
		while(!Q.isEmpty()) {
			PointR p = Q.poll();
			width++;
			
			for (int ro=0; ro<4; ro++) {
				int nr = p.r + dr[ro];
				int nc = p.c + dc[ro];
				
				if (nr < 0 || N <= nr || nc < 0 || M <= nc) continue;
				if (colorMap[nr][nc] == color) continue;
				if (ro==0 && (map[p.r][p.c] & 1<<1) != 0) continue;
				if (ro==1 && (map[p.r][p.c] & 1<<2) != 0) continue;
				if (ro==2 && (map[p.r][p.c] & 1<<3) != 0) continue;
				if (ro==3 && (map[p.r][p.c] & 1) != 0) continue;
				
				Q.offer(new PointR(nr, nc));
				colorMap[nr][nc] = color;
			}
		}
		list.add(new PointR2(r, c, color, width));
		maxWidth = Math.max(maxWidth, width);
	}
	
	static void bfs2 (int r, int c, int color) {
		Queue<PointR> Q = new ArrayDeque<>();
		Q.offer(new PointR(r, c));
		boolean[][] visited = new boolean[N][M];
		visited[r][c] = true;

		while(!Q.isEmpty()) {
			PointR p = Q.poll();
			for (int ro=0; ro<4; ro++) {
				int nr = p.r + dr[ro];
				int nc = p.c + dc[ro];
				
				if (nr < 0 || N <= nr || nc < 0 || M <= nc) continue;
				if (colorMap[nr][nc] != color) {
					int width1 = list.get(color-1).width;
					int width2 = list.get(colorMap[nr][nc]-1).width;
					maxWidthWithBroken = Math.max(width1 + width2, maxWidthWithBroken);
				}
				if (visited[nr][nc]) continue;
				
				if (ro==0 && (map[p.r][p.c] & 1<<1) != 0) continue;
				if (ro==1 && (map[p.r][p.c] & 1<<2) != 0) continue;
				if (ro==2 && (map[p.r][p.c] & 1<<3) != 0) continue;
				if (ro==3 && (map[p.r][p.c] & 1) != 0) continue;
				
				Q.offer(new PointR(nr, nc));
				visited[nr][nc] = true;
			}
		}
	}
}
