package managers;

import helpz.ImgFix;
import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    //B-bottom, T- top, L - left, R - right, H-horizontal, V-vertical, C-corner, D - dot in corner
    public Tile GRASS;
    public Tile ROAD_H, ROAD_V, ROAD_TL_C, ROAD_TR_C, ROAD_BR_C, ROAD_BL_C;
    public Tile WATER,
            WATER_T, WATER_R, WATER_B, WATER_L,
            WATER_BL_C, WATER_TL_C, WATER_TR_C, WATER_BR_C,
            WATER_TL_D, WATER_TR_D, WATER_BR_D, WATER_BL_D;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        //TODO: need to a lot refactor this method and ImgFix class methods!
        int id = 0;
        tiles.add(GRASS = new Tile(id++, "Grass", getSprite(9, 0)));
        tiles.add(WATER = new Tile(id++, "Water", getSprite(0, 0)));

        tiles.add(ROAD_H = new Tile(id++, "Road_H", getSprite(8, 0)));
        tiles.add(ROAD_V = new Tile(id++, "Road_V", ImgFix.getRotateImg(getSprite(8, 0), 90)));

        tiles.add(ROAD_TL_C = new Tile(id++, "Road_H", getSprite(7, 0)));
        tiles.add(ROAD_TR_C = new Tile(id++, "Road_V", ImgFix.getRotateImg(getSprite(7, 0), 90)));
        tiles.add(ROAD_BR_C = new Tile(id++, "Road_V", ImgFix.getRotateImg(getSprite(7, 0), 180)));
        tiles.add(ROAD_BL_C = new Tile(id++, "Road_V", ImgFix.getRotateImg(getSprite(7, 0), 270)));


        tiles.add(WATER_T = new Tile(id++, "Water_T_line", ImgFix.buildLayeredImg(getImgs(0, 0, 6, 0))));
        tiles.add(WATER_R = new Tile(id++, "Water_R_line", ImgFix.buildRotImg(getImgs(0, 0, 6, 0), 90, 1)));
        tiles.add(WATER_B = new Tile(id++, "Water_B_line", ImgFix.buildRotImg(getImgs(0, 0, 6, 0), 180, 1)));
        tiles.add(WATER_L = new Tile(id++, "Water_L_line", ImgFix.buildRotImg(getImgs(0, 0, 6, 0), 270, 1)));

        tiles.add(WATER_BL_C = new Tile(id++, "Water_BL_corner", ImgFix.buildLayeredImg(getImgs(0, 0, 5, 0))));
        tiles.add(WATER_TL_C = new Tile(id++, "Water_TL_corner", ImgFix.buildRotImg(getImgs(0, 0, 5, 0), 90, 1)));
        tiles.add(WATER_TR_C = new Tile(id++, "Water_TR_corner", ImgFix.buildRotImg(getImgs(0, 0, 5, 0), 180, 1)));
        tiles.add(WATER_BR_C = new Tile(id++, "Water_BR_corner", ImgFix.buildRotImg(getImgs(0, 0, 5, 0), 270, 1)));

        tiles.add(WATER_TL_D = new Tile(id++, "Water_TL_dot", ImgFix.buildLayeredImg(getImgs(0, 0, 4, 0))));
        tiles.add(WATER_TR_D = new Tile(id++, "Water_TR_dot", ImgFix.buildRotImg(getImgs(0, 0, 4, 0), 90, 1)));
        tiles.add(WATER_BR_D = new Tile(id++, "Water_BR_dot", ImgFix.buildRotImg(getImgs(0, 0, 4, 0), 180, 1)));
        tiles.add(WATER_BL_D = new Tile(id++, "Water_BL_dot", ImgFix.buildRotImg(getImgs(0, 0, 4, 0), 270, 1)));

    }

    //where x y - coordinates in sprite_atlas
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
