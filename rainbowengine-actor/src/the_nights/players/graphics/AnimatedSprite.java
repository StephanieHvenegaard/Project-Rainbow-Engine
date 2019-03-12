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

import the_nights.rainbow_engine.core.interfaces.ISprite;

/**
 *
 * @author Stephanie
 */
public class AnimatedSprite extends Sprite {//implements ICoreObject{

    Sprite[] sprites;
    int frameID;
    int counter;
    int speed;
    //boolean playing;

    public AnimatedSprite(Sprite[] sprites, int speed) {
        this.sprites = sprites;
        //this.playing = false;
        this.frameID = 0;
        this.counter = 0;
        this.speed = speed;
    }

    @Override
    public int getWidth() {
        if (sprites != null && sprites.length > 0) {
            return this.sprites[0].getWidth();
        } else {
            return -1;
        }
    }

    @Override
    public int getHeight() {
        if (sprites != null && sprites.length > 0) {
            return this.sprites[0].getHeight();
        } else {
            return -1;
        }
    }

    @Override
    public int[] getPixels() {
        return sprites[frameID].getPixels();
    }

    public ISprite getCurrentSprite() {
        return sprites[frameID];
    }

    public ISprite[] getSprites() {
        return sprites;
    }

    public int getFrameID() {
        return frameID;
    }

//    public boolean isPlaying() {
//        return playing;
//    }

    public void setFrameID(int frameID) {
        if (sprites != null && frameID < sprites.length) {
            this.frameID = frameID;
        }
    }

    private void incrementFrame() {
        if (sprites != null ){//&& playing) {
            this.frameID = (1 + frameID) % sprites.length;
        }
    }
//
//    public void setPlaying(boolean playing) {
//        this.playing = playing;
//    }

    public void update() {
        counter++;
        if (counter >= speed) {
            counter = 0;
            incrementFrame();
        }
    }

    public void reset() {
        counter =0;
        frameID = 0;
    }
}
