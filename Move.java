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
public class Move {
    
    private int row = 0;
    private int col = 0;
    
    public void setRow(int r){
        this.row = r;
    }
    
    public void setCol(int c){
        this.col = c;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
}
