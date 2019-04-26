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

import com.the_nights.rainbow_engine.core.Engine;
import com.the_nights.rainbow_engine.core.graphics.palettes.C64Palette;
import com.the_nights.rainbow_engine.core.interfaces.IScreenBuffer;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 *
 * @author Stephanie
 */
public final class Screenbuffer implements IScreenBuffer {

    private int screenWidth = 1;
    private int screenHeight = 1;
    private Canvas viewport;
    private BufferedImage screen;
    private int[] screenPixels;
    private RainbowPalette palette;
    private Graphics graphics;
    private BufferStrategy bufferStrategy;

    public Screenbuffer(Engine e) {
        this.palette = new C64Palette();
        this.screen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        this.screenPixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
                //Create our object for buffer strategy.    
        this.viewport.createBufferStrategy(3);
        // add Listners
        this.viewport.addMouseListener(e.getMouseEventListner());
        this.viewport.addMouseMotionListener(e.getMouseEventListner());
        this.viewport.addKeyListener(e.getKeyboardListner());
        this.viewport.addFocusListener(e.getKeyboardListner());
    }

    @Override
    public void renderRectangle(RainbowRectangle rec) {
        int[] recPixels = rec.getPixels();
        if (recPixels != null) {
            for (int y = 0; y < rec.getHeight(); y++) {
                for (int x = 0; x < rec.getWidth(); x++) {
                    int posID = x + (y * rec.getWidth());
                    if (posID < recPixels.length) {
                        int pixelColerID = recPixels[posID];
                        setPixelColor(pixelColerID, x + rec.getX(), y + rec.getY());
                    } else {
                        System.out.println("ups");
                    }
                }
            }
        }
    }

    @Override
    public void renderString(RainbowText text) {
        if (viewport != null) {
            Graphics graphics = screen.createGraphics();
            Color c = new Color(palette.getColor(text.getColor()));
            graphics.setColor(c);
            graphics.setFont(new Font(text.getFont(), Font.PLAIN, text.getSize()));
            graphics.drawString(text.getText(), text.getxPosition(), text.getyPosition());
            graphics.dispose();
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < screenPixels.length; i++) {
            screenPixels[i] = 0;
        }
    }

    @Override
    public void renderRImage(RainbowImage image, int x, int y) {
        // TODO : add support for an R-image.

//        renderPixels(sprite.getPixels(), xPosition, yPosition, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void setPixelColor(int pixelColerID, int x, int y) {
        int posID = x + (y * viewport.getWidth());
        if (posID < screenPixels.length && posID >= 0) {
            if (pixelColerID < palette.getPalleteSize()) {
                screenPixels[posID] = palette.getColor(pixelColerID);
            } else {
                System.out.println("ups");
            }
        } else {
            System.out.println("ups - pixel bigger then canvas");
        }

    }

    @Override
    public void DrawView() {       
        //System.out.println("W : " + screenWidth + " H " + screenHeight);
        int zoomY = (viewport.getHeight() / screenHeight);
        int zoomX = (viewport.getWidth() / screenWidth);
        //System.out.println("Zoom X : "+ zoomX + " Y : "+zoomY);        

        int zoomFactor = 1;
        if (zoomX <= zoomY) {
            zoomFactor = zoomX;
        } else {
            zoomFactor = zoomY;
        }

        int xPosition = (screenWidth - (screen.getWidth() * zoomFactor)) / 2;
        int yPosition = (screenHeight - (screen.getHeight() * zoomFactor)) / 2;

        //System.out.println("xpos : "+ xPosition + "ypos : "+ yPosition);
        BufferedImage screen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        int[] screenPixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
        for (int y = 0; y < screen.getHeight(); y++) {
            for (int x = 0; x < screen.getWidth(); x++) {
                for (int yz = 0; yz < zoomFactor; yz++) {
                    for (int xz = 0; xz < zoomFactor; xz++) {
                        int pixel = screenPixels[x + (y * screen.getWidth())];
                        int xi = ((x * zoomFactor) + xPosition + xz);
                        int yi = ((y * zoomFactor) + yPosition + yz);
                        screenPixels[xi + (yi * screenWidth)] = pixel;
                    }
                }
            }
        }
        getGraphics().drawImage(screen, 0, 0, screenWidth, screenHeight, null);
        bufferStrategy.show();
    }

    public RainbowPalette getPallete() {
        return palette;
    }

    public void setPallete(RainbowPalette palette) {
        this.palette = palette;
    }

    @Override
    public void setWidth(int w) {
        this.screenWidth = w;
        screen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        screenPixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
    }

    @Override
    public void setHeight(int h) {
        this.screenHeight = h;
        screen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        screenPixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
    }

    @Override
    public Canvas getCanvas() {
        return this.viewport;
    }

    @Override
    public Graphics getGraphics() {
        if (graphics != null) {
            bufferStrategy = viewport.getBufferStrategy();            
            graphics = bufferStrategy.getDrawGraphics();
        }
        return graphics;

    }

    @Override
    public void DisposeGraphics() {
        if (graphics != null) {
            this.graphics.dispose();
            this.graphics = null;
        }
    }
}
