/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_nigths.rainbow_engine.img_converter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Stephanie
 */
public class utils {
    public static BufferedImage loadImage(File file) {
        try {
            BufferedImage loaded = ImageIO.read(file);
            BufferedImage formatted = new BufferedImage(loaded.getWidth(), loaded.getHeight(), BufferedImage.TYPE_INT_RGB);
            formatted.getGraphics().drawImage(loaded, 0, 0, null);
            return formatted;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
//    public static File loadFile(String path) {
//        File loaded = null;
//        try {
//            loaded = new File(path);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return loaded;
//    }
}
