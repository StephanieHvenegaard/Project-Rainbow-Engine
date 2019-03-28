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
package the_nights.rainbow_engine.core.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import the_nights.rainbow_engine.core.graphics.palettes.RainbowPalette;
import the_nights.rainbow_engine.core.interfaces.IScreenBuffer;
import the_nights.rainbow_engine.core.interfaces.OBS_ISprite;

/**
 *
 * @author Stephanie
 */
public class CoreScreenbuffer implements IScreenBuffer {
    protected BufferedImage viewImage;
    protected int[] view;
    protected RainbowPalette palette;
    
    public CoreScreenbuffer(int width, int height) {
        viewImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        view = ((DataBufferInt) viewImage.getRaster().getDataBuffer()).getData();
    }
    @Override
    public void renderRectangle(Rectangle rec) {
        int[] recPixels = rec.getPixels();
        if (recPixels != null) {
            //TODO: FIX Later.
//            renderPixels(recPixels, rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
        }
    }
    @Override
    public void renderString(Text text) {
        Graphics graphics = viewImage.createGraphics();
        graphics.setColor(text.getColor());
        graphics.setFont(new Font(text.getFont(), Font.PLAIN, text.getSize()));
        graphics.drawString(text.getText(), text.getxPosition(), text.getyPosition());
        graphics.dispose();
    }
    @Override
    public void clear() {
        for (int i = 0; i < view.length; i++) {
            view[i] = 0;
        }
    }
    @Override
    public void renderRImage(RainbowImage image, int x, int y) {
        
//        renderPixels(sprite.getPixels(), xPosition, yPosition, sprite.getWidth(), sprite.getHeight());
    }
//    @Override
//    public void renderPixels(int[] renderPixels, int xPosition, int yPosition, int renderWidth, int renderHeight) {
//        for (int y = 0; y < renderHeight; y++) {
//            for (int x = 0; x < renderWidth; x++) {
//                int pixelID = x + (y * renderWidth);
//                int pixel = renderPixels[pixelID];
//                setPixel(pixel, x+xPosition, y+yPosition);
//            }
//        }
//    }
    @Override
    public void setPixel(int pixel, int x, int y) {
        if (pixel == RainbowPalette.ALPHA_RGB) {
            return;
        }
            int pixelID = x + (y * viewImage.getWidth());
            if (pixelID < view.length) {
                view[pixelID] = palette.getColor(pixel);
            }
    }
    
    @Override
    public void DrawView(Graphics graphics, int screenWidth, int screenHeight) {
        //System.out.println("W : " + screenWidth + " H " + screenHeight);

        int zoomY = (screenHeight / viewImage.getHeight());
        int zoomX = (screenWidth / viewImage.getWidth());
        //System.out.println("Zoom X : "+ zoomX + " Y : "+zoomY);        

        int zoomFactor = 1;
        if (zoomX <= zoomY) {
            zoomFactor = zoomX;
        } else {
            zoomFactor = zoomY;
        }

        //System.out.println("Zoomfactor " +zoomFactor);  
        int xPosition = (screenWidth - (viewImage.getWidth() * zoomFactor)) / 2;
        int yPosition = (screenHeight - (viewImage.getHeight() * zoomFactor)) / 2;

        //System.out.println("xpos : "+ xPosition + "ypos : "+ yPosition);
        BufferedImage screen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        int[] screenPixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
        for (int y = 0; y < viewImage.getHeight(); y++) {
            for (int x = 0; x < viewImage.getWidth(); x++) {
                for (int yz = 0; yz < zoomFactor; yz++) {
                    for (int xz = 0; xz < zoomFactor; xz++) {
                        int pixel = view[x + (y * viewImage.getWidth())];
                        int xi = ((x * zoomFactor) + xPosition + xz);
                        int yi = ((y * zoomFactor) + yPosition + yz);
                        screenPixels[xi + (yi * screenWidth)] = pixel;
                    }
                }
            }
        }
        graphics.drawImage(screen, 0, 0, screenWidth, screenHeight, null);
    }

