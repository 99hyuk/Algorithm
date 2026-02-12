import java.util.Scanner;

public class Main {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int N;
	static int[] arr;
	static int[] calCount = new int[4];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		
		for (int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		for (int i=0; i<4; i++) {
			calCount[i] = sc.nextInt();
		}
		
		dfs(1, arr[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int idx, int sum) {
		
		if (idx == N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if (calCount[i] != 0) {
				if (i==0) {
					int result = sum + arr[idx];
					calCount[i]--;
					dfs(idx+1, result);
					calCount[i]++;
				} else if (i==1) {
					int result = sum - arr[idx];
					calCount[i]--;
					dfs(idx+1, result);
					calCount[i]++;
				} else if(i==2) {
					int result = sum * arr[idx];
					calCount[i]--;
					dfs(idx+1, result);
					calCount[i]++;
				} else if(i==3) {
					int result = sum / arr[idx];
					calCount[i]--;
					dfs(idx+1, result);
					calCount[i]++;
				}
			}
		}
	}
}
