package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import model.Direction;
import model.Game;
import model.GameID;
import javax.swing.Timer;

public class MainWindow extends JFrame{
    private final Game game;
    private Board board;
    private final JLabel gameStatLabel; 
    int currLevel = 1;
    int doneLevels = 0;
    Timer time;
    int k = 1;
    
    /**
     * MainWindow()
     * Task:    Constructor
     * Input:   void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public MainWindow() throws IOException{
        game = new Game();
        
        setTitle("Labyrinth");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        URL url = MainWindow.class.getClassLoader().getResource("res/icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Játék");
        JMenu menuGameLevel = new JMenu("Újrakezdés");
        createGameLevelMenuItems(menuGameLevel);
        JMenu menuGameScale = new JMenu("Nagyítás");
        createScaleMenuItems(menuGameScale, 1.0, 2.0, 0.5);
        
        JMenuItem menuHighScores = new JMenuItem(new AbstractAction("Dicsőség tábla") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScoreWindow(game.getHighScores(), MainWindow.this);
            }
        });

        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Kilépés") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuGame.add(menuGameLevel);
        menuGame.add(menuGameScale);
        menuGame.add(menuHighScores);
        menuGame.addSeparator();
        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        setJMenuBar(menuBar);
        
        setLayout(new BorderLayout(0, 20));
        gameStatLabel = new JLabel("Kivitt szintek száma: 0 / Eltelt idő: 0");

        add(gameStatLabel, BorderLayout.NORTH);
        try { add(board = new Board(game), BorderLayout.CENTER); } catch (IOException ex) {}
        
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke); 
                if (!game.isLevelLoaded()) return;
                int kk = ke.getKeyCode();
                Direction d = null;
                switch (kk){
                    case KeyEvent.VK_LEFT:  d = Direction.LEFT; break;
                    case KeyEvent.VK_RIGHT: d = Direction.RIGHT; break;
                    case KeyEvent.VK_UP:    d = Direction.UP; break;
                    case KeyEvent.VK_DOWN:  d = Direction.DOWN; break;
                    case KeyEvent.VK_ESCAPE: game.loadGame(game.getGameID());
                }
                //refreshGameStatLabel();
                board.repaint();
                if (d != null && game.step(d)){
                    if (game.getLevelNumBoxes() == game.getLevelNumBoxesInPlace()){
                        if(currLevel < 10){
                           JOptionPane.showMessageDialog(MainWindow.this, "Gratulálok! Nyertél! Jön a következő szint!", "Gratulálok!", JOptionPane.INFORMATION_MESSAGE);
                            currLevel++;
                            doneLevels++;
                            game.setDoneLevels(doneLevels);
                            game.loadGame(new GameID("SZINTEK", currLevel));
                            board.refresh();
                            pack();
                            refreshGameStatLabel(); 
                        }
                        else{
                            doneLevels++;
                            game.setDoneLevels(doneLevels);
                            JOptionPane.showMessageDialog(MainWindow.this, "Gratulálok! Sikeresen kivitted a játékot!", "Gratulálok!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if(game.getGameEnd()){
                        JOptionPane.showMessageDialog(MainWindow.this, "Vesztettél!", "Vesztettél!", JOptionPane.INFORMATION_MESSAGE);
                        doneLevels = 0;
                        game.setDoneLevels(doneLevels);
                        currLevel = 1;
                        game.loadGame(new GameID("SZINTEK", currLevel));
                        board.refresh();
                        pack();
                        k = 1;
                        refreshGameStatLabel();
                    }
                } 
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        game.loadGame(new GameID("SZINTEK", currLevel));
        board.refresh();
        pack();
        refreshGameStatLabel();
        setVisible(true);
    }
    
    /**
     * refreshGameStatLabel()
     * Task:     refreshGameStatLabel
     * Input:    void       - nincs
     * Output:   void       - nincs
     * 
     * Activity:
     */
    private void refreshGameStatLabel(){
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String s = "Kivitt szintek száma: " + doneLevels + " / Eltelt idő: " + String.valueOf(k-1);
                gameStatLabel.setText(s);
                k++;
            }
        };
        
        time = new Timer(1000 ,taskPerformer);
        time.start();
    }
    
    /**
     * createGameLevelMenuItems(JMenu menu)
     * @param menu
     * Task:     createGameLevelMenuItems
     * Input:    menu       - JMenu
     * Output:   void       - nincs
     * 
     * Activity:
     */
    private void createGameLevelMenuItems(JMenu menu){
        for (String s : game.getDifficulties()){
            JMenu difficultyMenu = new JMenu(s);
            menu.add(difficultyMenu);
            for (Integer i : game.getLevelsOfDifficulty(s)){
                if(i == 1){
                    JMenuItem item = new JMenuItem(new AbstractAction("Level-" + i) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.loadGame(new GameID(s, i));
                        currLevel = i;
                        doneLevels = 0;
                        game.setDoneLevels(doneLevels);
                        k = 1;
                        board.refresh();
                        pack();
                    }
                    });
                    difficultyMenu.add(item);
                }
            }
        }
    }
    
    /**
     * createScaleMenuItems(JMenu menu, double from, double to, double by)
     * @param menu
     * @param from
     * @param to
     * @param by
     * Task:     createScaleMenuItems
     * Input:    menu, from, to, by       - JMenu, double, double, double
     * Output:   void       - nincs
     * 
     * Activity:
     */
    private void createScaleMenuItems(JMenu menu, double from, double to, double by){
        while (from <= to){
            final double scale = from;
            JMenuItem item = new JMenuItem(new AbstractAction(from + "x") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (board.setScale(scale)) pack();
                }
            });
            menu.add(item);
            
            if (from == to) break;
            from += by;
            if (from > to) from = to;
        }
    }
    
    public static void main(String[] args) {
        try {
            new MainWindow();
        } catch (IOException ex) {}
    }    
}
