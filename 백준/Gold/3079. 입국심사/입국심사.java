import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long M;
	static long arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		
		arr = new long[N];
		for (int i=0; i<N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		long low = 1;
		long high = 1_000_000_000_000_000_000L;
		long result = high;
		while (low<=high) {
			long mid = (low + high) / 2;
			if(ParaMetricSearch(mid)) {
				result = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		System.out.println(result);
	}
	
	static boolean ParaMetricSearch(long t) {
		long sum=0;
		for (long i : arr) {
			sum += t / i;
			if (sum >= M) return true;
		}
		return sum >= M;
	}
}
