import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int g = Integer.parseInt(br.readLine());
		int p = Integer.parseInt(br.readLine());
		arr = new int[g+1];
		Arrays.fill(arr, -1);
		
		int cnt = 0;
		for (int i=1; i<=p; i++) {
			int gate = Integer.parseInt(br.readLine());
			
			int goal = find(gate);
			
			if(goal == 0) break;
			
			arr[goal] = goal-1;
			cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static int find(int x) {
		if (arr[x] < 0) {
			return x;
		}
		
		return arr[x] = find(arr[x]);
	}
}
