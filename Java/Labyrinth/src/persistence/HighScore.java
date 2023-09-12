package persistence;

import java.util.Objects;
import model.GameID;

public class HighScore {
    public final String difficulty;
    public final int level;
    public final int doneLevels;
    
    /**
     * HighScore(GameID gameID, int doneLevel)
     * @param gameID
     * @param doneLevel
     * Task:    Constructor
     * Input:   gameID, doneLevel       - GameID, int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public HighScore(GameID gameID, int doneLevel){
        this.difficulty = gameID.difficulty;
        this.level = gameID.level;
        this.doneLevels = doneLevel;
    }
    
    /**
     * HighScore(String difficulty, int level, int doneLevel)
     * @param difficulty
     * @param level
     * @param doneLevel
     * Task:    Constructor
     * Input:   gameID, level, doneLevel        - GameID, int, int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public HighScore(String difficulty, int level, int doneLevel){
        this.difficulty = difficulty;
        this.level = level;
        this.doneLevels = doneLevel;
    }

    /**
     * hashCode()
     * @return hash
     * Task:    hashCode
     * Input:   void       - nincs
     * Output:   hash       - int
     * 
     * Activity:
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.difficulty);
        hash = 89 * hash + this.level;
        return hash;
    }

    /**
     * equals(Object obj)
     * @param obj
     * @return true
     * Task:     equals
     * Input:    obj       - Object
     * Output:   true       - boolean
     * 
     * Activity:
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HighScore other = (HighScore) obj;
        if (this.level != other.level) {
            return false;
        }
        if (!Objects.equals(this.difficulty, other.difficulty)) {
            return false;
        }
        return true;
    }   

    /**
     * toString()
     * @return difficulty + "-" + level + ": " + (level-1)
     * Task:     toString
     * Input:    void       - nincs
     * Output:   difficulty + "-" + level + ": " + (level-1)       - String
     * 
     * Activity:
     */
    @Override
    public String toString() {
        return difficulty + "-" + level + ": " + (level-1);
    }
    
    /**
     * getScore()
     * @return doneLevels
     * Task:     getScore
     * Input:    void       - nincs
     * Output:   doneLevels     - int
     * 
     * Activity:
     */
    public int getScore() {
        return doneLevels;
    }
    
    
}
