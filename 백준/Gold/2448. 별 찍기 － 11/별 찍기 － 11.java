import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N;
	static char[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		N = sc.nextInt();
		map = new char[2*N][2*N];
		
		for (int i = 1; i < 2*N; i++) {
            Arrays.fill(map[i], ' ');
        }
		
		star(N,1,N);
		
		for (int i=1; i<2*N; i++) {
			for (int j=1; j<2*N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void star(int n, int r, int c) {
		if (n==3) {
			map[r][c] = '*';
			map[r+1][c-1] = '*';
			map[r+1][c] = ' ';
			map[r+1][c+1] = '*';
			for(int i=0; i<5; i++) {
				map[r+2][c-2+i] = '*';
			}
			return;
			
		}
		
		star(n/2,r,c);
		
		for (int i=r+1; i<r+n; i++) {
			if ((i-1) % (n/2) == 0) {
				star(n/2, i, c-(n/2));
			}
		}
		
		for (int i=r+1; i<r+n; i++) {
			if ((i-1) % (n/2) == 0) {
				star(n/2, i, c+(n/2));
			}
		}
	}
}
