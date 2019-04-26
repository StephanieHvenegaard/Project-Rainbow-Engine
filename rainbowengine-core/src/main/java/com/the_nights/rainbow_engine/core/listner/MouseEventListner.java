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
package com.the_nights.rainbow_engine.core.listner;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.the_nights.rainbow_engine.core.Engine;
/**
 *
 * @author Stephanie
 */
public class MouseEventListner implements MouseListener,MouseMotionListener{
    private final Engine engine;
    public MouseEventListner(Engine engine)
    {
        this.engine = engine;
    }
    @Override        
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse was clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse was Pressed");
        //if(e.getButton() == MouseEvent.BUTTON1)
            //engine.leftClick(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse was Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse was Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse was Exited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    
    }
}
