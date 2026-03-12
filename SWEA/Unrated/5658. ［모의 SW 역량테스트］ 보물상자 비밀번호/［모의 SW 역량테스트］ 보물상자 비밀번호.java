import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, K;
	static char[] arr;
	static List<Integer> list;
	static int[] sixteen;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		sixteen = new int[100];
		sixteen['0'] = 0;
		sixteen['1'] = 1;
		sixteen['2'] = 2;
		sixteen['3'] = 3;
		sixteen['4'] = 4;
		sixteen['5'] = 5;
		sixteen['6'] = 6;
		sixteen['7'] = 7;
		sixteen['8'] = 8;
		sixteen['9'] = 9;
		sixteen['A'] = 10;
		sixteen['B'] = 11;
		sixteen['C'] = 12;
		sixteen['D'] = 13;
		sixteen['E'] = 14;
		sixteen['F'] = 15;
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			arr = new char[N];
			
			String str = br.readLine();
			for (int i=0; i<N; i++) {
				arr[i] = str.charAt(i);
			}
			
			for (int i=0; i<N/4; i++) {
				calculate();
				rotate();
			}
			
			Collections.sort(list);
			Collections.reverse(list);
			
			
			int cnt = 1;
			int temp = -1;
			for (int next : list) {
				if(next == temp) continue;
				if(cnt == K) {
					sb.append("#" + t + " " + next + "\n");
					break;
				}
				temp = next;
				cnt++;
			}
		}
		System.out.println(sb);
	}
	
	static void calculate() {
		int idx = 0;
		while(idx <= N-1) {
			int sum = 0;
			for (int i=idx; i<idx+N/4; i++) {
				int order = i % (N/4);
				sum += sixteen[arr[i]] * Math.pow(16, (N/4) - 1 - order);
			}
			list.add(sum);
			idx += N/4;
		}
	}
	
	static void rotate() {
		char temp = arr[N-1];
		for (int i=N-1; i>=1; i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = temp;
	}
}
