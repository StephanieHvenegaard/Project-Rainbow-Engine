/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeditor.guiobjects;

import com.sun.glass.events.KeyEvent;
import mapeditor.MEEngine;
import the_nights.rainbow_engine.common.IScreenBuffer;
import the_nights.rainbow_engine.core.Engine;
import the_nights.rainbow_engine.core.EngineColors;
import the_nights.rainbow_engine.core.KeyboardListner;
import the_nights.rainbow_engine.graphics.ColorPallete;
import the_nights.rainbow_engine.graphics.Rectangle;

/**
 *
 * @author Stephanie
 */
public class SpriteDrawUI extends BaseGuiObject {

    Rectangle rBorder;
    Rectangle rCurser;
    int iCurserMinX = 0;
    int iCurserMaxX;
    int iCurserMinY = 0;
    int iCurserMaxY;
    int iCurserX = 0;
    int iCurserY = 0;
    int iCurrentColorID = 0;
    int iPixelWidth = 1;
    int iPixelHeight = 1;
    private final int iCurserMoveDelay_MAX = 5;
    int iCurserMoveDelay = 0;
    int iSpriteWidth = 16;
    int iSpriteHeight = 16;
    int iWidth;
    int iHeight;
    int[] iaPixelIDS;
    int[] iaPixels;
    boolean bRedraw = true;

    public SpriteDrawUI(int x, int y, int width, int heigth, int spriteWidth, int spriteHeigth) {
        this.iHeight = heigth;
        this.iWidth = width; 
        this.iCurserMinX = x + 1;
        this.iCurserMinY = y + 1;
        this.iPixelHeight = heigth / spriteHeigth;
        this.iPixelWidth = width / spriteWidth;
        this.iaPixelIDS = new int[spriteHeigth * spriteWidth];
        this.iaPixels = new int[iaPixelIDS.length];
        this.rBorder = new Rectangle(x, y, width, heigth);
        this.rCurser = new Rectangle(iCurserMinX, iCurserMinY, 10, 10);
        rBorder.generateGrafics(EngineColors.AlphaARGB);
        rBorder.generateBorderGrafics(EngineColors.Grey);
        rCurser.generateBorderGrafics(EngineColors.Grey);
    }

    @Override
    public void render(IScreenBuffer renderHandler, int xZoom, int yZoom) {
        renderHandler.renderRectangle(rBorder, false);
        renderHandler.renderPixels(iaPixels, iCurserMinX,iCurserMinY,iSpriteWidth,iSpriteHeight,iPixelWidth,iPixelHeight);
        renderHandler.renderRectangle(rCurser, false);
    }

    @Override
    public void update(Engine engine) {
        MEEngine se = (MEEngine) engine.getGame();
        KeyboardListner k = engine.getKeyboardListner();
//        if (se.getMode() == se.DRAW_MODE) {
//            iCurrentColorID = se.getColorID();
//            rCurser.generateGrafics(se.getColor());
//            rCurser.generateBorderGrafics(EngineColors.Grey);
//
//            if (iCurserMoveDelay == 0) {
//                if (k.left()) {
//                    if (iCurserX > 0) {
//                        iCurserX--;
//                        rCurser.setX(iCurserMinX + (iCurserX * iPixelWidth));
//                        iCurserMoveDelay++;
//                    }
//                }
//                if (k.right()) {
//                    if ((iCurserX < iSpriteWidth - 1)) {
//                        iCurserX++;
//                        rCurser.setX(iCurserMinX + (iCurserX * iPixelWidth));
//                        iCurserMoveDelay++;
//                    }
//                }
//                if (k.up()) {
//                    if (iCurserY > 0) {
//                        iCurserY--;
//                        rCurser.setY(iCurserMinY + (iCurserY * iPixelHeight));
//                        iCurserMoveDelay++;
//                    }
//                }
//                if (k.down()) {
//                    if (iCurserY < iSpriteHeight - 1) {
//                        iCurserY++;
//                        rCurser.setY(iCurserMinY + (iCurserY * iPixelHeight));
//                        iCurserMoveDelay++;
//                    }
//                }
//            }
//
//            if (iCurserMoveDelay > 0) {
//                iCurserMoveDelay++;
//            }
//            if (iCurserMoveDelay > iCurserMoveDelay_MAX) {
//                iCurserMoveDelay = 0;
//            }
//            if (k.checkKey(KeyEvent.VK_SPACE)) {
//                int id = iCurserX + (iCurserY * iSpriteWidth);
//                iaPixelIDS[id] = iCurrentColorID;
//                bRedraw = true;
//            }
//        }
//        if(bRedraw)
//        {
//            System.arraycopy(iaPixelIDS, 0, iaPixels,0, iaPixels.length);
//            for (int i = 0; i < iaPixels.length; i++) {
//                iaPixels[i] = se.getColor(iaPixelIDS[i]);
//            }
//            bRedraw = false;
//        }
    }

    @Override
    public int getX() {
        return rBorder.getX();
    }

    @Override
    public int getY() {
        return rBorder.getY();
    }

    @Override
    public int getWidth() {
        return rBorder.getWidth();
    }

    @Override
    public int getHeight() {
        return rBorder.getHeight();
    }

    public void setRedraw(boolean redraw) {
        this.bRedraw = redraw;
    }
    
}
