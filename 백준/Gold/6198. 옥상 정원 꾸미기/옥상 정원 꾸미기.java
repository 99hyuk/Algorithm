import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		long totalCnt = 0;
		for (int i=0; i<N; i++) {
			int next = Integer.parseInt(br.readLine());
			
			while(!stack.isEmpty() && stack.peek() <= next) {
				stack.pop();
			}
			
			totalCnt += stack.size();
			stack.push(next);
		}
		
		System.out.println(totalCnt);
	}
}
