package LoserTree;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

interface Player {
	public boolean beat(Player p);
}

class PlayerImp implements Player {
	public int value;

	public PlayerImp(int value) {
		this.value = value;
	}

	public boolean beat(Player p) {
		return this.value < ((PlayerImp) p).value;
	}

	@Override
	public String toString() {
		return "" + value;
	}
}

public class LoserTree {
	private Player[] ps;
	private int size;
	private int[] tree;

	public LoserTree(Player[] ps) {
		// ps length should >=2
		this.size = ps.length;
		tree = new int[size];
		this.ps = ps;
		tree[0] = makeLoserTree(1);
	}

	private int makeLoserTree(int cn) {
		if (cn >= size) {
			return cn - size;
		}
		int left = cn * 2;
		int right = cn * 2 + 1;
		int leftWinner = makeLoserTree(left);
		int rightWinner = makeLoserTree(right);
		if (ps[leftWinner].beat(ps[rightWinner])) {
			tree[cn] = rightWinner;
			return leftWinner;
		} else {
			tree[cn] = leftWinner;
			return rightWinner;
		}
	}

	public Player changeWinnerPalyer(Player newPlayer) {
		if (newPlayer.beat(ps[tree[0]])) {
			return newPlayer;
		}
		Player ret = ps[tree[0]];
		ps[tree[0]] = newPlayer;
		int cur = tree[0];
		int next = (tree[0] + size) / 2;
		while (next > 0) {
			if (ps[cur].beat(ps[tree[next]]) == false) {
				int t = cur;
				cur = tree[next];
				tree[next] = t;
			}
			next /= 2;
		}
		tree[0] = cur;
		return ret;
	}

	public static void main(String[] args) {
		int myArray[]= {17,5,10,29,15};
		int changeArray[]={12,4,11,7,30};
		Random rand = new Random();
		//int len = 16;
		int len=5;
		Player[] ps = new PlayerImp[len];
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i = 0; i < len; i++) {
			//int d = rand.nextInt(len);
			int d=myArray[i];
			ps[i] = new PlayerImp(d);
			q.add(d);
		}
		Player[] newps = new Player[len];
		int[] tmp = new int[len];
		for (int i = 0; i < len; i++) {
			//int d = rand.nextInt(len);
			int d=changeArray[i];
			newps[i] = new PlayerImp(d);
			tmp[i] = d;
		}

		System.out.println(Arrays.toString(ps));
		System.out.println(Arrays.toString(newps));

		LoserTree lt = new LoserTree(ps);
		for (int i = 0; i < len; i++) {
			Player p = lt.changeWinnerPalyer(newps[i]);
			System.out.print(p + " ");
		}
		System.out.println();
		for (int d : tmp) {
			q.add(d);
			System.out.print(q.poll() + " ");
		}
	}
}
