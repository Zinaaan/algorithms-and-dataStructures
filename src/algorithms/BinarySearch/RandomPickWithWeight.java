package algorithms.BinarySearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomPickWithWeight {

    /**
     * step1: Construct a pre sum of the weight in the input list so that we can find real weight from each interval of the pre sum
     * step2: Since the pre sum is already sorted, use Binary search to search the first closest number in the pre sum
     *
     */
    private int[] preSumOfWeight;
    private final Random random = new Random();
    public RandomPickWithWeight(int[] w) {
        preSumOfWeight = new int[w.length + 1];
        for(int i = 1; i <= w.length; i++){
            preSumOfWeight[i] = preSumOfWeight[i - 1] + w[i - 1];
        }
    }

    public int pickIndex() {
        int target = random.nextInt(preSumOfWeight[preSumOfWeight.length - 1]) + 1;
        int left = 0, right = preSumOfWeight.length - 1;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(preSumOfWeight[mid] < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    public static int sumW(int[] arr) {
        int sum = 0;
        //Loop through the array to calculate sum of elements
        for (int value : arr) {
            sum = sum + value;
        }
        return sum;
    }

    static float round(float var) {
        float value = (int)(var * 100 + .5);
        return (float) value / 100;
    }

    public static void main(String args[]) {
        int counter = 900;
        int[] weights1 = {1, 2, 3, 4, 5};
        int[] weights2 = {1, 12, 23, 34, 45, 56, 67, 78, 89, 90};
        int[] weights3 = {10, 20, 30, 40, 50};
        int[] weights4 = {1, 10, 23, 32, 41, 56, 62, 75, 87, 90};
        int[] weights5 = {12, 20, 35, 42, 55};
        int[] weights6 = {10, 10, 10, 10, 10};
        int[] weights7 = {10, 10, 20, 20, 20, 30};
        int[] weights8 = {1, 2, 3};
        int[] weights9 = {10, 20, 30, 40};
        int[] weights10 = {5, 10, 15, 20, 25, 30};
        int[][] weights = {weights1, weights2, weights3, weights4, weights5,
                weights6, weights7, weights8, weights9, weights10};
        HashMap< Integer, Integer > dict = new HashMap < > ();

        for (int i = 0; i < weights.length; i++) {
            System.out.println((i + 1) + ".\tInput: " + Arrays.toString(weights[i]) + ", pickIndex() called " + counter + " times" + "\n");
            for (int l = 0; l < weights[i].length; l++) {
                dict.put(l, 0);
            }
            for (int j = 0; j < counter; j++) {
                RandomPickWithWeight sol = new RandomPickWithWeight(weights[i]);
                int index = sol.pickIndex();
                dict.put(index, dict.get(index) + 1);
            }
            System.out.println("Indexes\t" + "|" + "\tWeights\t" + "|" + "\tOccurences\t" + "|" + "\tFrequency\t" + "|" + "\tExpected Frequency");
            for (Map.Entry < Integer, Integer > entry: dict.entrySet()) {
                System.out.println(entry.getKey() + "\t|\t" + weights[i][entry.getKey()] + "\t|\t" + dict.get(entry.getKey()) + "\t\t|\t" + round(dict.get(entry.getKey()) / (float) counter) * 100 + "%" + "\t\t|\t" + round(weights[i][entry.getKey()] / (float) sumW(weights[i]) * 100) + "%");
            }
            dict.clear();
        }

    }
}