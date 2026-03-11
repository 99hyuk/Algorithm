import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] youngMap;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		youngMap = new int[500001];
		Arrays.fill(youngMap, -1);
		
		int pos = K;
		int accel = 0;
		int time = 0;
		while(pos<=500000) {
			youngMap[pos] = time++;
			accel++;
			pos = pos + accel;
		}
		
		bfs();
		System.out.println(min);
	}
	
	static void bfs() {
		Queue<Integer> Q = new ArrayDeque<>();
		boolean[][] visited = new boolean[500001][2];
		Q.offer(N);
		visited[N][0] = true;
		
		boolean finsih = false;
		int time = 0;
		while(!Q.isEmpty()) {
			
			int size = Q.size();
			for (int i=0; i<size; i++) {
				int pos = Q.poll();
				
				if(youngMap[pos] >= time && youngMap[pos] % 2 == time % 2) {
					min = Math.min(min, youngMap[pos]);
					finsih = true;
				}
				
				if(isIn(pos+1) && !visited[pos+1][(time+1)%2]) {
					Q.offer(pos+1);
					visited[pos+1][(time+1)%2] = true;
				}
				
				if(isIn(pos*2) && !visited[pos*2][(time+1)%2]) {
					Q.offer(pos*2);
					visited[pos*2][(time+1)%2] = true;
				}
				
				if(isIn(pos-1) && !visited[pos-1][(time+1)%2]) {
					Q.offer(pos-1);
					visited[pos-1][(time+1)%2] = true;
				}
			}
			time++;
		}
		
		if(!finsih) {
			min = -1;
		}
	}
	
	static boolean isIn(int x) {
		return 0<=x && x<=500000;
	}
}
