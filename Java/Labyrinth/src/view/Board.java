package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.swing.JPanel;
import model.Game;
import model.LevelItem;
import model.Position;
import res.ResourceLoader;

public class Board extends JPanel {
    private Game game;
    private final Image exit, dragon, player, wall, empty, light, dragoninside;
    private double scale;
    private int scaled_size;
    private final int tile_size = 32;
    
    /**
     * Board(Game g)
     * @param g
     * Task:    Constructor
     * Input:   g       - Game
     * Output:   void       - nincs
     * 
     * Activity:
     */
    public Board(Game g) throws IOException{
        game = g;
        scale = 1.0;
        scaled_size = (int)(scale * tile_size);
        exit = ResourceLoader.loadImage("res/exit.png");
        dragon = ResourceLoader.loadImage("res/dragon.png");
        player = ResourceLoader.loadImage("res/player.png");
        wall = ResourceLoader.loadImage("res/wall.png");
        empty = ResourceLoader.loadImage("res/empty.png");
        light = ResourceLoader.loadImage("res/light.png");
        dragoninside = ResourceLoader.loadImage("res/dragoninside.png");
    }
    
    /**
     * setScale(double scale)
     * @param scale
     * @return refresh()
     * Task:     setScale
     * Input:    scale       - double
     * Output:   refresh()       - boolean
     * 
     * Activity:
     */
    public boolean setScale(double scale){
        this.scale = scale;
        scaled_size = (int)(scale * tile_size);
        return refresh();
    }
    
    /**
     * refresh()
     * @return true
     * Task:     refresh
     * Input:    void       - nincs
     * Output:   true       - boolean
     * 
     * Activity:
     */
    public boolean refresh(){
        if (!game.isLevelLoaded()) return false;
        Dimension dim = new Dimension(game.getLevelCols() * scaled_size, game.getLevelRows() * scaled_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);
        repaint();
        return true;
    }
    
    /**
     * paintComponent(Graphics g)
     * @param g
     * Task:     paintComponent
     * Input:    g       - Graphics
     * Output:   void       - nincs
     * 
     * Activity:
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (!game.isLevelLoaded()) return;
        Graphics2D gr = (Graphics2D)g;
        int w = game.getLevelCols();
        int h = game.getLevelRows();
        Position p = game.getPlayerPos();
        Position p2 = game.getDragonPos();
        for (int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                Image img = null;
                LevelItem li = game.getItem(y, x);
                switch (li){
                    case EXIT: img = exit; break;
                    case WALL: img = wall; break;
                    case EMPTY: img = empty; break;
                    case DRAGON_OUTSIDE: img = dragoninside; break;
                }
                if(!(x > p.x - 4 && x < p.x + 4 && y > p.y - 4 && y < p.y + 4)){
                    img = light;
                }
                else{
                    if (p2.x == x && p2.y == y) img = dragon;
                }
                if (p.x == x && p.y == y) img = player;
                if (img == null) continue;
                gr.drawImage(img, x * scaled_size, y * scaled_size, scaled_size, scaled_size, null);
            }
        }
    }
    
}
