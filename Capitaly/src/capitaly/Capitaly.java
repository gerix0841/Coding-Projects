/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

import java.io.FileNotFoundException;

/**
 *
 * @author ASPP08
 */
public class Capitaly {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database database = new Database();
        try {
            database.read("data.txt");
            //database.read("data2.txt");
            //database.read("data3.txt");
            //database.read("data4.txt");
            //database.read("data5.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        database.play();
        database.clear();
    }
    
}
