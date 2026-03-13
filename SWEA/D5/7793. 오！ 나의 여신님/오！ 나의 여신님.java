import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r;
	int c;
	int type;
	int time;
	public Node(int r, int c, int type, int time) {
		this.r = r;
		this.c = c;
		this.type = type;
		this.time = time;
	}
}

public class Solution {
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N,M;
	static char[][] map;
	static List<Node> devilList;
	static Node person;
	static Node god;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			
			devilList = new ArrayList<>();
			
			for (int r=0; r<N; r++) {
				String str = br.readLine();
				for (int c=0; c<M; c++) {
					map[r][c] = str.charAt(c);
					if(map[r][c] == '*') {
						devilList.add(new Node(r, c, 1, 0));
					} else if(map[r][c] == 'S') {
						person = new Node(r, c, 0, 0);
					} else if(map[r][c] == 'D') {
						god = new Node(r, c, 2, 0);
					}
				}
			}
			
			sb.append("#" + t + " ");
			bfs();
		}
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Node> Q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		for (Node devil : devilList) {
			Q.offer(devil);
		}
		
		Q.offer(person);
		visited[person.r][person.c] = true;
		
		while(!Q.isEmpty()) {
			Node node = Q.poll();
			int r = node.r;
			int c = node.c;
			int type = node.type;
			int time = node.time;
			
			if(type == 0) {
				if(r == god.r && c == god.c) {
					sb.append(time + "\n");
					return;
				}
				
				for (int dir = 0; dir<4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					
					if(!isIn(nr,nc)) continue;
					if(map[nr][nc] == '*') continue;
					if(visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					Q.offer(new Node(nr, nc, type, time+1));
				}
			} else if(type == 1) {
				for (int dir = 0; dir<4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					
					if(!isIn(nr,nc)) continue;
					if(map[nr][nc] == '*') continue;
					if(map[nr][nc] == 'D') continue;
					
					map[nr][nc] = '*';
					Q.offer(new Node(nr, nc, type, time+1));
				}
			}
		}
		
		sb.append("GAME OVER\n");
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M && map[r][c] != 'X';
	}
}
