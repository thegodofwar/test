package BM;

import java.util.Arrays;  
import java.util.HashMap;  
import java.util.List;  
import java.util.ArrayList;  
import java.util.Map;  
  
public class BoyerMoore {  
    public static List<Integer> match(String pattern, String text) {  
        List<Integer> matches = new ArrayList<Integer>();  
        int m = text.length();  
        int n = pattern.length();  
        Map<Character, Integer> rightMostIndexes = preprocessForBadCharacterShift(pattern);  
        int alignedAt = 0;  
        while (alignedAt + (n - 1) < m) {  
            for (int indexInPattern = n - 1; indexInPattern >= 0; indexInPattern--) {  
            int indexInText = alignedAt + indexInPattern;  
            char x = text.charAt(indexInText);  
            char y = pattern.charAt(indexInPattern);  
                if (indexInText >= m) break;  
                if (x != y) {  
                    Integer r = rightMostIndexes.get(x);  
                    if (r == null) {  
                        alignedAt = indexInText + 1;  
                    }  
                    else {  
                        int shift = indexInText - (alignedAt + r);  
                        alignedAt += shift > 0 ? shift : 1;  
                    }  
                    break;  
                }  
                else if (indexInPattern == 0) {  
                    matches.add(alignedAt);  
                    alignedAt++;  
                }  
            }  
        }  
        return matches;  
    }  
    private static Map<Character, Integer> preprocessForBadCharacterShift(  
            String pattern) {  
        Map<Character, Integer> map = new HashMap<Character, Integer>();  
        for (int i = pattern.length() - 1; i >= 0; i--) {  
            char c = pattern.charAt(i);  
            if (!map.containsKey(c)) map.put(c, i);  
        }  
        return map;  
    }  
    public static void main(String[] args) {  
        List<Integer> matches = match("annn", "banaaasrnanannnawannn");  
        for (Integer integer : matches) System.out.println("Match at: " + integer);  
        System.out.println((matches.equals(Arrays.asList(1, 3)) ? "OK" : "Failed"));  
    }  
}  
