import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {Integer.parseInt(st.nextToken()), 1});
		sb.append("0 ");
		
		int index = 1;
		for (int i=0; i<N-1; i++) {
			int next = Integer.parseInt(st.nextToken());
			index++;
			
			while(true) {
				if(stack.isEmpty()) {
					sb.append("0 ");
					break;
				}
				
				int[] curArr = stack.peek();
				int curIdx = curArr[1];
				int curHeight = curArr[0];
				
				if (curHeight < next) {
					stack.pop();
				} else {
					sb.append(curIdx + " ");
					break;
				}
			}
			
			stack.push(new int[] {next, index});
		}
		
		System.out.println(sb);
	}
}
