package algorithms.Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindKSumSubsets {
    /**
        step1: Traverse each number of the input list, add the number in the current index
        step2: If the sum equal to the target, add to the output list
        step3: Else if the sum smaller than the target, keep backtracking the input list
        step4: Else if the sum greater than the target, return to the upper level of the tree


                                          8                        13                    3              22
                                8,13            8,3         13,3        13,22    3,22
                          8,13,3   8,13,22  8,3,22     13,3,22
                   8,13,3,22
    */
    static List<List<Integer>> ans = new ArrayList<>();
    public static List<List<Integer>> getKSumSubsets(List<Integer> setOfIntegers, int targetSum) {
        if(setOfIntegers.size() == 0){
            return ans;
        }
        List<Integer> indexOfIntegers = new ArrayList<>();
        dfsAndBacktrack(setOfIntegers, indexOfIntegers, targetSum, 0, 0);
        return ans;
    }

    public static void dfsAndBacktrack(List<Integer> setOfIntegers, List<Integer> indexOfIntegers, int targetSum, int currentIndex, int currentSum){
        if(currentSum == targetSum){
            ans.add(new ArrayList<>(indexOfIntegers));
            System.out.println("-------------------------");
            System.out.println("equals: " + indexOfIntegers);
            return;
        }
        if(currentSum > targetSum){
            return;
        }
        for(int i = currentIndex; i < setOfIntegers.size(); i++){
            indexOfIntegers.add(setOfIntegers.get(i));
            System.out.println("before: " + indexOfIntegers);
            dfsAndBacktrack(setOfIntegers, indexOfIntegers, targetSum, i + 1, currentSum + setOfIntegers.get(i));
            indexOfIntegers.remove(indexOfIntegers.size() - 1);
            System.out.println("after: " + indexOfIntegers);
        }
    }

    public static void main(String[] args) {
//        System.out.println(getKSumSubsets(Arrays.asList(8, 13, 3, 22, 17, 39, 87, 45, 36), 1));
//        System.out.println(getKSumSubsets(Arrays.asList(8, 13, 3, 22, 17, 39, 87, 45, 36), 21));
        System.out.println(getKSumSubsets(Arrays.asList(8, 13, 3, 22, 17, 39, 87, 45, 36), 38));
    }
}