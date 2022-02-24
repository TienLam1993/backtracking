import java.util.Arrays;

public class BACP {

    private static final int C = 6;
    private static final int P = 2;
    private static final int[][] matrix = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0}
    };
    private static final int[] credits = {4,4,4,4,2,4};


    static int[][] board = new int[P][C];
    private static int output = 0;

    // print solution for debugging purpose
    void printSolution(int[][] board) {

        System.out.println("A solution is found!");

        for (int i = 0; i < P; i++) {
            for (int j = 0; j < C; j++)
                System.out.print(" " + board[i][j]
                        + " ");
            System.out.println();
        }
    }

    // calculate max load of a solution
    private int calculateMaximumLoad(int[][] board) {
        int[] load = new int[P];
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 1) {
                    load[i] += credits[j];
                }
            }
        }
        Arrays.sort(load);
        return load[P-1];
    }



    static boolean isSafe(int[][] board, int row, int col) {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j <= row; j++) {
                if (matrix[col][i] == 1 && board[j][i] == 1) {
                    return false;
                }
            }
        }
        return true;
    }


    // solve the BACP problem using backtracking
    void solve(int[][] board, int col) {

        for (int i = 0; i < P; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                if (col >= C - 1) {
                    printSolution(board);
                    if (calculateMaximumLoad(board) < output || output == 0 ) {
                        output = calculateMaximumLoad(board);
                    }
                    return;
                }
                solve(board, col + 1);
                board[i][col] = 0;

            }
        }
    }

    public static void main(String[] args) {
        BACP app = new BACP();
        app.solve(board, 0);
        System.out.println(output);

    }


}
