package managers;

import helpz.ImgFix;
import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    //B-bottom, T- top, L - left, R - right, C-corner
    public Tile GRASS, WATTER, ROAD, BL_WATTER_C, TL_WATTER_C;
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
        tiles.add(BL_WATTER_C
                = new Tile(
                id++,
                "BL water corner",
                ImgFix.buildLayeredImg(getImgs(0, 0, 5, 0)))
        );
        tiles.add(TL_WATTER_C
                = new Tile(
                id++,
                "TL water corner",
                ImgFix.getBuildRotImg(getImgs(0, 0, 5, 0), 90, 1))
        );

    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[]{getSprite(firstX, firstY),
                getSprite(secondX, secondY)};
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
