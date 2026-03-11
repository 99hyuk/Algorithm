import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeLight {
	int y;
	int x;
	public NodeLight(int y, int x) {
		this.x=x;
		this.y=y;
	}
}

class LightSwitch {
	int x;
	int y;
	int a;
	int b;
	public LightSwitch(int x, int y, int a, int b) {
		this.x=x;
		this.y=y;
		this.a=a;
		this.b=b;
	}
}

public class Main {
	
	static int N, M;
	static int[][] map;
	static List<List<LightSwitch>> graph = new ArrayList<>();
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	static boolean[][] finished;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		
		for (int i=0; i<=2*N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int sum = x + y;
			graph.get(sum).add(new LightSwitch(x, y, a, b));
		}
		
		finished = new boolean[N+1][N+1];
		
		while(bfs()) {
		}
		
		int cnt = 0;
		for (int i=1; i<=N; i++) {
			for (int j=0; j<=N; j++) {
				if(map[i][j] == 1) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	static boolean bfs() {
		Queue<NodeLight> Q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N+1][N+1];
		boolean finish = false;
		
		Q.offer(new NodeLight(1, 1));
		visited[1][1] = true;
		map[1][1] = 1;
		
		while(!Q.isEmpty()) {
			NodeLight node = Q.poll();
			int x = node.x;
			int y = node.y;
			int sum = x + y;
			
			if(!finished[y][x]) {
				for (LightSwitch lightSwitch : graph.get(sum)) {
					int ly = lightSwitch.y;
					int lx = lightSwitch.x;
					int lb = lightSwitch.b;
					int la = lightSwitch.a;
					
					if(x == lx && y == ly) {
						map[lb][la] = 1;
					}
				}
				finished[y][x] = true;
				finish = true;
			}
			
			for (int dir=0; dir<4; dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				
				if(!isIn(ny,nx)) continue;
				if(visited[ny][nx]) continue;
				if(map[ny][nx] == 0) continue;
				
				Q.offer(new NodeLight(ny, nx));
				visited[ny][nx] = true;
			}
		}
		
		return finish;
	}
	
	static boolean isIn(int r, int c) {
		return 0<r && r<=N && 0<c && c<=N;
	}
}
