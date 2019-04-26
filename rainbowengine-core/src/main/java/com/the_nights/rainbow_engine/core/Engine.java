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
package com.the_nights.rainbow_engine.core;

import com.the_nights.rainbow_engine.core.graphics.RainbowRectangle;
import com.the_nights.rainbow_engine.core.graphics.RainbowText;
import com.the_nights.rainbow_engine.core.graphics.Screenbuffer;
import com.the_nights.rainbow_engine.core.graphics.SplashScreen;
import com.the_nights.rainbow_engine.core.interfaces.IGame;
import com.the_nights.rainbow_engine.core.interfaces.IScreenBuffer;
import com.the_nights.rainbow_engine.core.listner.KeyboardListner;
import com.the_nights.rainbow_engine.core.listner.MouseEventListner;
import com.the_nights.rainbow_engine.core.logging.RELogger;
import com.the_nights.rainbow_engine.core.settings.EngineSettings;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Engine implements Runnable {

    //--------------------------------------------------------------------------
    //Static 
    private boolean _showDebugInfo = false;
    public static final String ENGINE_VERSION = "0.1.15";
    public static final String DEFAULT_FONT = "Consolas";
    //--------------------------------------------------------------------------
    private IGame game;
    //--------------------------------------------------------------------------
    // double
    public static final double NANOSEC_TO_SEC = 1000000000.0;
    public static final double NANOSEC_TO_MILLISEC = 1000000.0;
    //--------------------------------------------------------------------------
    // long
    private long updateTime = 0;
    private long renderTime = 0;
    //--------------------------------------------------------------------------
    //Int    
    private int fps = 0;
    private int tickCounter = 0; // works as FPS Counter.
    private int fx_Counter;
    //--------------------------------------------------------------------------
    // Components  
    private EngineSettings engineSettings;
    private IScreenBuffer screenBuffer;
    private final KeyboardListner keyboardListner = new KeyboardListner(this);
    private final MouseEventListner mouseEventListner = new MouseEventListner(this);
    private final RainbowRectangle debugRec;
    private JFrame frame;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------
    public Engine() {
        //debug rectangle.
        debugRec = new RainbowRectangle(0, 0, 140, 50);
        debugRec.generateGrafics(0);
        screenBuffer = new Screenbuffer(this);
    }

    //--------------------------------------------------------------------------
    // Engine Loop
    //--------------------------------------------------------------------------
    public void update() {
        if (this.game != null) {
            game.update(this);
        }
        if (keyboardListner.checkKey(KeyEvent.VK_ESCAPE)) {
            game.esc(this);
        }
        //SHOW DEBUG.
        if (fx_Counter == 0) {
            if (keyboardListner.checkKey(KeyEvent.VK_F1)) {
                if (_showDebugInfo) {
                    _showDebugInfo = false;
                } else {
                    _showDebugInfo = true;
                }
                fx_Counter++;
            }
        }
        if (fx_Counter > 0) {
            fx_Counter++;
        }
        if (fx_Counter > 2 * engineSettings.desiredSpeed) {
            fx_Counter = 0;
        }
    }

    public void render() {
        if (screenBuffer != null) {
            if (this.game != null) {
                screenBuffer.clear();
                frame.paint(screenBuffer.getGraphics());
                game.render(screenBuffer);
                if (_showDebugInfo) {
                    screenBuffer.renderRectangle(debugRec);
                    screenBuffer.renderString(new RainbowText()
                            .setText(String.format("fps  : %7d", fps)).setFont(DEFAULT_FONT)
                            .setColor(1)
                            .setxPosition(5)
                            .setyPosition(11));
                    screenBuffer.renderString(new RainbowText()
                            .setText(String.format("upd  : %7d ns", updateTime))
                            .setColor(1)
                            .setxPosition(5)
                            .setyPosition(11 * 2));
                    screenBuffer.renderString(new RainbowText()
                            .setText(String.format("rndr : %7d ns", renderTime))
                            .setColor(1)
                            .setxPosition(5)
                            .setyPosition(11 * 3));
                    screenBuffer.renderString(new RainbowText()
                            .setText(String.format("gobj : %7d's", game.countGameObjects()))
                            .setColor(1)
                            .setxPosition(5)
                            .setyPosition(11 * 4));
                }
                screenBuffer.DrawView();
                //Clean up;
                screenBuffer.DisposeGraphics();
            }
        }
    }

    @Override
    public void run() {
        if (game != null) {
            game.startGame(this);
            long lastTime = System.nanoTime(); //long 2^63
            long averageTickTime = (long) NANOSEC_TO_SEC / engineSettings.desiredSpeed;
            double changeInSeconds = 0;
            while (true) {
                long tick = System.nanoTime();
                long now = System.nanoTime();
                update();
                long ut = System.nanoTime() - now;
                now = System.nanoTime();
                render();
                long rt = System.nanoTime() - now;
                now = System.nanoTime();
                changeInSeconds += ((now - lastTime) / NANOSEC_TO_SEC);
                if (changeInSeconds >= 1) {
                    changeInSeconds = 0.0;
                    this.fps = tickCounter;
                    this.tickCounter = 0;
                    this.updateTime = ut;
                    this.renderTime = rt;
                }
                long ticktime = System.nanoTime() - tick;
                if (ticktime > averageTickTime) {
                    System.out.println("tick is taking to long");
                }
                try {
                    long tickDiff = averageTickTime - ticktime;
                    long sleeptime = (long) (tickDiff / this.NANOSEC_TO_MILLISEC);
                    if (sleeptime > 0) {
                        //System.out.println("Sleep for " + sleeptime + " ms");
                        Thread.sleep(sleeptime);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Main Thread got a interrubt : \r\n" + e.getMessage());
                }
                lastTime = tick;
                tickCounter++;
                //System.out.println("ticks : " + tickCounter);
            }
        } else {
            System.out.println("cant start a game with no game class defined.");
        }
    }

    //--------------------------------------------------------------------------
    // methods
    //--------------------------------------------------------------------------
    public void startEngine() {
        if (game != null) {
            frame = new JFrame("Rainbow Engine - " + game.getName() + " " + game.getVersionNumber());
            try {
                Image img = ImageIO.read(getClass().getResourceAsStream("/icon.png"));
                this.frame.setIconImage(img);
                engineSettings = new EngineSettings();
            } catch (IOException ex) {
                RELogger.writelog(ex.getMessage(), this);
            }
            RELogger.writelog("Starting engine version : " + ENGINE_VERSION, this);
            //RELogger.writelog("Showing splashscreen", this);
            //showSplashScreen();
            this.loadSettings();
            game.loadAssets();
            //get focus.
            screenBuffer.getCanvas().requestFocus();
            Thread engineT = new Thread(this);
            engineT.start();
        }
    }

    private void loadSettings() {
        //Make our program shutdown when we exit out.
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add our graphics compoent      
        this.frame.add(screenBuffer.getCanvas());
        RELogger.writelog("Loading engine settings", this);
        RELogger.writelog("Borderless              : " + engineSettings.borderless, this);
        this.frame.setUndecorated(engineSettings.borderless);
        RELogger.writelog("fullscreen              : " + engineSettings.fullscreen, this);
        if (engineSettings.fullscreen) {
            this.frame.setExtendedState(this.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
        RELogger.writelog("Resolution              : " + engineSettings.resolution.getName(), this);
        this.setRenderSize(engineSettings.resolution.width, engineSettings.resolution.heigth);
        //Make our frame visible.
        this.frame.setVisible(true);
    }

    public void shutdownEngine() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void showSplashScreen() {
        SplashScreen ss = new SplashScreen();
        try {
            Thread.sleep(1200);
        } catch (InterruptedException ex) {
            RELogger.writelog(ex.getMessage(), ss);
        }
        ss.dispatchEvent(new WindowEvent(ss, WindowEvent.WINDOW_CLOSING));
    }

    //--------------------------------------------------------------------------
    // Getters
    //--------------------------------------------------------------------------
    public int getDesiredSpeed() {
        return engineSettings.desiredSpeed;
    }

    public int getScreenWidth() {
        return this.screenBuffer.getCanvas().getWidth();
    }

    public int getScreenHeight() {
        return this.screenBuffer.getCanvas().getHeight();
    }

    public KeyboardListner getKeyboardListner() {
        return keyboardListner;
    }

    public MouseEventListner getMouseEventListner() {
        return mouseEventListner;
    }

    public IScreenBuffer getScreenBuffer() {
        return screenBuffer;
    }

    public IGame getGame() {
        return game;
    }

    //--------------------------------------------------------------------------
    // Setters
    //--------------------------------------------------------------------------
    public void setRenderSize(int screenWidth, int screenHeight) {
        // first set the screen size to se if there is any borders.
        this.frame.setBounds(0, 0, screenWidth, screenHeight);
        this.frame.setVisible(true);
        // calculates offset course by the winframe overlay and canvas
        int offsetW = screenWidth - this.screenBuffer.getCanvas().getWidth();
        int offsetH = screenHeight - this.screenBuffer.getCanvas().getHeight();
        // set the screen size to the new size with offsets.

        this.frame.setBounds(0, 0, screenWidth + offsetW, screenHeight + offsetH);
        this.frame.setMinimumSize(new Dimension(screenWidth + offsetW, screenHeight + offsetH));
        //Put our frame in the center of the screen.
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        this.screenBuffer.setHeight(screenHeight);
        this.screenBuffer.setWidth(screenWidth);
//        this.screenBuffer = new CoreScreenbuffer(screenWidth, screenHeight);
//        this.screenBuffer.setPallete(engineSettings.palette);
    }

    public void setScreenBuffer(IScreenBuffer screenBuffer) {
        this.screenBuffer = screenBuffer;
        //this.setRenderSize(engineSettings.resolution.width, engineSettings.resolution.heigth);
    }

    public void setDesiredSpeed(int desiredSpeed) {
        this.engineSettings.desiredSpeed = desiredSpeed;
    }

    public void setGame(IGame game) {
        RELogger.writelog("setting game : " + game.getName() + " " + game.getVersionNumber(), this);
        this.game = game;
    }
}
