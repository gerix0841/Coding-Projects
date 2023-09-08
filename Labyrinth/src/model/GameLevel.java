package model;

import java.util.ArrayList;
import java.util.Random;

public class GameLevel {
    public final GameID        gameID;
    public final int           rows, cols;
    public final LevelItem[][] level;
    public Position            player = new Position(0, 0);
    public Position            dragon = new Position(0, 0);
    private int                numBoxes, numBoxesInPlace, doneLevels;
    public boolean gameEnd = false;
    Random rand = new Random();
    Direction d2 = null;
    int rand_int;
    
    /**
     * GameLevel(ArrayList<String> gameLevelRows, GameID gameID)
     * @param gameLevelRows
     * @param gameID
     * Task:    Constructor
     * Input:   gameLevelRows, gameID       - ArrayList<String>, GameID
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public GameLevel(ArrayList<String> gameLevelRows, GameID gameID){
        this.gameID = gameID;
        int c = 0;
        for (String s : gameLevelRows) if (s.length() > c) c = s.length();
        rows = gameLevelRows.size();
        cols = c;
        level = new LevelItem[rows][cols];
        numBoxes = 0;
        numBoxesInPlace = 0;
        
        for (int i = 0; i < rows; i++){
            String s = gameLevelRows.get(i);
            for (int j = 0; j < s.length(); j++){
                switch (s.charAt(j)){
                    case '@': player = new Position(j, i);
                              level[i][j] = LevelItem.EMPTY; break;
                    case '#': level[i][j] = LevelItem.WALL; break;
                    case '$': level[i][j] = LevelItem.EXIT; 
                              numBoxes++;
                              break;
                    case '*': dragon = new Position(j, i);
                              level[i][j] = LevelItem.EMPTY; break;
                    default:  level[i][j] = LevelItem.EMPTY; break;
                }
            }
            for (int j = s.length(); j < cols; j++){
                level[i][j] = LevelItem.EMPTY;
            }
        }
        
        rand_int = rand.nextInt(4);
        switch (rand_int){
            case 0:  d2 = Direction.LEFT; break;
            case 1:  d2 = Direction.RIGHT; break;
            case 2:  d2 = Direction.UP; break;
            case 3:  d2 = Direction.DOWN; break;
        }
    }

    /**
     * GameLevel(GameLevel gl)
     * @param gl
     * Task:    Constructor
     * Input:   gl       - GameLevel
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public GameLevel(GameLevel gl) {
        gameID = gl.gameID;
        rows = gl.rows;
        cols = gl.cols;
        numBoxes = gl.numBoxes;
        numBoxesInPlace = gl.numBoxesInPlace;
        level = new LevelItem[rows][cols];
        player = new Position(gl.player.x, gl.player.y);
        dragon = new Position(gl.dragon.x, gl.dragon.y);
        for (int i = 0; i < rows; i++){
            System.arraycopy(gl.level[i], 0, level[i], 0, cols);
        }
        rand_int = rand.nextInt(4);
        switch (rand_int){
            case 0:  d2 = Direction.LEFT; break;
            case 1:  d2 = Direction.RIGHT; break;
            case 2:  d2 = Direction.UP; break;
            case 3:  d2 = Direction.DOWN; break;
        }
    }

    /**
     * isValidPosition(Position p)
     * @param p
     * @return (p.x >= 0 && p.y >= 0 && p.x <> cols && p.y <> rows)
     * Task:     isValidPosition
     * Input:    p       - Position
     * Output:   (p.x >= 0 && p.y >= 0 && p.x <> cols && p.y <> rows)       - boolean
     * 
     * Activity:
     */
    public boolean isValidPosition(Position p){
        return (p.x >= 0 && p.y >= 0 && p.x < cols && p.y < rows);
    }
    
    /**
     * isFree(Position p)
     * @param p
     * @return (level[p.y][p.x] == LevelItem.EMPTY)
     * Task:     isValidPosition
     * Input:    p       - Position
     * Output:   (level[p.y][p.x] == LevelItem.EMPTY)      - boolean
     * 
     * Activity:
     */
    public boolean isFree(Position p){
        if (!isValidPosition(p)) return false;
        LevelItem li = level[p.y][p.x];
        return (li == LevelItem.EMPTY);
    }
    
    /**
     * isExit(Position p)
     * @param p
     * @return (level[p.y][p.x] == LevelItem.EXIT)
     * Task:     isValidPosition
     * Input:    p       - Position
     * Output:   (level[p.y][p.x] == LevelItem.EXIT)      - boolean
     * 
     * Activity:
     */
    public boolean isExit(Position p) {
        if (!isValidPosition(p)) return false;
        LevelItem li = level[p.y][p.x];
        return (li == LevelItem.EXIT);
    }
    
