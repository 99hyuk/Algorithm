import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		
		for (int i=1; i<=n; i++) {
			String str = br.readLine();
			for (int j=1; j<=n; j++) {
				map[i][j] = str.charAt(j-1) - '0';
			}
		}
		
		recursive(1, 1, n);
		System.out.println(sb);
	}
	
	static void recursive(int r, int c, int size) {
		if(size == 1) {
			sb.append(map[r][c]);
			return;
		}
		
		if(check(r,c,0,size) || check(r,c,1,size)) {
			sb.append(map[r][c]);
			return;
		} 
		
		sb.append("(");
		recursive(r,c,size/2);
		recursive(r,c+size/2,size/2);
		recursive(r+size/2,c,size/2);
		recursive(r+size/2,c+size/2,size/2);
		sb.append(")");
	}
	
	static boolean check(int r, int c, int num, int size) {
		for (int i=r; i<r+size; i++) {
			for (int j=c; j<c+size; j++) {
				if (map[i][j] != num) return false;
			}
		}
		return true;
	}
}
