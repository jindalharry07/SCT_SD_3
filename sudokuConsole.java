import java.util.Scanner;

public class sudokuConsole {
  private static final int SIZE = 9;
  private static final int EMPTY = 0;

  public static void main(String[] args) {
    int[][] board = new int[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the Sudoku puzzle row by row (use 0 for empty cells):");
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = sc.nextInt();
      }
    }

    if (solve(board)) {
      System.out.println("\nSolved Sudoku:");
      printBoard(board);
    } else {
      System.out.println("No solution exists.");
    }

    sc.close();
  }

  private static boolean solve(int[][] board) {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        if (board[row][col] == EMPTY) {
          for (int num = 1; num <= SIZE; num++) {
            if (isSafe(board, row, col, num)) {
              board[row][col] = num;
              if (solve(board))
                return true;
              board[row][col] = EMPTY;
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  private static boolean isSafe(int[][] board, int row, int col, int num) {
    for (int i = 0; i < SIZE; i++) {
      if (board[row][i] == num || board[i][col] == num
          || board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
        return false;
      }
    }
    return true;
  }

  private static void printBoard(int[][] board) {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
}
