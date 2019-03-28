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

import the_nights.players.graphics.SpriteSheet;
import the_nights.rainbow_engine.core.interfaces.IRender;
import the_nights.rainbow_engine.core.interfaces.IScreenBuffer;

/**
 *
 * @author Stephanie
 */
public class Labyrinth{ // implements IRender{

    private int size;
    private Room[][] maze = new Room[0][0];
    private boolean isLoop = false;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        maze = new Room[size][size];
    }

    public Room[][] getMaze() {
        return maze;
    }

    public void setMaze(Room[][] maze) {
        this.maze = maze;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    public void render(IScreenBuffer isb, SpriteSheet spritesheet) {
        // Render Lab.             
        for (int x = 0; x < this.getSize(); x++) {
            for (int y = 0; y < this.getSize(); y++) {
                Room room = this.getMaze()[x][y];
                byte exits = 0b0000;
                for (String exit : room.getExits()) {
                    if (exit.equals(Compass.N.direction)) {
                        exits += 0b0001;
                    }
                    if (exit.equals(Compass.S.direction)) {
                        exits += 0b0010;
                    }
                    if (exit.equals(Compass.W.direction)) {
                        exits += 0b0100;
                    }
                    if (exit.equals(Compass.E.direction)) {
                        exits += 0b1000;
                    }
                }
                int index = 0;
                switch (exits) {
                    case 0b0001:    // dead end North
                        index = 19;
                        break;
                    case 0b0010:    // dead end South
                        index = 17;
                        break;
                    case 0b0011:    // South-north
                        index = 2;
                        break;
                    case 0b0100:    // dead end West
                        index = 18;
                        break;
                    case 0b0101:    // West - North
                        index = 9;
                        break;
                    case 0b0110:    // West - south
                        index = 11;
                        break;
                    case 0b0111:    // West - South - North
                        index = 5;
                        break;
                    case 0b1000:    // deadend East
                        index = 16;
                        break;
                    case 0b1001:    // East - North
                        index = 8;
                        break;
                    case 0b1010:    // East - South
                        index = 10;
                        break;
                    case 0b1011:    // East - South - North
                        index = 6;
                        break;
                    case 0b1100:    // east - west
                        index = 0;
                        break;
                    case 0b1101:    // east west North
                        index = 7;
                        break;
                    case 0b1110:    // EAST WEST SOUTH
                        index = 4;
                        break;
                    case 0b1111:    // cross
                        index = 15;
                        break;
                }
                isb.renderRImage(spritesheet.getSprite(index).getImage(), x * 16, y * 16);
            }
        }
    }
}
