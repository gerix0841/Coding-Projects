package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import model.GameID;

public class Database {
    private final String tableName = "highscore";
    private final Connection conn;
    private final HashMap<GameID, Integer> highScores;
    int maxScores;
    
    /**
     * Database(int maxScores)
     * @param maxScores
     * Task:    Constructor
     * Input:   maxScores      - int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Database(int maxScores){
        this.maxScores = maxScores;
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/labyrinth?"
                    + "serverTimezone=UTC&user=student&password=student");
        } catch (Exception ex) {
            System.out.println("No connection");
        }
        this.conn = c;
        highScores = new HashMap<>();
        loadHighScores();
    }
    
    /**
     * GameID readGameID(String line)
     * @param id
     * @param newScore
     * @return mergeHighScores(id, newScore, newScore > 0)
     * Task:     readGameID
     * Input:    id, newScore       - GameID, int
     * Output:   mergeHighScores(id, newScore, newScore > 0)       - boolean
     * 
     * Activity:
     */
    public boolean storeHighScore(GameID id, int newScore){
        return mergeHighScores(id, newScore, newScore > 0);
    }
    
    /**
     * getHighScores()
     * @return scores
     * Task:     getHighScores
     * Input:    void       - nincs
     * Output:   scores       - ArrayList<HighScore> 
     * 
     * Activity:
     */
    public ArrayList<HighScore> getHighScores(){
        ArrayList<HighScore> scores = new ArrayList<>();
        for (GameID id : highScores.keySet()){
            HighScore h = new HighScore(id, highScores.get(id));
            scores.add(h);
            System.out.println(h);
        }
        return scores;
    }
    
    /**
     * loadHighScores()
     * Task:     loadHighScores
     * Input:    void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    private void loadHighScores(){
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()){
                String diff = rs.getString("Difficulty");
                int level = rs.getInt("GameLevel");
                int doneLevels = rs.getInt("DoneLevel");
                GameID id = new GameID(diff, level);
                mergeHighScores(id, doneLevels, false);
            }
        } catch (Exception e){ System.out.println("loadHighScores error: " + e.getMessage());}
    }
    
    /**
     * mergeHighScores(GameID id, int score, boolean store)
     * @param id
     * @param score
     * @param store
     * @return false
     * Task:     mergeHighScores
     * Input:    id, score, store       - int, int, int
     * Output:   false       - boolean
     * 
     * Activity:
     */
    private boolean mergeHighScores(GameID id, int score, boolean store){
        System.out.println("Merge: " + id.difficulty + "-" + id.level + ":" + (id.level-1) + "(" + store + ")");
        boolean doUpdate = true;
        if (highScores.containsKey(id)){
            int oldScore = highScores.get(id);
            doUpdate = ((score < oldScore && score != 0) || oldScore == 0);
        }
        if (doUpdate){
            highScores.remove(id);
            highScores.put(id, score);
            if (store) return storeToDatabase(id, score) > 0;
            return true;
        }
        return false;
    }
    
    /**
     * storeToDatabase(GameID id, int score)
     * @param id
     * @param score
     * @return 0
     * Task:     mergeHighScores
     * Input:    id, score       - int, int
     * Output:   0       - int
     * 
     * Activity:
     */
    private int storeToDatabase(GameID id, int score){
        ArrayList<HighScore> highScores = getHighScores();
        if (highScores.size() < maxScores) {
            try (Statement stmt = conn.createStatement()){
            String s = "INSERT INTO " + tableName + 
                    " (Difficulty, GameLevel, DoneLevel) " + 
                    "VALUES('" + id.difficulty + "'," + id.level + 
                    "," + score + 
                    ") ON DUPLICATE KEY UPDATE DoneLevel=" + score;
            return stmt.executeUpdate(s);
            } catch (Exception e){
                System.out.println("storeToDatabase error");
            }
        }
        return 0;
    }
    
}
