package com.ivmov.mytd.helper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgFix {

    //Rotate image
    public static BufferedImage getRotateImg(BufferedImage img, int rotationAngle) {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, img.getType());

        Graphics2D g2d = newImg.createGraphics(); // used like graphics (extends it) but more for work with 2d com.ivmov.td.objects

        g2d.rotate(Math.toRadians(rotationAngle), w / 2, h / 2); //what if 31?
        g2d.drawImage(img, 0, 0, null); //draw newImg on top of old img
        g2d.dispose();

        return newImg;
    }

    //rotate only index img
    public static BufferedImage buildRotImg(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (int i = 0; i < imgs.length; i++) {
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
            }
            g2d.drawImage(imgs[i], 0, 0, null);
            //don't understand why we need that - all works without this if
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(-rotAngle), w / 2, h / 2);
            }
        }
        g2d.dispose();
        return newImg;
    }

    //rotate only index img + animation
    public static BufferedImage[] buildAnimatedRotImgWater(BufferedImage[] imgs, BufferedImage secondImg, int rotAngle) {
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage[] arr = new BufferedImage[imgs.length];

        for (int i = 0; i < imgs.length; i++) {
            BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
            Graphics2D g2d = newImg.createGraphics();

            g2d.drawImage(imgs[i], 0, 0, null);
            g2d.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
            g2d.drawImage(secondImg, 0, 0, null);
            g2d.dispose();

            arr[i] = newImg;
        }

        return arr;
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
