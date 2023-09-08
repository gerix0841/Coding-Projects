package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import persistence.HighScore;

public class HighScoreTableModel extends AbstractTableModel{
    private final ArrayList<HighScore> highScores;
    private final String[] colName = new String[]{  "Pálya", "Labirintusok száma" };
    
    /**
     * HighScoreTableModel(ArrayList<HighScore> highScores
     * @param highScores
     * Task:    Constructor
     * Input:   highScores       - ArrayList<HighScore>
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public HighScoreTableModel(ArrayList<HighScore> highScores){
        this.highScores = highScores;
    }

    /**
     * getRowCount()
     * @return highScores.size()
     * Task:     getRowCount
     * Input:    void       - nincs
     * Output:   highScores.size()      - int
     * 
     * Activity:
     */
    @Override
    public int getRowCount() { return highScores.size(); }

    /**
     * getColumnCount()
     * @return 2
     * Task:     getColumnCount
     * Input:    void       - nincs
     * Output:   2      - int
     * 
     * Activity:
     */
    @Override
    public int getColumnCount() { return 2; }

    /**
     * getValueAt(int r, int c)
     * @return h.level-1
     * Task:     getValueAt
     * Input:    r, c       - int, int
     * Output:   h.level-1      - Object
     * 
     * Activity:
     */
    @Override
    public Object getValueAt(int r, int c) {
        HighScore h = highScores.get(r);
        if (c == 0) return h.level;
        return h.level-1;
    }

    /**
     * getColumnName(int i)
     * @return colName[i]
     * Task:     getColumnName
     * Input:    void       - nincs
     * Output:   colName[i]      - String
     * 
     * Activity:
     */
    @Override
    public String getColumnName(int i) { return colName[i]; }    
    
}
