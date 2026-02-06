import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int N, M, C, max, finalMax;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			finalMax = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int[] scores = new int[N];

			for (int i = 0; i < N; i++) {
				max = 0;
				for (int j = 0; j <= N - M; j++) {
					dfs(0, new int[M], i, j);
					scores[i] = max;
					
				}
			}
			
			Arrays.sort(scores);
			
			finalMax = scores[N-2] + scores[N-1];
			
			System.out.println("#" + test_case + " " + finalMax);
		}
		
	}

	static void dfs(int cnt, int[] arr, int posR, int posC) {
		if (cnt == M) {
			int sum = 0;
			for (int i=0; i<arr.length; i++) {
				sum += arr[i];
				if (sum > C) return;
			}
			
			int finalSum = 0;
			for (int i=0; i<arr.length; i++) {
				finalSum += arr[i]*arr[i];
			}
			max = Math.max(max, finalSum);
			return;
		}
		
		arr[cnt] = map[posR][posC];
		dfs(cnt+1, arr, posR, posC+1);
		arr[cnt] = 0;
		
		dfs(cnt+1, arr, posR, posC+1);
		
	}
}
