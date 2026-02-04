import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] arr;
	static boolean[] usedCard;
	static int wCnt;
	static int lCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case=1; test_case<=T; test_case++) {
			arr = new int[10];
			usedCard = new boolean[19];
			wCnt = 0;
			lCnt = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=1; i<10; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				usedCard[arr[i]] = true;
			}
			
			dfs(1,0,0);
			
			System.out.println("#" + test_case + " " + wCnt + " " + lCnt);
		}
	}
	
	static void dfs(int depth, int score1, int score2) {
		if (depth == 10) {
			if (score1 > score2) {
				wCnt++;
			} else if (score1 < score2){
				lCnt++;
			}
			return;
		}
		
		for (int i=1; i<19; i++) {
			if (usedCard[i] == false) {
				if (arr[depth] > i) {
					usedCard[i] = true;
					dfs(depth+1, score1+i+arr[depth], score2);
					usedCard[i] = false;
				} else {
					usedCard[i] = true;
					dfs(depth+1, score1, score2+i+arr[depth]);
					usedCard[i] = false;
				}
				
			}
		}
	}
}
