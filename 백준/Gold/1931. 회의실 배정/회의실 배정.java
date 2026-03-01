import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class NodeTime implements Comparable<NodeTime> {
	int startTime;
	int endTime;
	public NodeTime(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	@Override
	public int compareTo(NodeTime o) {
		if (this.endTime != o.endTime) {
			return Integer.compare(this.endTime, o.endTime);
		} else {
			return Integer.compare(this.startTime, o.startTime);
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		NodeTime[] list = new NodeTime[n];
		int maxCnt = 0;
		int curTime = 0;
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list[i] = new NodeTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(list);
		
		for (int i=0; i<n; i++) {
			if (list[i].startTime < curTime) continue;
			curTime = list[i].endTime;
			maxCnt++;
		}
		
		System.out.println(maxCnt);
	}
}
