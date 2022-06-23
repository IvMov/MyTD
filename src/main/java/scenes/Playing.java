package scenes;

import core.Game;
import helpz.LevelBuilder;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods {

    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId; // to prevent unnecessary drawing already drawn tile
    private boolean drawSelect;
    private int[][] lvl;
    private TileManager tileManager;
    private Tile selectedTile;
    private BottomBar bottomBar;


    public Playing(Game game) {
        super(game);

        lvl = LevelBuilder.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 100, this);

    }


    @Override
    public void render(Graphics g) {
        drawContent(g);
        drawBottom(g);
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    @Override
    public void initButtons() {

    }

    @Override
    public void drawButtons(Graphics g) {

    }

    @Override
    public void drawContent(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }
    }


    private void drawBottom(Graphics g) {
        bottomBar.draw(g);
    }

    //mouse events
    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
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

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseMoved(x, y);
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
            bottomBar.mousePressed(x, y);
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);

    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640) {
            //temporary nothing
        } else {
            changeTile(x, y);
        }
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
        drawSelect = true;
    }
}
