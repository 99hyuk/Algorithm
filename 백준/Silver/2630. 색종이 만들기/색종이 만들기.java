import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static int blue = 0;
	static int white = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		StringTokenizer st;
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		devideConquar(1, 1, N);
		
		System.out.println(white + "\n" + blue);
	}
	
	static void devideConquar(int r, int c, int size) {
		
		for (int i=r; i<r+size; i++) {
			for (int j=c; j<c+size; j++) {
				if(map[r][c] != map[i][j]) {
					devideConquar(r, c, size/2);
					devideConquar(r, c+size/2, size/2);
					devideConquar(r+size/2, c, size/2);
					devideConquar(r+size/2, c+size/2, size/2);
					return;
				}
			}
		}
		
		if(map[r][c] == 1) {
			blue++;
		} else {
			white++;
		}
	}
}
