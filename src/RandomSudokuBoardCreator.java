import java.util.Random;
// temp
public class RandomSudokuBoardCreator {

    // Make recursive or backtracking fill method
    // Keep constraint trackers (used-in-row 9x10 booleans, used-in-col,used-in-grid)
    // Placing a number will be just 3 boolean checks
    // mark/remark if valid or not
    public static int[][] generateBoard() {
        int[][] board = new int[9][9];
        fillBoard(board);
        return board;
    }
    public static void fillBoard(int[][] board) {
        boolean[][] usedRow = new boolean[9][10];
        boolean[][] usedCol = new boolean[9][10];
        boolean[][] gridUsed = new boolean[9][10];

        backtrackingFill(board, usedRow, usedCol, gridUsed);
    }
    public static int[][] sudokufyBoard(int[][] board) {
        Random rnd = new Random();
        double eraseProbability = .6;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (rnd.nextDouble() < eraseProbability) {
                    board[row][col] = 0;
                }
            }
        }

        return board;
    }
    // Durstenfeld Shuffle for random assignment
    private static int[] shuffledFillOrder() {
        int[] fillOrder = {1,2,3,4,5,6,7,8,9};
        Random rnd = new Random();
        for (int i = fillOrder.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = fillOrder[index];
            fillOrder[index] = fillOrder[i];
            fillOrder[i] = a;
    }
        return fillOrder;
    }
    // Get earliest emtpy block
    private static int[] earliestEmptyBlockCord(int[][] board) {
        int[] blockCord = new int[2];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    blockCord[0] = row;
                    blockCord[1] = col;
                    return blockCord;
                }
            }
        }
        return null;
    }

    // backtrack fill method
    private static boolean backtrackingFill(int[][] board, boolean[][] usedRow, boolean[][] usedCol, boolean[][] gridUsed) {
        int[] fillOrder = shuffledFillOrder();
        int[] blockCord = earliestEmptyBlockCord(board);
        if (blockCord == null) {
            return true;
        }
        int row = blockCord[0];
        int col = blockCord[1];
        int subGrid = ( (row/3) * 3) + (col/3);
        for (int i = 0; i < fillOrder.length; i++) {
            if (!usedRow[row][i] && !usedCol[col][i] && !gridUsed[subGrid][i]) {
                board[row][col] = fillOrder[i];
                usedCol[row][i] = true;
                usedRow[col][i] = true;
                gridUsed[subGrid][i] = true;

                if (backtrackingFill(board, usedRow, usedCol, gridUsed)) {
                    return true;
                }
                else {
                    board[row][col] = 0;
                    usedCol[row][i] = false;
                    usedRow[col][i] = false;
                    gridUsed[subGrid][i] = false;
                }
        }
    }
        return false;
    }

}
