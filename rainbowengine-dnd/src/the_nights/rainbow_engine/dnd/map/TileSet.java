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
//package the_nights.rainbow_engine.dnd.map;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Scanner;
//import the_nights.rainbow_engine.graphics.Sprite;
//import the_nights.rainbow_engine.graphics.SpriteSheet;
///**
// *
// * @author Stephanie
// */
//public class TileSet {
//
//    SpriteSheet sheet;
//    ArrayList<BaseTile> tiles;
//
//    public TileSet(File tilesFile, SpriteSheet sheet) {
//        this.sheet = sheet;
//        this.tiles = new ArrayList<>();
//        try {
//            Scanner input = new Scanner(tilesFile);
//            while (input.hasNext()) {
//                String line = input.nextLine();
//                if (!line.startsWith("//")) {
//                    String[] parts = line.split("-");
//                    String tileName = parts[0];
//                    int SpriteX = Integer.parseInt(parts[1]);
//                    int SpriteY = Integer.parseInt(parts[2]);
//                    boolean colidable = false;
//                    if(parts.length == 4)
//                    {
//                        colidable= Boolean.parseBoolean(parts[3]);
//                    }
//                    BaseTile tile = new BaseTile(tileName, sheet.getSprite(SpriteX, SpriteY),colidable);
//                    tiles.add(tile);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    public BaseTile getBaseTile(int id)
//    {
//        if(id < tiles.size())
//        {
//            return tiles.get(id);
//        }
//        return null;
//    }
//
//    class BaseTile {
//        
//        String name;
//        Sprite sprite;
//        boolean colidable;
//
//        public BaseTile(String name, Sprite sprite,boolean colidable) {
//            this.name = name;
//            this.sprite = sprite;
//            this.colidable = colidable;
//        }
//    }
//}
