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

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 *
 * @author Stephanie
 */
public class Screenbuffer {

    private int width = 1;
    private int height = 1;
    private BufferedImage viewImage;
    private int[] view;
    //private RainbowPalette palette;

    public void setWidth(int w) {
        this.width = w;
        viewImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        view = ((DataBufferInt) viewImage.getRaster().getDataBuffer()).getData();
    }


    public void setHeight(int h) {
        this.height = h;
        viewImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        view = ((DataBufferInt) viewImage.getRaster().getDataBuffer()).getData();
    }
    
}
