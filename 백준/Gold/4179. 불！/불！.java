import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int r,c;
	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int R,C;
	static char[][] map;
	static int startJR, startJC;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static List<Point> fireList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		fireList = new ArrayList<Point>();
		
		for (int i=0; i<R; i++) {
			String str = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'J') {
					startJR = i;
					startJC = j;
				}
				if (map[i][j] == 'F') {
					fireList.add(new Point(i, j));
				}
			}
		}
		
		bfs();
	}
	
	static void bfs() {
		Queue<Point> QF = new ArrayDeque<Point>();
		for (Point fire : fireList) {
			QF.offer(fire);
		}
		
		Queue<Point> QJ = new ArrayDeque<Point>();
		QJ.offer(new Point(startJR, startJC));
		
		int cnt=0;
		
		while(!QJ.isEmpty()) {
			cnt++;
			int size = QJ.size();
			for (int i=0; i<size; i++) {
				Point p = QJ.poll();
				
				if (map[p.r][p.c] == 'F') continue;
				if (p.r==0 || p.r==R-1 || p.c==0 || p.c==C-1) {
					System.out.println(cnt);
					return;
				}
				
				for (int ro=0; ro<4; ro++) {
					int nr = p.r + dr[ro];
					int nc = p.c + dc[ro];
					
					if (nr<0 || nr>=R || nc<0 || nc>=C) continue;
					if (map[nr][nc] == 'F' || map[nr][nc] == '#' || map[nr][nc] == 'J') continue;
					
					map[nr][nc] = 'J';
					QJ.offer(new Point(nr, nc));
				}
			}
			
			int sizeF = QF.size();
			for (int i=0; i<sizeF; i++) {
				Point p = QF.poll();
				
				for (int ro=0; ro<4; ro++) {
					int nr = p.r + dr[ro];
					int nc = p.c + dc[ro];
					
					if (nr<0 || nr>=R || nc<0 || nc>=C) continue;
					if (map[nr][nc] == '#' || map[nr][nc] == 'F') continue;
					
					map[nr][nc] = 'F';
					QF.offer(new Point(nr, nc));
				}
			}
		}
		
		System.out.println("IMPOSSIBLE");
	}
}
