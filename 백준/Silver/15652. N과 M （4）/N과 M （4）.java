import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dfs(1, new int[M + 1]);
		System.out.println(sb);
	}

	static void dfs(int idx, int[] arr) {
		if (idx == 1) {
			arr[0] = 1;
		}

		if (idx == M + 1) {
			for (int i = 1; i <= M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = arr[idx - 1]; i <= N; i++) {
			arr[idx] = i;
			dfs(idx + 1, arr);
		}
	}

}
