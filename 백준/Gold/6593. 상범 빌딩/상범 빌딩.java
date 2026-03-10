import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeSb {
	int z;
	int y;
	int x;
	int time;
	NodeSb(int z, int y, int x, int time) {
		this.z = z;
		this.y = y;
		this.x = x;
		this.time = time;
	}
}

public class Main {
	static int[] dz = {1,-1,0,0,0,0};
	static int[] dy = {0,0,1,-1,0,0};
	static int[] dx = {0,0,0,0,1,-1};
	static int L, R, C;
	static char[][][] map;
	static int[][] startEnd;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L==0 && R==0 && C==0) {
				break;
			}
			
			map = new char[L][R][C];
			
			startEnd = new int[2][3];
			
			for (int z=0; z<L; z++) {
				for (int y=0; y<R; y++) {
					String str = br.readLine();
					for (int x=0; x<C; x++) {
						map[z][y][x] = str.charAt(x);
						if (map[z][y][x] == 'S') {
							startEnd[0][0] = z;
							startEnd[0][1] = y;
							startEnd[0][2] = x;
						} else if (map[z][y][x] == 'E' ) {
							startEnd[1][0] = z;
							startEnd[1][1] = y;
							startEnd[1][2] = x;
						}
					}
				}
				br.readLine();
			}
			bfs();
		}
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<NodeSb> Q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[L][R][C];
		
		Q.offer(new NodeSb(startEnd[0][0], startEnd[0][1], startEnd[0][2], 0));
		visited[startEnd[0][0]][startEnd[0][1]][startEnd[0][2]] = true;
		
		while(!Q.isEmpty()) {
			NodeSb node = Q.poll();
			
			if (node.z == startEnd[1][0] && node.y == startEnd[1][1] && node.x == startEnd[1][2]) {
				sb.append("Escaped in " + node.time + " minute(s).\n");
				return;
			}
			
			for (int i=0; i<6; i++) {
				int nz = node.z + dz[i];
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				
				if(!isIn(nz,ny,nx)) continue;
				if(visited[nz][ny][nx]) continue;
				if(map[nz][ny][nx] == '#') continue;
				
				Q.offer(new NodeSb(nz, ny, nx, node.time + 1));
				visited[nz][ny][nx] = true;
			}
		}
		
		sb.append("Trapped!\n");
	}
	
	static boolean isIn(int z, int y, int x) {
		return 0<=z && z<L && 0<=y && y<R && 0<=x && x<C;
	}
}