    @Override
    public RainbowPalette getPallete() {
        return palette;
    }

    @Override
    public void setPallete(RainbowPalette palette) {
        this.palette = palette;
    }
}

//    
//    @Override
//    public void renderImage(BufferedImage image, int xPosition, int yPosition) {
//        int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
//        renderPixels(imagePixels, xPosition, yPosition, image.getWidth(), image.getHeight());
//    }
//
//    @Override
//    public void renderSprite(OBS_ISprite sprite, int xPosition, int yPosition, boolean renderBackground) {
//        renderSprite(sprite, xPosition, yPosition, 1, 1, renderBackground);
//    }
//
//    @Override
//    public void renderSprite(OBS_ISprite sprite, int xPosition, int yPosition, int xZoom, int yZoom, boolean renderBackground) {
//        sprite.getPixels();
//        if (renderBackground) {
//            renderBackgroundPixels( sprite.getPixels(), xPosition, yPosition, sprite.getWidth(), sprite.getHeight(), xZoom, yZoom);
//        } else {
//            
//        }
//    }
//    
//    @Override
//    public void renderImage(BufferedImage image, int xPosition, int yPosition, boolean renderBackground) {
//        renderImage(image, xPosition, yPosition, 1, 1, renderBackground);
//    }
//
//    @Override
//    public void renderImage(BufferedImage image, int xPosition, int yPosition, int xZoom, int yZoom, boolean renderBackground) {
//        int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
//        if (renderBackground) {
//            renderBackgroundPixels(imagePixels, xPosition, yPosition, image.getWidth(), image.getHeight(), xZoom, yZoom);
//        } else {
//            renderPixels(imagePixels, xPosition, yPosition, image.getWidth(), image.getHeight(), xZoom, yZoom);
//        }
//    }
//
//    @Override
//    public void renderRectangle(Rectangle rec, boolean renderBackground) {
//        renderRectangle(rec, 1, 1, renderBackground);
//    }
//
//    @Override
//    public void renderRectangle(Rectangle rec, int xZoom, int yZoom, boolean renderBackground) {
//        int[] recPixels = rec.getPixels();
//        if (recPixels != null) {
//            if (renderBackground) {
//                renderBackgroundPixels(recPixels, rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight(), xZoom, yZoom);
//            } else {
//                renderPixels(recPixels, rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight(), xZoom, yZoom);
//            }
//        }
//    }
//
//    @Override
//    public void renderString(Text text, boolean renderBackground) {
//        Graphics graphics;
//        if (renderBackground) {
//            graphics = this.backgroundImage.getGraphics();
//        } else {
//            graphics = viewImage.createGraphics();
//        }
//        graphics.setColor(text.getColor());
//        graphics.setFont(new Font(text.getFont(), Font.PLAIN, text.getSize()));
//        graphics.drawString(text.getText(), text.getxPosition(), text.getyPosition());
//        graphics.dispose();
//    }
//
//    @Override
//    public void renderString(String s, String fontName, int fontSize, int xPosition, int yPosition, boolean renderBackground) {
//        Text t = new Text(s, fontSize, fontName, xPosition, yPosition);
//        renderString(t, renderBackground);
//    }
//
//    @Override
//    public void renderPixels(int[] renderPixels, int xPosition, int yPosition, int renderWidth, int renderHeight) {
//        renderPixels(renderPixels, xPosition, yPosition, renderWidth, renderHeight, 1, 1);
//    }
//
//    @Override
//    public void renderPixels(int[] renderPixels, int xPosition, int yPosition, int renderWidth, int renderHeight, int xZoom, int yZoom) {
//        for (int y = 0; y < renderHeight; y++) {
//            for (int x = 0; x < renderWidth; x++) {
//                for (int xz = 0; xz < xZoom; xz++) {
//                    for (int yz = 0; yz < yZoom; yz++) {
//                        int pixelID = x + (y * renderWidth);
//                        int pixel = renderPixels[pixelID];
//                        int xi = ((x * xZoom) + xPosition + xz);
//                        int yi = ((y * yZoom) + yPosition + yz);
//                        setPixel(pixel, xi, yi);
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void setPixel(int pixel, int x, int y) {
//        if (renderAlpha) {
//            if (pixel == BasePalette.ALPHA_RGB) {
//                return;
//            }
//        }
//        if ((x >= camera.getX() && x < camera.getX() + camera.getWidth())
//                && (y >= camera.getY() && y < camera.getY() + camera.getHeight())) {
//            int pixelIndex = (x - camera.getX()) + (y - camera.getY()) * viewImage.getWidth();
//            if (pixelIndex < view.length) {
//                view[pixelIndex] = pixel;
//            }
//        }
//    }
//
//    @Override
//    public void DrawView(Graphics graphics) {
//        graphics.drawImage(viewImage, 0, 0, viewImage.getWidth(), viewImage.getHeight(), null);
//    }
//
//    @Override
//    public void DrawView(Graphics graphics, int screenWidth, int screenHeight) {
//        //System.out.println("W : "+screenWidth +" H "+ screenHeight);
//
//        int zoomY = (screenHeight / viewImage.getHeight());
//        int zoomX = (screenWidth / viewImage.getWidth());
//        //System.out.println("Zoom X : "+ zoomX + " Y : "+zoomY);        
//
//        int zoomFactor = 1;
//        if (zoomX <= zoomY) {
//            zoomFactor = zoomX;
//        } else {
//            zoomFactor = zoomY;
//        }
//
//        //System.out.println("Zoomfactor " +zoomFactor);  
//        int xPosition = (screenWidth - (viewImage.getWidth() * zoomFactor)) / 2;
//        int yPosition = (screenHeight - (viewImage.getHeight() * zoomFactor)) / 2;
//
//        //System.out.println("xpos : "+ xPosition + "ypos : "+ yPosition);
//        BufferedImage screen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
//        int[] screenPixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
//        for (int y = 0; y < viewImage.getHeight(); y++) {
//            for (int x = 0; x < viewImage.getWidth(); x++) {
//                for (int yz = 0; yz < zoomFactor; yz++) {
//                        for (int xz = 0; xz < zoomFactor; xz++) {
//                            int pixel = view[x + (y * viewImage.getWidth())];
//                            int xi = ((x * zoomFactor) + xPosition + xz);
//                            int yi = ((y * zoomFactor) + yPosition + yz);
//                            screenPixels[xi + (yi * screenWidth)] = pixel;
//                        }
//                }
//            }
//        }
//        graphics.drawImage(screen, 0, 0, screenWidth, screenHeight, null);
//    }
//    @Override
//    public void renderBackgroundPixels(int[] renderPixels, int xPosition, int yPosition, int renderWidth, int renderHeight, int xZoom, int yZoom) {
//        for (int y = 0; y < renderHeight; y++) {
//            for (int x = 0; x < renderWidth; x++) {
//                for (int xz = 0; xz < xZoom; xz++) {
//                    for (int yz = 0; yz < yZoom; yz++) {
//                        int pixelID = x + (y * renderWidth);
//                        int pixel = renderPixels[pixelID];
//                        int xi = ((x * xZoom) + xPosition + xz);
//                        int yi = ((y * yZoom) + yPosition + yz);
//                        setBackgroundPixel(pixel, xi, yi);
//                    }
//                }
//            }
//        }
//    }
//    @Override
//    public void setBackgroundPixel(int pixel, int x, int y) {
//        if (pixel != C64Palette.ALPHA_RGB) {
//            int pixelIndex = x + (y * backgroundImage.getWidth());
//            if (pixelIndex < bgPixels.length) {
//                bgPixels[pixelIndex] = pixel;
//            }
//        }     
//    }
//    @Override
//    public void setBackgroundSize(int width, int heigth) {
//        backgroundImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
//        bgPixels = ((DataBufferInt) backgroundImage.getRaster().getDataBuffer()).getData();
//    }
//
//    @Override
//    public void setRenderAlpha(boolean renderAlpha) {
//        this.renderAlpha = renderAlpha;
//    }
//
//    @Override
//    public boolean isRenderAlpha() {
//        return renderAlpha;
//    }
//
//    @Override
//    public Rectangle getCamara() {
//        return camera;
//    }
//
//    @Override
//    public BufferedImage getView() {
//        return viewImage;
//    }
//
//    @Override
//    public int[] getViewPixels() {
//        return view;
//    }
//
//    @Override
//    public void clear(boolean renderbackground) {
//        if (renderbackground) {
//            renderImage(backgroundImage, 0, 0, false);
//        } else {
//            for (int i = 0; i < view.length; i++) {
//                view[i] = 0;
//            }
//        }
//    }
//    @Override
//    public BufferedImage getBufferImage() {
//        return viewImage;
//    }
//    @Override
//    public int calculateTextSize(Text text) {
//        Font font = new Font(text.getFont(), Font.PLAIN, text.getSize());
//        return (int) (font.getStringBounds(text.getText(), frc).getWidth());
//    }
//}
//    ---- ----- OLD SHIT ------------
//    @Deprecated
//    private int convertPixel(int pixel) {
//        int ConvertedPixel = 0;
//        Color pColor = new Color(pixel);
//        double distance = -1;
//        for (int i = 0; i < EngineColors.ColorCodes.length; i++) {
//            Color cColor = new Color(EngineColors.ColorCodes[i]);
//            double r = calcColorDistanse(pColor, cColor);
//            if (r < distance || distance == -1) {
//                distance = r;
//                ConvertedPixel = EngineColors.ColorCodes[i];
//            }
//        }
//        return ConvertedPixel;
//    }
//
//    @Deprecated
//    private double calcColorDistanse(Color origanal, Color convertion) {
//
//// FAILED MOST        
////        double distance = (origanal.getRed() - convertion.getRed())*(origanal.getRed() - convertion.getRed()) +
////                                    (origanal.getGreen() - convertion.getGreen())*(origanal.getGreen() -convertion.getGreen()) + 
////                                    (origanal.getBlue()-convertion.getBlue())*(origanal.getBlue() - convertion.getBlue());
//// FAILED FEVER        
////        double distance = (Math.pow(30*(origanal.getRed() - convertion.getRed()),2) +
////                          Math.pow(59*(origanal.getGreen() - convertion.getGreen()),2) + 
////                          Math.pow(11*(origanal.getBlue()- convertion.getBlue()),2));
//        float[] hsb = Color.RGBtoHSB(convertion.getRed(), convertion.getGreen(), convertion.getBlue(), null);
//        float H0 = hsb[0];
//        float S0 = hsb[1];
//        float V0 = hsb[2];
//
//        double X0 = Math.cos(H0) * S0 * V0;
//        double Y0 = Math.sin(H0) * S0 * V0;
//        double Z0 = V0;
//
//        hsb = Color.RGBtoHSB(origanal.getRed(), origanal.getGreen(), origanal.getBlue(), null);
//        double H1 = hsb[0];
//        double S1 = hsb[1];
//        double V1 = hsb[2];
//
//        double X1 = Math.cos(H1) * S1 * V1;
//        double Y1 = Math.sin(H1) * S1 * V1;
//        double Z1 = V1;
//
//        //For both points and then taking the Euclidian distance between them:
//        //double distance = Math.sqrt((X0 - X1) * (X0 - X1) + (Y0 - Y1) * (Y0 - Y1) + (Z0 - Z1) * (Z0 - Z1));
//        double A = S0 * V0;
//        double B = S1 * V1;
//        double dTheta = H1 - H0;
//        double dZ = V0 - V1;
//        double distance = Math.sqrt(dZ * dZ + A * A + B * B + 2 * A * B * Math.cos(dTheta));
//
//        return distance;
//    }
//}
