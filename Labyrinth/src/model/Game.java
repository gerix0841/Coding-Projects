package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import persistence.Database;
import persistence.HighScore;
import res.ResourceLoader;

public class Game {
    private final HashMap<String, HashMap<Integer, GameLevel>> gameLevels;
    private GameLevel gameLevel = null;
    private final Database database;
    private boolean isBetterHighScore = false;
    
    /**
     * Game()
     * Task:     Constructor
     * Input:    void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Game() {
        gameLevels = new HashMap<>();
        database = new Database(10);
        readLevels();
    }

    // ------------------------------------------------------------------------
    // The 'interesting' part :)
    // ------------------------------------------------------------------------
    /**
     * loadGame(GameID gameID)
     * @param gameID
     * Task:     Game loading
     * Input:    gameID       - GameID
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void loadGame(GameID gameID){
        gameLevel = new GameLevel(gameLevels.get(gameID.difficulty).get(gameID.level));
        isBetterHighScore = false;
    }
    
    /**
     * printGameLevel()
     * Task:     GameLevel printing
     * Input:    void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void printGameLevel(){ gameLevel.printLevel(); }
    
    /**
     * step(Direction d)
     * @param d
     * @return stepped
     * Task:     step
     * Input:    d       - Direction
     * Output:   stepped       - boolean
     * 
     * Activity:
     */
    public boolean step(Direction d){
        boolean stepped = (gameLevel.movePlayer(d));
        if (stepped && getGameEnd()){
            GameID id = gameLevel.gameID;
            int doneLevels = gameLevel.getDoneLevels();
            isBetterHighScore = database.storeHighScore(id, doneLevels);
        }
        return stepped;
    }
    
    // ------------------------------------------------------------------------
    // Getter methods
    // ------------------------------------------------------------------------
    
    /**
     * getDifficulties()
     * @return gameLevels.keySet()
     * Task:     getDifficulties
     * Input:    void       - nincs
     * Output:   gameLevels.keySet()       - Collection<String>
     * 
     * Activity:
     */
    public Collection<String> getDifficulties(){ return gameLevels.keySet(); }
    
    /**
     * getLevelsOfDifficulty(String difficulty)
     * @param difficulty
     * @return gameLevels.get(difficulty).keySet()
     * Task:     getLevelsOfDifficulty
     * Input:    difficulty       - String
     * Output:   gameLevels.get(difficulty).keySet()       - Collection<Integer>
     * 
     * Activity:
     */
    public Collection<Integer> getLevelsOfDifficulty(String difficulty){
        if (!gameLevels.containsKey(difficulty)) return null;
        return gameLevels.get(difficulty).keySet();
    }
    
    /**
     * isLevelLoaded()
     * @return gameLevel != null
     * Task:     isLevelLoaded
     * Input:    void       - nincs
     * Output:   gameLevel != null      - boolean
     * 
     * Activity:
     */
    public boolean isLevelLoaded(){ return gameLevel != null; }
    
    /**
     * getLevelRows()
     * @return gameLevel.rows
     * Task:     getLevelRows
     * Input:    void       - nincs
     * Output:   gameLevel.rows      - int
     * 
     * Activity:
     */
    public int getLevelRows(){ return gameLevel.rows; }
    
    /**
     * getLevelCols()
     * @return gameLevel.cols
     * Task:     getLevelCols
     * Input:    void       - nincs
     * Output:   gameLevel.cols     - int
     * 
     * Activity:
     */
    public int getLevelCols(){ return gameLevel.cols; }
    
    /**
     * getItem(int row, int col)
     * @param row
     * @param col
     * @return gameLevel.level[row][col]
     * Task:     getItem LevelItem
     * Input:    row, col       - int, int
     * Output:   gameLevel.level[row][col]     - LevelItem
     * 
     * Activity:
     */
    public LevelItem getItem(int row, int col){ return gameLevel.level[row][col]; }
    
    /**
     * getGameID()
     * @return gameLevel.gameID
     * Task:     getLevelCols
     * Input:    void       - nincs
     * Output:   (gameLevel != null) ? gameLevel.gameID : null     - GameID
     * 
     * Activity:
     */
    public GameID getGameID(){ return (gameLevel != null) ? gameLevel.gameID : null; }
    
    /**
     * getLevelNumBoxes()
     * @return gameLevel.getNumBoxes()
     * Task:     getLevelNumBoxes
     * Input:    void       - nincs
     * Output:   (gameLevel != null) ? gameLevel.getNumBoxes() : 0     - int
     * 
     * Activity:
     */
    public int getLevelNumBoxes(){ return (gameLevel != null) ? gameLevel.getNumBoxes() : 0; }
    
    /**
     * getLevelNumBoxesInPlace()
     * @return gameLevel.getNumBoxesInPlace()
     * Task:     getLevelNumBoxesInPlace
     * Input:    void       - nincs
     * Output:   (gameLevel != null) ? gameLevel.getNumBoxesInPlace(): 0     - int
     * 
     * Activity:
     */
    public int getLevelNumBoxesInPlace(){ return (gameLevel != null) ? gameLevel.getNumBoxesInPlace(): 0; }
    
