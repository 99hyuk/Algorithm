import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[301];
		
		for (int i=1; i<=n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[] score = new int[301];
		score[1] = arr[1];
		score[2] = arr[1]+arr[2];
		score[3] = Math.max(score[1]+arr[3], arr[2]+arr[3]);
		
		for (int i=4; i<=n; i++) {
			score[i] = Math.max(score[i-2] + arr[i], score[i-3] + arr[i-1] + arr[i]);
			
		}
		
		System.out.println(score[n]);
	}
}
