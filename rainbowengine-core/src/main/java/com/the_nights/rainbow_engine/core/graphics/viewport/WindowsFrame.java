/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.rainbow_engine.core.graphics.viewport;

import com.the_nights.rainbow_engine.core.Engine;
import com.the_nights.rainbow_engine.core.IGame;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Stephanie
 */
public class WindowsFrame extends JFrame implements IViewPort {

    Engine engine = new Engine();

    @Override
    public void setGame(IGame game) {
        engine.setGame(game);
    }

    @Override
    public void start() {
        engine.startEngine();
    }

    @Override
    public void shutdown() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
