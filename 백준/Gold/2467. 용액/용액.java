import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}
		
		int low = 0;
		int high = arr.length - 1;
		int min = Integer.MAX_VALUE;
		int finalLow = -1;
		int finalHigh = -1;
		
		while(low<high) {
			if (Math.abs(arr[low]+arr[high]) < Math.abs(min)) {
				min = arr[low] + arr[high];
				finalLow = low;
				finalHigh = high;
			}
				
			if (arr[low] + arr[high] > 0) {
				high--;
			} else if (arr[low] + arr[high] < 0) {
				low++;
			} else {
				System.out.println(arr[low] + " " + arr[high]);
				return;
			}
		}
		
		System.out.println(arr[finalLow] + " " + arr[finalHigh]);
	}
}
