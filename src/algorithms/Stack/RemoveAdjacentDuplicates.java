package algorithms.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAdjacentDuplicates {

    public String removeDuplicates(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (deque.isEmpty() || deque.getLast() != curr) {
                deque.addLast(curr);
            } else {
                deque.removeLast();
            }
        }
        while (!deque.isEmpty()) {
            stb.append(deque.removeFirst());
        }
        return stb.toString();
    }
}