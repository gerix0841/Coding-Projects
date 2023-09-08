package rubiktable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;

/**
 *
 * @author pinter
 */
public class BoardGUI {

    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;
    private ArrayList<Point> points;

    private Random random = new Random();
    private int clickNum = 0;

    /**
     * BoardGUI(int boardSize)
     * @param boardSize
     * Task:    Constructor
     * Input:   boardSize       - int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public BoardGUI(int boardSize) {
        board = new Board(boardSize);
        boardPanel = new JPanel();
        points = new ArrayList<>();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button;
                if(i == 0){
                    if(j == 0 || j == board.getBoardSize()-1){
                        button = new JButton();
                    }
                    else{
                        button = new JButton("^");
                    }
                }
                else if(i == board.getBoardSize()-1){
                    if(j == 0 || j == board.getBoardSize()-1){
                        button = new JButton();
                    }
                    else{
                        button = new JButton("v");
                    }
                }
                else if(j == board.getBoardSize()-1){
                    if(i == 0 || i == board.getBoardSize()-1){
                        button = new JButton();
                    }
                    else{
                        button = new JButton(">");
                    }
                }
                else if(j == 0){
                    if(i == 0 || i == board.getBoardSize()-1){
                        button = new JButton();
                    }
                    else{
                        button = new JButton("<");
                    }
                }
                else{
                    button = new JButton();
                }
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(80, 40));
                buttons[i][j] = button;
                boardPanel.add(button);
                points.add(new Point(i, j));
            }
        }
        Collections.shuffle(points);
        oldRefresh();
    }

    /**
     * newRefresh()
     * @param direction
     * @param line
     * Task:    színek beállítása lépéskor
     * Input:   direction,line       - String,int
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void newRefresh(String direction,int line) {
        if(direction.equals("UP")){
            Color color = board.get(1, line).getColor();
            for(int i = 1;i < board.getBoardSize()-2;i++){
                board.get(i, line).setColor(board.get(i+1, line).getColor());
            }
            board.get(board.getBoardSize()-2, line).setColor(color);
        }
        else if(direction.equals("DOWN")){
            Color color = board.get(board.getBoardSize()-2, line).getColor();
            for(int i = board.getBoardSize()-2;i > 1;i--){
                board.get(i, line).setColor(board.get(i-1, line).getColor());
            }
            board.get(1, line).setColor(color);
        }
        else if(direction.equals("LEFT")){
            Color color = board.get(line, 1).getColor();
            for(int j = 1;j < board.getBoardSize()-2;j++){
                board.get(line, j).setColor(board.get(line, j+1).getColor());
            }
            board.get(line, board.getBoardSize()-2).setColor(color);
        }
        else if(direction.equals("RIGHT")){
            Color color = board.get(line, board.getBoardSize()-2).getColor();
            for(int j = board.getBoardSize()-2;j > 1;j--){
                board.get(line, j).setColor(board.get(line, j-1).getColor());
            }
            board.get(line, 1).setColor(color);
        }
        else{
        }
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                Field field = board.get(i, j);
                JButton button = buttons[i][j];
                button.setBackground(field.getColor());
            }
        }
    }
    
    /**
     * oldRefresh()
     * Task:    színek beállítása kezdéskor
     * Input:   void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public void oldRefresh() {
        for (int i = 1; i < board.getBoardSize()-1; i++) {
            for (int j = 1; j < board.getBoardSize()-1; j++) {
                if(board.get(i, j).getType().equals("coloredButton")){
                    if (board.get(i, j).getColor() == null) {
                        Color color = new Color(random.nextInt(256),
                                random.nextInt(256), random.nextInt(256));
                        board.get(i, j).setColor(color);
                        for (int x = 0; x < board.getBoardSize()-3;) {
                            Point point = points.remove(points.size()-1);
                            if (board.get(point).getColor() == null && !board.get(point).getType().equals("arrowButton")) {
                                board.get(point).setColor(color);
                                x++;
                            }
                        }
                        oldRefresh();
                    }
                }
                JButton button = buttons[i][j];
                button.setBackground(board.get(i, j).getColor());
            }
        }
        if (board.isOver()) {
            JOptionPane.showMessageDialog(boardPanel, "You have won in " + clickNum + " clicks", "Congrats!",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * getBoardPanel()
     * @return boardPanel 
     * Task:    boardPanel lekérdezés
     * Input:   void       - nincs
     * Output:   boardPanel       - JPanel
     * 
     * Activity:
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    class ButtonListener implements ActionListener {

        private int x, y;

        /**
         * ButtonListener(int x, int y)
         * @param x
         * @param y
         * Task:    Constructor
         * Input:   x,y       - int,int
         * Output:   void       - nincs
         * 
         * Activity:
         */
        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * actionPerformed(ActionEvent e)
         * @param e
         * Task:    műveletek gombokkal
         * Input:   e       - ActionEvent
         * Output:   void       - nincs
         * 
         * Activity:
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(board.get(x, y).getType().equals("arrowButton") && x == 0){
                clickNum++;
                newRefresh("UP",y);
                if (board.isOver()) JOptionPane.showMessageDialog(boardPanel, "You have won in " + clickNum + " clicks", "Congrats!",JOptionPane.PLAIN_MESSAGE);
            }
            else if(board.get(x, y).getType().equals("arrowButton") && x == board.getBoardSize()-1){
                clickNum++;
                newRefresh("DOWN",y);
                if (board.isOver()) JOptionPane.showMessageDialog(boardPanel, "You have won in " + clickNum + " clicks", "Congrats!",JOptionPane.PLAIN_MESSAGE);
            }
            else if(board.get(x, y).getType().equals("arrowButton") && y == 0){
                clickNum++;
                newRefresh("LEFT",x);
                if (board.isOver()) JOptionPane.showMessageDialog(boardPanel, "You have won in " + clickNum + " clicks", "Congrats!",JOptionPane.PLAIN_MESSAGE);
            }
            else if(board.get(x, y).getType().equals("arrowButton") && y == board.getBoardSize()-1){
                clickNum++;
                newRefresh("RIGHT",x);
                if (board.isOver()) JOptionPane.showMessageDialog(boardPanel, "You have won in " + clickNum + " clicks", "Congrats!",JOptionPane.PLAIN_MESSAGE);
            }
            else{
            }
        }
    }

}
