package com.ivmov.mytd.managers;

import com.ivmov.mytd.helper.ImgFix;
import com.ivmov.mytd.helper.LoadSave;
import com.ivmov.mytd.objects.Tile;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Getter
@Setter
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
    public ArrayList<Tile> roadsS = new ArrayList<>();
    public ArrayList<Tile> roadsC = new ArrayList<>();
    public ArrayList<Tile> waterC = new ArrayList<>();
    public ArrayList<Tile> waterLines = new ArrayList<>();
    public ArrayList<Tile> waterD = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    public boolean isSpriteAnimation(int spriteId) {
        return tiles.get(spriteId).isAnimation();
    }


    private void createTiles() {
        //TODO: need to a lot refactor this method and ImgFix class methods!
        int id = 0;

        tiles.add(GRASS = new Tile(id++, "Grass",
                getSpriteById(9, 0)));
        tiles.add(WATER = new Tile(id++, "Water",
                getSpritesAnimationWater(0, 0)));

        roadsS.add(ROAD_H = new Tile(id++, "Road_H",
                getSpriteById(8, 0)));
        roadsS.add(ROAD_V = new Tile(id++, "Road_V",
                ImgFix.getRotateImg(getSpriteById(8, 0), 90)));

        roadsC.add(ROAD_TL_C = new Tile(id++, "Road_H",
                getSpriteById(7, 0)));
        roadsC.add(ROAD_TR_C = new Tile(id++, "Road_V",
                ImgFix.getRotateImg(getSpriteById(7, 0), 90)));
        roadsC.add(ROAD_BR_C = new Tile(id++, "Road_V",
                ImgFix.getRotateImg(getSpriteById(7, 0), 180)));
        roadsC.add(ROAD_BL_C = new Tile(id++, "Road_V",
                ImgFix.getRotateImg(getSpriteById(7, 0), 270)));


        waterLines.add(WATER_T = new Tile(id++, "Water_T_line",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(6, 0), 0)));
        waterLines.add(WATER_R = new Tile(id++, "Water_R_line",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(6, 0), 90)));
        waterLines.add(WATER_B = new Tile(id++, "Water_B_line",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(6, 0), 180)));
        waterLines.add(WATER_L = new Tile(id++, "Water_L_line",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(6, 0), 270)));

        waterC.add(WATER_BL_C = new Tile(id++, "Water_BL_corner",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(5, 0), 0)));
        waterC.add(WATER_TL_C = new Tile(id++, "Water_TL_corner",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(5, 0), 90)));
        waterC.add(WATER_TR_C = new Tile(id++, "Water_TR_corner",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(5, 0), 180)));
        waterC.add(WATER_BR_C = new Tile(id++, "Water_BR_corner",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(5, 0), 270)));


        waterD.add(WATER_TL_D = new Tile(id++, "Water_TL_dot",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(4, 0), 0)));
        waterD.add(WATER_TR_D = new Tile(id++, "Water_TR_dot",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(4, 0), 90)));
        waterD.add(WATER_BR_D = new Tile(id++, "Water_BR_dot",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(4, 0), 180)));
        waterD.add(WATER_BL_D = new Tile(id++, "Water_BL_dot",
                ImgFix.buildAnimatedRotImgWater(getSpritesAnimationWater(0, 0), getSpriteById(4, 0), 270)));

        tiles.addAll(roadsS);
        tiles.addAll(roadsC);
        tiles.addAll(waterLines);
        tiles.addAll(waterC);
        tiles.addAll(waterD);
    }

    //where x y - coordinates in sprite_atlas
    private BufferedImage[] getImgsBy(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[]{getSpriteById(firstX, firstY),
                getSpriteById(secondX, secondY)};
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    private BufferedImage getSpriteById(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

    private BufferedImage[] getSpritesAnimationWater(int xCord, int yCord) {
        BufferedImage[] arr = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = getSpriteById(xCord + i, yCord);
        }
        return arr;
    }

    public BufferedImage getSpriteById(int id) {
        return tiles.get(id).getOneSprite();
    }

    public BufferedImage getSpriteByIdAndIndex(int id, int animationIndex) {
        return tiles.get(id).getSpriteByIndex(animationIndex);
    }

    public Tile getTileById(int id) {
        return tiles.get(id);
    }
}
