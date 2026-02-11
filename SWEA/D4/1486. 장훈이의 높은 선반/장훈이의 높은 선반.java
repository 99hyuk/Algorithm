import java.util.Scanner;

public class Solution {
	
	static int N, B, emp[], min;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case=1; test_case<=T; test_case++) {
			N = sc.nextInt();
			B = sc.nextInt();
			min = Integer.MAX_VALUE;
			emp = new int[N];
			visited = new boolean[N];
			
			for (int i=0; i<N; i++) {
				emp[i] = sc.nextInt();
			}
			
			recursive(0);
			
			System.out.println("#" + test_case + " " + min);
		}
	}
	
	static void recursive(int idx) {
		if (idx == emp.length) {
			int sum = 0;
			for (int i=0; i<N; i++) {
				if(visited[i]) {
					sum += emp[i];
				}
			}
			
			if (sum >= B) {				
				min = Math.min(min, sum-B);
			}
			
			return;
		}
		
		visited[idx] = true;
		recursive(idx+1);

		visited[idx] = false;
		recursive(idx+1);
	}
}
