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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import the_nights.rainbow_engine.core.graphics.palettes.RainbowImage;

/**
 *
 * @author Stephanie
 */
public class ImageConver {

    String sp = "+--------------------------------------------+";
    File file;

    public void Start() {
        System.out.println(sp);
        System.out.println("| welcome to the image converter.            |");
        System.out.println("| this software can be used to               |");
        System.out.println("| convert regular png images to              |");
        System.out.println("| the file format that the rainbow           |");
        System.out.println("| engine supports.                           |");
        System.out.println(sp);
        System.out.println("| Please choose a file to convert.           |");
        file = picFile();
        if (file != null) {

        }
        System.out.println("Good bi");

    }

    private File picFile() {
        File f = null;
        //Create a file chooser
        JFileChooser fc = new JFileChooser();

        //In response to a button click:
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            System.out.println("| Opening: " + f.getName());
        }

        return f;
    }

    public void stuff() {
//        Scanner keyboard = new Scanner(System.in);
//        BufferedImage file;
//        Path original;
//        do {
//            System.out.println("Select File path to convert.");
//            String path = keyboard.nextLine();
//            original = Paths.get(path);
//            file = loadImage(path);
//        } while (file == null);
//        System.out.println("Found file, starting parsing");
//        ImageConver ic = new ImageConver();
//        try {
//            ic.converRI(file, original);
//            ic.converRS(file, original);
//        } catch (IOException ex) {
//            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//    }    
    }

    public void converRS(File file) throws IOException {
        
        BufferedImage image = utils.loadImage(file);
        if (image != null) {
            System.out.println(sp);
            System.out.println("| found and image starting converting       |");
            String filename = file.getPath() + getFileName() + ".rsprite";
            System.out.println("original : " + file.toString());
            System.out.println("new file : " + filename);
            ArrayList<Integer> palette = new ArrayList<>();
            
            int[] view = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
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
        } else {
            System.out.println("file is not a picture");
        }
    }

    public void converRI(BufferedImage file, Path original) throws IOException {
//        int[] view = ((DataBufferInt) file.getRaster().getDataBuffer()).getData();
//        ArrayList<Integer> palette = new ArrayList<>();
//        System.out.println("Starting comparativ method");
//        int colorID;
//        int color;
//        RainbowImage img = new RainbowImage(file.getHeight(), file.getWidth());
//        for (int y = 0; y < file.getHeight(); y++) {
//            for (int x = 0; x < file.getWidth(); x++) {
//                color = view[x + (y * file.getWidth())];
//                if (!palette.contains(color)) {
//                    System.out.println("adding color to palette : " + color);
//                    palette.add(color);
//                    //TODO base 4 color palette size.
//                    System.out.println("Palette size : " + (palette.size()));
//                }
//                colorID = palette.indexOf(color);
//                img.setID(x, y, (byte) colorID);
//            }
//        }
//        img.saveImage(img, "\\" + getFileName(original) + ".ri");
    }

    private String getFileName() {
        // get file name with no extension.
        String[] namep = file.getPath().split("\\.");
        return namep[0];
    }

    public void writeIDFile(String path, int[] ids, int palettesize) {
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
