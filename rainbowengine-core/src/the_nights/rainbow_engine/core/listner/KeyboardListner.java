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
package the_nights.rainbow_engine.core.listner;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import the_nights.rainbow_engine.core.Engine;

/**
 *
 * @author Stephanie
 */
public class KeyboardListner implements KeyListener, FocusListener {

    private boolean[] keys = new boolean[210];
    private Engine engine;

    public KeyboardListner(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("key : " + e.getKeyChar() + " with code : " + e.getKeyCode() + " was pressed.");
        int keyCode = e.getKeyCode();
        if (keyCode < keys.length) {
            keys[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("key : " + e.getKeyChar() + " with code : " + e.getKeyCode() + " was released.");
        int keyCode = e.getKeyCode();
        if (keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
    }

    public boolean checkKey(int key) {
        return keys[key];
    }

    public boolean anyKey() {
        for (boolean b : keys) {
            if (b) {
                return true;
            }
        }
        return false;
    }

    public boolean enter() {
        return keys[KeyEvent.VK_ENTER];
    }

    public boolean esc() {
        return keys[KeyEvent.VK_ESCAPE];
    }

    public boolean up() {
        return keys[KeyEvent.VK_UP];
    }

    public boolean down() {
        return keys[KeyEvent.VK_DOWN];
    }

    public boolean left() {
        return keys[KeyEvent.VK_LEFT];
    }

    public boolean right() {
        return keys[KeyEvent.VK_RIGHT];
    }

    public boolean w() {
        return keys[KeyEvent.VK_W];
    }

    public boolean s() {
        return keys[KeyEvent.VK_S];
    }

    public boolean a() {
        return keys[KeyEvent.VK_A];
    }

    public boolean d() {
        return keys[KeyEvent.VK_D];
    }

}
