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
//package the_nights.rainbow_engine.dnd.map;
//
//import the_nights.rainbow_engine.core.IPlacable;
//import the_nights.rainbow_engine.graphics.IRender;
//import the_nights.rainbow_engine.graphics.Sprite;
//import the_nights.rainbow_engine.graphics.IScreenBuffer;
//
///**
// *
// * @author Stephanie
// */
//public class Tile implements IRender, IPlacable {
//
//    protected boolean colliable;
//    protected String name;
//    protected Sprite sprite;
//    protected int x;
//    protected int y;
//
//    public Tile(String name, Sprite sprite, int x, int y, boolean colidable) {
//        this.name = name;
//        this.sprite = sprite;
//        this.colliable = colidable;
//        this.x = x;
//        this.y = y;
//    }
//
//    @Override
//    public void render(IScreenBuffer renderHandler, int xZoom, int yZoom) {
//        renderHandler.renderSprite(sprite, x, y, true);
//    }
//
//    @Override
//    public int getX() {
//        return x;
//
//    }
//
//    @Override
//    public int getY() {
//        return y;
//    }
//
//    @Override
//    public void setX(int x) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void setY(int y) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public boolean isColiable() {
//        return this.colliable;
//    }
//}
