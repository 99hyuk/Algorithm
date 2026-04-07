import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int r;
	int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Wait {
	int ArriveTime;
	int waitTime;
	public Wait(int arriveTime, int waitTime) {
		ArriveTime = arriveTime;
		this.waitTime = waitTime;
	}
}

public class Solution {
	
	static int n;
	static int[][] map;
	static List<Pos> people;
	static List<Pos> stairs;
	static int[] selected;
	static int[][] dp;
	static int minTime;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			minTime = Integer.MAX_VALUE;
			
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						people.add(new Pos(i, j));
					} else if(map[i][j] > 1) {
						stairs.add(new Pos(i, j));
					}
				}
			}
			
			selected = new int[people.size()];
			dp = new int[people.size()][2];
			
			for (int i=0; i<people.size(); i++) {
				dp[i][0] = Math.abs(stairs.get(0).r - people.get(i).r) 
						+ Math.abs(stairs.get(0).c - people.get(i).c);
				
				dp[i][1] = Math.abs(stairs.get(1).r - people.get(i).r) 
						+ Math.abs(stairs.get(1).c - people.get(i).c);
			}
			
			recursive(0);
			
			sb.append("#").append(t).append(" ").append(minTime).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void recursive(int idx) {
		if (idx == people.size()) {
			simulate();
			return;
		}
		
		selected[idx] = 0;
		recursive(idx+1);
		selected[idx] = 1;
		recursive(idx+1);
	}
	
	static void simulate() {
		List<Integer> stair0 = new ArrayList<>();
		List<Integer> stair1 = new ArrayList<>();
		
		for (int i=0; i<people.size(); i++) {
			if (selected[i] == 0) {
				stair0.add(dp[i][0]);
			} else {
				stair1.add(dp[i][1]);
			}
		}
		
		Collections.sort(stair0);
		Collections.sort(stair1);
		
		int time1 = calculate(stair0, map[stairs.get(0).r][stairs.get(0).c]);
		int time2 = calculate(stair1, map[stairs.get(1).r][stairs.get(1).c]);
		
		int time = Math.max(time1, time2);
		
		minTime = Math.min(time, minTime);
	}
	
	static int calculate(List<Integer> arriveTimes, int stairK) {
	    if (arriveTimes.isEmpty()) return 0;

	    int[] finishTime = new int[arriveTimes.size()];

	    for (int i = 0; i < arriveTimes.size(); i++) {
	        int arrive = arriveTimes.get(i);
	        
	        int readyTime = arrive + 1;

	        if (i < 3) {
	            finishTime[i] = readyTime + stairK;
	        } else {
	            int waitTime = Math.max(readyTime, finishTime[i - 3]);
	            finishTime[i] = waitTime + stairK;
	        }
	    }

	    return finishTime[finishTime.length - 1];
	}
}
