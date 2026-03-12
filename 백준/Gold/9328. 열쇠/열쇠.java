import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeKey {
	int r;
	int c;
	NodeKey(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int h,w;
	static char[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static boolean[] keyArr;
	static List<NodeKey>[] keyList;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			keyArr = new boolean[200];
			keyList = new ArrayList[200];
			
			for (int i=0; i<200; i++) {
				keyList[i] = new ArrayList<>();
			}
			
			for (int i=0; i<h; i++) {
				String str = br.readLine();
				for (int j=0; j<w; j++) {
					map[i][j] = str.charAt(j);
					if('A' <= map[i][j] && map[i][j] <='Z') {
						keyList[map[i][j]].add(new NodeKey(i, j));
					}
				}
			}
			
			String str = br.readLine();
			for (int i=0; i<str.length(); i++) {
				if (str.charAt(0) == '0') break;
				keyArr[str.charAt(i)] = true;
			}
			
			bfs();
		}
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<NodeKey> Q = new ArrayDeque<>();
		boolean[][] visited = new boolean[h][w];
		int getDoc = 0;
		
		for (int r=0; r<h; r++) {
			for (int c=0; c<w; c++) {
				if(r==0 || r==h-1 || c==0 || c==w-1) {
					if (map[r][c] == '*') continue;
					if ('A' <= map[r][c] && map[r][c] <='Z') {
						if(keyArr[map[r][c] + 'a' - 'A']) {
							Q.offer(new NodeKey(r, c));
							visited[r][c] = true;
						}
						
					} else if(map[r][c] == '.') {
						Q.offer(new NodeKey(r, c));
						visited[r][c] = true;
						
					} else if('a' <= map[r][c] && map[r][c] <= 'z') {
						keyArr[map[r][c]] = true;
						Q.offer(new NodeKey(r, c));
						visited[r][c] = true;
					} else if(map[r][c] == '$') {
						getDoc++;
						Q.offer(new NodeKey(r, c));
						visited[r][c] = true;
					}
				}
			}
		}
		
		while(!Q.isEmpty()) {
			NodeKey node = Q.poll();
			
			for (int dir=0; dir<4; dir++) {
				int nr = node.r + dr[dir];
				int nc = node.c + dc[dir];
				
				if(!isIn(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] == '*') continue;
				
				if ('A' <= map[nr][nc] && map[nr][nc] <='Z') {
					if(keyArr[map[nr][nc] + 'a' - 'A']) {
						Q.offer(new NodeKey(nr, nc));
						visited[nr][nc] = true;
					}
					
				} else if(map[nr][nc] == '.') {
					Q.offer(new NodeKey(nr, nc));
					visited[nr][nc] = true;
					
				} else if('a' <= map[nr][nc] && map[nr][nc] <= 'z') {
					if(!keyArr[map[nr][nc]]) {
						keyArr[map[nr][nc]] = true;
						for (NodeKey nodeKey : keyList[map[nr][nc] - ('a' - 'A')]) {
							if (nodeKey.r==0 || nodeKey.r==h-1 || nodeKey.c==0 || nodeKey.c==w-1) {
								Q.offer(new NodeKey(nodeKey.r, nodeKey.c));
								visited[nodeKey.r][nodeKey.c] = true;
								continue;
							}
							
							for (int dirK=0; dirK<4; dirK++) {
								int kr = nodeKey.r + dr[dirK];
								int kc = nodeKey.c + dc[dirK];
								if(!isIn(kr,kc)) continue;
								if(visited[kr][kc]) {
									Q.offer(new NodeKey(nodeKey.r, nodeKey.c));
									visited[nodeKey.r][nodeKey.c] = true;
									break;
								}
							}
						}
					}
					
					Q.offer(new NodeKey(nr, nc));
					visited[nr][nc] = true;
					
				} else if(map[nr][nc] == '$') {
					getDoc++;
					Q.offer(new NodeKey(nr, nc));
					visited[nr][nc] = true;
				}
				
			}
		}
		
		sb.append(getDoc + "\n");
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<h && 0<=c && c<w;
	}
}
