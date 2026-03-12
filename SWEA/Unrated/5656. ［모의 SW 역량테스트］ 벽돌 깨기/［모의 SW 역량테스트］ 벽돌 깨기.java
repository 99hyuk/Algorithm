import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Brick {
	int r;
	int c;
	int bomb;
	public Brick(int r, int c, int bomb) {
		this.r = r;
		this.c = c;
		this.bomb = bomb;
	}
}

public class Solution {
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N, W, H;
	static int[][] map, copyMap;
	static int[] orderArr;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			copyMap = new int[H][W];
			orderArr = new int[N];
			min = Integer.MAX_VALUE;
			
			for (int r=0; r<H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c=0; c<W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0);
			
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int idx) {
		if(idx == N) {
			for (int r=0; r<H; r++) {
				for (int c=0; c<W; c++) {
					copyMap[r][c] = map[r][c];
				}
			}
			
			for (int i=0; i<N; i++) {
				simulation(orderArr[i]);
			}
			
			int sum = 0;
			
			for (int r=0; r<H; r++) {
				for (int c=0; c<W; c++) {
					if(copyMap[r][c] > 0) sum++;
					if(sum >= min) return;
				}
			}
			min = sum;
			
	
			
			return;
		}
		
		for (int i=0; i<W; i++) {
			orderArr[idx] = i;
			dfs(idx+1);
		}
	}
	
	static void simulation(int x) {
		Queue<Brick> Q = new ArrayDeque<>();
		for(int i=0; i<H; i++) {
			if(copyMap[i][x] > 0) {
				Q.offer(new Brick(i, x, copyMap[i][x]));
				copyMap[i][x] = 0;
				break;
			}
		}
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			for (int i=0; i<size; i++) {
				Brick brick = Q.poll();
				int r = brick.r;
				int c = brick.c;
				int bomb = brick.bomb;
				
				for (int dir=0; dir<4; dir++) {
					for (int s=1; s<bomb; s++) {
						int nr = r + dr[dir]*s;
						int nc = c + dc[dir]*s;
						
						if(!isIn(nr,nc)) continue;
						
						if(copyMap[nr][nc] > 0) {
							Q.offer(new Brick(nr, nc, copyMap[nr][nc]));
							copyMap[nr][nc] = 0;
						}
					}
				}
			}
		}
		gravity();
	}
	
	static void gravity() {
		for (int c=0; c<W; c++) {
			int bom = H-1;
			int pointer = H-1;
			while(pointer >= 1) {
				if(copyMap[pointer][c] > 0) {
					bom--;
					pointer--;
					continue;
				}
				
				if(copyMap[pointer][c] == 0) {
					if(copyMap[pointer-1][c] > 0) {
						copyMap[bom][c] = copyMap[pointer-1][c];
						copyMap[pointer-1][c] = 0;
						bom--;
						pointer--;
					} else {
						pointer--;
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<H && 0<=c && c<W;
	}
}
