package ui;

import core.GameStates;
import helpz.LoadSave;
import managers.TileManager;
import objects.Tile;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EditingBar extends Bar {

    private int[][] lvl;
    private MyButton bMenu, bSave;
    private Editing editing;

    private Tile selectedTile;

    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public EditingBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;

        initButtons();
    }

    public void initButtons() {
        initNavButtons();
        initTileButtons();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);
        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);

        drawTileButtons(g);
        drawSelectedTile(g);
    }


    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            saveLevel();
        } else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {
                    selectedTile = takeTileManager().getTile(b.getId());
                    editing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }

    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(bMenu.getBounds().contains(x, y));
        bSave.setMouseOver(bSave.getBounds().contains(x, y));
        for (MyButton b : tileButtons) {
            b.setMouseOver(b.getBounds().contains(x, y));
        }
    }

    public void mousePressed(int x, int y) {
        bMenu.setMousePressed(bMenu.getBounds().contains(x, y));
        bSave.setMousePressed(bSave.getBounds().contains(x, y));
        for (MyButton b : tileButtons) {
            b.setMousePressed(b.getBounds().contains(x, y));
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        for (MyButton b : tileButtons) {
            b.resetBooleans();
        }

    }


    private void drawTileButtons(Graphics g) {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        for (MyButton b : tileButtons) {
            x = (int) b.getBounds().getX();
            y = (int) b.getBounds().getY();
            width = (int) b.getBounds().getWidth();
            height = (int) b.getBounds().getHeight();

            //set borders
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);

            //get sprite for button
            g.drawImage(getButtImg(b.getId()), x, y, width, height, null);

            //mouseOver
            if (b.isMouseOver()) {
                g.setColor(Color.white);
                g.drawRect(x, y, width, height);
            } else {
                g.setColor(Color.black);
                g.drawRect(x, y, width, height);

            }
            ;
            //MousePressed
            if (b.isMousePressed()) {
                g.drawRect(x + 1, y + 1, width - 2, height - 2);
                g.drawRect(x + 2, y + 2, width - 4, height - 4);
            }
        }
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 580, 674, 40, 40, null);
            g.setColor(Color.black);
            g.drawRect(580, 674, 40, 40);
        }
    }

    private void saveLevel() {
        editing.saveLevel();
        lvl = LoadSave.GetLevelData("new_lvl");
        editing.getGame().getPlaying().setLvl(lvl);
    }

    private void initNavButtons() {
        bMenu = new MyButton(2, 642, 120, 120 / 3, "Menu");
        bSave = new MyButton(560, 642, 78, 30, "Save");
    }

    private void initTileButtons() {
        int xPosition = 130;
        int yPosition = 642;
        int width = 40;
        int height = 40;
        int xOffset = (int) (width * 1.1f);
        int i = 0;

        for (Tile tile : takeTileManager().tiles) {
            tileButtons.add(new MyButton(i++, xPosition, yPosition, width, height, tile.getName()));
            xPosition = xPosition + xOffset;
        }
    }

    public BufferedImage getButtImg(int id) {
        return takeTileManager().getSprite(id);

    }

    private TileManager takeTileManager() {
        return editing.getGame().getTileManager();
    }

}
