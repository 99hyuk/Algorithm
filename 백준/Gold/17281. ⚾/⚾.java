import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] arr;
	static boolean[] visited = new boolean[9];
	static int[] order = new int[9];
	static int maxScore = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][9];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.print(maxScore);
	}
	
	static void dfs(int depth) {
		if (depth == 9) {
			playBall();
			return;
		}
		
		if (depth == 3) {
			order[depth] = 0;
			dfs(depth+1);
			return;
		}
		
		for (int i=1; i<9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				order[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static void playBall() {
		int score = 0;
		int nextOrder = 0;
		for (int i=0; i<N; i++) {
			int out = 0;
			boolean[] base = new boolean[3];
			while (out < 3) {
				if (out == 3) {
					break;
				}
				
				switch (arr[i][order[nextOrder]]) {
				case 1:
					if (base[2]) score++;
					base[2] = base[1];
					base[1] = base[0];
					base[0] = true;
					break;
					
				case 2:
					if (base[2]) score++;
					if (base[1]) score++;
					base[2] = base[0];
					base[1] = true;
					base[0] = false;
					break;
				case 3:
					if (base[2]) score++;
					if (base[1]) score++;
					if (base[0]) score++;
					base[2] = true;
					base[1] = false;
					base[0] = false;
					break;
				case 4:
					if (base[0]) score++;
					if (base[2]) score++;
					if (base[1]) score++;
					score++;
					base[2] = false;
					base[1] = false;
					base[0] = false;
					break;
				case 0:
					out++;
					break;
				}
				
				nextOrder = (nextOrder+1) % 9;
			}
			
		}
		maxScore = Math.max(maxScore, score);
	}

}
