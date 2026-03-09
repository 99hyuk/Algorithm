import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] map = new int[100001];
		
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(n);
		
		boolean[] visited = new boolean[100001];
		visited[n] = true;
		
		int[] parents = new int[100001];
		int cnt = 1;
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			
			for (int i=0; i<size; i++) {
				int curIdx = Q.poll();
				
				if (curIdx == k) {
					System.out.println(cnt-1);
					Stack<Integer> stack = new Stack<>();
					
					while(curIdx != n) {
						stack.push(curIdx);
						curIdx = parents[curIdx];
					}
					stack.push(n);
					
					while(!stack.isEmpty()) {
						sb.append(stack.pop() + " ");
					}
					
					System.out.println(sb);
					
					return;
				}
				
				int next = curIdx+1;
				if(isIn(next) && !visited[next]) {
					Q.offer(next);
					map[next] = cnt;
					parents[next] = curIdx;
					visited[next] = true;
				}
				
				next = curIdx-1;
				if(isIn(next) && !visited[next]) {
					Q.offer(next);
					map[next] = cnt;
					parents[next] = curIdx;
					visited[next] = true;
				}
				
				next = curIdx*2;
				if(isIn(next) && !visited[next]) {
					Q.offer(next);
					map[next] = cnt;
					parents[next] = curIdx;
					visited[next] = true;
				}
			}
			cnt++;
		}
		
		
	}
	
	static boolean isIn(int x) {
		return 0<=x && x<=100000;
	}
}
