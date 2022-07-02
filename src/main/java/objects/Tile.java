package objects;

import java.awt.image.BufferedImage;

public class Tile {

    private int id;
    private String name;
    private BufferedImage[] sprite;

    public Tile(int id, String name, BufferedImage sprite) {
        this.id = id;
        this.name = name;
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;
    }

    public Tile(int id, String name, BufferedImage[] sprite) {
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

    public BufferedImage getOneSprite() {
        return sprite[0];
    }

    public BufferedImage getSpriteByIndex(int animationIndex) {
        return sprite[animationIndex];
    }

    public boolean isAnimation() {
        return sprite.length > 1;
    }

}
