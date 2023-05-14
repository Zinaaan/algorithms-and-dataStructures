package algorithms.HashMap;

import java.util.HashMap;

class FractionToDecimal {

    public static String fractionToDecimal(int numerator, int denominator) {
        long a = numerator, b = denominator;
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        StringBuilder result = new StringBuilder();
        if (a * b < 0) {
            result.append("-");
        }
        a = Math.abs(a);
        b = Math.abs(b);
        long remainder = (a % b) * 10;
        result.append(a / b).append(".");
        HashMap<Long, Integer> mapForReminder = new HashMap<>();
        while (remainder != 0) {
            if (mapForReminder.containsKey(remainder)) {
                int begin = mapForReminder.get(remainder);
                String left = result.substring(0, begin);
                String right = result.substring(begin);
                result = new StringBuilder(left + "(" + right + ")");
                return result.toString();
            }
            mapForReminder.put(remainder, result.length());
            result.append(remainder / b);
            remainder = (remainder % b) * 10;
        }
        return result.toString();
    }


    public static void main(String[] args) {
//        int[][] inputs = {{0, 4}, {4, 2}, {5, 333}, {2, 3}, {47, 18}, {93, 7}, {-5, 333}, {47, -18}, {-4, -2}};
        int[][] inputs = {{47 , -18}};
        for (int i = 0; i < inputs.length; i++) {
            System.out.print(i + 1 + ".\tInput: fraction_to_decimal(");
            for (int j = 0; j < inputs[i].length - 1; j++) {
                System.out.print(inputs[i][j]);
                System.out.print(", ");
            }
            System.out.println(inputs[i][inputs[i].length - 1] + ")");
            String result = fractionToDecimal(inputs[i][0], inputs[i][1]);
            System.out.println("\tOutput: " + result);
        }
    }
}