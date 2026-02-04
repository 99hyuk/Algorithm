import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] arr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);

		dfs(0, new int[N]);

		System.out.println(sb);
	}

	static void dfs(int idx, int[] check) {
		if (idx == M) {
			for (int i=0; i<M; i++) {
				sb.append(check[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				check[idx] = arr[i];
				dfs(idx+1, check);
				visited[i] = false;
			}
		}
	}
}
