package algorithms.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzn
 * @date 2023/03/15 11:58
 * @Description
 */
public class RemoveAdjacentDuplicatesII {

    public static String removeDuplicates(String s, int k) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1 || k == 1) {
            return s;
        }
        Deque<Pair> pairStack = new ArrayDeque<>();
        StringBuilder stb = new StringBuilder(s);
        pairStack.add(new Pair(stb.charAt(0), 1));
        for (int i = 1; i < stb.length(); i++) {
            char curr = stb.charAt(i);
            if (pairStack.isEmpty() || pairStack.getLast().letter != curr) {
                pairStack.add(new Pair(curr, 1));
            } else {
                pairStack.getLast().frequency++;
            }
            if (pairStack.getLast().frequency == k) {
                stb.delete(i - k + 1, i + 1);
                i = i - k;
                pairStack.removeLast();
            }
        }

        return stb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(removeDuplicates("dddaa", 3));
        System.out.println(removeDuplicates("daddaa", 3));
        System.out.println(removeDuplicates("pbbcggttciiippooaais", 2));
        System.out.println(removeDuplicates("abcd", 2));
        System.out.println(removeDuplicates("deeedbbcccbdaaa", 2));
    }
}
