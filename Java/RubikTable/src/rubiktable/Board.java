package rubiktable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Point;

/**
 *
 * @author pinter
 */
public class Board {

    private Field[][] board;
    private final int boardSize;

    /**
     * Board(int boardSize)
     * @param boardSize
     * Task:    Constructor
     * Input:   boardSize       - int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                if(i == 0 || j == 0 || i == boardSize-1 || j == boardSize-1){
                    board[i][j] = new Field("arrowButton");
                }
                else{
                    board[i][j] = new Field("coloredButton");
                }
            }
        }
    }
    
    /**
     * boolean isOver()
     * @return over
     * Task:    játék vége ellenőrzés
     * Input:   void       - nincs
     * Output:   over       - boolean
     * 
     * Activity:
     */
    public boolean isOver() {
        boolean over = false;
        
        if(boardSize == 8){
            if((board[1][1].getColor() == board[1][2].getColor() && board[1][1].getColor() == board[1][3].getColor() && board[1][1].getColor() == board[1][4].getColor() && board[1][1].getColor() == board[1][5].getColor() && board[1][1].getColor() == board[1][6].getColor()
            && board[2][1].getColor() == board[2][2].getColor() && board[2][1].getColor() == board[2][3].getColor() && board[2][1].getColor() == board[2][4].getColor() && board[2][1].getColor() == board[2][5].getColor() && board[2][1].getColor() == board[2][6].getColor()
            && board[3][1].getColor() == board[3][2].getColor() && board[3][1].getColor() == board[3][3].getColor() && board[3][1].getColor() == board[3][4].getColor() && board[3][1].getColor() == board[3][5].getColor() && board[3][1].getColor() == board[3][6].getColor()
            && board[4][1].getColor() == board[4][2].getColor() && board[4][1].getColor() == board[4][3].getColor() && board[4][1].getColor() == board[4][4].getColor() && board[4][1].getColor() == board[4][5].getColor() && board[4][1].getColor() == board[4][6].getColor()
            && board[5][1].getColor() == board[5][2].getColor() && board[5][1].getColor() == board[5][3].getColor() && board[5][1].getColor() == board[5][4].getColor() && board[5][1].getColor() == board[5][5].getColor() && board[5][1].getColor() == board[5][6].getColor()       
            && board[6][1].getColor() == board[6][2].getColor() && board[6][1].getColor() == board[6][3].getColor() && board[6][1].getColor() == board[6][4].getColor() && board[6][1].getColor() == board[6][5].getColor() && board[6][1].getColor() == board[6][6].getColor())
                ||   
            (board[1][1].getColor() == board[2][1].getColor() && board[1][1].getColor() == board[3][1].getColor() && board[1][1].getColor() == board[4][1].getColor() && board[1][1].getColor() == board[5][1].getColor() && board[1][1].getColor() == board[6][1].getColor()
            && board[1][2].getColor() == board[2][2].getColor() && board[1][2].getColor() == board[3][2].getColor() && board[1][2].getColor() == board[4][2].getColor() && board[1][2].getColor() == board[5][2].getColor() && board[1][2].getColor() == board[6][2].getColor()
            && board[1][3].getColor() == board[2][3].getColor() && board[1][3].getColor() == board[3][3].getColor() && board[1][3].getColor() == board[4][3].getColor() && board[1][3].getColor() == board[5][3].getColor() && board[1][3].getColor() == board[6][3].getColor()
            && board[1][4].getColor() == board[2][4].getColor() && board[1][4].getColor() == board[3][4].getColor() && board[1][4].getColor() == board[4][4].getColor() && board[1][4].getColor() == board[5][4].getColor() && board[1][4].getColor() == board[6][4].getColor()
            && board[1][5].getColor() == board[2][5].getColor() && board[1][5].getColor() == board[3][5].getColor() && board[1][5].getColor() == board[4][5].getColor() && board[1][5].getColor() == board[5][5].getColor() && board[1][5].getColor() == board[6][5].getColor()       
            && board[1][6].getColor() == board[2][6].getColor() && board[1][6].getColor() == board[3][6].getColor() && board[1][6].getColor() == board[4][6].getColor() && board[1][6].getColor() == board[5][6].getColor() && board[1][6].getColor() == board[6][6].getColor())
                )over = true;
        }
        else if(boardSize == 6){
            if((board[1][1].getColor() == board[1][2].getColor() && board[1][1].getColor() == board[1][3].getColor() && board[1][1].getColor() == board[1][4].getColor()
            && board[2][1].getColor() == board[2][2].getColor() && board[2][1].getColor() == board[2][3].getColor() && board[2][1].getColor() == board[2][4].getColor()
            && board[3][1].getColor() == board[3][2].getColor() && board[3][1].getColor() == board[3][3].getColor() && board[3][1].getColor() == board[3][4].getColor()
            && board[4][1].getColor() == board[4][2].getColor() && board[4][1].getColor() == board[4][3].getColor() && board[4][1].getColor() == board[4][4].getColor())
                ||   
            (board[1][1].getColor() == board[2][1].getColor() && board[1][1].getColor() == board[3][1].getColor() && board[1][1].getColor() == board[4][1].getColor()
            && board[1][2].getColor() == board[2][2].getColor() && board[1][2].getColor() == board[3][2].getColor() && board[1][2].getColor() == board[4][2].getColor()
            && board[1][3].getColor() == board[2][3].getColor() && board[1][3].getColor() == board[3][3].getColor() && board[1][3].getColor() == board[4][3].getColor()
            && board[1][4].getColor() == board[2][4].getColor() && board[1][4].getColor() == board[3][4].getColor() && board[1][4].getColor() == board[4][4].getColor())
                )over = true;
        }
        else{
            if((board[1][1].getColor() == board[1][2].getColor()
            && board[2][1].getColor() == board[2][2].getColor())
                ||   
            (board[1][1].getColor() == board[2][1].getColor()
            && board[1][2].getColor() == board[2][2].getColor())
                )over = true;
        }
        return over;
    }
    
    /**
     * get(int x, int y)
     * @param x
     * @param y
     * @return board[x][y] 
     * Task:    board[x][y] lekérdezés
     * Input:   x,y       - int,int
     * Output:   board[x][y]       - Field
     * 
     * Activity:
     */
    public Field get(int x, int y) {
        return board[x][y];
    }
    
    /**
     * get(Point point)
     * @param point
     * @return get(x, y)
     * Task:    board[x][y] lekérdezés
     * Input:   point      - Point
     * Output:   get(x, y)       - Field
     * 
     * Activity:
     */
    public Field get(Point point) {
        int x = (int)point.getX();
        int y = (int)point.getY();
        return get(x, y);
    }

    /**
     * getBoardSize()
     * @return boardSize 
     * Task:    boardSize lekérdezés
     * Input:   void       - nincs
     * Output:   boardSize       - int
     * 
     * Activity:
     */
    public int getBoardSize() {
        return boardSize;
    }

}
