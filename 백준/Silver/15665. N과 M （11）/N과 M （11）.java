import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N, M, arr[], picked[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		picked = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		dfs(0);
		
		System.out.println(sb);
		
	}
	
	static void dfs(int idx) {
		if (idx == M) {
			for (int i=0; i<M; i++) {
				sb.append(picked[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		int before = 0;
		
		for (int i=0; i<N; i++) {
			if (before == arr[i]) continue;
			
			before = arr[i];
			picked[idx] = arr[i];
			dfs(idx+1);
		}
	}
}
