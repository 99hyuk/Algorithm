import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


class Atom {
	int x,y,state,k;
	Atom (int x, int y, int state, int k) {
		this.x = (x+1000)<<1;
		this.y = (y+1000)<<1;
		this.state = state;
		this.k = k;
	}
}

public class Solution {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	static Atom[] atoms;
	static int totalEnergy;
	static int[][] scoreMap = new int[4001][4001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine().trim());
			atoms = new Atom[N];
			totalEnergy = 0;
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				atoms[i] = new Atom(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			bfs();
			
			System.out.println("#" + test_case + " " + totalEnergy);
		}	
	}
	
	static void bfs() {
		Queue<Atom> Q = new ArrayDeque<Atom>();
		for (Atom atom : atoms) {
			Q.offer(atom);
			scoreMap[atom.y][atom.x] = atom.k;
		}
		
		while(!Q.isEmpty()) {
			Atom atom = Q.poll();
			
			if (scoreMap[atom.y][atom.x] != atom.k) {
				totalEnergy += scoreMap[atom.y][atom.x];
				scoreMap[atom.y][atom.x] = 0;
				continue;
			}
			
			scoreMap[atom.y][atom.x] = 0;
			int ny = atom.y + dy[atom.state];
			int nx = atom.x + dx[atom.state];
			
			if (!isIn(ny,nx)) continue;
			atom.y = ny;
			atom.x = nx;
			scoreMap[atom.y][atom.x] += atom.k;
			Q.offer(atom);
		}
	}
	
	static boolean isIn(int y, int x) {
		return 0<=y && y<4001 && 0<=x && x<4001;
	}
}
