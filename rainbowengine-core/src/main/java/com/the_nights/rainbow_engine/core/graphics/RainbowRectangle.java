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
package com.the_nights.rainbow_engine.core.graphics;

/**
 *
 * @author Stephanie
 */
public class RainbowRectangle {

    private int x;
    private int y;
    private int width;
    private int height;
    private int[] pixels;

    public RainbowRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
        for (int iy = 0; iy < height; iy++) {
            for (int ix = 0; ix < width; ix++) {
                pixels[ix + (iy * width)] = -1;
            }
        }
    }

    public RainbowRectangle() {
        this(0, 0, 0, 0);
    }

    public void generateGrafics(int colorID) {
        //pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = colorID;
            }
        }
    }
    
    public void generateBorderGrafics(int colorID) {
        //pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if ((y == 0 || y == height - 1) || (x == 0 || x == width - 1)) {
                    pixels[x + y * width] = colorID;
                } 
                else 
                    pixels[x + y * width] = -1;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        if (pixels != null) {
            return pixels;
        } else {
            System.out.println("no Grafics for rectagle");
            return null;
        }
    }

    public boolean Overlap(RainbowRectangle other) {
        if (other.x >= this.x && other.x <= (this.x + this.width)
        && (other.y >= this.y && other.y <= (this.y + this.height))) {
            return true;
        }else if(this.x >= other.x && this.x <= (other.x + other.width)
             && (this.y >= other.y && this.y <= (other.y + other.height)))
        {
            return true;
        }
        else {
            return false;
        }
    }
}

//        int px = other.getX();
//        int py = other.getY();
//        int cornerx = this.x-(this.width/2);
//        int cornery = this.y+(this.height/2);
//        int endx = cornerx + this.width;
//        int endy = cornery - this.height;
//        if(px >cornerx & px < endx)
//        {
//            if(py >cornery && py < endy)
//            {
//                return true;
//            }
//        }        
//        return false;        
//        // If one rectangle is on left side of other
//        if (this.x > (other.x+other.width) || other.x > (this.x+this.width)) {
//            return false;
//        }
//        // If one rectangle is above other
//        if (this.y < (other.y+other.height)|| other.y < (this.y+this.height)) {
//            return false;
//        }
//        return true;
