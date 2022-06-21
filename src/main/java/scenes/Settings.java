package scenes;

import core.Game;
import core.GameStates;
import ui.MyButton;

import java.awt.*;

public class Settings extends GameScene implements SceneMethods {

    private MyButton bMenu;


    public Settings(Game game) {
        super(game);
        initButtons();
    }


    @Override
    public void initButtons() {
        bMenu = new MyButton(5, 5, 120, 120 / 3, "Menu");
    }

    @Override
    public void drawContent(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, 640, 640);
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
