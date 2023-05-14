package algorithms.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinRemoveParentheses {

    /**
     step1: Initialize a stack to store parentheses
     step2: Traverse the input string
     - If we meet the letters, continue;
     - Else if we meet the ')',
     - If stack is empty, remove the current ')'
     - Else Compare with the root of the stack, if it's '(', pop the root of the stack
     - Else push '(' to stack
     step3: return the sting
     */
    public String minRemoveToMakeValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder stb = new StringBuilder(s);
        for(int i = 0; i < stb.length(); i++){
            char curr = s.charAt(i);
            if(Character.isLetter(curr)){
                continue;
            }
            //())()
            if(curr == ')'){
                if(stack.isEmpty()){
                    stb.deleteCharAt(i);
                    i--;
                } else if(stack.peek() == '('){
                    stack.pop();
                }
            } else {
                stack.push('(');
            }
        }
        return stb.toString();
    }

    public static void main(String[] args) {
        MinRemoveParentheses parentheses = new MinRemoveParentheses();
        System.out.println(parentheses.minRemoveToMakeValid("a)b(c)d"));
    }
}