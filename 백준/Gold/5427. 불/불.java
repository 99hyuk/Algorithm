import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeFire {
	int r;
	int c;
	int time;
	public NodeFire(int r, int c, int time) {
		this.r = r;
		this.c = c;
		this.time = time;
	}
}

public class Main {
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int w, h;
	static char[][] map;
	static int[][] fireMap;
	static Queue<NodeFire> QF;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			fireMap = new int[h][w];
			QF = new ArrayDeque<>();
			int sangaenR = -1;
			int sangaenC = -1;
			
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					fireMap[i][j] = -1;
				}
			}
			
			for (int i=0; i<h; i++) {
				String str = br.readLine();
				for (int j=0; j<w; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '*') {
						QF.offer(new NodeFire(i, j, 0));
						fireMap[i][j] = 0;
					}
					if (map[i][j] == '@') {
						sangaenR = i;
						sangaenC = j;
					}
				}
			}
			
			bfsFire();
			bfsSangaen(sangaenR, sangaenC);
					
		}
		
		System.out.println(sb);
	}
	
	static void bfsFire() {
		
		while(!QF.isEmpty()) {
			NodeFire node = QF.poll();
			
			for (int ro=0; ro<4; ro++) {
				int nr = node.r + dr[ro];
				int nc = node.c + dc[ro];
				
				if(!isIn(nr,nc)) continue;
				if(map[nr][nc] == '#' || map[nr][nc] == '*') continue;
				
				map[nr][nc] = '*';
				fireMap[nr][nc] = node.time+1;
				QF.offer(new NodeFire(nr, nc, node.time+1));
			}
		}
	}
	
	static void bfsSangaen(int r, int c) {
		Queue<NodeFire> Q = new ArrayDeque<>();
		Q.offer(new NodeFire(r, c, 0));
		
		while(!Q.isEmpty()) {
			NodeFire node = Q.poll();
			
			for (int ro=0; ro<4; ro++) {
				int nr = node.r + dr[ro];
				int nc = node.c + dc[ro];
				
				if(!isIn(nr,nc)) {
					sb.append(node.time+1 + "\n");
					return;
				}
				if(map[nr][nc] == '#' || map[nr][nc] == '@') continue;
				if(fireMap[nr][nc] != -1 && fireMap[nr][nc] <= node.time+1) continue;
				
				map[nr][nc] = '@';
				Q.offer(new NodeFire(nr, nc, node.time+1));
			}
		}
		
		sb.append("IMPOSSIBLE\n");
		return;
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<h && 0<=c && c<w;
	}
}
