import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n, m;
	static boolean[][][][] isChecked;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			n = sc.nextInt();
			m = sc.nextInt();

			isChecked = new boolean[10][10][10][10];
			isChecked[num1000(n)][num100(n)][num10(n)][num1(n)] = true;

			int fin = bfs();
			if (fin == -1) {
				System.out.println("Impossible");
			} else {
				System.out.println(fin);
			}

		}
	}

	static int bfs() {

		Queue<Integer> Q = new ArrayDeque<>();
		int cnt = 0;

		Q.offer(n);

		while (!Q.isEmpty()) {
			int size = Q.size();

			for (int i = 0; i < size; i++) {
				int num = Q.poll();
				
				if (num == m) {
					return cnt;
				}

				for (int idx = 0; idx <= 9; idx++) {
					if (!isChecked[num1000(num)][num100(num)][num10(num)][idx]) {
						isChecked[num1000(num)][num100(num)][num10(num)][idx] = true;
						int nextNum = num1000(num) * 1000 + num100(num) * 100 + num10(num) * 10 + idx;
						if (priNum(nextNum)) {
							Q.offer(nextNum);
						}
					}

					if (!isChecked[num1000(num)][num100(num)][idx][num1(num)]) {
						isChecked[num1000(num)][num100(num)][idx][num1(num)] = true;
						int nextNum = num1000(num) * 1000 + num100(num) * 100 + idx * 10 + num1(num);
						if (priNum(nextNum)) {
							Q.offer(nextNum);
						}
					}

					if (!isChecked[num1000(num)][idx][num10(num)][num1(num)]) {
						isChecked[num1000(num)][idx][num10(num)][num1(num)] = true;
						int nextNum = num1000(num) * 1000 + idx * 100 + num10(num) * 10 + num1(num);
						if (priNum(nextNum)) {
							Q.offer(nextNum);
						}
					}

					if (idx != 0 && !isChecked[idx][num100(num)][num10(num)][num1(num)]) {
						isChecked[idx][num100(num)][num10(num)][num1(num)] = true;
						int nextNum = idx * 1000 + num100(num) * 100 + num10(num) * 10 + num1(num);
						if (priNum(nextNum)) {
							Q.offer(nextNum);
						}
					}

				}
			}

			cnt++;
		}

		return -1;
	}

	static boolean priNum(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	static int num1000(int num) {
		return num / 1000;
	}

	static int num100(int num) {
		return (num - (num / 1000) * 1000) / 100;
	}

	static int num10(int num) {
		return (num - (num / 100) * 100) / 10;
	}

	static int num1(int num) {
		return num % 10;
	}
}
