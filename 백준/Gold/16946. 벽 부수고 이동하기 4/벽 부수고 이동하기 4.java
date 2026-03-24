import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class NodeB {
	int r;
	int c;
	public NodeB(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int N, M;
	static int[][] map;
	static int[] colorMap;
	static int color = 2;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		colorMap = new int[1000002];
		
		for (int r=0; r<N; r++) {
			String str = br.readLine();
			for (int c=0; c<M; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if(map[r][c] == 0) {
					colorMap[color] = bfs(r,c);
					color++;
				}
			}
		}
		
		int[][] finalMap = new int[N][M];
		Set<Integer> set = new HashSet<>();
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if(map[r][c] == 1) {
					for (int dir=0; dir<4; dir++) {
						int nr = r + dr[dir];
						int nc = c + dc[dir];
						
						if(!isIn(nr,nc)) continue;
						if(map[nr][nc] == 1) continue;
						
						set.add(map[nr][nc]);
					}
					
					for (int num : set) {
						finalMap[r][c] += colorMap[num];
					}
					finalMap[r][c]++;
					
					set.clear();
				}
			}
		}
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				sb.append(finalMap[r][c] % 10);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static int bfs(int r, int c) {
		Queue<NodeB> Q = new ArrayDeque<>();
		Q.offer(new NodeB(r, c));

		map[r][c] = color;
		int cnt = 1;
		
		while(!Q.isEmpty()) {
			NodeB node = Q.poll();
			
			for (int dir=0; dir<4; dir++) {
				int nr = node.r + dr[dir];
				int nc = node.c + dc[dir];
				
				if(!isIn(nr,nc)) continue;
				if(map[nr][nc] != 0) continue;
				
				map[nr][nc] = color;
				cnt++;
				Q.offer(new NodeB(nr, nc));
			}
		}
		
		return cnt;
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
