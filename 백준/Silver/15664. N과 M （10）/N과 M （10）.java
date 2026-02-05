import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, arr[], pick[];
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		pick = new int[M];
		isSelected = new boolean[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		dfs(0, 0);

		System.out.println(sb);
	}

	static void dfs(int cnt, int start) {
		if (cnt == M) {
			for (int i = 0; i < cnt; i++) {
				//System.out.println(pick[i]);
				sb.append(pick[i] + " ");
			}
			sb.append("\n");
			return;
		}

		int before = 0;

		for (int i = start; i < N; i++) {
			if (isSelected[i] || arr[i] == before)
				continue;

			before = arr[i];
			isSelected[i] = true;
			pick[cnt] = arr[i];
			dfs(cnt + 1, i);
			isSelected[i] = false;
		}
	}
}
