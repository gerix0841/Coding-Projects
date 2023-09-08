/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

/**
 *
 * @author ASPP08
 */
public abstract class Field {
    protected final String category;
    protected final int price;
    protected boolean hasHouse;
    protected boolean owned;
    protected int ownerID;
    
    /**
     * Field(String category,int price)
     * @param category
     * @param price
     * Task:    Constructor
     * Input:   category,price       - String,int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Field(String category,int price) {
        this.category = category;
        this.price = price;
        this.hasHouse = false;
        this.owned = false;
        this.ownerID = -1;
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
     * getPrice()
     * @return price 
     * Task:    Price lekérdezés
     * Input:   void       - nincs
     * Output:   price       - int
     * 
     * Activity:
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * getHasHouse()
     * @return hasHouse 
     * Task:    HasHouse lekérdezés
     * Input:   void       - nincs
     * Output:   hasHouse       - boolean
     * 
     * Activity:
     */
    public boolean getHasHouse() {
        return hasHouse;
    }
    
    /**
     * setHasHouse()
     * @param a 
     * Task:    hasHouse beállítás
     * Input:   a       - boolean
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void setHasHouse(boolean a) {
        hasHouse = a;
    }
    
    /**
     * getOwned()
     * @return owned 
     * Task:    Owned lekérdezés
     * Input:   void       - nincs
     * Output:   owned       - boolean
     * 
     * Activity:
     */
    public boolean getOwned() {
        return owned;
    }
    
    /**
     * setOwned()
     * @param a 
     * Task:    setOwned beállítás
     * Input:   a       - boolean
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void setOwned(boolean a) {
        owned = a;
    }
    
    /**
     * getOwnerID()
     * @return ownerID 
     * Task:    OwnerID lekérdezés
     * Input:   void       - nincs
     * Output:   ownerID       - int
     * 
     * Activity:
     */
    public int getOwnerID() {
        return ownerID;
    }
    
    /**
     * setOwnerID()
     * @param ID 
     * Task:    setOwnerID beállítás
     * Input:   ownerID       - int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void setOwnerID(int ID) {
        this.ownerID = ID;
    }
    
    /**
     * toString()
     * @return "Field{" + "category=" + category + ", price=" + price + '}'
     * Task:    toString
     * Input:   void       - nincs
     * Output:             - String
     * 
     * Activity:
     */
    @Override
    public String toString() {
        return "Field{" + "category=" + category + ", price=" + price + '}';
    }
}
