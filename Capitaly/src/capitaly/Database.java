/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author bli
 */
public class Database {

    private final ArrayList<Player> players;
    private final ArrayList<Field> mapFields;
    private final ArrayList<Integer> rolls;
    private int rollSize;
    private int mapLength;
    private int playerNumber;
    Random rand = new Random();

    /**
     * Database()
     * Task:    Constructor
     * Input:   void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Database() {
        players = new ArrayList<>();
        mapFields = new ArrayList<>();
        rolls = new ArrayList<>();
        this.rollSize = 0;
        this.mapLength = 0;
        this.playerNumber = 0;
    }

    /**
     * read(String filename)
     * @param filename 
     * @throws FileNotFoundException
     * @throws InvalidInputException
     * Task:    Beolvasás
     * Input:   filename   - String
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void read(String filename) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        mapLength = sc.nextInt();
        int i = mapLength;
        while (sc.hasNext() && i > 0) {
            Field field;
            String category = sc.next();
            int price = sc.nextInt();
            switch (category) {
                case "L":
                    field = new LuckyField(category,price);
                    break;
                case "P":
                    field = new Property(category,price);
                    break;
                case "S":
                    field = new Services(category,price);
                    break;
                default:
                    throw new InvalidInputException();
            }
            mapFields.add(field);
            i--;
        }
        
        playerNumber = sc.nextInt();
        int j = playerNumber;
        
        while (sc.hasNext() && j > 0) {
            Player player;
            String name = sc.next();
            String category = sc.next();
            switch (category) {
                case "G":
                    player = new Greedy(name,category);
                    break;
                case "C":
                    player = new Cautious(name,category);
                    break;
                case "T":
                    player = new Tactician(name,category);
                    break;
                default:
                    throw new InvalidInputException();
            }
            players.add(player);
            j--;
        }
        
        while (sc.hasNext()) {
            rolls.add(sc.nextInt());
            rollSize++;
        }
    }
    
    /**
     * play()
     * Task:    Játék
     * Input:   void   - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void play() {
        /*System.out.println("mapLength:");
        System.out.println(mapLength);
        
        System.out.println("mapFields in the database:");
        for (Field a : mapFields) {
            System.out.println(a);
        }
        
        System.out.println("playerNumber:");
        System.out.println(playerNumber);
        
        System.out.println("players in the database:");
        for (Player a : players) {
            System.out.println(a);
        }
        
        System.out.println("rolls:");
        for (Integer a : rolls) {
            System.out.println(a);
        }*/
        
        int currentPlayerID = 0;
        int currentRound = 0;
        int currentRoll = 0;
        
