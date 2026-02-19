import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class NodePre {
	int idx;
	int cost;
	NodePre(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

public class Main {
	
	static int N, M;
	static int[] checkedArray;
	static List<List<Integer>> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		
		for (int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		checkedArray = new int[N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(start).add(to);
			checkedArray[to]++;
		}
		
		topologicalSort();
		
	}
	
	static void topologicalSort() {
		Queue<NodePre> Q = new ArrayDeque<>();
		int[] pre = new int[N+1];
		for (int i=1; i<=N; i++) {
			if (checkedArray[i] == 0) {
				Q.offer(new NodePre(i, 1));
				pre[i] = 1;
			}
		}
		
		List<Integer> visitedList = new ArrayList<>();
		
		while(!Q.isEmpty()) {
			NodePre node = Q.poll();
			visitedList.add(node.idx);
			
			for (int next : list.get(node.idx)) {
				checkedArray[next]--;
				if (checkedArray[next] == 0) {
					Q.offer(new NodePre(next, node.cost+1));
					pre[next] = node.cost+1;
				}
			}
		}
		
		for (int i=1; i<=N; i++) {
			System.out.print(pre[i] + " ");
		}
		
		if (visitedList.size() == N) {
			return;
		}
	}
}
