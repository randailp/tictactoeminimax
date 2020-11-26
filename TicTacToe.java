/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;
import java.util.Scanner;
/**
 *
 * @author Randall
 */
public class TicTacToe {
    
    //Static variables
    static final int ROW = 5;
    static final int COL = 5;
    static final char PLAYER = 'X';
    static final char AI = 'O';

    //Driver
    public static void main(String[] args) {
        
        Board b = new Board(); //create new board 'b'
        char[][] board = b.getBoard(); //store  board 'b' in reusable variable
        
        String currentTurn = "player"; //Game starts with player's turn first
        boolean game = true; //flag for game loop
        
        b.printBoard(); //print initial empty board
        
        do{
            //Start player turn
            if(currentTurn.equals("player")){
                
                //Ensure input is handled
                int choice = tryCatch();
                
                //Checks if spot is available
                if(isAvailable(board, choice)){ 
                    pickSpot(board, currentTurn, choice);
                    currentTurn = "ai";
                }
                else{ //If spot not available, it remains player's turn
                    System.out.println("Spot not available.");
                    currentTurn = "player"; //Next Turn
                }
                b.printBoard(); //Update board
                
                //Checks for winner
                if(checkWinner(board) != null && checkWinner(board) != "tie"){ //Check for winner
                    System.out.println(checkWinner(board) + " wins!");
                    currentTurn = null;
                    game = false;
                }
                else if (checkWinner(board) == "tie"){ //If a tie occurs
                    System.out.println("It's a tie!");
                    currentTurn = null;
                    game = false;
                }
            }
            //Start AI turn
            else if(currentTurn.equals("ai")){
                
                //AI looks for optimal move
                findBestMove(board);
                //Update board
                b.printBoard();
                currentTurn = "player"; //Next Turn
                
                //Checks for winner
                if(checkWinner(board) != null && checkWinner(board) != "tie"){
                    System.out.println(checkWinner(board) + " wins!");
                    currentTurn = null;
                    game = false;
                }
                else if (checkWinner(board) == "tie"){ //If a tie occurs
                    System.out.println("It's a tie!");
                    currentTurn = null;
                    game = false;
                }
            }
            //If board is full the game is over
            if(isFull(board)){
                game = false;
            }
            
        }while(game);

    }
    
    //Function let's Player pick a spot on the board
    public static char[][] pickSpot(char[][] b, String turn, int spot){
        
        //switch case for every position on the board
        switch(spot){
        case 1:
            b[0][0] = PLAYER;
            break;
        case 2:
            b[0][2] = PLAYER;
            break;
        case 3:
            b[0][4] = PLAYER;
            break;
        case 4:
            b[2][0] = PLAYER;
            break;
        case 5:
            b[2][2] = PLAYER;
            break;
        case 6:
            b[2][4] = PLAYER;
            break;
        case 7:
            b[4][0] = PLAYER;
            break;
        case 8:
            b[4][2] = PLAYER;
            break;
        case 9:
            b[4][4] = PLAYER;
            break;
        default:
            break;
        } 

        return b;
    }
    
    //Function checks if spot on the board is available
    public static boolean isAvailable(char[][] b, int spot){
        
        //switch case for every spot on board, returns true if spot is available, else, false
        switch(spot){
            case 1:
                return b[0][0] == ' ';
            case 2:
                return b[0][2] == ' ';
            case 3:
                return b[0][4] == ' ';
            case 4:
                return b[2][0] == ' ';
            case 5:
                return b[2][2] == ' ';
            case 6:
                return b[2][4] == ' ';
            case 7:
                return b[4][0] == ' ';
            case 8:
                return b[4][2] == ' ';
            case 9:
                return b[4][4] == ' ';
            default:
                return false;

        }
    }
    
    //Function checks if there is a winner
    public static String checkWinner(char[][] b){
        
        char winner = ' ';
        
        //row across win
        for(int i = 0; i < ROW; i=i+2){
            if(b[i][0] == b[i][2] && b[i][2] == b[i][4] && b[i][0] != ' '){
                winner = b[i][0];
            }
        }
        
        //column win
        for(int i = 0; i < COL; i=i+2){
            if(b[0][i] == b[2][i] && b[2][i] == b[4][i] && b[0][i] != ' '){
                winner = b[0][i];
            }
        }
        
        //diagonal win
        if(b[0][0] == b[2][2] && b[2][2] == b[4][4]){
            winner = b[0][0];
        }
        if(b[0][4] == b[2][2] && b[2][2] == b[4][0]){
            winner = b[0][4];
        }
        
        //Determines who is the winner
        if (winner == 'X'){
            return "player";
        }
        else if(winner == 'O'){
            return "ai";
        }
        else if(isFull(b)){
            return "tie";
        }
        return null;
    }
    
    //Function checks if the board is full
    public static boolean isFull(char[][] b){
        
        //Iterates through every position of the board. If at least one spot is blank, board is not full
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(b[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
    
    //Function lets AI determine best move to make, implements minimax algorithm
    public static void findBestMove(char[][] b ){
        
        //Declare new move object to hold coordinates
        Move move = new Move();
        int bestScore = -1000; //Best score intialized with the intention of being replaced

        //Iterates through entire board to look for a move
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(b[i][j] == ' '){
                    
                    //make the current move
                    b[i][j] = AI;
                    
                    //determine the score of move using minimax algorithm
                    int score = minimax(b, 0, false);
                    
                    //undo move
                    b[i][j] = ' ';
                    
                    //if score > bestScore, score is new bestScore
                    if(score > bestScore){
                        bestScore = score;
                        move.setRow(i);
                        move.setCol(j);
                    }
                    
                }
            }
        }
        
        b[move.getRow()][move.getCol()] = AI; //AI moves in the most optimal spot on board
    }
    
    //Function implements the minimax algorithm recursively
    public static int minimax(char[][] b, int depth, boolean isMax){
        
        //check for terminal state
        if(checkWinner(b) == "player"){
            return -10; //Returns -10 which indicates a more favorable board state for Player
        }
        else if(checkWinner(b) == "ai"){
            return 10; //Returns 10 which indicates a more favorable board state for AI
        }
        else if(checkWinner(b) == "tie"){
            return 0; //Returns 0 if board state results in tie
        }
        
        //AI is the maximizing player, looking for max score
        if(isMax){
            int bestScore = -1000;
            for(int i = 0; i < ROW; i++){
                for(int j =0; j < COL; j++){
                    if(b[i][j] == ' '){
                        b[i][j] = AI;
                        int score = minimax(b, depth+1, false); //recursive call with +1 depth
                        b[i][j] = ' ';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
        
        //Player is minimizng player, looking for min score
        else{
            int bestScore = 1000;
            for(int i = 0; i < ROW; i++){
                for(int j =0; j < COL; j++){
                    if(b[i][j] == ' '){
                        b[i][j] = PLAYER;
                        int score = minimax(b, depth+1, true); //recursive call with +1 depth
                        b[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
      
    }
    
    //Function handles exceptions
    public static int tryCatch(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Pick a position from 1-9"); //Player picks a spot on board
        
        try{
            int input = sc.nextInt();
            if(input > 9 || input < 1){
                throw new ArithmeticException();
            }
            return input;
        }
        catch(ArithmeticException e){
            System.out.println("Input is not in range 1-9.");
        }
        catch(Exception e){
            System.out.println("Invalid input.");
        }
        
        
        return 0;
    }
    
}
