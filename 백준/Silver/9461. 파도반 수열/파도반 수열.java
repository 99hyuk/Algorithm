import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		long[] arr = new long[101];
		arr[1] = 1;
		arr[2] = 1;
		arr[3] = 1;
		
		for (int i=4; i<=100; i++) {
			arr[i] = arr[i-2] + arr[i-3];
		}
		
		for (int t=0; t<T; t++) {
			int n = sc.nextInt();
			System.out.println(arr[n]);
		}
	}
}
