import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Virus {
	int r;
	int c;
	int cnt;
	int move;
	int idx;
	Virus(int r, int c, int cnt, int move, int idx) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
		this.move = move;
		this.idx = idx;
	}
}

public class Solution {
	
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,-1,1};
	static int N,M,K;
	static Virus[] virusList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			virusList = new Virus[K+1];
			
			for (int i=1; i<=K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int move = Integer.parseInt(st.nextToken());

				virusList[i] = new Virus(r,c,cnt,move,i);
			}
			
			for (int i=0; i<M; i++) {
				move();
			}
			
			int totalCnt = 0;
			for (int i=1; i<=K; i++) {
				totalCnt += virusList[i].cnt;
			}
			
			sb.append("#" + t + " " + totalCnt + "\n");
		}
		System.out.println(sb);
	}
	
	static void move() {
		int[][] tempMap = new int[N][N];
		for (int i=1; i<=K; i++) {
			if(virusList[i].cnt == 0) continue;
			
			Virus virus = virusList[i];
			int r = virus.r;
			int c = virus.c;
			int nr = r + dr[virus.move];
			int nc = c + dc[virus.move];
			virus.r = nr;
			virus.c = nc;
			
			if (nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
				virus.cnt /= 2;
				if (virus.move == 1 || virus.move == 3) {
					virus.move += 1;					
				} else {
					virus.move -= 1;
				}
				continue;
			}
			
			tempMap[nr][nc]++;
		}
		
		for (int j=0; j<N; j++) {
			for (int k=0; k<N; k++) {
				if (tempMap[j][k] > 1) {  
					composition(j, k);
				}
			}
		}
	}
	
	static void composition(int r, int c) {
		List<Virus> list = new ArrayList<>();
		
		for (int i=1; i<=K; i++) {
			Virus virus = virusList[i];
			if(virus.r == r && virus.c == c) {
				list.add(virus);
			}
		}
		
		int maxIdx = -1;
		int maxCnt = -1;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).cnt > maxCnt) {
				maxIdx = list.get(i).idx;
				maxCnt = list.get(i).cnt;
			}
		}
		
		for (int i=0; i<list.size(); i++) {
			if (maxIdx == list.get(i).idx) continue;
			virusList[maxIdx].cnt += list.get(i).cnt;
			list.get(i).cnt = 0;
		}
	}
}
