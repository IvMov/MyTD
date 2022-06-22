package scenes;

import core.Game;
import core.GameStates;
import helpz.LevelBuilder;
import managers.TileManager;
import ui.BottomBar;
import ui.MyButton;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private MyButton bMenu;
    private BottomBar bottomBar;


    public Playing(Game game) {
        super(game);

        lvl = LevelBuilder.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 100);
        initButtons();
    }

    @Override
    public void initButtons() {
        bMenu = new MyButton(5, 5, 120, 120 / 3, "Menu");
    }

    @Override
    public void drawContent(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }

        drawBottom(g);
    }

    @Override
    public void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    @Override
    public void render(Graphics g) {
        drawContent(g);
        drawButtons(g);

    }

    private void drawBottom(Graphics g) {
        bottomBar.draw(g);
    }

    //mouse events
    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(bMenu.getBounds().contains(x, y));
    }

    @Override
    public void mousePressed(int x, int y) {
        bMenu.setMousePressed(bMenu.getBounds().contains(x, y));
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }


}
