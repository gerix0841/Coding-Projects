package rubiktable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.util.Random;

/**
 *
 * @author pinter
 */
public class Field {

    private Color color;
    private String type;

    /**
     * Field(String type)
     * @param type
     * Task:    Constructor
     * Input:   type      - String
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Field(String type) {
        color = null;
        this.type = type;
    }

    /**
     * getColor()
     * @return color 
     * Task:    Color lekérdezés
     * Input:   void       - nincs
     * Output:   color       - Color
     * 
     * Activity:
     */
    public Color getColor() {
        return color;
    }

    /**
     * setColor()
     * @param color 
     * Task:    setColor beállítás
     * Input:   color       - Color
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * getType()
     * @return type 
     * Task:    type lekérdezés
     * Input:   void       - nincs
     * Output:   type       - String
     * 
     * Activity:
     */
    public String getType() {
        return type;
    }
}
