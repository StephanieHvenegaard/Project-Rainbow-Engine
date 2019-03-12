/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spriteseditor.guiobjects;

import the_nights.rainbow_engine.common.ICollisionInfo;
import the_nights.rainbow_engine.common.IGameObject;
import the_nights.rainbow_engine.common.IScreenBuffer;
import the_nights.rainbow_engine.core.Engine;
import the_nights.rainbow_engine.graphics.Rectangle;
import the_nights.rainbow_engine.graphics.Sprite;

/**
 *
 * @author Stephanie
 */
public abstract class BaseGuiObject {

    public abstract void render(IScreenBuffer renderHandler, int xZoom, int yZoom);
    public abstract void update(Engine engine) ;
    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();    
}
