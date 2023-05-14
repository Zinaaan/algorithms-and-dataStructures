package algorithms.SlidingWindows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class RepeatedDNA {

//    public static List<String> findRepeatedSequences(String s, int k) {
//      int windowSize = k;
//      if (s.length()<= windowSize) {
//        return new ArrayList<String>();
//      }
//      int base = 4;
//      int hiPlaceValue = (int) Math.pow(base, windowSize);
//      Map<Character, Integer> mapping = new HashMap<Character, Integer>() {
//        {
//          put('A', 1);
//          put('C', 2);
//          put('G', 3);
//          put('T', 4);
//        }
//      };
//      List<Integer> numbers = new ArrayList<Integer>();
//      for (int i = 0; i<s.length(); i++)
//        numbers.add(mapping.get(s.charAt(i)));
//      int hashing = 0;
//      Set<Integer> seen = new HashSet<Integer>();
//      Set<String> output = new HashSet<String>();
//      for (int start = 0; start<s.length() - windowSize + 1; ++start) {
//        if (start != 0) {
//          hashing = hashing * base;
//          hashing -= numbers.get(start - 1) * hiPlaceValue;
//          hashing += numbers.get(start + windowSize - 1);
//        } else {
//          for (int j = 0; j<windowSize; j++) {
//            hashing = hashing * base + numbers.get(j);
//          }
//        }
//        if (seen.contains(hashing))
//          output.add(s.substring(start, start + windowSize));
//        seen.add(hashing);
//      }
//      return new ArrayList<String> (output);
//    }

    public static List<String> findRepeatedSequences(String s, int k) {

        // Your code will replace this placeholder return statement
        int length = s.length();
        List<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stb.append(s.charAt(i));
            if (stb.length() > 10) {
                stb.deleteCharAt(0);
            }
            if (stb.length() == 10) {
                String key = stb.toString();
                map.put(key, map.getOrDefault(key, 0) + 1);
                if (map.get(key) > 1 && !res.contains(key)) {
                    res.add(key);
                }
            }
        }
        return res;
    }

    public static void main(String args[]) {
        List<String> inputString = Arrays.asList(
                "AAAAACCCCCAAAAACCCCCC",
                "ACGT",
                "AGACCTAGAC",
                "AAAAACCCCCAAAAACCCCCC",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT",
                "TTTTTGGGTTTTCCA",
                "",
                "AAAAAACCCCCCCAAAAAAAACCCCCCCTG",
                "ATATATATATATATAT"
        );
        List<Integer> inputK = Arrays.asList(
                8, 3, 3, 8, 12, 10, 14, 10, 10, 6);
        for (int i = 0; i < inputK.size(); i++) {
            System.out.println(i + 1 + ".\tInput Sequence: '" + inputString.get(i) + "'");
            System.out.println("\tk: " + inputK.get(i));
            System.out.println("\tRepeated Subsequence: " +
                    findRepeatedSequences(inputString.get(i), inputK.get(i)));
//        System.out.println(PrintHyphens.repeat("-", 100));
        }
    }
}