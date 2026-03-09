import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		int[] fibo = new int[2000001];
		fibo[1000000] = 0;
		fibo[1000001] = 1;
		fibo[999999] = 1;
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt() + 1000000;
		
		for (int i=1; i<=1000000; i++) {
			fibo[1000000+i] = (fibo[1000000+i-1] + fibo[1000000+i-2]) % 1_000_000_000;
			
			fibo[1000000-i] = (fibo[1000000-i+2] - fibo[1000000-i+1]) % 1_000_000_000;
		}
		
		int k = 0;
		if (fibo[n] > 0) {
			k = 1;
		} else if (fibo[n] < 0) {
			k = -1;
		}
		System.out.println(k);
		System.out.println(Math.abs(fibo[n]));
	}
}
