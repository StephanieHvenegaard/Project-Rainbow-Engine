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
package the_nights.rainbow_engine.palleterender.graphics.palettes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import the_nights.rainbow_engine.core.FileHandler;
import the_nights.rainbow_engine.core.logging.RELogger;

/**
 *
 * @author Stephanie
 */
public class RainbowPalette {

    public static int ALPHA_RGB = 0xFF00DD;
    public static String EXTENSION = ".rp";
    protected int[] colors;
    private int size = -1;
    private String name = "c64";

    public int getColor(int id) {
        if (id >= size) {
            System.out.println("Can't fetch that id : " + id + " are you still using old colors?");
            return ALPHA_RGB;
        }
        if (id == -1) {
            return ALPHA_RGB;
        }
        return colors[id];
    }

    public int getPalleteSize() {
        return colors.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RainbowPalette LoadPallete(String Filename) throws FileNotFoundException {
        RainbowPalette pall = new RainbowPalette();
        int linecounter = 0;
        int index = 0;
        File file = FileHandler.loadFile(Filename);
        Scanner input = new Scanner(file);
        RELogger.writelog("Loading palette from : " + file.getAbsolutePath(), pall);
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
                    pall.size = s;
                    pall.colors = new int[s];
                }
                if (sp[0].equalsIgnoreCase("color")) {
                    if (pall.size != -1) {
                        String hex = sp[1].trim();
                        pall.colors[index] = Integer.parseInt(hex, 16);
                        index++;
                    }
                }
                if (index == pall.size) {
                    break;
                }
            }
        }
        return pall;
    }

}
