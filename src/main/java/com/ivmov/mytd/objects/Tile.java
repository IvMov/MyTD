package com.ivmov.mytd.objects;

import java.awt.image.BufferedImage;

public class Tile {

    private int id;
    private String name;
    private BufferedImage[] sprites;

    public Tile(int id, String name, BufferedImage sprites) {
        this.id = id;
        this.name = name;
        this.sprites = new BufferedImage[1];
        this.sprites[0] = sprites;
    }

    public Tile(int id, String name, BufferedImage[] sprites) {
        this.id = id;
        this.name = name;
        this.sprites = sprites;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
