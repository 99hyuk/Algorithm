import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] arr, check;
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

		dfs(0, 0, check);

		System.out.println(sb);
	}

	static void dfs(int idx, int start, int[] check) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(check[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				check[idx] = arr[i];
				dfs(idx + 1, i, check);
				visited[i] = false;
			}
		}
	}
}
