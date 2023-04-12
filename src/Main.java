import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.lang.Integer;


public class Main{

    static Scanner scanner = new Scanner(System.in);

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (char[] pos : board) {
            for (int i = 0; i < pos.length; i++) {
                if (i == 0) {
                    System.out.print("| ");
                    System.out.print(pos[i] + " ");
                } else if (i == pos.length - 1) {
                    System.out.print(pos[i]);
                    System.out.print(" |");
                } else {
                    System.out.print(pos[i] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("---------");
    }


    //-------------------------------------
    public static boolean makeMove(String move, char[][] board, char symbol) {
        if (!move.matches("\\d\\s\\d")) {
            System.out.println("You should enter numbers!");
            return false; //move doesn't match required input
        }

        move = move.replaceAll("\\s", "");

        for (char c : move.toCharArray()) {
            int n = Character.getNumericValue(c);
            if (n > 3 || n < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false; // one int is less than 1 or more than 3
            }
        }

        int coordinates = Integer.parseInt(move);

        switch (coordinates) {
            case 11:
                return fillCellIfEmpty(board, 0, 0, symbol);
            case 12:
                return fillCellIfEmpty(board, 0, 1 , symbol);
            case 13:
                return fillCellIfEmpty(board, 0, 2, symbol);
            case 21:
                return fillCellIfEmpty(board, 1, 0, symbol);
            case 22:
                return fillCellIfEmpty(board, 1, 1, symbol);
            case 23:
                return fillCellIfEmpty(board, 1, 2, symbol);
            case 31:
                return fillCellIfEmpty(board, 2, 0, symbol);
            case 32:
                return fillCellIfEmpty(board, 2, 1, symbol);
            case 33:
                return fillCellIfEmpty(board, 2, 2, symbol);
            default:
                return false;
        }
    }

    public static boolean fillCellIfEmpty(char[][] board, int i1, int i2, char symbol) {
        if (board[i1][i2] == 'X' || board[i1][i2] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        board[i1][i2] = symbol;
        return true;
    }

    public static String getState(char[][] board) {
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
        String result = "";
        if(xMoves + oMoves == 9){
            result = "true";
        }else {
            result = "false";
        }
        return result;
    }




    //----------------------------------------------------
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] board = new char[3][3];
        String input = br.readLine();

        int l = 0; // initialize here

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length && l < input.length(); j++, l++) {
                board[i][j] = input.charAt(l);

            }
        }

        printBoard(board);

        Player player = new Player('U');
        boolean canMakeMove = false;
        boolean exit = false;



        do {

            player.changePlayer();

            do {

                char cPlayer = player.getSymbol();

                   System.out.print("Enter the coordinates: ");
                String move = br.readLine();

                canMakeMove = makeMove(move, board, cPlayer);

            } while (!canMakeMove);
            printBoard(board);

            String gameState = getState(board);
           // System.out.println(gameState);
            if(!gameState.equals("false")){
                exit = true;
            }

        } while (!exit);









    }//main method
}//main class

//-----------------------------------------
class Player {

    char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void changePlayer(){

        if(this.symbol == 'U'){
            this.setSymbol('X');
        }
    }

    public char getSymbol() {
        return symbol;
    }
}//player

//-----------------------------------------