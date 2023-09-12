package model;

public class Position {
    public int x, y;
    
    /**
     * Position(int x, int y)
     * @param x
     * @param y
     * Task:    Constructor
     * Input:   x, y       - int, int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }    
    
    /**
     * translate(Direction d)
     * @param d
     * @return new Position(x + d.x, y + d.y)
     * Task:     addNewGameLevel
     * Input:    d       - Direction
     * Output:   new Position(x + d.x, y + d.y)       - Position
     * 
     * Activity:
     */
    public Position translate(Direction d){
        return new Position(x + d.x, y + d.y);
    }
}