        while(playerNumber > 1){
            Player currentPlayer = players.get(currentPlayerID);
            
            //DOBÁS
            int roll;
            if(rolls.isEmpty()){
               roll = rand.nextInt(6) + 1;
            }
            else{
                roll = rolls.get(currentRoll);
                if(currentRoll < rollSize - 1){
                    currentRoll++;
                }
                else{
                    currentRoll = 0;
                }
            }
            
            //JÁTÉKOS LÉPTETÉS
            currentPlayer.setCurrentPos((currentPlayer.getCurrentPos() + roll) % mapLength);

            Field currentField = mapFields.get(currentPlayer.getCurrentPos());
            
            //MEZŐ ELLENŐRZÉS
            if(currentField.getCategory().equals("S")){
                currentPlayer.divMoney(currentField.getPrice());
            }
            else if(currentField.getCategory().equals("L")){
                currentPlayer.addMoney(currentField.getPrice());
            }
            else if(currentField.getCategory().equals("P")){
                //Greedy
                if(currentPlayer.getCategory().equals("G")){
                    //Vásárolatlan
                    if(currentField.getOwned() == false && currentPlayer.getMoney() - currentField.getPrice() > 0){
                        currentPlayer.divMoney(currentField.getPrice());
                        currentPlayer.buyField(currentField);
                        currentField.setOwned(true);
                        currentField.setOwnerID(currentPlayerID);
                    }
                    //Vásárolt de nem saját
                    else if(currentField.getOwned() == true && !currentPlayer.getFields().contains(currentField)){
                        if(currentField.getHasHouse()){
                            currentPlayer.divMoney(2000);
                            players.get(currentField.getOwnerID()).addMoney(2000);
                        }
                        else{
                            currentPlayer.divMoney(500);
                            players.get(currentField.getOwnerID()).addMoney(500);
                        }
                    }
                    //Vásárolt saját
                    else if(currentField.getOwned() == true && currentPlayer.getFields().contains(currentField)){
                        if(!currentField.getHasHouse() && currentPlayer.getMoney() > 4000){
                            currentPlayer.divMoney(4000);
                            currentField.setHasHouse(true);
                        }
                    }
                }

                //Cautious
                else if(currentPlayer.getCategory().equals("C")){
                    //Vásárolatlan
                    if(currentField.getOwned() == false && (currentPlayer.getMoney() - currentField.getPrice()) > (currentPlayer.getMoney()/2)){
                        currentPlayer.divMoney(currentField.getPrice());
                        currentPlayer.buyField(currentField);
                        currentField.setOwned(true);
                        currentField.setOwnerID(currentPlayerID);
                    }
                    //Vásárolt de nem saját
                    else if(currentField.getOwned() == true && !currentPlayer.getFields().contains(currentField)){
                        if(currentField.getHasHouse()){
                            currentPlayer.divMoney(2000);
                            players.get(currentField.getOwnerID()).addMoney(2000);
                        }
                        else{
                            currentPlayer.divMoney(500);
                            players.get(currentField.getOwnerID()).addMoney(500);
                        }
                    }
                    //Vásárolt saját
                    else if(currentField.getOwned() == true && currentPlayer.getFields().contains(currentField)){
                        if(!currentField.getHasHouse() && currentPlayer.getMoney() > 8000){
                            currentPlayer.divMoney(4000);
                            currentField.setHasHouse(true);
                        }
                    }
                }

                //Tactician
                else if(currentPlayer.getCategory().equals("T")){
                    //Vásárolatlan
                    if((currentField.getOwned() == false) && (currentPlayer.getMoney() - currentField.getPrice() > 0) && (currentRound % 2 != 0)){
                        currentPlayer.divMoney(currentField.getPrice());
                        currentPlayer.buyField(currentField);
                        currentField.setOwned(true);
                        currentField.setOwnerID(currentPlayerID);
                    }
                    //Vásárolt de nem saját
                    else if(currentField.getOwned() == true && !currentPlayer.getFields().contains(currentField)){
                        if(currentField.getHasHouse()){
                            currentPlayer.divMoney(2000);
                            players.get(currentField.getOwnerID()).addMoney(2000);
                        }
                        else{
                            currentPlayer.divMoney(500);
                            players.get(currentField.getOwnerID()).addMoney(500);
                        }
                    }
                    //Vásárolt saját
                    else if(currentField.getOwned() == true && currentPlayer.getFields().contains(currentField)){
                        if(!currentField.getHasHouse() && currentPlayer.getMoney() > 4000 && currentRound % 2 != 0){
                            currentPlayer.divMoney(4000);
                            currentField.setHasHouse(true);
                        }
                    }
                }
                
                //ÉLET ELLENŐRZÉS
                if(!currentPlayer.isAlive()) {
                    for (Field a : currentPlayer.getFields()) {
                        a.setHasHouse(false);
                        a.setOwned(false);
                        a.setOwnerID(-1);
                    }
                    currentPlayer.removeFields();
                    players.remove(players.indexOf(currentPlayer));
                    playerNumber--;
                }
                
                //JÁTÉKOS / KÖR VÁLTÁS
                if(currentPlayerID < playerNumber - 1){
                    currentPlayerID++; 
                }
                else{
                    currentPlayerID = 0;
                }
            }
        }
        
        System.out.println("Winner:");
        System.out.println(players.get(0).toString());
        
        /*System.out.println(players.get(0).toString());
        System.out.println(players.get(1).toString());
        players.get(0).setCurrentPos(0);
        players.get(0).buyField(mapFields.get(0));
        players.get(1).setCurrentPos(0);
        players.get(1).divMoney(500);
        players.get(0).addMoney(500);
        System.out.println(players.get(0).toString());
        System.out.println(players.get(1).toString());
        */
    }
    
    /**
     * clear()
     * Task:    ArrayList-ek törlése
     * Input:   void   - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void clear() {
        players.clear();
        mapFields.clear();
    }

}
