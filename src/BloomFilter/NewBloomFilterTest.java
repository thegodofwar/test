package BloomFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewBloomFilterTest {
    static int elementCount = 50000; // Number of elements to test

    public static void printStat(long start, long end) {
        double diff = (end - start) / 1000.0;
        System.out.println(diff + "s, " + (elementCount / diff) + " elements/s");
    }

    public static void main(String[] argv) {

        final Random r = new Random();

        // Generate elements first
        List<String> existingElements = new ArrayList(elementCount);
        for (int i = 0; i < elementCount; i++) {
            byte[] b = new byte[200];
            r.nextBytes(b);
            existingElements.add(new String(b));
        }
        
        List<String> nonExistingElements = new ArrayList(elementCount);
        for (int i = 0; i < elementCount; i++) {
            byte[] b = new byte[200];
            r.nextBytes(b);
            nonExistingElements.add(new String(b));
        }

        NewBloomFilter<String> bf = new NewBloomFilter<String>(0.001, elementCount);

        System.out.println("Testing " + elementCount + " elements");
        System.out.println("k is " + bf.getK());

        // Add elements
        System.out.print("add(): ");
        long start_add = System.currentTimeMillis();
        for (int i = 0; i < elementCount; i++) {
            bf.add(existingElements.get(i));
        }
        long end_add = System.currentTimeMillis();
        printStat(start_add, end_add);

        // Check for existing elements with contains()
        System.out.print("contains(), existing: ");
        long start_contains = System.currentTimeMillis();
        for (int i = 0; i < elementCount; i++) {
            bf.contains(existingElements.get(i));
        }
        long end_contains = System.currentTimeMillis();
        printStat(start_contains, end_contains);

        // Check for nonexisting elements with contains()
        System.out.print("contains(), nonexisting: ");
        long start_ncontains = System.currentTimeMillis();
        for (int i = 0; i < elementCount; i++) {
            bf.contains(nonExistingElements.get(i));
        }
        long end_ncontains = System.currentTimeMillis();
        printStat(start_ncontains, end_ncontains);

    }
}
