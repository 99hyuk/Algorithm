import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int[][] home;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		home = new int[N][3];
		
		StringTokenizer st;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			home[i][0] = Integer.parseInt(st.nextToken());
			home[i][1] = Integer.parseInt(st.nextToken());
			home[i][2] = Integer.parseInt(st.nextToken());
		}
	
		int[][] dist = new int[N][3];
		int INF = 1000000;
		
		int min = Integer.MAX_VALUE;
		
		dist[0][0] = home[0][0];
		dist[0][1] = INF;
		dist[0][2] = INF;
		for (int i=1; i<N; i++) {
			dist[i][0] = Math.min(dist[i-1][1], dist[i-1][2]) + home[i][0]; 
			dist[i][1] = Math.min(dist[i-1][0], dist[i-1][2]) + home[i][1]; 
			dist[i][2] = Math.min(dist[i-1][0], dist[i-1][1]) + home[i][2]; 
		}
		int min0 = Math.min(dist[N-1][1], dist[N-1][2]);
		min = Math.min(min0, min);
		
		dist[0][0] = INF;
		dist[0][1] = home[0][1];
		dist[0][2] = INF;
		for (int i=1; i<N; i++) {
			dist[i][0] = Math.min(dist[i-1][1], dist[i-1][2]) + home[i][0]; 
			dist[i][1] = Math.min(dist[i-1][0], dist[i-1][2]) + home[i][1]; 
			dist[i][2] = Math.min(dist[i-1][0], dist[i-1][1]) + home[i][2]; 
		}
		int min1 = Math.min(dist[N-1][0], dist[N-1][2]);
		min = Math.min(min1, min);
		
		dist[0][0] = INF;
		dist[0][1] = INF;
		dist[0][2] = home[0][2];
		for (int i=1; i<N; i++) {
			dist[i][0] = Math.min(dist[i-1][1], dist[i-1][2]) + home[i][0]; 
			dist[i][1] = Math.min(dist[i-1][0], dist[i-1][2]) + home[i][1]; 
			dist[i][2] = Math.min(dist[i-1][0], dist[i-1][1]) + home[i][2]; 
		}
		int min2 = Math.min(dist[N-1][0], dist[N-1][1]);
		min = Math.min(min2, min);
		
		System.out.println(min);
	}
}
