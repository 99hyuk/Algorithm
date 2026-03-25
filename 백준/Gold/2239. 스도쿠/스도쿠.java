import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] sdoque;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sdoque = new int[9][9];
		for (int i=0; i<9; i++) {
			String str = br.readLine();
			for (int j=0; j<9; j++) {
				sdoque[i][j] = str.charAt(j) - '0';
			}
		}
		
		dfs(0,0);
		
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				System.out.print(sdoque[i][j]);
			}
			System.out.println();
		}
	}
	
	static boolean dfs(int r, int c) {
		for (int i=r; i<9; i++) {
			int startJ = i==r ? c : 0;
			for (int j=startJ; j<9; j++) {
				if(i==8 && j==8) {
					if (sdoque[i][j] == 0) {
						for (int num=1; num<=9; num++) {
							if (checkRow(i,j,num) && checkCol(i,j,num) && checkRec(i,j,num)) {
								sdoque[i][j] = num;
								return true;
							}
						}
					} else {
						return true;
					}
				}
				if (sdoque[i][j] == 0) {
					for (int num=1; num<=9; num++) {
						if (checkRow(i,j,num) && checkCol(i,j,num) && checkRec(i,j,num)) {
							sdoque[i][j] = num;
							if(dfs(i, j)) return true;
							sdoque[i][j] = 0;
						}
					}
					return false;
				}
			}
		}
		return false;
	}
	
	static boolean checkRow(int r, int c, int idx) {
		for (int i=0; i<9; i++) {
			if (i==c) continue;
			if (sdoque[r][i] == idx) return false;
		}
		
		return true;
	}
	
	static boolean checkCol(int r, int c, int idx) {
		for (int i=0; i<9; i++) {
			if (i==r) continue;
			if (sdoque[i][c] == idx) return false;
		}
		
		return true;
	}
	
	static boolean checkRec(int r, int c, int idx) {
		int restR = r / 3 * 3;
		int restC = c / 3 * 3;
		
		for (int i=restR; i<restR+3; i++) {
			for (int j=restC; j<restC+3; j++) {
				if (i==r && j==c) continue;
				if (sdoque[i][j] == idx) return false;
			}
		}
		
		return true;
	}
}
