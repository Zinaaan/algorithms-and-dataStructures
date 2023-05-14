package algorithms.TwoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author lzn
 * @date 2023/02/27 20:38
 * @Description O(n2)
 */
public class CountingAnagrams {

    public static int countingAnagrams(String str) {
        String[] words = str.split(" ");
        HashSet<String> filterSet = new HashSet<>(Arrays.asList(words));
        HashMap<String, Integer> counts = new HashMap<>();
        for (String word : filterSet) {
            char[] chars = new char[26];
            for(char c : word.toCharArray()){
                chars[c - 'a']++;
            }
            String canonicalForm = new String(chars);
            counts.put(canonicalForm, counts.getOrDefault(canonicalForm, 0) + 1);
        }
        int countAnagrams = 0;
        for (int count : counts.values()) {
            if (count > 1) {
                countAnagrams += 1;
            }
        }
        return countAnagrams;
    }

    public static void main(String[] args) {
//        String str = "cars are very cool so are arcs and my os";
        System.out.print(countingAnagrams("cars are very cool so are arcs and my os") == 2);
        System.out.print("\n");
        System.out.print(countingAnagrams("cars are very cool so are arcs and my os uuid iduu") == 3);
        System.out.print("\n");
        System.out.print(countingAnagrams("aab abb bhu bhu aba bab") == 2);
//        int countAnagrams = countingAnagrams(str);
//        System.out.println(countAnagrams); // Output: 2
    }
}
