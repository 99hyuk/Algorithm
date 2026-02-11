import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int R, C;
	static int cnt=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		recursive(N, 0, 0);
	}
	
	static void recursive(int n, int r, int c) {
		if (n==1) {
			for (int i=r; i<r+2; i++) {
				for (int j=c; j<c+2; j++) {
					if (i==R && j==C) {
						System.out.println(cnt);
						return;
					}
					cnt++;
				}
			}
			return;
		}
		
		int size = (int)Math.pow(2, n);
		
		if(R < r+size/2 && C < c+size/2) {
			recursive(n-1, r, c);
		} else if(R < r+size/2 && C >= c+size/2) {
			cnt += size/2 * size/2;
			recursive(n-1, r, c+size/2);
		} else if(R >= r+size/2 && C < c+size/2) {
			cnt += (size/2) * (size/2) * 2;
			recursive(n-1, r+size/2, c);
		} else if(R >= r+size/2 && C >= c+size/2) {
			cnt += (size/2) * (size/2) * 3;
			recursive(n-1, r+size/2, c+size/2);
		}
	}
}
