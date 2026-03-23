import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int pushNum = 1;
		
		for (int i=0; i<N; i++) {
			int next = Integer.parseInt(br.readLine());
			
			if (next >= pushNum) {
				while(next >= pushNum) {
					stack.push(pushNum++);
					sb.append("+\n");
				}
				stack.pop();
				sb.append("-\n");
				
			} else {
				if (stack.isEmpty() || stack.peek() < next) {
					System.out.println("NO");
					return;
				} else {
					while(!stack.isEmpty() && stack.peek() >= next) {
						stack.pop();
						sb.append("-\n");
					}
				}
			}
		}
		
		System.out.println(sb);
	}
}
