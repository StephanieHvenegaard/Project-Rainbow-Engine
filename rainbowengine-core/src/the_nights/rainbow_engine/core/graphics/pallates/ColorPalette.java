/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_nights.rainbow_engine.core.graphics.pallates;

import java.io.FileNotFoundException;
import java.util.Scanner;
import the_nights.rainbow_engine.core.FileHandler;
import the_nigths.rson.RSONObject;

/**
 *
 * @author Stephanie
 */
public class ColorPalette {
    public static int ALPHA_RGB = 0xFF00DD;  
    public static String EXTENSION = ".rp";
    protected int[] colors;   
    private int size=-1;
    public int getColor(int id) {
        if(id == -1)
            return ALPHA_RGB;
        return colors[id];
    }    

    public int getPalleteSize() {
        return colors.length;
    }    
    public static ColorPalette LoadPallete(String Filename) throws FileNotFoundException
    {
        ColorPalette pall = new ColorPalette();
        int linecounter = 0;
        int index =0;
        
        Scanner input = new Scanner(FileHandler.loadFile(Filename));
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
                if(sp[0].equalsIgnoreCase("size"))
                {
                    int s = Integer.parseInt(sp[1]);
                    pall.size = s;
                    pall.colors = new int[s];                            
                }
                if(sp[0].equalsIgnoreCase("color"))
                {
                    if(pall.size !=-1)
                    {                        
                        pall.colors[index]= Integer.parseInt(sp[1]);
                        index++;
                    }
                }
                if(index == pall.size)
                {
                    break;
                }
            }
        }
        return pall;
    }
}
