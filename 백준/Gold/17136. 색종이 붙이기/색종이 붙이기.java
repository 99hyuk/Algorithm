import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static int[] rect = {0,5,5,5,5,5};
	static boolean poss = false;
	static int minCount = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[10][10];
		
		for (int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0, 0);
		
		if (!poss) minCount = -1;
		System.out.println(minCount);
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && r<10 && 0<=c && c<10;
	}
	
	static boolean stitch(int r, int c, int width) {
		
		if (!isIn(r+width-1, c+width-1)) return false;
		
		if (rect[width] == 0) return false;
		
		for (int i=r; i<r+width; i++) {
			for (int j=c; j<c+width; j++) {
				if (map[i][j] != 1) {
					return false;
				}
			}
		}
		
		for (int i=r; i<r+width; i++) {
			for (int j=c; j<c+width; j++) {
				map[i][j] = 2;
			}
		}
		
		rect[width]--;
		return true;
	}
	
	static void returnStitch(int r, int c, int width) {
		for (int i=r; i<r+width; i++) {
			for (int j=c; j<c+width; j++) {
				map[i][j] = 1;
			}
		}
		
		rect[width]++;
	}
	
	static void dfs(int count, int startI, int startJ) {
		
		if (count >= minCount) return;
		
		for (int i=startI; i<10; i++) {
			int start = i == startI ? startJ : 0;
			for (int j=start; j<10; j++) {
				if(map[i][j] == 1) {
					for (int big=5; big>=1; big--) {
						if(rect[big] == 0) continue;
						if(stitch(i, j, big)) {
							dfs(count+1, i, j);
							returnStitch(i, j, big);
						}
					}
					return;
				}
			}
		}
		poss = true;
		minCount = Math.min(minCount, count);
	}
}
