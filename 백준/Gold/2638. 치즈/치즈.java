import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r;
	int c;
	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int N,M;
	static int[][] map, temp;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int outColor = 2;
//	static int inColor = -2;
	static int totalTime = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(mapInCheese()) {
			checkBfs();
			checkCheese();
//			test();
			outColor++;
//			inColor--;
//			test();
		}
		
	}
	
	static boolean mapInCheese() {
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (map[r][c] == 1) {
					totalTime++;
					return true;
				}
			}
		}
		System.out.println(totalTime);
		return false;
	}
	
	static void checkCheese() {
		temp = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (map[r][c] == 1) {
					dieCheese(r, c);
				}
			}
		}
		
		map = temp;
	}
	
	static void dieCheese(int r, int c) {
		int cnt = 0;
		
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isIn(nr, nc)) continue;
			
			if (map[nr][nc] == outColor) cnt++;
		}
		
		if (cnt >= 2) {
			temp[r][c] = outColor;
		}
	}
	
	static void checkBfs() {
//		for (int r=0; r<N; r++) {
//			for (int c=0; c<M; c++) {
//				if (map[r][c] != 1) {
////					if(rectEndCheck(r, c) && map[r][c] != inColor) {
////						toColorBfs(r, c, inColor);
////					} else if (!rectEndCheck(r, c) && map[r][c] != outColor){
////						toColorBfs(r, c, outColor);
////					}
//					if (map[r][c] != outColor && !rectEndCheck(r, c) ){
//						toColorBfs(r, c, outColor);
//					}
//				}
//			}
//		}
		toColorBfs(0, 0, outColor);
	}
	
	static boolean rectEndCheck(int r, int c) {
		for (int i=0; i<4; i++) {
			int curR = r;
			int curC = c;
			
			while(isIn(curR, curC) && map[curR][curC] != 1) {
				if (r == 0 || r == N-1 || c == 0 || c == M-1) return false;
				curR = curR + dr[i];
				curC = curC + dc[i];
			}
		}
		
		return true;
	}
	
	
	static void toColorBfs(int r, int c, int color) {
		Queue<Node> Q = new ArrayDeque<>();
		Q.offer(new Node(r, c));
		map[r][c] = color;
		
		while(!Q.isEmpty()) {
			Node node = Q.poll();
			
			for (int i=0; i<4; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				if(map[nr][nc] == color) continue;
				if(map[nr][nc] == 1) continue;
				
				map[nr][nc] = color;
				Q.offer(new Node(nr, nc));
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
	
	static void test() {
		System.out.println();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
