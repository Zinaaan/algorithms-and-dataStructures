package algorithms.TwoPointers;

import java.util.HashMap;

/**
 * @author lzn
 * @date 2023/03/07 14:08
 * @description
 * Given a string s, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example
 * For example, Given s = "eceba", k = 3,
 * T is "eceb" which its length is 4.
 *
 * Challenge
 * O(n), n is the size of the string s.
 */
public class LengthOfLongestSubstringKDistinct {
    /**
     * k distinct characters:P HashMap
     *
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null){
            return -1;
        }
        int length = s.length();
        if(length < 2){
            return length;
        }
        HashMap<Character, Integer> subStrFrequency = new HashMap<>();
        char[] chs = s.toCharArray();
        int index = 0, longest = 0;
        for(int i = 0; i < length; i++){
            subStrFrequency.put(chs[i], subStrFrequency.getOrDefault(chs[i], 0) + 1);
            while(subStrFrequency.size() > k){
                char key = chs[index];
                subStrFrequency.put(key, subStrFrequency.get(key) - 1);
                if(subStrFrequency.get(key) == 0){
                    subStrFrequency.remove(key);
                }
                index++;
            }
            longest = Math.max(longest, i - index + 1);
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 3));
    }
}
