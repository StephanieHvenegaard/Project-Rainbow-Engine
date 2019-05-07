/*
 * MIT License
 * 
 * Copyright (c) 2019 Stephanie Hvenegaard
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.the_nights.rainbow_engine.core;

import com.the_nights.rainbow_engine.core.graphics.Palette;
import com.the_nights.rainbow_engine.core.graphics.RImage;
import com.the_nights.rainbow_engine.core.graphics.RColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Stephanie
 */
public class FileHandler {

    public static Palette LoadPallete(String Filename) throws FileNotFoundException {
        Palette pall = new Palette();
        int linecounter = 0;
        int index = 0;
        File file = FileHandler.loadFile(Filename);
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            String line = input.nextLine();
            linecounter++;
            if (line.startsWith("#")) // # is comment                
            {
                continue;
            } else if (line.isEmpty()) {
                continue;
            } else {
                String[] sp = line.split("#"); // end of line comments.
                sp = sp[0].split(":");
                if (sp.length != 2) {
                    throw new IllegalArgumentException("missing a ':' at line: " + linecounter);
                }
                if (sp[0].isEmpty()) {
                    throw new IllegalArgumentException("Missing element name at line: " + linecounter);
                }
                if (sp[0].equalsIgnoreCase("size")) {
                    int s = Integer.parseInt(sp[1]);                    
                    pall.setColors(new int[s]);
                }
                if (sp[0].equalsIgnoreCase("color")) {
                    if (pall.getSize() != 0) {
                        String hex = sp[1].trim();
                        pall.setColor(index,Integer.parseInt(hex, 16));
                        index++;
                    }
                }
                if (index == pall.getSize()) {
                    break;
                }
            }
        }
        return pall;
    }

    public static BufferedImage loadImage(String path) {
        try {
            BufferedImage loaded = ImageIO.read(loadFile(path));
            BufferedImage formatted = new BufferedImage(loaded.getWidth(), loaded.getHeight(), BufferedImage.TYPE_INT_RGB);
            formatted.getGraphics().drawImage(loaded, 0, 0, null);
            return formatted;
        } catch (IOException e) {
            return null;
        }
    }

    public static File loadFile(String path) {
        File loaded = null;
        try {
            loaded = new File(path);
        } catch (Exception e) {
        }
        return loaded;
    }

    public static RImage loadRainbowImage(String Filename) throws IOException, ClassNotFoundException {
        RImage ri = null;
        int linecounter = 0;
        int index = 0;
        File file = FileHandler.loadFile(Filename);
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            index = 0;
            if (linecounter > 0 && ri == null) {
                break;
            }
            String line = input.nextLine();
            if (line.startsWith("#")) // # is comment                
            {
                continue;
            } else if (line.isEmpty()) {
                continue;
            } else {
                String[] sp = line.split("#"); // end of line comments.
                sp = sp[0].split(":");
                if (sp.length != 2) {
                    throw new IllegalArgumentException("missing a ':' at line: " + linecounter);
                }
                if (sp[0].isEmpty()) {
                    throw new IllegalArgumentException("Missing element name at line: " + linecounter);
                }
                if (sp[0].equalsIgnoreCase("size")) {
                    int w = Integer.parseInt(sp[1]);
                    int h = Integer.parseInt(sp[2]);
                    ri = new RImage(h, w);
                }
                if (sp[0].equalsIgnoreCase("img")) {
                    for (int i = 1; i < sp.length; i++) {
                        int b =Integer.getInteger(sp[i]);
                        ri.setID(i, linecounter, new RColor().setID(b));
                    }
                }
            }
            linecounter++;
        }
        return ri;
    }

    public static void saveRainbowImage(RImage ri, String Filename) throws IOException {
        OutputStream outStream = new FileOutputStream(Filename);
        ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);
        fileObjectOut.writeObject(ri);
        fileObjectOut.close();
        outStream.close();
    }
}
