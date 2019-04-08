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
package the_nights.rainbow_engine.core;

import the_nights.rainbow_engine.core.interfaces.IGame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import the_nights.rainbow_engine.core.interfaces.IScreenBuffer;
import the_nights.rainbow_engine.core.graphics.RainbowRectangle;
import the_nights.rainbow_engine.core.graphics.SplashScreen;
import the_nights.rainbow_engine.core.graphics.Text;
import the_nights.rainbow_engine.core.listner.KeyboardListner;
import the_nights.rainbow_engine.core.listner.MouseEventListner;
import the_nights.rainbow_engine.core.logging.RELogger;
import the_nights.rainbow_engine.core.settings.EngineSettings;

public class Engine extends JFrame implements Runnable {

    //Static 
    //public static final RLogger LOG = new RLogger();
    private static boolean _showDebugInfo = false;
    public static String ENGINE_VERSION = "0.1.15";
    
    private IGame game;
    //private String DefaultFont = "Arial";
    private final String DEFAULT_FONT = "Consolas";
 
    // double
    public static final double NANOSEC_TO_SEC = 1000000000.0;
    public static final double NANOSEC_TO_MILLISEC = 1000000.0;

    // long
    private long updateTime = 0;
    private long renderTime = 0;

    //Int    
    private int fps = 0;
    private int tickCounter = 0; // works as FPS Counter.

    private int fx_Counter;

    // Components
    private Canvas canvas = new Canvas();
    private EngineSettings engineSettings;
    private IScreenBuffer screenBuffer;
    private KeyboardListner keyboardListner = new KeyboardListner(this);
    private MouseEventListner mouseEventListner = new MouseEventListner(this);
    private RainbowRectangle debugRec;
   
    public Engine() {
        
        try {
            Image img = ImageIO.read(getClass().getResourceAsStream("/icon.png"));
              this.setIconImage(img);
            engineSettings = new EngineSettings();
          
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        RELogger.writelog("Starting engine version : "+ENGINE_VERSION, this);
        RELogger.writelog("Showing splashscreen", this);
        showSplashScreen();        
        RELogger.writelog("Loading settings", this);         
        RELogger.writelog("Initializing Engine", this);
        RELogger.writelog("Borderless              : " + engineSettings.borderless, this);
        RELogger.writelog("fullscreen              : " + engineSettings.fullscreen, this);
        RELogger.writelog("Resolution              : " + engineSettings.resolution.getName(), this);
//        RELogger.writelog("Palette                 : " + engineSettings.palette.getName(), this);
        
        
        
        //debug rectangle.
        debugRec = new RainbowRectangle(0, 0, 140, 50);
        debugRec.generateGrafics(0);
        //Make our program shutdown when we exit out.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add our graphics compoent      
        this.add(canvas);
        this.setUndecorated(engineSettings.borderless);
        //Make our frame visible.
        this.setVisible(true);
        //Create our object for buffer strategy.    
        this.canvas.createBufferStrategy(3);
        // add Listners
        canvas.addMouseListener(mouseEventListner);
        canvas.addMouseMotionListener(mouseEventListner);
        canvas.addKeyListener(keyboardListner);
        canvas.addFocusListener(keyboardListner);
        //get focus.
        canvas.requestFocus();
        this.setScreenSize(engineSettings.resolution.width,engineSettings.resolution.heigth);
        if (engineSettings.fullscreen) {
            this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
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
                BufferStrategy bufferStrategy = canvas.getBufferStrategy();
                Graphics graphics = bufferStrategy.getDrawGraphics();
                super.paint(graphics);
                game.render(screenBuffer);
                if (_showDebugInfo) {
                    screenBuffer.renderRectangle(debugRec);
                    screenBuffer.renderString(new Text(String.format("fps  : %7d", fps), DEFAULT_FONT, 12, 5, 11));
                    screenBuffer.renderString(new Text(String.format("upd  : %7d ns", updateTime), DEFAULT_FONT, 12, 5, 11 * 2));
                    screenBuffer.renderString(new Text(String.format("rndr : %7d ns", renderTime), DEFAULT_FONT, 12, 5, 11 * 3));
                    screenBuffer.renderString(new Text(String.format("gobj : %7d's", game.countGameObjects()), DEFAULT_FONT, 12, 5, 11 * 4));
                }
                screenBuffer.DrawView(graphics, canvas.getWidth(), canvas.getHeight());
                //Clean up;
                graphics.dispose();
                bufferStrategy.show();
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
        RELogger.writelog("Starting engine", this);
        game.loadAssets();
        Thread engineT = new Thread(this);
        engineT.start();
    }
    public void shutdownEngine() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void showSplashScreen() {
        SplashScreen ss = new SplashScreen();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
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
        return this.canvas.getWidth();
    }

    public int getScreenHeight() {
        return this.canvas.getHeight();
    }

    public Canvas getCanvas() {
        return canvas;
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
    public void setScreenSize(int screenWidth, int screenHeight) {
        // first set the screen size to se if there is any borders.
        this.setBounds(0, 0, screenWidth, screenHeight);
        this.setVisible(true);
        // calculates offset course by the winframe overlay and canvas
        int offsetW = screenWidth - this.canvas.getWidth();
        int offsetH = screenHeight - this.canvas.getHeight();
        // set the screen size to the new size with offsets.

        this.setBounds(0, 0, screenWidth + offsetW, screenHeight + offsetH);
        this.setMinimumSize(new Dimension(screenWidth + offsetW, screenHeight + offsetH));
        //Put our frame in the center of the screen.
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        this.screenBuffer = new CoreScreenbuffer(screenWidth, screenHeight);
//        this.screenBuffer.setPallete(engineSettings.palette);
    }

    public void setScreenBuffer(IScreenBuffer screenBuffer) {
        this.screenBuffer = screenBuffer;
    }

    public void setDesiredSpeed(int desiredSpeed) {
        this.engineSettings.desiredSpeed = desiredSpeed;
    }

//    public void setScreenWidth(int screenWidth) {
//        this.screenWidth = screenWidth;
//    }
//    public void setScreenHeight(int screenHeight) {
//        this.screenHeight = screenHeight;
//    }
    public void setGame(IGame game) {
        RELogger.writelog("setting game : " + game.getName() + " " + game.getVersionNumber(), this);
        this.setTitle("Rainbow Engine - "+game.getName() +" "+ game.getVersionNumber());
        this.game = game;
    }
}
