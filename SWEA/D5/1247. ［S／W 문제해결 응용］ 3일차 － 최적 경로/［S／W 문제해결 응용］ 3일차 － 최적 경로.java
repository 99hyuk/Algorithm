import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[][] people;
	static int[] arr;
	static int n;
	static int min;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			people = new int[n+2][n+2];
			visited = new boolean[n];
			arr = new int[n];
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<n+2; i++) {
				people[i][0] = Integer.parseInt(st.nextToken());
				people[i][1] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0);
			sb.append("#" + t + " " + min + "\n");

		}
		System.out.println(sb);
	}
	
	static void dfs(int idx) {
		if(idx == n) {
			int sum = 0;
			sum += Math.abs(people[0][0] - people[arr[0]+2][0]) + Math.abs(people[0][1] - people[arr[0]+2][1]);
			sum += Math.abs(people[1][0] - people[arr[n-1]+2][0]) + Math.abs(people[1][1] - people[arr[n-1]+2][1]);
			for (int i=0; i<n-1; i++) {
				sum += Math.abs(people[arr[i]+2][0] - people[arr[i+1]+2][0]) + Math.abs(people[arr[i]+2][1] - people[arr[i+1]+2][1]);
				if (sum > min) return;
			}
			
			min = Math.min(min, sum);
			return;
		}
		
		for (int i=0; i<n; i++) {
			if(visited[i]) continue;
			arr[idx] = i;
			visited[i] = true;
			dfs(idx+1);
			arr[idx] = 0;
			visited[i] = false;
		}
	}
}
