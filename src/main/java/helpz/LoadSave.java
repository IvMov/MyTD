package helpz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

    //txt file

    public static void createFile(){
        File textFile = new File("src/main/resources/testTextFile.txt");
        try {
            textFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
