/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeditor;

import mapeditor.guiobjects.SpriteDrawUI;
import the_nights.rainbow_engine.common.IGame;
import the_nights.rainbow_engine.common.IScreenBuffer;
import the_nights.rainbow_engine.core.Engine;

/**
 *
 * @author Stephanie
 */
public class MEEngine implements IGame {

    private SpriteDrawUI sdUI;

    @Override
    public void update(Engine engine) {

    }

    @Override
    public void render(IScreenBuffer isb) {
        sdUI.render(isb, 1, 1);}

    @Override
    public void loadAssets() {

    }

    @Override
    public void startGame() {
    
    }

    @Override
    public void pauseGame() {
    
    }

    @Override
    public void esc(Engine engine) {

    }

    @Override
    public void quitGame() {
    
    }

    @Override
    public int countGameObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
