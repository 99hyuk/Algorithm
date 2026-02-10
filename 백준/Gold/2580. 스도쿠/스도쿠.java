import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class PointSdoque {
	int r;
	int c;
	public PointSdoque(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int[][] map;
	static List<PointSdoque> list;
	static int cnt;
	static boolean fin = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		list = new ArrayList<PointSdoque>();
		
		for (int r=0; r<9; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c=0; c<9; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 0) {
					list.add(new PointSdoque(r, c));
				}
			}
		}
		
		cnt = list.size();
		dfs(0);
		
	}
	
	static void dfs(int idx) {
		if (fin) return;
		
		if (idx == cnt) {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					sb.append(map[i][j] + " ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			fin = true;
			return;
		}
		
		int r = list.get(idx).r;
		int c = list.get(idx).c;
		
		for (int num=1; num<=9; num++) {
			if(!checkRow(r, c, num)) continue;
			if(!checkCol(r, c, num)) continue;
			if(!checkRec(r, c, num)) continue;

			map[r][c] = num;
			if (fin) return;
			dfs(idx+1);
			map[r][c] = 0;
		}
	}
	
	static boolean checkRow(int r, int c, int num) {
		for (int i=0; i<9; i++) {
			if(i==r) continue;
			if(map[i][c] == num) return false;
		}
		return true;
	}
	
	static boolean checkCol(int r, int c, int num) {
		for (int i=0; i<9; i++) {
			if(i==c) continue;
			if(map[r][i] == num) return false;
		}
		return true;
	}
	
	static boolean checkRec(int r, int c, int num) {
		int row = r / 3;
		int col = c / 3;
		
		for (int i=row*3; i<row*3+3; i++) {
			for (int j=col*3; j<col*3+3; j++) {
				if(r==i && c==j) continue;
				if(map[i][j] == num) return false;
			}
		}
		return true;
	}
}
