import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for (int T=0; T<t; T++) {
			String command = br.readLine();
			int size = Integer.parseInt(br.readLine());
			ArrayDeque<Integer> deque = new ArrayDeque<>();
			
			String arrStr = br.readLine();
			
			int cnt=0;
			int index = 0;
			int startIdx = -1;
			int endIdx = -1;
			while (cnt < size) {
				if (size == 0) {
					break;
				}
				
				if (!(arrStr.charAt(index) < '0' || arrStr.charAt(index) > '9')) {
					if(startIdx == -1) {
						startIdx = index;
						endIdx = index;
					} else {
						endIdx++;
					}
					
				} else {
					if (startIdx != -1 && endIdx != -1) {
						deque.offer(Integer.parseInt(arrStr.substring(startIdx, endIdx+1)));
						startIdx = -1;
						endIdx = -1;
						cnt++;
					}
				}
				index++;
			}

			boolean curDir = true;
			boolean notEmpty = true;
			for (int i=0; i<command.length(); i++) {
				char c = command.charAt(i);
				
				if (c == 'R') {
					if (curDir) {
						curDir = false;
					} else {
						curDir = true;
					}
				} else if(c == 'D') {
					if (deque.size() > 0) {
						if (curDir) {
							deque.removeFirst();
						} else {
							deque.removeLast();
						}
					} else {
						sb.append("error\n");
						notEmpty = false;
						break;
					}
				}
			}
			
			if(notEmpty) {
				sb.append("[");
				int qSize = deque.size(); 
				for (int i=0; i<qSize; i++) {
					if(curDir) {
						if (i == qSize-1) {
							sb.append(deque.poll());
						} else {
							sb.append(deque.poll() + ",");
						}
						
					} else {
						if (i == qSize-1) {
							sb.append(deque.pollLast());
						} else {
							sb.append(deque.pollLast() + ",");
						}
					}
				}
				sb.append("]\n");
				
			}
			
		}
		System.out.println(sb);
	}
}
