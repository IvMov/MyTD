package com.ivmov.mytd.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {
        BufferedImage img;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("sprite_atlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return img;
    }

    public static void CreateLevel(String name, int[] idArr) {
        File newLvl = new File("src/main/resources/" + name + ".txt");
        if (newLvl.exists()) {
            System.out.println("File with name: " + name + " already exists!");
        } else {
            try {
                newLvl.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            writeToFile(newLvl, idArr);
        }

    }

    public static int[][] GetLevelData(String name) {

        File lvlFile = new File("src/main/resources/" + name + ".txt");

        if (lvlFile.exists()) {
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            return ArrayUtil.parseListToTwoDimArray(list, 20, 20);
        } else {
            System.out.println("File with name: " + name + " NOT exist");
            return null;
        }
    }

    public static void SaveLevel(String name, int[][] idArr) {
        File lvlFile = new File("src/main/resources/" + name + ".txt");

        if(lvlFile.exists()){
            writeToFile(lvlFile, ArrayUtil.parseTwoDimArrayToOneDimArray(idArr));
        }else{
            System.out.println("File with name: " + name + " NOT exist");
        }
    }

    private static ArrayList<Integer> ReadFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                list.add(Integer.valueOf(sc.nextLine()));
            }
            sc.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    private static void writeToFile(File f, int[] idArr) {

        try {
            PrintWriter pw = new PrintWriter(f);
            for (Integer i : idArr) {
                pw.println(i);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
