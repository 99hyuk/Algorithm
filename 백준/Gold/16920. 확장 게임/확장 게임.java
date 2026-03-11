import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeCastle {
	int r;
	int c;
	int num;
	NodeCastle(int r, int c, int num) {
		this.r = r;
		this.c = c;
		this.num = num;
	}
}

public class Main {
	
	static int N, M, P;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int[] pSize;
	static char[][] map;
	static List<List<NodeCastle>> castleList;
	static int[] castleCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		pSize = new int[P+1];
		castleCnt = new int[P+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=P; i++) {
			pSize[i] = Integer.parseInt(st.nextToken());
		}
		
		map = new char[N][M];
		castleList = new ArrayList<>();
		for (int i=0; i<=P; i++) {
			castleList.add(new ArrayList<>());
		}
		
		for (int r=0; r<N; r++) {
			String str = br.readLine();
			for (int c=0; c<M; c++) {
				map[r][c] = str.charAt(c);
				if (1 <= map[r][c] - '0' && map[r][c] - '0' <= P) {
					castleList.get(map[r][c]-'0').add(new NodeCastle(r, c, map[r][c] - '0'));
					castleCnt[map[r][c]-'0']++;
				}
			}
		}
		
		bfs();
		
		for (int i=1; i<=P; i++) {
			System.out.print(castleCnt[i] + " ");
		}
	}
	
	static void bfs() {
		Queue<NodeCastle>[] Q = new ArrayDeque[P+1];
		
		for (int i=0; i<=P; i++) {
			Q[i] = new ArrayDeque<>();
			for (NodeCastle node : castleList.get(i)) {
				Q[i].offer(node);
			}
		}
		
		while(true) {
			boolean finished = false;
			
			for (int i=1; i<=P; i++) {
				int castleSize = pSize[i];
				Queue<NodeCastle> q = Q[i];
				
				for (int d=0; d<castleSize && !q.isEmpty(); d++) {
					int size = q.size();
					
					for (int s=0; s<size; s++) {
						NodeCastle node = q.poll();
						
						int r = node.r;
						int c = node.c;
						
						for (int ro=0; ro<4; ro++) {
							int nr = r + dr[ro];
							int nc = c + dc[ro];
							
							if(!isIn(nr,nc)) continue;
							if(map[nr][nc] != '.') continue;
							
							finished = true;
							map[nr][nc] = (char)(node.num + '0');
							castleCnt[node.num]++;
							q.offer(new NodeCastle(nr, nc, node.num));
						}
					}
				}
			}
			
			if(!finished) {
				break;
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
