package objects;

import java.awt.image.BufferedImage;

public class Tile {

    private int id;
    private String name;
    private BufferedImage sprite;

    public Tile(int id, String name, BufferedImage sprite) {
        this.id = id;
        this.name = name;
        this.sprite = sprite;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
