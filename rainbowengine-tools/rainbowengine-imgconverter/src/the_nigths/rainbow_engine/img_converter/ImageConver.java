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
import java.util.ArrayList;
import the_nights.rainbow_engine.core.graphics.pallates.RainbowImage;

/**
 *
 * @author Stephanie
 */
public class ImageConver {
    public void converRS(BufferedImage file, Path original) throws IOException
    {
     String filename = "\\"+getFileName(original)+".rsprite";
        System.out.println("original : " + original.toString());
        System.out.println("new file : " + filename);
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
    public void converRI(BufferedImage file, Path original) throws IOException
    {
         int[] view = ((DataBufferInt) file.getRaster().getDataBuffer()).getData();
         ArrayList<Integer> palette = new ArrayList<>();
            System.out.println("Starting comparativ method");
        int colorID;
        int color;
        RainbowImage img = new RainbowImage(file.getHeight(),file.getWidth());
        for (int y = 0; y < file.getHeight(); y++) {
            for (int x = 0; x < file.getWidth(); x++) {            
            color = view[x + (y * file.getWidth())];
            if (!palette.contains(color)) {
                System.out.println("adding color to palette : " + color);
                palette.add(color);
                //TODO base 4 color palette size.
                System.out.println("Palette size : " + (palette.size()));
            }
            colorID = palette.indexOf(color);
            img.setID(x, y,(byte)colorID);
        }
        }
        img.saveImage(img, "\\"+getFileName(original)+".ri");
    }     
    private String getFileName(Path original) {        
        String name = (original.getFileName().toString());                          // get file name with no extension.
        String[] namep = name.split("\\.");
        return namep[0];
    }
    public  void writeIDFile(String path, int[] ids, int palettesize) {
        try {
            File file = new File(path);
            // creates the file
            file.createNewFile();
            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file);
            // Writes the content to the file
            // Writes size
            writer.write("palettesize:" + palettesize + "\r");
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
