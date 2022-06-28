package managers;

import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {

    public Tile GRASS, WATTER, ROAD;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(id++, "Grass", getSprite(9, 0)));
        tiles.add(WATTER = new Tile(id++, "Watter", getSprite(0, 0)));
        tiles.add(ROAD = new Tile(id++, "Road", getSprite(8, 0)));

    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }
}
