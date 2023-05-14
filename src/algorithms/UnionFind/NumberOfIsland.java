package algorithms.UnionFind;

/**
 * @author lzn
 * @date 2023/03/30 10:02
 * @Description
 */
public class NumberOfIsland {

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    //search whether an island can be formed at the top, right, bottom and left positions
                    findFormedIsland(grid, i, j);
                    //count the number of island start from (i, j)
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Based on the initial position of (i, j) recursion to see if there is land in the upper, lower, left and right positions,
     * if there is, then mark him as 2; if not, end this recursion
     * 基于初始位置的(i, j)递归查看上下左右位置是否有陆地，如果有则把他标记为2；如果没有就结束这次递归
     *
     * @param grid
     * @param row
     * @param col
     */
    private void findFormedIsland(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] != '1') {
            return;
        }
        grid[row][col] = '2';
        findFormedIsland(grid, row - 1, col);
        findFormedIsland(grid, row, col + 1);
        findFormedIsland(grid, row + 1, col);
        findFormedIsland(grid, row, col - 1);
    }

    public static void main(String[] args) {
        NumberOfIsland numberOfIsland = new NumberOfIsland();
        char[][] inputs = new char[4][];
        inputs[0] = new char[]{'1', '1', '1', '1', '0'};
        inputs[1] = new char[]{'1', '1', '0', '1', '0'};
        inputs[2] = new char[]{'1', '1', '0', '0', '0'};
        inputs[3] = new char[]{'0', '0', '0', '0', '0'};
        System.out.println(numberOfIsland.numIslands(inputs));

    }
}
