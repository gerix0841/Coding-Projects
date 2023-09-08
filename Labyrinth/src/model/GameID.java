package model;

import java.util.Objects;

public class GameID {
    public final String difficulty;
    public final int    level;

    /**
     * GameID(String difficulty, int level)
     * @param difficulty
     * @param level
     * Task:    Constructor
     * Input:   difficulty, level       - String, int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public GameID(String difficulty, int level) {
        this.difficulty = difficulty;
        this.level = level;
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
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.difficulty);
        hash = 29 * hash + this.level;
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
        final GameID other = (GameID) obj;
        if (this.level != other.level) {
            return false;
        }
        if (!Objects.equals(this.difficulty, other.difficulty)) {
            return false;
        }
        return true;
    }
    
    
}
