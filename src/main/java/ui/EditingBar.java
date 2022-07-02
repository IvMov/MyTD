package ui;

import core.GameStates;
import helpz.LoadSave;
import managers.TileManager;
import objects.Tile;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditingBar extends Bar {

    private int currentIndex;

    private int[][] lvl;

    private MyButton bMenu, bSave;
    private MyButton bCurrent, bGrass, bWater;

    private Editing editing;
    private Tile selectedTile;

    private Map<MyButton, ArrayList<Tile>> buttonsHashMap = new HashMap<>();


    public EditingBar(int xStart, int yStart, int width, int height, Editing editing) {
        super(xStart, yStart, width, height);
        this.editing = editing;

        initButtons();
    }

    public void initButtons() {
        initNavButtons();
        initTileButtons();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(xStart, yStart, width, height);
        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        //drawing buttons with text
        bMenu.draw(g);
        bSave.draw(g);

        //drawing buttons with images
        drawSingleButton(g, bWater);
        drawSingleButton(g, bGrass);

        drawHashMapButtons(g);

        drawSelectedTile(g);
    }

    public void rotateSprite(){
        currentIndex++;
        if(currentIndex >= buttonsHashMap.get(bCurrent).size()){
            currentIndex = 0;
        }
        selectedTile = buttonsHashMap.get(bCurrent).get(currentIndex);
        editing.setSelectedTile(selectedTile);
    }

    public void mouseClicked(int x, int y) {

        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);

        } else if (bSave.getBounds().contains(x, y)) {
            saveLevel();

        } else if (bWater.getBounds().contains(x, y)) {
            selectedTile = takeTileManager().getTile(bWater.getId());
            editing.setSelectedTile(selectedTile);

        } else if (bGrass.getBounds().contains(x, y)) {
            selectedTile = takeTileManager().getTile(bGrass.getId());
            editing.setSelectedTile(selectedTile);

        } else {
            for (MyButton b : buttonsHashMap.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    selectedTile = buttonsHashMap.get(b).get(0);
                    editing.setSelectedTile(selectedTile);
                    bCurrent = b;
                    currentIndex = 0;
                    return;
                }
            }
        }

    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(bMenu.getBounds().contains(x, y));
        bSave.setMouseOver(bSave.getBounds().contains(x, y));

        bGrass.setMouseOver(bGrass.getBounds().contains(x, y));
        bWater.setMouseOver(bWater.getBounds().contains(x, y));

        for (MyButton b : buttonsHashMap.keySet()) {
            b.setMouseOver(b.getBounds().contains(x, y));
        }
    }

    public void mousePressed(int x, int y) {
        bMenu.setMousePressed(bMenu.getBounds().contains(x, y));
        bSave.setMousePressed(bSave.getBounds().contains(x, y));

        bGrass.setMousePressed(bGrass.getBounds().contains(x, y));
        bWater.setMousePressed(bWater.getBounds().contains(x, y));

        for (MyButton b : buttonsHashMap.keySet()) {
            b.setMousePressed(b.getBounds().contains(x, y));
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();

        bGrass.resetBooleans();
        bWater.resetBooleans();

        for (MyButton b : buttonsHashMap.keySet()) {
            b.resetBooleans();
        }

    }


    private void drawHashMapButtons(Graphics g) {

        for (Map.Entry<MyButton, ArrayList<Tile>> entry : buttonsHashMap.entrySet()) {

            MyButton b = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getOneSprite();

            int x = b.getBounds().x;
            int y = b.getBounds().y;
            int w = b.getBounds().width;
            int h = b.getBounds().height;

            g.drawImage(img, x, y, w, h, null);

            drawButtonEvents(g, b, x, y, w, h);

        }
    }


    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getOneSprite(), 580, 674, 40, 40, null);
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

        bGrass = new MyButton(i++, xPosition, yPosition, width, height, "Grass");
        bWater = new MyButton(i++, xPosition + xOffset, yPosition, width, height, "Water");

        initHashMapForButton(editing.getGame().getTileManager().getRoadsS(), xPosition, yPosition, xOffset, width, height, i++);
        initHashMapForButton(editing.getGame().getTileManager().getRoadsC(), xPosition, yPosition, xOffset, width, height, i++);
        initHashMapForButton(editing.getGame().getTileManager().getWaterLines(), xPosition, yPosition, xOffset, width, height, i++);
        initHashMapForButton(editing.getGame().getTileManager().getWaterC(), xPosition, yPosition, xOffset, width, height, i++);
        initHashMapForButton(editing.getGame().getTileManager().getWaterD(), xPosition, yPosition, xOffset, width, height, i++);

    }

    private void initHashMapForButton(ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
        MyButton b = new MyButton(id, x + xOff * id, y, w, h, "");
        buttonsHashMap.put(b, list);

    }

    private void drawSingleButton(Graphics g, MyButton b) {

        int x = b.getBounds().x;
        int y = b.getBounds().y;
        int w = b.getBounds().width;
        int h = b.getBounds().height;

        g.drawImage(getButtImg(b.getId()), x, y, w, h, null);

        drawButtonEvents(g, b, x, y, w, h);
    }

    private void drawButtonEvents(Graphics g, MyButton b, int x, int y, int w, int h) {

        //set borders
        g.setColor(Color.black);
        g.drawRect(x, y, w, h);

        //mouseOver
        if (b.isMouseOver()) {
            g.setColor(Color.white);
            g.drawRect(x, y, w, h);

        } else {
            g.setColor(Color.black);
            g.drawRect(x, y, w, h);
        }

        //MousePressed
        if (b.isMousePressed()) {
            g.drawRect(x + 1, y + 1, w - 2, h - 2);
            g.drawRect(x + 2, y + 2, w - 4, h - 4);
        }
    }

    public BufferedImage getButtImg(int id) {
        return takeTileManager().getSprite(id);

    }

    private TileManager takeTileManager() {
        return editing.getGame().getTileManager();
    }

}
