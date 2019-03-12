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
package the_nights.rainbow_engine.dnd.labyrinth;

/**
 *
 * @author Stephanie
 */
public enum Compass {
    N("north", 0, -1), S("south", 0, 1), E("east", 1, 0), W("west", -1, 0);
    public final String direction; //representation of direction. 
    public final int dx;  // Direction in the array out of the x axis 
    public final int dy;  // Direction in the array out of the y axis
    public Compass opposite;  // deklares the opposite direction for ref. 
    public Compass left;
    public Compass right;
    // use the static initializer to resolve forward references
    static {
        N.opposite = S;
        S.opposite = N;
        E.opposite = W;
        W.opposite = E;
        N.left = W;
        S.left = E;
        E.left = N;
        W.left = S;
        N.right = E;
        S.right = W;
        E.right = S;
        W.right = N;
    }
    private Compass(String dir, int dx, int dy) {
        this.direction = dir;
        this.dx = dx;
        this.dy = dy;
    }
};