    /**
     * getDoneLevels()
     * @return gameLevel.getDoneLevels()
     * Task:     getDoneLevels
     * Input:    void       - nincs
     * Output:   gameLevel.getDoneLevels()     - int
     * 
     * Activity:
     */
    public int getDoneLevels(){ return gameLevel.getDoneLevels(); }
    
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
        gameLevel.setDoneLevels(doneLevel);
    }
    
    /**
     * getGameEnd()
     * @return (gameLevel != null && gameLevel.getGameEnd())
     * Task:     getGameEnd
     * Input:    void       - nincs
     * Output:   (gameLevel != null && gameLevel.getGameEnd())     - boolean
     * 
     * Activity:
     */
    public boolean getGameEnd(){ return (gameLevel != null && gameLevel.getGameEnd()); }
    
    /**
     * isBetterHighScore()
     * @return isBetterHighScore
     * Task:     isBetterHighScore
     * Input:    void       - nincs
     * Output:   isBetterHighScore     - boolean
     * 
     * Activity:
     */
    public boolean isBetterHighScore(){ return isBetterHighScore; }

    /**
     * getPlayerPos()
     * @return new Position(gameLevel.player.x, gameLevel.player.y)
     * Task:     getPlayerPos
     * Input:    void       - nincs
     * Output:   new Position(gameLevel.player.x, gameLevel.player.y)    - Position
     * 
     * Activity:
     */
    public Position getPlayerPos(){ // MAKE IT ~IMMUTABLE
        return new Position(gameLevel.player.x, gameLevel.player.y); 
    }
    
    /**
     * getDragonPos()
     * @return new Position(gameLevel.dragon.x, gameLevel.dragon.y)
     * Task:     getDragonPos
     * Input:    void       - nincs
     * Output:   new Position(gameLevel.dragon.x, gameLevel.dragon.y)    - Position
     * 
     * Activity:
     */
    public Position getDragonPos(){ // MAKE IT ~IMMUTABLE
        return new Position(gameLevel.dragon.x, gameLevel.dragon.y); 
    }
    
    /**
     * getHighScores()
     * @return database.getHighScores()
     * Task:     getHighScores
     * Input:    void       - nincs
     * Output:   database.getHighScores()   - ArrayList<HighScore>
     * 
     * Activity:
     */
    public ArrayList<HighScore> getHighScores() {
        return database.getHighScores();
    }
    
    // ------------------------------------------------------------------------
    // Utility methods to load game levels from res/levels.txt resource file.
    // ------------------------------------------------------------------------

    /**
     * readLevels()
     * Task:     readLevels
     * Input:    void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    private void readLevels(){
        //ClassLoader cl = getClass().getClassLoader();
        InputStream is;// = cl.getResourceAsStream("res/levels.txt");
        is = ResourceLoader.loadResource("res/levels.txt");
        
        try (Scanner sc = new Scanner(is)){
            String line = readNextLine(sc);
            ArrayList<String> gameLevelRows = new ArrayList<>();
            
            while (!line.isEmpty()){
                GameID id = readGameID(line);
                if (id == null) return;
                
                // System.out.println(id.difficulty + " " + id.id);

                gameLevelRows.clear();
                line = readNextLine(sc);
                while (!line.isEmpty() && line.trim().charAt(0) != ';'){
                    gameLevelRows.add(line);                    
                    line = readNextLine(sc);
                }
                addNewGameLevel(new GameLevel(gameLevelRows, id));
            }
            //if (is != null) is.close();
        } catch (Exception e){
            System.out.println("Ajaj");
        }
        
    }
    
    /**
     * addNewGameLevel(GameLevel gameLevel)
     * @param gameLevel
     * Task:     addNewGameLevel
     * Input:    gameLevel       - GameLevel
     * Output:   void       - nincs
     * 
     * Activity:
     */
    private void addNewGameLevel(GameLevel gameLevel){
        HashMap<Integer, GameLevel> levelsOfDifficulty;
        if (gameLevels.containsKey(gameLevel.gameID.difficulty)){
            levelsOfDifficulty = gameLevels.get(gameLevel.gameID.difficulty);
            levelsOfDifficulty.put(gameLevel.gameID.level, gameLevel);
        } else {
            levelsOfDifficulty = new HashMap<>();
            levelsOfDifficulty.put(gameLevel.gameID.level, gameLevel);
            gameLevels.put(gameLevel.gameID.difficulty, levelsOfDifficulty);
        }
    }
  
    /**
     * readNextLine(Scanner sc
     * @param sc
     * @return line
     * Task:     addNewGameLevel
     * Input:    sc       - Scanner
     * Output:   line       - String
     * 
     * Activity:
     */
    private String readNextLine(Scanner sc){
        String line = "";
        while (sc.hasNextLine() && line.trim().isEmpty()){
            line = sc.nextLine();
        }
        return line;
    }
    
    /**
     * GameID readGameID(String line)
     * @param line
     * @return new GameID(difficulty, id)
     * Task:     readGameID
     * Input:    line       - String
     * Output:   new GameID(difficulty, id)       - GameID
     * 
     * Activity:
     */
    private GameID readGameID(String line){
        line = line.trim();
        if (line.isEmpty() || line.charAt(0) != ';') return null;
        Scanner s = new Scanner(line);
        s.next(); // skip ';'
        if (!s.hasNext()) return null;
        String difficulty = s.next().toUpperCase();
        if (!s.hasNextInt()) return null;
        int id = s.nextInt();
        return new GameID(difficulty, id);
    }    
}
