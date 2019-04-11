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
package the_nights.rainbow_engine.core.interfaces;

import java.awt.Graphics;
import the_nights.rainbow_engine.core.graphics.RainbowImage;
import the_nights.rainbow_engine.core.graphics.RainbowRectangle;
import the_nights.rainbow_engine.core.graphics.RainbowText;
/**
 *
 * @author Stephanie
 */
public interface IScreenBuffer {
    public void setWidth(int w);
    public void setHeight(int h);
            
    public void renderRImage(RainbowImage image, int x, int y);
    
    //public void renderSprite(OBS_ISprite sprite, int xPosition, int yPosition);
    
    public void renderRectangle(RainbowRectangle rec);
    
    public void renderString(RainbowText text);

    //public void renderPixels(int[] renderPixels, int xPosition, int yPosition, int renderWidth, int renderHeight);

    public void setPixel(int pixel, int x, int y);   

    public void DrawView(Graphics graphics, int screenWidth, int screenHeight);

    public void clear();  
    
//    public RainbowPalette getPallete();
//    
//    public void setPallete(RainbowPalette pallete);

    //public void renderRectangle(RainbowRectangle rec, int xZoom, int yZoom);
   
    //public void renderImage(BufferedImage image, int xPosition, int yPosition, int xZoom, int yZoom);

    //public void renderSprite(OBS_ISprite sprite, int xPosition, int yPosition, int xZoom, int yZoom);
    
    //public void renderString(String s, String fontName, int fontSize, int xPosition, int yPosition);

    //public void renderBackgroundPixels(int[] renderPixels, int xPosition, int yPosition, int renderWidth, int renderHeight, int xZoom, int yZoom);

    //public void setBackgroundPixel(int pixel, int x, int y);

    //public void setBackgroundSize(int width, int heigth);

    //public void renderPixels(int[] renderPixels, int xPosition, int yPosition, int renderWidth, int renderHeight, int xZoom, int yZoom);

    //public void setRenderAlpha(boolean renderAlpha);
    
    //public boolean isRenderAlpha();

    //public void DrawView(Graphics graphics);
    
    //public RainbowRectangle getCamara();

    //public BufferedImage getView();

    //public int[] getViewPixels();
    
    //public int calculateTextSize(Text text);
    
    //public BufferedImage getBufferImage();
}
