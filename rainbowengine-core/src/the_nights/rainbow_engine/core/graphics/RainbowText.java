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
package the_nights.rainbow_engine.core.graphics;

import java.awt.Color;

/**
 *
 * @author Stephanie
 */
public class RainbowText {

    private String text;
    private int size;
    private String Font;
    private int xPosition;
    private int yPosition;
    private int color;                    // TO-DO, Change to pallette color.

    public RainbowText() {
        this.text = "";
        this.size = 12;
        this.Font = "Consolas";
        this.color = -1;
        this.xPosition = 0;
        this.yPosition = 0;
    }

    public String getText() {
        return text;
    }

    public int getSize() {
        return size;
    }

    public String getFont() {
        return Font;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getColor() {
        return color;
    }

    public RainbowText setText(String text) {
        this.text = text;
        return this;
    }

    public RainbowText setSize(int size) {
        this.size = size;
        return this;
    }

    public RainbowText setFont(String Font) {
        this.Font = Font;
        return this;
    }

    public RainbowText setxPosition(int xPosition) {
        this.xPosition = xPosition;
        return this;
    }

    public RainbowText setyPosition(int yPosition) {
        this.yPosition = yPosition;
        return this;
    }

    public RainbowText setColor(int color) {
        this.color = color;
        return this;
    }

}
