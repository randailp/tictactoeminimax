/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Randall
 */
public class Board {
    
    private static final int ROW = 5;
    private static final int COL = 5;
    
    private char[][] board = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};
    
    public void printBoard(){
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public char[][] getBoard(){
        return this.board;
    }
    
    public void setBoard(char[][] b){
        this.board = b;
    }
    
}
