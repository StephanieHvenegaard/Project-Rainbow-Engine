/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spriteseditor;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import spriteseditor.guiobjects.ColorPalleteUI;
import spriteseditor.guiobjects.SpriteDrawUI;
import the_nights.rainbow_engine.common.IGame;
import the_nights.rainbow_engine.common.IScreenBuffer;
import the_nights.rainbow_engine.core.Engine;
import the_nights.rainbow_engine.core.EngineColors;
import the_nights.rainbow_engine.core.KeyboardListner;
import the_nights.rainbow_engine.graphics.ColorPallete;
import the_nights.rainbow_engine.graphics.Text;

/**
 *
 * @author Stephanie
 */
public class SEEngine implements IGame {

    // Constants 
    public final int DRAW_MODE = 0;
    public final int COLOR_MODE = 1;
    public final int PALLETE_MODE = 2;
    // Boolean
    boolean bHasChanges = false;
    //boolean 
    // Texts
    Text tColorMode = new Text("C", 10, 8);
    Text tDrawMode = new Text("D", 20, 8);
    Text tPalleteMode = new Text("P", 30, 8);
    //Text tDrawMode = new Text("D", 20, 8);
    Text tSpriteHeight = new Text("Sprite Height", Color.DARK_GRAY, 180, 18);
    Text tSpriteWidth = new Text("Sprite Width", Color.DARK_GRAY, 180, 28);
    Text tSpriteHeightValue = new Text("16", Color.DARK_GRAY, 280, 18);
    Text tSpriteWidthValue = new Text("16", Color.DARK_GRAY, 280, 28);
    //int 
    int iMode;
    int iSpriteHeight = 16;
    int iSpriteWidth = 16;
    int iDrawZoomX = 0;
    int iDrawZoomY = 0;

    //int arrays
    int[] iaSpritePixels;
    int[] iaSpriteSheetPixels;

    // Components
    ColorPallete cpPallete = new ColorPallete();
    SpriteDrawUI sdUI = new SpriteDrawUI(10, 10, 162, 162, 16, 16);
    ColorPalleteUI cpUI = new ColorPalleteUI(cpPallete, 10, 10 + sdUI.getHeight() + 10);

    public int getMode() {
        return iMode;
    }

    public void setiMode(int iMode) {
        this.iMode = iMode;
    }

    public int getColor() {
        int id = cpUI.getCurrentColorID();
        return cpUI.getColors().getColor(id);
    }
    public int getColor(int id) {        
        return cpUI.getColors().getColor(id);
    }

    public int getColorID() {
        return cpUI.getCurrentColorID();
    }

    @Override
    public void update(Engine engine) {
        KeyboardListner k = engine.getKeyboardListner();
        if (k.checkKey(KeyEvent.VK_CONTROL)) {
            if (k.checkKey(KeyEvent.VK_D)) {
                this.iMode = DRAW_MODE;
            }
            if (k.checkKey(KeyEvent.VK_C)) {
                this.iMode = COLOR_MODE;
            }
            if (k.checkKey(KeyEvent.VK_P)) {
                this.iMode = PALLETE_MODE;
            }
        }
        // Update Modes
        if (iMode == COLOR_MODE) {
            tColorMode.setColor(Color.gray);
        } else {
            tColorMode.setColor(Color.DARK_GRAY);
        }
        if (iMode == DRAW_MODE) {
            tDrawMode.setColor(Color.gray);
        } else {
            tDrawMode.setColor(Color.DARK_GRAY);
        }
        if (iMode == PALLETE_MODE) {
            tPalleteMode.setColor(Color.gray);
        } else {
            tPalleteMode.setColor(Color.DARK_GRAY);
        }
        cpUI.update(engine);
        sdUI.update(engine);
    }

    @Override
    public void render(IScreenBuffer screenbuffer) {
        screenbuffer.clear(false);

        cpUI.render(screenbuffer, 1, 1);
        sdUI.render(screenbuffer, 1, 1);

        // text modes.
        screenbuffer.renderString(tDrawMode, false);
        screenbuffer.renderString(tColorMode, false);
        screenbuffer.renderString(tPalleteMode, false);
        // text setting.
        screenbuffer.renderString(tSpriteHeight, false);
        screenbuffer.renderString(tSpriteWidth, false);
        screenbuffer.renderString(tSpriteHeightValue, false);
        screenbuffer.renderString(tSpriteWidthValue, false);
    }

    @Override
    public void loadAssets() {
        // load pallete

        // calulations
        iDrawZoomX = this.sdUI.getWidth() / iSpriteWidth;
        iDrawZoomY = this.sdUI.getWidth() / iSpriteHeight;
        // Rectangles Borders.
        // SpritePixels
        iaSpritePixels = new int[iSpriteHeight * iSpriteWidth];
        for (int i = 0; i < iaSpritePixels.length; i++) {
            iaSpritePixels[i] = EngineColors.AlphaARGB;
        }
    }

    @Override
    public void startGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public int countGameObjects() {
        return 0;
    }

    @Override
    public void esc(Engine engine) {
        engine.dispatchEvent(new WindowEvent(engine, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void quitGame() {
    
    }
}
