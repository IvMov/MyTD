package ui;

import core.GameStates;
import objects.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BottomBar {


    private int x, y, width, height;
    private MyButton bMenu;
    private Playing playing;

    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        initButtons();
    }

    public void initButtons() {
        bMenu = new MyButton(2, 642, 120, 120 / 3, "Menu");
        int xPosition = 130;
        int yPosition = 642;
        int width = 40;
        int height = 40;
        int xOffset = (int) (width * 1.1f);
        int i = 0;

        for (Tile tile : playing.getTileManager().tiles) {
            tileButtons.add(new MyButton(i++ ,xPosition, yPosition, width, height, tile.getName()));
            xPosition = xPosition + xOffset;
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);
        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        bMenu.draw(g);
        drawTileButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(bMenu.getBounds().contains(x, y));
    }

    public void mousePressed(int x, int y) {
        bMenu.setMousePressed(bMenu.getBounds().contains(x, y));
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton b : tileButtons) {
            g.drawImage(getButtImg(b.getId()),
                    (int) b.getBounds().getX(),
                    (int) b.getBounds().getY(),
                    (int) b.getBounds().getWidth(),
                    (int) b.getBounds().getHeight(),
                    null);
        }
    }

    public BufferedImage getButtImg(int id) {
        return playing.getTileManager().getSprite(id);

    }
}

