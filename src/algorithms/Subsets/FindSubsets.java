package algorithms.Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class FindSubsets {
    /**
     * DFS the input list and add each integer to the output list
     */
    static List<HashSet<Integer>> setsList = new ArrayList<>();

    public static List<HashSet<Integer>> findAllSubsets(List<Integer> v) {
        setsList.clear();
        List<Integer> subset = new ArrayList<>();
        dfs(v, subset, 0);
        return setsList;
    }

    public static void dfs(List<Integer> inputList, List<Integer> subset, int index) {
        setsList.add(new HashSet<>(subset));
        for (int i = index; i < inputList.size(); i++) {
            subset.add(inputList.get(i));
            dfs(inputList, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        Integer[][] inputs = {{}, {2, 5, 7}, {1, 2}, {1, 2, 3, 4}, {7, 3, 1, 5}};

        for (int i = 0; i < inputs.length; i++) {
            List<Integer> v = Arrays.asList(inputs[i]);
            List<HashSet<Integer>> subsets;
            System.out.println(i + 1 + ". Set:     " + v);
            subsets = findAllSubsets(v);

            System.out.println("   Subsets: " + subsets.toString());
        }
    }
}