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

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Stephanie
 */
public class LabyrinthGenerator {
    private static Labyrinth lab;
    public static Labyrinth Generate(int size) {
        lab = new Labyrinth(); 
        lab.setSize(size);
        Room room = (lab.getMaze()[0][0] == null) ? new Room("") : lab.getMaze()[0][0];        
        room.setIsExit(true);
        Compass dir = Compass.S;        
        // Add Exit from Current Room        
        int nextX = dir.dx;      // gets X for the next Room 
        int nextY = dir.dy;      // gets Y for the nex Room
        Room nextroom = (lab.getMaze()[nextX][nextY] == null)? new Room("") : lab.getMaze()[nextX][nextY];   
        room.setExit(dir.direction,nextroom);
        // Add oppesite exit to next Room to bind them together
        nextroom.setExit(dir.opposite.direction, room);
        // goes to next Cell and start the proces over again untill all rooms is full.
        lab.getMaze()[0][0] = room;
        lab.getMaze()[nextX][nextY] = nextroom;
        generateRoom(nextX, nextY);
        return lab;
    }

    private static void generateRoom(int currentX, int currentY) {        
        Compass[] dirs = Compass.values(); // gets all 4 enums for direction
        Collections.shuffle(Arrays.asList(dirs)); // mixes them up to get a reandom direction.
        for (Compass compass : dirs) // loops through each posible direction
        {                
            int nextX = currentX + compass.dx;      // gets X for the next Room 
            int nextY = currentY + compass.dy;      // gets Y for the nex Room
            if (nextX == 0 && nextY == 0) {
                continue; //checks if Spawn room if true skip.
            }            
            if (isInsideMaze(nextX, lab.getSize()) // checks if x is indside maze.
             && isInsideMaze(nextY, lab.getSize())) // checks if y is indside maze.
            {
                Room room = (lab.getMaze()[currentX][currentY] == null) ? new Room("") : lab.getMaze()[currentX][currentY];  
                Room nextroom = (lab.getMaze()[nextX][nextY] == null)? new Room("") : lab.getMaze()[nextX][nextY];  
                // checks if room has no exits 
                if (!nextroom.hasAnyExits()) {
                    // Add Exit to Current Room
                    room.setExit(compass.direction, nextroom);
                    // Add oppesite exit to next Room to bind them together
                   nextroom.setExit(compass.opposite.direction, room);
                    // goes to next Cell and start the proces over again untill all rooms is full.
                    lab.getMaze()[currentX][currentY] = room;
                    lab.getMaze()[nextX][nextY] = nextroom;
                    generateRoom(nextX, nextY);
                } else {
                    // we have reached a room with an exit, checks if we should punch a hole 
                    // before backtracking to make sure the map is a loop
                    if (!nextroom.hasExit(compass.opposite.direction) // is there allready a exit to this room
                            && (!lab.isLoop() || ((int) (Math.random() * 20) == 5))) // force or random
                    {
                        // Add Exit to Current Room
                        room.setExit(compass.direction, nextroom);
                        // Add oppesite exit to next Room to bind them together
                        nextroom.setExit(compass.opposite.direction,room);
                        // the maze is now a loop
                        lab.setLoop(true);   // no need to force no more   
                        lab.getMaze()[currentX][currentY] = room;
                        lab.getMaze()[nextX][nextY] = nextroom;
                    }
                }
            }
        }
    }
    /*
     * Checks if the next room is inside the maze.
     */
    private static boolean isInsideMaze(int v, int upper) {
        return (v >= 0) && (v < upper);
    }
}
