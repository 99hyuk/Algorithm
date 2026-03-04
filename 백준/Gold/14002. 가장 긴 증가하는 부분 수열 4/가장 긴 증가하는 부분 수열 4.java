import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		int[] parents = new int[n];
		Arrays.fill(parents, -1);
		Arrays.fill(dp, 1);
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<i; j++) {
				if (arr[i] > arr[j]) {
					if (dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
						parents[i] = j;
					}
				}
			}
		}
		
		int maxLength = 0;
		int maxIndex = 0;
		for (int i=0; i<n; i++) {
			if (maxLength < dp[i]) {
				maxLength = dp[i];
				maxIndex = i;
			}
		}
		
		System.out.println(maxLength);
		
		ArrayList<Integer> list = new ArrayList<>();
		
		while(true) {
			list.add(arr[maxIndex]);
			
			if (parents[maxIndex] == -1) break;
			maxIndex = parents[maxIndex];
			
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<list.size(); i++) {
			sb.append(list.get(list.size()-1-i) + " ");
		}
		System.out.println(sb);
	}
}
