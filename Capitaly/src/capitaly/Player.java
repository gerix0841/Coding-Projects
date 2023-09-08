/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

import java.util.ArrayList;
/**
 *
 * @author ASPP08
 */
public abstract class Player {
    protected final String name;
    protected final String category;
    protected int money = 10000;
    protected ArrayList<Field> fields;
    protected int currentPos;
    
    /**
     * Player(String name, String category)
     * @param name
     * @param category
     * Task:    Constructor
     * Input:   name,category       - String,String
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Player(String name, String category) {
        this.name = name;
        this.category = category;
        fields = new ArrayList<>();
        this.currentPos = -1;
    }
    
    /**
     * getCategory()
     * @return category 
     * Task:    Category lekérdezés
     * Input:   void       - nincs
     * Output:   category       - String
     * 
     * Activity:
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * getName()
     * @return name 
     * Task:    Név lekérdezés
     * Input:   void       - nincs
     * Output:   name       - String
     * 
     * Activity:
     */
    public String getName() {
        return name;
    }
    
    /**
     * getMoney()
     * @return money 
     * Task:    Pénz lekérdezés
     * Input:   void       - nincs
     * Output:   money       - int
     * 
     * Activity:
     */
    public int getMoney() {
        return money;
    }
    
    /**
     * divMoney(int money)
     * @param money 
     * Task:    Pénz levonás
     * Input:   int money   - levonandó pénz
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void divMoney(int money) {
        this.money = this.money - money;
    }
    
    /**
     * addMoney(int money)
     * @param money 
     * Task:    Pénz hozzáadás
     * Input:   int money   - hozzáadandó pénz
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void addMoney(int money) {
        this.money = this.money + money;
    }
    
    /**
     * getCurrentPos()
     * @return currentPos 
     * Task:    currentPos lekérdezés
     * Input:   void       - nincs
     * Output:   currentPos       - int
     * 
     * Activity:
     */
    public int getCurrentPos() {
        return currentPos;
    }
    
    /**
     * setCurrentPos()
     * @param pos
     * Task:    CurrentPos beállítás
     * Input:   pos       - int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void setCurrentPos(int pos) {
        this.currentPos = pos;
    }
    
    /**
     * isAlive()
     * @return money > 0 
     * Task:    Életben van e
     * Input:   void       - nincs
     * Output:   true/false       - boolean
     * 
     * Activity:
     */
    public boolean isAlive() {
        return money > 0;
    }
    
    /**
     * toString()
     * @return "Player{" + "name=" + name + ", category=" + category + ", money=" + money + ", fields=" + fields + '}'
     * Task:    toString
     * Input:   void       - nincs
     * Output:             - String
     * 
     * Activity:
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", category=" + category + ", money=" + money + ", fields=" + fields + '}';
    }
    
    /**
     * buyField()
     * @param field
     * Task:      Field vásárlás
     * Input:     field       - Field
     * Output:    void       - nincs
     * 
     * Activity:
     */
    public void buyField(Field field) {
        fields.add(field);
    }
    
    /**
     * removeFields()
     * Task:     Field eltávolítás
     * Input:    void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void removeFields() {
        fields.clear();
    }
    
    /**
     * getFields()
     * @return fields
     * Task:     Field lekérdezés
     * Input:    void       - nincs
     * Output:   fields       - ArrayList(Field)
     * 
     * Activity:
     */
    public ArrayList<Field> getFields() {
        return fields;
    }
}
