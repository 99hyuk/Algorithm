import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		long minSum = Long.MAX_VALUE;
		int min1 = 0;
		int min2 = 0;
		int min3 = 0;
		
		for (int p1=0; p1<N-2; p1++) {
			int p2 = p1+1;
			int p3 = N-1;
			
			while(p2<p3) {
				long sum = (long)arr[p1] + (long)arr[p2] + (long)arr[p3];
				
				if(minSum > Math.abs(sum)) {
					minSum = Math.abs(sum);
					min1 = arr[p1];
					min2 = arr[p2];
					min3 = arr[p3];
				}
				
				if (sum == 0) {
					System.out.println(min1 + " " + min2 + " " + min3);
					return;
				} else if(sum < 0) {
					p2++;
				} else {
					p3--;
				}
			}
		}
		
		System.out.println(min1 + " " + min2 + " " + min3);
	}
}
