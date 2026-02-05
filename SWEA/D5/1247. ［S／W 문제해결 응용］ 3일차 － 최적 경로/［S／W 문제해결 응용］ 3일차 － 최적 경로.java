import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class PointN {
	int r;
	int c;

	PointN(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Solution {
	
	static boolean[] isSelected;
	static PointN[] map;
	static PointN[] picked;
	static PointN office, home;
	static int N;
	static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new PointN[N];
			isSelected = new boolean[N];
			picked = new PointN[N];
			min = Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			office = new PointN(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new PointN(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			for (int i=0; i<N; i++) {
				map[i] = new PointN(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			dfs(0);
			
			System.out.println("#" + test_case + " " + min);
		}

	}
	
	static void dfs (int idx) {
		if (idx == N) {
			int sum = 0;
			sum += Math.abs(picked[0].r - office.r) + Math.abs(picked[0].c - office.c);
			sum += Math.abs(picked[N-1].r - home.r) + Math.abs(picked[N-1].c - home.c);
			
			for (int i=0; i<N-1; i++) {
				sum += Math.abs(picked[i+1].r - picked[i].r) + Math.abs(picked[i+1].c - picked[i].c);
                if (sum > min) return;
			}
			
			min = min < sum ? min : sum;
			
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (isSelected[i]) continue;
			
			isSelected[i] = true;
			picked[idx] = map[i];
			dfs(idx + 1);
			isSelected[i] = false;
		}
	}
}
