package helpz;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgFix {

    //Rotate image
    public static BufferedImage getRotateImg(BufferedImage img, int rotationAngle) {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, img.getType());

        Graphics2D g2d = newImg.createGraphics(); // used like graphics (extends it) but more for work with 2d objects

        g2d.rotate(Math.toRadians(rotationAngle), w / 2, h / 2); //what if 31?
        g2d.drawImage(img, 0, 0, null); //draw newImg on top of old img
        g2d.dispose();

        return newImg;
    }

    //Img layers build
    public static BufferedImage buildLayeredImg(BufferedImage[] imgs) {

        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (BufferedImage img : imgs) {
            g2d.drawImage(img, 0, 0, null);
        }

        g2d.dispose();
        return newImg;

    }

}
