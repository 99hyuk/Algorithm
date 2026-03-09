import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeFire {
	int r;
	int c;
	int bool;
	int time;
	public NodeFire(int r, int c, int bool, int time) {
		this.r = r;
		this.c = c;
		this.bool = bool;
		this.time = time;
	}
}

public class Main {
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int w, h;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[h][w];
			Queue<NodeFire> Q = new ArrayDeque<>();
			int sangaenR = -1;
			int sangaenC = -1;
			
			for (int i=0; i<h; i++) {
				String str = br.readLine();
				for (int j=0; j<w; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '*') {
						Q.offer(new NodeFire(i, j, 1, 0));
					}
					if (map[i][j] == '@') {
						sangaenR = i;
						sangaenC = j;
					}
				}
			}
			
			boolean finish = false;
			if (sangaenR==0 || sangaenR==h-1 || sangaenC==0 || sangaenC==w-1) {
				finish = true;
				sb.append(1+"\n");
			}
			
			Q.offer(new NodeFire(sangaenR, sangaenC, 0, 0));
			
			while(!Q.isEmpty() && !finish) {
				NodeFire node = Q.poll();
				
				if (node.bool == 1) {
					for (int ro=0; ro<4; ro++) {
						int nr = node.r + dr[ro];
						int nc = node.c + dc[ro];
						
						if(!isIn(nr,nc)) continue;
						if(map[nr][nc] == '#' || map[nr][nc] == '*') continue;
						
						map[nr][nc] = '*';
						Q.offer(new NodeFire(nr, nc, node.bool, node.time+1));
					}
				} else {
					for (int ro=0; ro<4; ro++) {
						int nr = node.r + dr[ro];
						int nc = node.c + dc[ro];
						
						if(!isIn(nr,nc)) continue;
						if(map[nr][nc] == '#' || map[nr][nc] == '*' || map[nr][nc] == '@') continue;
						
						map[nr][nc] = '@';
						Q.offer(new NodeFire(nr, nc, node.bool, node.time+1));
						
						if(nr==0 || nr==h-1 || nc==0 || nc==w-1) {
							sb.append(node.time+2 + "\n");
							finish = true;
							break;
						}
					}
					
				}
			}
			
			if(!finish) {
				sb.append("IMPOSSIBLE\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<h && 0<=c && c<w;
	}
}
