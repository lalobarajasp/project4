public class proof {
    public static boolean getState(char[][] board) {
        //Calculate number of moves for X and O
        int xMoves = 0;
        int oMoves = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    xMoves++;
                }
                if (board[i][j] == 'O') {
                    oMoves++;
                }
            }
        }

        boolean isFullBoard = (xMoves + oMoves) == 9;

        return isFullBoard;
    }
}
