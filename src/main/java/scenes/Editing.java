package scenes;

import core.Game;
import helpz.LoadSave;
import objects.Tile;
import ui.EditingBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Editing extends GameScene implements SceneMethods {

    private boolean drawSelect;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId; // to prevent unnecessary drawing already drawn tile
    private int[][] lvl;
    private Tile selectedTile;
    private final EditingBar editingBar;


    public Editing(Game game) {
        super(game);
        loadDefaultLvl();
        editingBar = new EditingBar(0, 640, 640, 100, this);
    }


    public void saveLevel() {
        LoadSave.SaveLevel("new_lvl", lvl);
    }

    @Override
    public void initButtons() {

    }

    @Override
    public void drawContent(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    @Override
    public void drawButtons(Graphics g) {

    }

    @Override
    public void render(Graphics g) {
        drawContent(g);
        drawEditingBar(g);
        drawSelectedTile(g);
    }


    //mouse events
    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            editingBar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            editingBar.mouseMoved(x, y);
            drawSelect = false;
        } else {
            drawSelect = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            editingBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        editingBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640) {
            //temporary nothing
        } else {
            changeTile(x, y);
        }
    }

    private void drawEditingBar(Graphics g) {
        editingBar.draw(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    private void changeTile(int x, int y) {
        //set coordinates in array
        if (selectedTile != null) {

            int tileX = x / 32;
            int tileY = y / 32;

            if (lastTileX == tileX &&
                    lastTileY == tileY &&
                    lastTileId == selectedTile.getId()) {
                return;
            }
            lastTileX = tileX;
            lastTileY = tileY;
            lastTileId = selectedTile.getId();
            lvl[tileY][tileX] = selectedTile.getId();
        }
    }

    private BufferedImage getSprite(int id) {
        return getGame().getTileManager().getSprite(id);
    }

    private void loadDefaultLvl() {
        lvl = LoadSave.GetLevelData("new_lvl");
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
        drawSelect = true;
    }
}
