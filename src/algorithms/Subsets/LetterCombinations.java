package algorithms.Subsets;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    /**
     * step1: Initialize a hash table to store the relationship between the digit number and letters
     * step2: Get the current letters by the digit number
     * step3: Dfs the total letters and put the combinations into the output list
     */
    List<String> ans = new ArrayList<>();
    //step1
    String[] letters = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        int length = digits.length();
        if (length == 0) {
            return ans;
        }
        StringBuilder stb = new StringBuilder();
        dfs(digits, 0, stb);
        return ans;
    }

    public void dfs(String digits, int currentIndex, StringBuilder stb) {
        if (stb.length() == digits.length()) {
            ans.add(stb.toString());
            return;
        }
        String currentLetters = letters[digits.charAt(currentIndex) - '0'];
        for (int i = 0; i < currentLetters.length(); i++) {
            stb.append(currentLetters.charAt(i));
            dfs(digits, currentIndex + 1, stb);
            stb.deleteCharAt(stb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinations sol = new LetterCombinations();
        String[] digitsArray = {"73", "426", "78", "925", "2345"};
        for (String s : digitsArray) {
            System.out.println("All letter combinations for  " + s + " " + sol.letterCombinations(s));
        }
    }
}