import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static char[][] map;
	static Set<String> set;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static char[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			map = new char[4][4];
			set = new HashSet<>();
			selected = new char[7];
			
			for (int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<4; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}
			
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					dfs(0,i,j);
				}
			}
			
			sb.append("#" + t + " " + set.size() + "\n");
		}
		System.out.println(sb);
	}
	
	
	static void dfs(int idx, int r , int c) {
		if(idx==7) {
			String str = "";
			for (int i=0; i<7; i++) {
				str = str + String.valueOf(selected[i]);
			}
			set.add(str);
			return;
		}
		
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isIn(nr,nc)) continue;
			
			selected[idx] = map[nr][nc];
			dfs(idx+1, nr, nc);
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<4 && 0<=c && c<4;
	}
}
