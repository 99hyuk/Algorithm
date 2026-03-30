import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Sort implements Comparable<Sort> {
	int num;

	public Sort(int num) {
		this.num = num;
	}

	@Override
	public int compareTo(Sort o) {
		return Integer.compare(this.num, o.num);
	}
}

class ReverseSort implements Comparable<ReverseSort> {
	int num;
	
	public ReverseSort(int num) {
		this.num = num;
	}

	@Override
	public int compareTo(ReverseSort o) {
		return Integer.compare(o.num, this.num);
	}
	
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<ReverseSort> PQ1 = new PriorityQueue<>();
		PriorityQueue<Sort> PQ2 = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i=1; i<=N; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if (i<3) {
				PQ1.offer(new ReverseSort(input));
			} else {
				if (input > PQ2.peek().num) {
					PQ2.offer(new Sort(input));
				} else {
					PQ1.offer(new ReverseSort(input));
				}
			}
			
			if (PQ1.size() > PQ2.size() + 1) {
				int num1 = PQ1.poll().num;
				PQ2.offer(new Sort(num1));
			} else if (PQ2.size() > PQ1.size()) {
				int num2 = PQ2.poll().num;
				PQ1.offer(new ReverseSort(num2));
			}
			
			if (i % 2 == 0) {
				int num1 = PQ1.peek().num;
				int num2 = PQ2.peek().num;
				sb.append(num1 > num2 ? num2 : num1).append("\n");
			} else {
				sb.append(PQ1.peek().num).append("\n");
			}
		}
		System.out.println(sb);
	}
}
