import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] arr, check;
	static int N, M;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		check = new int[M];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		dfs(0);

		System.out.println(sb);
	}

	static void dfs(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(check[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		int before = 0;
		
		for (int i = 0; i < N; i++) {
			if (before == arr[i]) continue;
			
			if (!visited[i]) {
			
				before = arr[i];
				
				visited[i] = true;
				check[idx] = arr[i];
				dfs(idx + 1);
				visited[i] = false;
			}
		}
	}
}
