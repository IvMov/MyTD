package com.ivmov.mytd.objects;

import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class Tile {

    private int id;
    private String name;
    private TileType tileType;
    private BufferedImage[] sprites;

    public Tile(int id, String name, TileType tileType, BufferedImage sprites) {
        this.id = id;
        this.name = name;
        this.tileType = tileType;
        this.sprites = new BufferedImage[1];
        this.sprites[0] = sprites;
    }

    public Tile(int id, String name,TileType tileType, BufferedImage[] sprites) {
        this.id = id;
        this.name = name;
        this.tileType = tileType;
        this.sprites = sprites;
    }


    public BufferedImage getOneSprite() {
        return sprites[0];
    }

    public BufferedImage getSpriteByIndex(int animationIndex) {
        return sprites[animationIndex];
    }

    public boolean isAnimation() {
        return sprites.length > 1;
    }

}
