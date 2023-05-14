package algorithms.Subsets;

import java.util.ArrayList;

public class Permutations {
    /**
        time: O(n2), space: O(n)
        step1: dfs the input word, add each characters into stringbuilder
        step2: Loop to recurse the dfs process, If the length of stringbuilder equals to the length of 
                the input string,add the string permutation into the output list
        step3: Backtrack to the original status and start another dfs
    */
    static ArrayList<String> ans = new ArrayList<>();
    public static ArrayList<String> permuteWord(String word) {
        if(word == null){
            return ans;
        }
        StringBuilder stb = new StringBuilder();
        dfs(word, stb, word.length());
        return ans;
    }

    public static void dfs(String word, StringBuilder stb, int wordLength){
        if(wordLength == stb.length()){
            ans.add(stb.toString());
            return;
        }
        for(int i = 0; i < wordLength; i++){
            if(stb.indexOf(Character.toString(word.charAt(i))) != -1){
                continue;
            }
            stb.append(word.charAt(i));
            dfs(word, new StringBuilder(stb), wordLength);
            stb.deleteCharAt(stb.length() - 1);
        }
    }
}