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
package the_nights.rainbow_engine.core.settings;

/**
 *
 * @author Stephanie
 */
public enum ScreenRessolution {
    CGA(0, 320, 200), VGA(1, 640, 480), HD720(2, 1280, 720);
    //public final String res; //representation of direction. 
    public final int index;
    public final int width;
    public final int heigth;

    private ScreenRessolution(int index, int width, int heigth) {
        //this.res = ressolution;
        this.index = index;
        this.width = width;
        this.heigth = heigth;
    }

    public static ScreenRessolution fromId(int id) {
        for (ScreenRessolution res : values()) {
            if (res.index == id) {
                return res;
            }
        }
        return null;
    }
    public String getName()
    {
        return width +"x"+heigth;
    }
};
