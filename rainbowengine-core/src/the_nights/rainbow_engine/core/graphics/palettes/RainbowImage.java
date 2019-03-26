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
package the_nights.rainbow_engine.core.graphics.palettes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 *
 * @author Stephanie
 */
public class RainbowImage implements Serializable{
    private int height = 0;
    private int width = 0;
    private byte[][] ids;                       // because max colors suported will be 255
    public RainbowImage(int h, int w)
    {
        this.height = h;
        this.width = w;
        this.ids = new byte[w][h];              // X first, why later
    }   
           
    public byte getID(int x, int y)
    {
        return ids[x][y];
    }
    public void setID(int x,int y,byte id)
    {
        ids[x][y]=id;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }    
    
    public static void saveImage(RainbowImage ri,String Filename ) throws IOException { 
        OutputStream outStream = new FileOutputStream(Filename);    
        ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);
        fileObjectOut.writeObject(ri);
        fileObjectOut.close();
        outStream.close();
    }  

    public static RainbowImage loadImage(String Filename) throws IOException, ClassNotFoundException {
        InputStream inStream = new FileInputStream(Filename);
        ObjectInputStream fileObjectIn = new ObjectInputStream(inStream);
        RainbowImage ri = (RainbowImage) fileObjectIn.readObject();
        fileObjectIn.close();
        inStream.close();         
        return ri;
    }
}