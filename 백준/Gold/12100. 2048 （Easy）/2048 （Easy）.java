import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static int[][] copyMap;
	static int[] selected;
	static int[] rotateIdx = {0,1,2,3}; // 위, 왼, 아래, 오른 
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		copyMap = new int[n][n];
		selected = new int[5];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(max);
	}
	
	static void dfs(int idx) {
		if(idx == 5) {
			simulation();
			return;
		}
		
		for (int i=0; i<4; i++) {
			selected[idx] = i;
			dfs(idx+1);
		}
	}
	
	static void simulation() {
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		for (int i=0; i<5; i++) {
			for (int r=0; r<selected[i]; r++) {
				rotate();
			}
			downBlock();
			for (int r=0; r<selected[i]; r++) {
				unRotate();
			}
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				max = Math.max(max, copyMap[i][j]);
			}
		}
	}
	
	static void downBlock() {
		for (int j=0; j<n; j++) {
			int pos = n-1;
			int[] temp = new int[n];
			boolean combined = false;
			
			for (int i = n - 1; i >= 0; i--) {
	            if (copyMap[i][j] == 0) continue;

	            if (temp[pos] == 0) {
	                temp[pos] = copyMap[i][j];
	            } 
	            else if (temp[pos] == copyMap[i][j] && !combined) {
	                temp[pos] *= 2;
	                combined = true; 
	            } 
	            else {
	                pos--;
	                temp[pos] = copyMap[i][j];
	                combined = false;
	            }
	        }
			
			for (int i=0; i<n; i++) {
				copyMap[i][j] = temp[i];
			}
		}
	}
	
	static void rotate() {
		int[][] rotateMap = new int[n][n];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				rotateMap[i][j] = copyMap[i][j];
			}
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				rotateMap[j][n-i-1] = copyMap[i][j];
			}
		}
		
		copyMap = rotateMap;
	}
	
	static void unRotate() {
		int[][] unRotateMap = new int[n][n];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				unRotateMap[i][j] = copyMap[i][j];
			}
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				unRotateMap[n-1-j][i] = copyMap[i][j];
			}
		}
		
		copyMap = unRotateMap;
	}
}