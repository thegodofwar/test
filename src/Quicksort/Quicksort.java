package Quicksort;

import java.util.Comparator;
import java.util.Random;
 
public class Quicksort {
    public static final Random RND = new Random();
 
    private static void swap(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
 
    private static <E> int partition(E[] array, int begin, int end, Comparator<? super E> cmp) {
        int index = begin + RND.nextInt(end - begin + 1);
        E pivot = array[index];
        swap(array, index, end);        
        for (int i = index = begin; i < end; ++ i) {
            if (cmp.compare(array[i], pivot) <= 0) {
                swap(array, index++, i);
            }
        }
        swap(array, index, end);        
        return (index);
    }
 
    private static <E> void qsort(E[] array, int begin, int end, Comparator<? super E> cmp) {
        if (end > begin) {
            int index = partition(array, begin, end, cmp);
            qsort(array, begin, index - 1, cmp);
            qsort(array, index + 1,  end,  cmp);
        }
    }
 
    public static <E> void sort(E[] array, Comparator<? super E> cmp) {
        qsort(array, 0, array.length - 1, cmp);
    }
}
