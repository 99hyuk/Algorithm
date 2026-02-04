import java.util.Scanner;

public class Main {
	static int n,m;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		dfs(0,new int[m]);
		
		System.out.println(sb);
	}
	
	static void dfs(int idx, int[] arr) {
		if (idx == m) {
			for (int i=0; i<m; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=1; i<=n; i++) {
			arr[idx] = i;
			dfs(idx+1, arr);
		}
	}
}
