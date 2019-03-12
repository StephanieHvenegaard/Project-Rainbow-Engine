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
package the_nights.players.graphics;

import the_nights.players.graphics.Sprite;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

/**
 *
 * @author Stephanie
 */
public class SpriteSheet {

    private int[] pixels;
    private BufferedImage sheet;
    public final int WIDTH;
    public final int HEIGHT;
    public final int SPRITE_SIZE;
    private Sprite[] sprites;

    public SpriteSheet(BufferedImage sheet, int spriteSize) {
        this.sheet = sheet;
        this.HEIGHT = sheet.getHeight();
        this.WIDTH = sheet.getWidth();
        this.SPRITE_SIZE = spriteSize;
        this.pixels = new int[HEIGHT * WIDTH];
        this.sprites = new Sprite[(HEIGHT / spriteSize) * (WIDTH / spriteSize)];
        sheet.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, 0);

        int spriteID = 0;
        for (int y = 0; y < HEIGHT; y += spriteSize) {
            for (int x = 0; x < WIDTH; x += spriteSize) {
                int[] spritePix = new int[spriteSize * spriteSize];
                int spriteWidth = spriteSize;
                int spriteHeight = spriteSize;
                spritePix = getSpritePixels(x, y, spriteWidth, spriteHeight, spritePix, 0, spriteWidth);
                Sprite s = new Sprite(spriteSize, spritePix);
                sprites[spriteID] = s;
                spriteID++;
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public BufferedImage getSheet() {
        return sheet;
    }

    public Sprite getSprite(int id) {
        if (id < sprites.length) {
            return sprites[id];
        } else {
            System.out.println("ID " + id+ " is out of range for this sprite sheet.  Only "+sprites.length+" sprites loaded");
            return null;
        }
    }

    public Sprite getSprite(int x, int y) {
        int width = (WIDTH/SPRITE_SIZE);
        int id = x + (y*width);
        //System.out.println("fetching sprite x:"+x +" y:"+y+ " with id:"+id);
        return getSprite(id);
    }
    
    public Sprite[] getSprites(int row) {
        Sprite[] returnedSprites = new Sprite[(WIDTH / SPRITE_SIZE)];        
        for(int i =0; i < returnedSprites.length; i++)
        {
            returnedSprites[i] = getSprite(i,row);
        }
        return returnedSprites;
    }
    
     public int[] getSpritePixels(int startX, int startY, int w, int h,
                        int[] rgbArray, int offset, int scansize) {
        int yoff  = offset;
        int off;       
        if (rgbArray == null) {
            rgbArray = new int[offset+h*scansize];
        }

        for (int y = startY; y < startY+h; y++, yoff+=scansize) {
            off = yoff;
            for (int x = startX; x < startX+w; x++) {
                rgbArray[off++] = pixels[x+y];
            }
        }

        return rgbArray;
    }
    
}
