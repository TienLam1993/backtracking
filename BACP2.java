import java.util.Arrays;
import java.util.Scanner;

public class BACP2 {

    // number of courses
    private int C;
    // number of periods
    private int P;
    // course credits
    private  int[] credits;
    // constraint matrix
    private int[][] matrix;

    private final Scanner scanner = new Scanner(System.in);


    // a 2 dimension array represent the solution
    int[][] board;

    // output - minimum of the maximum load between solution
    private int output = 0;

    // print solution for debugging purpose
//    void printSolution(int[][] board) {
//
//        System.out.println("A solution is found!");
//
//        for (int i = 0; i < P; i++) {
//            for (int j = 0; j < C; j++)
//                System.out.print(" " + board[i][j]
//                        + " ");
//            System.out.println();
//        }
//    }

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


    // check if a point on board is safe to put course
    boolean isSafe(int[][] board, int row, int col) {
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
//                    printSolution(board);
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

    void run() {

        C = scanner.nextInt();
        P = scanner.nextInt();

        credits = new int[C];
        matrix = new int[C][C];
        board = new int[P][C];

        for (int i = 0; i < C; i++) {
            credits[i] = scanner.nextInt();
        }

        for (int i = 0; i < C; i++) {
            for (int j = 0; j < C; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        solve(board, 0);

    }

    public static void main(String[] args) {
        BACP2 app = new BACP2();
        app.run();
        System.out.println(app.output);
    }


}