    /**
     * movePlayer(Direction d)
     * @param d
     * @return false
     * Task:     movePlayer
     * Input:    d       - Direction
     * Output:   false      - boolean
     * 
     * Activity:
     */
    public boolean movePlayer(Direction d){
        Position curr = player;
        Position next = curr.translate(d);
        if (numBoxesInPlace < numBoxes && isFree(next) || isExit(next)) {
            player = next;
            if(isExit(next)){
                numBoxesInPlace = numBoxes;
            }
            Position curr2 = dragon;
            Position next2 = curr2.translate(d2);
            if(!isFree(next2) || isExit(next2)){
                while(!isFree(next2) || isExit(next2)){
                    rand_int = rand.nextInt(4);
                    switch (rand_int){
                        case 0:  d2 = Direction.LEFT; break;
                        case 1:  d2 = Direction.RIGHT; break;
                        case 2:  d2 = Direction.UP; break;
                        case 3:  d2 = Direction.DOWN; break;
                    }
                    next2 = curr2.translate(d2);
                }
            }
            dragon = next2;
            
            //moveDragon(d2);
            gameEnd = isKilled();
            return true;
        } 
        return false;
    }
    
    /**
     * isKilled()
     * @return false
     * Task:     isKilled
     * Input:    void       - nincs
     * Output:   false      - boolean
     * 
     * Activity:
     */
    public boolean isKilled(){
        if (dragon.y == player.y && dragon.x == player.x ||
            dragon.y == player.y-1 && dragon.x == player.x ||
            dragon.y == player.y+1 && dragon.x == player.x ||
            dragon.y == player.y && dragon.x == player.x+1 ||
            //dragon.y == player.y-1 && dragon.x == player.x+1 ||
            //dragon.y == player.y+1 && dragon.x == player.x+1 ||
            dragon.y == player.y && dragon.x == player.x-1 //||
            //dragon.y == player.y-1 && dragon.x == player.x-1 ||
            //dragon.y == player.y+1 && dragon.x == player.x-1
            ){
            return true;
        }
        return false;
    }
    
    /**
     * moveDragon(Direction d)
     * @param d
     * @return false
     * Task:     moveDragon
     * Input:    d       - Direction
     * Output:   false      - boolean
     * 
     * Activity:
     */
    public boolean moveDragon(Direction d){
        /*
        Position curr2 = dragon;
            Position next2 = curr2.translate(d2);
            if (!isFree(next2) || isExit(next2) || numBoxesInPlace >= numBoxes){
                    while(!isFree(next2)){
                        int rand_int = rand.nextInt(4);
                        //Direction d2 = null;
                        switch (rand_int){
                                case 0:  d2 = Direction.LEFT; break;
                                case 1:  d2 = Direction.RIGHT; break;
                                case 2:  d2 = Direction.UP; break;
                                case 3:  d2 = Direction.DOWN; break;
                            }
                        next2 = curr2.translate(d2);
                    }
            }
            else if(numBoxesInPlace < numBoxes && isFree(next2) && !isExit(next2)) {
                dragon = next2;
                return true;
            } 
        return false;
        */
        Position curr = dragon;
        Position next = curr.translate(d);
        if (numBoxesInPlace < numBoxes && isFree(next) && !isExit(next)) {
            dragon = next;
            return true;
        }else if(!isFree(next)|| isExit(next)){
            while(!isFree(next)){
                int rand_int = rand.nextInt(4);
                Direction d2 = null;
                switch (rand_int){
                    case 0:  d2 = Direction.LEFT; break;
                    case 1:  d2 = Direction.RIGHT; break;
                    case 2:  d2 = Direction.UP; break;
                    case 3:  d2 = Direction.DOWN; break;
                }
                next = curr.translate(d2);
            }
            dragon = next;
            return true;
        }
        return false;
    }
    
    /**
     * printLevel()
     * Task:     printLevel
     * Input:    void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void printLevel(){
        int x = player.x, y = player.y;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (i == y && j == x)
                    System.out.print('@');
                else 
                    System.out.print(level[i][j].representation);
            }
            System.out.println("");
        }
    }

    /**
     * getNumBoxes()
     * @return numBoxes
     * Task:     getNumBoxes
     * Input:    void       - nincs
     * Output:   numBoxes     - int
     * 
     * Activity:
     */
    public int getNumBoxes() {
        return numBoxes;
    }

    /**
     * getNumBoxesInPlace()
     * @return numBoxesInPlace
     * Task:     getNumBoxesInPlace
     * Input:    void       - nincs
     * Output:   numBoxesInPlace     - int
     * 
     * Activity:
     */
    public int getNumBoxesInPlace() {
        return numBoxesInPlace;
    }

    /**
     * getDoneLevels()
     * @return doneLevels
     * Task:     getDoneLevels
     * Input:    void       - nincs
     * Output:   doneLevels     - int
     * 
     * Activity:
     */
    public int getDoneLevels() {
        return doneLevels;
    }
    
    /**
     * setDoneLevels(int doneLevel)
     * @param doneLevel
     * Task:     setDoneLevels
     * Input:    doneLevel       - int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void setDoneLevels(int doneLevel) {
        doneLevels = doneLevel;
    }

    /**
     * getGameEnd()
     * @return gameEnd
     * Task:     getGameEnd
     * Input:    void       - nincs
     * Output:   gameEnd     - boolean
     * 
     * Activity:
     */
    public boolean getGameEnd() {
        return gameEnd;
    }
    
}
