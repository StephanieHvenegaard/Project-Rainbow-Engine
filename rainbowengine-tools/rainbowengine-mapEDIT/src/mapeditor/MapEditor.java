/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeditor;

import javax.swing.JFrame;
import the_nights.rainbow_engine.core.Engine;

/**
 *
 * @author Stephanie
 */
public class MapEditor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MEEngine game = new MEEngine();        
        Engine engine = new Engine(true);  
        //engine.setUndecorated(true);
        engine.setDesiredSpeed(30);
        engine.setScreenSize(360, 360);
        engine.setGame(game);  
        engine.getScreenBuffer().setRenderAlpha(false);
        engine.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        game.loadAssets();        
        Thread gameThread = new Thread(engine);
        gameThread.start();
    }
    
}
