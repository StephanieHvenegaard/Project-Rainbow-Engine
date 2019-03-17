/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_nigths.rainbow_engine.img_converter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Stephanie
 */
public class Converter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        BufferedImage file;
        Path original;
        do {
            System.out.println("Select File path to convert.");
            String path = keyboard.nextLine();
            original = Paths.get(path);
            file = loadImage(path);
        } while (file == null);
        System.out.println("Found file, starting parsing");
        ImageConver ic = new ImageConver();
        try {
            ic.converRI(file, original);
            ic.converRS(file, original);
        } catch (IOException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    public static BufferedImage loadImage(String path) {
        try {
            BufferedImage loaded = ImageIO.read(loadFile(path));
            BufferedImage formatted = new BufferedImage(loaded.getWidth(), loaded.getHeight(), BufferedImage.TYPE_INT_RGB);
            formatted.getGraphics().drawImage(loaded, 0, 0, null);
            return formatted;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static File loadFile(String path) {
        File loaded = null;
        try {
            loaded = new File(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loaded;
    }
}
