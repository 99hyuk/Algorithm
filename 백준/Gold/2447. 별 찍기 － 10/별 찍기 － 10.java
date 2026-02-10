import java.util.Scanner;

public class Main {
	
	static int N;
	static String[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		map = new String[N][N];
		
		star(N,0,0);
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void star(int n, int r, int c) {
		
		if (n==3) {
			for (int i=0; i<3; i++) {
				map[r][c+i] = "*";
			}
			
			for (int i=0; i<1; i++) {
				map[r+1][c+i] = "*";
			}

			map[r+1][c+1] = " ";
			
			for (int i=2; i<3; i++) {
				map[r+1][c+i] = "*";
			}
			
			for (int i=0; i<n; i++) {
				map[r+2][c+i] = "*";
			}	
			return;
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (i == (n/3) && j == (n/3)) {
					for (int r1=i; r1<n*2/3; r1++) {
						for (int c1=j; c1<n*2/3; c1++) {
							map[r+r1][c+c1] = " ";
						}
					}
				} else if (j%(n/3) == 0 && i%(n/3) == 0) {
					star(n/3, r+i, c+j);
				}
			}
		}
	}
}
