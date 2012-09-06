package TopKMinHeap;

import java.util.Random;

public class TopKMinHeap {

	public static void main(String[] args) {
		// the size of the array
		int number = 100000000;
		// the top k values
		int k = 100;
		// the range of the values in the array
		int range = 1000000001;

		// input for minHeap based method
		int[] array = new int[number];

		Random random = new Random();
		for (int i = 0; i < number; i++) {
			array[i] = random.nextInt(range);
		}

		TopKMinHeap thh = new TopKMinHeap();

		long t1, t2;
		// start time
		t1 = System.currentTimeMillis();
		int[] top = thh.topHundred(array, k);

		// end time
		t2 = System.currentTimeMillis();
		System.out.println("The total execution time of "
				+ "quicksort based method is " + (t2 - t1) + " millisecond!");

		// print out the top k largest values in the top array
		System.out.println("The top " + k + "largest values are:");
		for (int i = 0; i < k; i++) {
			System.out.println(top[i]);
		}
	}

	public int[] topHundred(int[] array, int k) {
		// the heap with size k
		int[] top = new int[k];

		for (int i = 0; i < k; i++) {
			top[i] = array[i];
		}

		buildMinHeap(top);

		for (int i = k; i < array.length; i++) {
			if (top[0] < array[i]) {
				top[0] = array[i];
				minHeapify(top, 0, top.length);
			}
		}

		return top;
	}

	// create a min heap
	public void buildMinHeap(int[] array) {
		int heapSize = array.length;
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			minHeapify(array, i, heapSize);
		}
	}

	// / MinHeapify is to build the min heap from the 'position'
	public void minHeapify(int[] array, int position, int heapSize) {
		int left = left(position);
		int right = right(position);
		int maxPosition = position;

		if (left < heapSize && array[left] < array[position]) {
			maxPosition = left;
		}

		if (right < heapSize && array[right] < array[maxPosition]) {
			maxPosition = right;
		}

		if (position != maxPosition) {
			swap(array, position, maxPosition);
			minHeapify(array, maxPosition, heapSize);
		}
	}

	public void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// / return the left child position
	public int left(int i) {
		return 2 * i + 1;
	}

	// / return the right child position
	public int right(int i) {
		return 2 * i + 2;
	}
}
