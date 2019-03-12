/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeditor.guiobjects;

import java.awt.event.KeyEvent;
import javafx.scene.paint.Color;
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
public class ColorPalleteUI extends BaseGuiObject {

    Rectangle rBorder;
    Rectangle rCurser;
    Rectangle[] rColorBoxes;
    ColorPallete cpColors;
    int iCurserMinX = 0;
    int iCurserMaxX;
    int iCurserMinY = 0;
    int iCurserMaxY;
    int iCurserX = 0;
    int iCurserY = 0;
    int iColorWidth;
    int iColorHiegth;
    int iColorRows;
    int iColorCol = 8;
    int iCurrentColorID = 0;
    private int iCurserMoveDelay_MAX = 5;
    int iCurserMoveDelay = 0;

    public ColorPalleteUI(ColorPallete cp, int X, int Y) {
        this.cpColors = cp;
        int colors = cp.getColors().length - 1;
        int rows = (colors / iColorCol) + 1;
        this.iColorHiegth = 20;
        this.iColorWidth = 30;
        this.iCurserMinX = X + 1;
        this.iCurserMinY = Y + 1;
        this.rBorder = new Rectangle(X, Y, iColorCol * iColorWidth + 2, rows * iColorHiegth + 2);
        this.rCurser = new Rectangle(iCurserMinX, iCurserMinY, rBorder.getWidth() - 2, iColorHiegth);
        rBorder.generateGrafics(EngineColors.Black);
        rBorder.generateBorderGrafics(EngineColors.Grey);
        rCurser.generateBorderGrafics(EngineColors.Grey);
        this.rColorBoxes = new Rectangle[cp.getColors().length];
        Rectangle r = new Rectangle(X + 1, Y + 1, rBorder.getWidth() - 2, iColorHiegth);
        r.generateGrafics(cp.getColor(0));
        r.generateBorderGrafics(EngineColors.Black);
        this.rColorBoxes[0] = r;
        int row = 1;
        int col = 0;
        for (int c = 1; c < cp.getColors().length; c++) {
            r = new Rectangle((X + 1) + (col * iColorWidth), (Y + 1) + (row * iColorHiegth), iColorWidth, iColorHiegth);
            r.generateGrafics(cp.getColor(c));
            r.generateBorderGrafics(EngineColors.Black);
            this.rColorBoxes[c] = r;
            col++;
            if (col == iColorCol) {
                col = 0;
                row++;
            }
        }
        iColorRows = row;
    }

    @Override
    public void render(IScreenBuffer renderHandler, int xZoom, int yZoom) {
        renderHandler.renderRectangle(rBorder, false);
        if (rColorBoxes != null) {
            for (Rectangle r : rColorBoxes) {
                renderHandler.renderRectangle(r, false);
            }
        }
        renderHandler.renderRectangle(rCurser, false);
    }

    @Override
    public void update(Engine engine) {
        MEEngine se = (MEEngine) engine.getGame();
        KeyboardListner k = engine.getKeyboardListner();
//        if (se.getMode() == se.COLOR_MODE) {
//            if (iCurserMoveDelay == 0) {
//                if (k.left()) {
//                    if (iCurserX > 0 && iCurserY > 0) {
//                        iCurserX--;
//                        rCurser.setX(iCurserMinX + (iCurserX * iColorWidth));
//                        iCurserMoveDelay++;
//                    }
//                }
//                if (k.right()) {
//                    if ((iCurserX < iColorCol - 1) && iCurserY > 0) {
//                        iCurserX++;
//                        rCurser.setX(iCurserMinX + (iCurserX * iColorWidth));
//                        iCurserMoveDelay++;
//                    }
//                }
//                if (k.up()) {
//                    if (iCurserY > 0) {
//                        iCurserY--;
//                        rCurser.setY(iCurserMinY + (iCurserY * iColorHiegth));
//                        iCurserMoveDelay++;
//                    }
//                }
//                if (k.down()) {
//                    if (iCurserY < iColorRows - 1) {
//                        iCurserY++;
//                        rCurser.setY(iCurserMinY + (iCurserY * iColorHiegth));
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
//            if (iCurserY == 0) {
//                rCurser.setY(iCurserMinY);
//                rCurser.setX(iCurserMinX);
//                rCurser.setWidth(iColorWidth * iColorCol);
//            } else {
//                rCurser.setWidth(iColorWidth);
//                rCurser.setX(iCurserMinX + (iCurserX * iColorWidth));
//                
//            }
//            if (iCurserY == 0) {
//                rCurser.generateGrafics(cpColors.getColor(0));
//            } else {
//                int colorid = iCurserX + (iCurserY * iColorCol) - 7;
//                rCurser.generateGrafics(cpColors.getColor(colorid));
//                System.out.println("color Id :" + colorid);
//            }
//            rCurser.generateBorderGrafics(EngineColors.Grey);
//            
//            if(k.checkKey(KeyEvent.VK_SPACE))
//            {
//                this.iCurrentColorID = iCurserX + (iCurserY * iColorCol) - 7;   
//                se.setiMode(se.DRAW_MODE);
//            }
//            
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

    public int getCurrentColorID() {
        return iCurrentColorID;
    }

    public ColorPallete getColors() {
        return cpColors;
    }
    

}
