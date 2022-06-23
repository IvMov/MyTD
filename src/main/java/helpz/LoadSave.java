package helpz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return img;
    }

    public static void readFromFile() {
        File textFile = new File("src/main/resources/test2TextFile.txt");
        try {
            Scanner sc = new Scanner(textFile);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createLevel(String name, int[] idArr) {
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
