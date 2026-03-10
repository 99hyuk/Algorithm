import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class EdgeTernal implements Comparable<EdgeTernal> {
	int u;
	int v;
	double weight;
	public EdgeTernal(int u, int v, double weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}
	@Override
	public int compareTo(EdgeTernal o) {
		return Double.compare(this.weight, o.weight);
	}
}

public class Solution {
	
	static int[] P;
	static int N;
	static double E;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			long[][] ternal = new long[N][N];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				ternal[i][0] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				ternal[i][1] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			P = new int[N];
			Arrays.fill(P, -1);
			
			List<EdgeTernal> list = new ArrayList<>();
			
			for (int i=0; i<N-1; i++) {
				for (int j=i+1; j<N; j++) {
					double distance = E * (Math.pow(ternal[i][0] - ternal[j][0], 2) + Math.pow(ternal[i][1] - ternal[j][1], 2));
					list.add(new EdgeTernal(i, j, distance));
				}
			}
			
			Collections.sort(list);
			
			int totalCnt = 0;
			double totalWeight = 0;
			
			for (EdgeTernal edgeTernal : list) {
				if(union(edgeTernal.u, edgeTernal.v)) {
					totalCnt++;
					totalWeight += edgeTernal.weight;
				}
				if (totalCnt == N-1) {
					long finalWeight = Math.round(totalWeight);
					sb.append("#" + t + " " + finalWeight + "\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
	
	static int find(int x) {
		if (P[x] < 0) {
			return x;
		}
		
		return P[x] = find(P[x]);
	}
	
	static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u==v) return false;
		
		P[v] = u;
		return true;
	}
}
