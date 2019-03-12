/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_nigths.rainbow_engine.img_converter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
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
        String filename = original.getParent().toString();
        String name = (original.getFileName().toString());// get file name with no extension.
        String[] namep = name.split("\\.");        
        filename +="\\"+namep[0]+".rsprite";
        System.out.println("original : "+ original.toString());
        System.out.println("new file : "+ filename);
        ArrayList<Integer> palette = new ArrayList<>();
        int[] view = ((DataBufferInt) file.getRaster().getDataBuffer()).getData();
        int[] ids = new int[view.length];
        for (int i = 0; i < view.length; i++) {
            int colorID;
            int color = view[i];
            if (!palette.contains(color)) {
                System.out.println("adding color to palette : " + color);
                palette.add(color);
                //TODO base 4 color palette size.
                System.out.println("Palette size : " + (palette.size()));
            }
            colorID = palette.indexOf(color);
            ids[i] = colorID;
        }
        System.out.println("Done");
        System.out.println("Saving file");      
        writeIDFile(filename, ids, palette.size());
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

    public static void writeIDFile(String path, int[] ids,int palettesize) {
        try {
            File file = new File(path);
            // creates the file
            file.createNewFile();
            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file);            
            // Writes the content to the file
            // Writes size
            writer.write("palettesize:"+palettesize+"\r");
            writer.write("data:");
            for (int i = 0; i < ids.length; i++) {
                writer.write(ids[i]);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
