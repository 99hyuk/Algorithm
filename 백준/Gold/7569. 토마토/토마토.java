import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Tomato {
		int r, c, h;
		Tomato(int h, int r, int c) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}
	
	static int M;
	static int N;
	static int H;
	static int[] dc = {1,-1,0,0,0,0};
	static int[] dr = {0,0,1,-1,0,0};
	static int[] dh = {0,0,0,0,1,-1};
	static int map[][][];
	static List<Tomato> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		list = new ArrayList<>();
		
		for (int z=0; z<H; z++) {
			for (int y=0; y<N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x=0; x<M; x++) {
					map[z][y][x] = Integer.parseInt(st.nextToken());
					if (map[z][y][x] == 1) {
						list.add(new Tomato(z, y, x));
					}
				}
			}
		}
		
		bfs();
	}
	
	static void bfs() {
		Queue<Tomato> Q = new ArrayDeque<>();
		for (Tomato tomato : list) {
			Q.offer(tomato);
		}
		int cnt=0;
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			for (int i=0; i<size; i++) {
				Tomato t = Q.poll();
				
				for (int ro=0; ro<6; ro++) {
					int nh = t.h + dh[ro]; int nr = t.r + dr[ro]; int nc = t.c + dc[ro];
					
					if (nh<0 || H<=nh || nr<0 || N<=nr || nc<0 || M<=nc) continue;
					if (map[nh][nr][nc] == -1 || map[nh][nr][nc] == 1) continue;
					
					map[nh][nr][nc] = 1;
					Q.offer(new Tomato(nh, nr, nc));
				}
			}
			if (Q.size() > 0) {
				cnt++;
			}
		}
		
		for (int z=0; z<H; z++) {
			for (int y=0; y<N; y++) {
				for (int x=0; x<M; x++) {
					if(map[z][y][x] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
}
