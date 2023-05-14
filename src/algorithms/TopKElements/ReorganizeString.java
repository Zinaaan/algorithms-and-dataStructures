package algorithms.TopKElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
//    public static String reorganizeString(String string1) {
//        int length = string1.length();
//        if (length < 2) {
//            return string1;
//        }
//        int slow = 0, fast = 1;
//        char[] reorganizeChars = string1.toCharArray();
//        while (fast < length) {
//            char slowChar = string1.charAt(slow);
//            char fastChar = string1.charAt(fast);
//            if (fastChar == slowChar) {
//                fast++;
//            } else if (fast - slow == 1) {
//                slow ++;
//                fast ++;
//            } else {
//                char temp = reorganizeChars[slow++];
//                reorganizeChars[slow] = fastChar;
//                reorganizeChars[fast] = temp;
//                slow++;
//                fast = slow + 1;
//            }
//        }
//        for (int i = 0, j = 1; j < length; ) {
//            if (reorganizeChars[i] == reorganizeChars[j]) {
//                return "";
//            }
//            i++;
//            j++;
//        }
//        StringBuilder stb = new StringBuilder();
//        for (char c : reorganizeChars) {
//            stb.append(c);
//        }
//        return stb.toString();
//    }

    /**
     * step1: Construct a mas-heap for most frequency character in the input string so that the most frequently occurring character is at the root of the heap
     * step2: Iterate over the heap and in each iteration, pop the most frequently occurring character and append it into the result string
     * step3: Decrement the frequency of the popped character and put it back onto the heap if the updated frequency is greater than 0
     *
     * @param string1
     * @return
     */
    public static String reorganizeString(String string1) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        //O(n)
        for (char c : string1.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        //step1
        PriorityQueue<Map.Entry<Character, Integer>> maxHeapForFrequency = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        //O(log n)
        maxHeapForFrequency.addAll(frequencyMap.entrySet());
        StringBuilder stb = new StringBuilder();
        HashMap<Character, Integer> prevFrequencyMap = new HashMap<>(1);
        while (!maxHeapForFrequency.isEmpty()) {
            //step2
            Map.Entry<Character, Integer> curr = maxHeapForFrequency.poll();
            stb.append(curr.getKey());
            int frequency = curr.getValue() - 1;
            if (!prevFrequencyMap.isEmpty()) {
                maxHeapForFrequency.addAll(prevFrequencyMap.entrySet());
                prevFrequencyMap.clear();
            }
            if (frequency > 0) {
                prevFrequencyMap.put(curr.getKey(), frequency);
            }
        }
        return !prevFrequencyMap.isEmpty() ? "" : stb.toString();
    }

    public static void main(String args[]) {
        String[] inputs = {
                "programming",
                "hello",
                "fofjjb",
                "abbacdde",
                "aba",
                "awesome",
                "aaab",
//                "aab"
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput string: " + inputs[i]);

            String output = reorganizeString(inputs[i]);
            output = (output.length() == 0) ? "''" : output;

            System.out.println("\n\tReorganize string: " + output);
        }

    }
}