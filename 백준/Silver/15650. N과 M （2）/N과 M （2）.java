import java.util.Scanner;

public class Main {

	static int n, m;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		visited = new boolean[n + 1];

		dfs(0, new int[m+1]);
	}
	
	static void dfs(int idx, int arr[]) {
		if (idx == 0) {
			arr[idx] = 1;
		}
		
		if (idx==m) {
			for (int i=1; i<=m; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i=arr[idx]; i<=n; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				arr[idx+1] = i;
				dfs(idx+1, arr);
				visited[i] = false;
			}
		}
	}
}
