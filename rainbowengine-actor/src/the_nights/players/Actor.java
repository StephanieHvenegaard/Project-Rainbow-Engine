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
package the_nights.players;

import the_nights.players.graphics.AnimatedSprite;
import the_nights.players.graphics.Sprite;
import the_nights.rainbow_engine.core.graphics.Rectangle;
import the_nights.rainbow_engine.core.GameObject;
import the_nights.rainbow_engine.core.interfaces.ICoreObject;
import the_nights.rainbow_engine.core.interfaces.IMoveable;
import the_nights.rainbow_engine.core.interfaces.IPlacable;
import the_nights.rainbow_engine.core.interfaces.IScreenBuffer;
import the_nights.rainbow_engine.core.interfaces.ISprite;
/**
 *
 * @author Stephanie
 */
public abstract class Actor extends GameObject implements ICoreObject, IPlacable, IMoveable {
    protected final AnimatedSprite[] animTree;
    protected ISprite sprite;
    protected Rectangle rPlacement;
    protected Rectangle rCollisionBox;
    protected int activeAnimTree = 0;
    protected int speed;
    protected int health;
    public Actor(AnimatedSprite[] animTree, int speed) {
        this.animTree = animTree;
        this.sprite = animTree[0].getCurrentSprite();
        this.rPlacement = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
        this.rCollisionBox = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
        this.speed = speed;
    }
    public Actor(Sprite sprite, int speed) {
        this.animTree = null;
        this.sprite = sprite;
        this.rPlacement = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
        this.rCollisionBox = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
        this.speed = speed;
    }
    public Actor(Rectangle playerRec, int speed) {
        this.animTree = null;
        this.sprite = null;
        this.rPlacement = playerRec;
        this.rCollisionBox = playerRec;
    }
    @Override
    public int getX() {
        return rPlacement.getX();
    }
    @Override
    public int getY() {
        return rPlacement.getY();
    }
    public ISprite getSprite() {
        return sprite;
    }
    @Override
    public void setX(int x) {
        this.rPlacement.setX(x);
        this.rCollisionBox.setX(x);
    }
    @Override
    public void setY(int y) {
        this.rPlacement.setY(y);
        this.rCollisionBox.setY(y);
    }
    @Override
    public void moveX(int x) {
        int dxp = rPlacement.getX() + x;
        int dxc = rCollisionBox.getX() + x;
        this.rPlacement.setX(dxp);
        this.rCollisionBox.setX(dxc);
    }
    @Override
    public void moveY(int y) {
        int dyp = rPlacement.getY() + y;
        int dyc = rCollisionBox.getY() + y;
        this.rPlacement.setY(dyp);
        this.rCollisionBox.setY(dyc);
    }
    @Override
    public void render(IScreenBuffer screenBuffer) {
        if (animTree != null) {
            screenBuffer.renderSprite(animTree[activeAnimTree].getCurrentSprite(), rPlacement.getX(), rPlacement.getY());
        } else if (sprite != null) {
            screenBuffer.renderSprite(sprite, rPlacement.getX(), rPlacement.getY());
        } else {
            screenBuffer.renderRectangle(rPlacement);
        }               
    }
    
    public boolean collidable() {
        return (rCollisionBox != null);
    }

    public boolean checkCollision(Rectangle rec) {
    return this.rCollisionBox.Overlap(rec);
    }

    public void setCollisionBox(Rectangle rec) {
        this.rCollisionBox = rec;
        //this.rCollisionBox.generateBorderGrafics();
    }

    public void setCollisionBox(int width, int height) {
        Rectangle rec = new Rectangle(rPlacement.getX(), rPlacement.getY(), width, height);
        setCollisionBox(rec);
    }
}
