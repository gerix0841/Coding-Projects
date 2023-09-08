package model;

public enum Direction {
    DOWN(0, 1), LEFT(-1, 0), UP(0, -1), RIGHT(1, 0);
    /**
     * Direction(int x, int y)
     * @param x
     * @param y
     * Task:    Constructor
     * Input:   x, y       - int, int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
    public final int x, y;
}
