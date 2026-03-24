import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		int sum = 0;
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		
		if (sum < S) {
			System.out.println(0);
			return;
		}
		
		int left = 0;
		int right = 1;
		
		if(arr[left] >= S || arr[right] >= S) {
			System.out.println(1);
			return;
		}
		
		int length = Integer.MAX_VALUE;
		int total = arr[left] + arr[right];
		while(left <= right && right < N) {
			if (total >= S) {
				length = Math.min(length, right-left+1);
			}

			if (total < S) {
				right++;
				if (right >= N) break;
				total += arr[right];
			} else {
				total -= arr[left++];
			}
			
		}
		
		System.out.println(length);
	}
}
