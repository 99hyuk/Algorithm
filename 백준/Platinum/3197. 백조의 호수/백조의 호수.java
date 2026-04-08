import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeWater {
	int r;
	int c;
	char type;
	public NodeWater(int r, int c, char type) {
		this.r = r;
		this.c = c;
		this.type = type;
	}
}

public class Main {
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int R,C;
	static char[][] map;
	static int[][] duck;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		duck = new int[2][2];
		int idx = 0;

		for (int r=0; r<R; r++) {
			String str = br.readLine();
			for (int c=0; c<C; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c] == 'L') {
					if(idx==0) {
						map[r][c] = 'a';
					} else {
						map[r][c] = 'b';
					}
					duck[idx][0] = r;
					duck[idx][1] = c;
					idx++;
				}
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		
		Queue<NodeWater> waterQ = new ArrayDeque<>();
		Queue<NodeWater> nextWaterQ = new ArrayDeque<>();
		Queue<NodeWater> duckQ = new ArrayDeque<>();
		Queue<NodeWater> nextDuckQ = new ArrayDeque<>();
		
		boolean[][] visitedWater = new boolean[R][C];
		boolean[][] visitedDuck = new boolean[R][C];
		
		for (int r=0; r<R; r++) {
			for (int c=0; c<C; c++) {
				if(map[r][c] == 'a' || map[r][c] == 'b') {
					duckQ.offer(new NodeWater(r, c, map[r][c]));
					visitedDuck[r][c] = true;
					
					waterQ.offer(new NodeWater(r, c, '.'));
					visitedWater[r][c] = true;
				} else if(map[r][c] == '.') {
					waterQ.offer(new NodeWater(r, c, map[r][c]));
					visitedWater[r][c] = true;
				}
			}
		}
		
		int time = 0;
		
		while(true) {
			while(!duckQ.isEmpty()) {
				NodeWater node = duckQ.poll();
				
				map[node.r][node.c] = node.type;
				
				for (int dir=0; dir<4; dir++) {
					int nr = node.r + dr[dir];
					int nc = node.c + dc[dir];
					
					if(!isIn(nr,nc)) continue;
					
					if(map[nr][nc] == 'a' && node.type == 'b') {
						return time;
					} else if(map[nr][nc] == 'b' && node.type == 'a') {
						return time;
					}
					
					if(visitedDuck[nr][nc]) continue;
					
					if(map[nr][nc] == '.') {
						visitedDuck[nr][nc] = true;
						map[nr][nc] = node.type;
						duckQ.offer(new NodeWater(nr, nc, node.type));
					} else if(map[nr][nc] == 'X') {
						nextDuckQ.offer(new NodeWater(nr, nc, node.type));
						visitedDuck[nr][nc] = true;
					}
				}
			}
			
			duckQ = nextDuckQ;
			nextDuckQ = new ArrayDeque<>();
			time++;
			
			while(!waterQ.isEmpty()) {
				NodeWater node = waterQ.poll();
				
				for (int dir=0; dir<4; dir++) {
					int nr = node.r + dr[dir];
					int nc = node.c + dc[dir];
					
					if(!isIn(nr,nc)) continue;
					if(visitedWater[nr][nc]) continue;
					
					if(map[nr][nc] == '.' || map[nr][nc] == 'a' || map[nr][nc] == 'b') {
						visitedWater[nr][nc] = true;
						waterQ.offer(new NodeWater(nr, nc, node.type));
					} else if(map[nr][nc] == 'X') {
						visitedWater[nr][nc] = true;
						map[nr][nc] = '.';
						nextWaterQ.offer(new NodeWater(nr, nc, node.type));
					} 
				}
			}
			
			waterQ = nextWaterQ;
			nextWaterQ = new ArrayDeque<>();
		}
	}

	static boolean isIn(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}
}
