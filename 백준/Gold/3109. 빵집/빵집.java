import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };
	static char[][] map;
	static boolean[][] visited;
	static int cnt = 0;
	static int max = Integer.MIN_VALUE;
	static boolean possible;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int r=0; r<R; r++) {
			String str = br.readLine();
			for (int c=0; c<C; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		
		for (int i=0; i<R; i++) {
			possible = false;
			dfs(0,i,0);
		}

		System.out.println(cnt);
	}
	
	static void dfs(int idx, int r, int c) {
		if (idx==C-1 && !possible) {
			cnt++;
			possible = true;
			return;
		}

		for (int ro=0; ro<3; ro++) {
			int nr = r + dr[ro];
			int nc = c + dc[ro];
			
			if (nr<0 || R<=nr || nc<0 || C<=nc) continue;
			if (map[nr][nc] == 'x') continue;
			if (visited[nr][nc]) continue;
			
			if (possible) return;
			visited[nr][nc] = true;
			dfs(idx+1, nr, nc);
		}
	}
}
