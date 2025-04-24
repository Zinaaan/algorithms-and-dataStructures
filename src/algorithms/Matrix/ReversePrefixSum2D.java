package algorithms.Matrix;

import java.util.Arrays;

public class ReversePrefixSum2D {

    public static void main(String[] args) {
        ReversePrefixSum2D reversePrefixSum2D = new ReversePrefixSum2D();
        int[][] after = {
                {1, 3, 6},
                {5, 12, 21},
                {12, 27, 45}
        };
        int[][] matrix = reversePrefixSum2D.reversePrefixSum2D(after);
        System.out.println("Previous matrix: " + Arrays.deepToString(matrix));
    }

    private int[][] reversePrefixSum2D(int[][] afterMatrix){
        if(afterMatrix.length == 0){
            return null;
        }
        int rows = afterMatrix.length, columns = afterMatrix[0].length;
        int[][] beforeMatrix = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                int current = afterMatrix[i][j];
                int top = (i > 0) ? afterMatrix[i - 1][j] : 0;
                int left = (j > 0) ? afterMatrix[i][j - 1] : 0;
                int topLeft = (i > 0 && j > 0) ? afterMatrix[i - 1][j - 1] : 0;

                beforeMatrix[i][j] = current - top - left + topLeft;
            }
        }

        return beforeMatrix;
    }
}
