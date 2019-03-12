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
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Scanner;
//import the_nights.rainbow_engine.core.graphics.IRender;
//import the_nights.rainbow_engine.core.graphics.IScreenBuffer;
//import the_nights.rainbow_engine.core.graphics.Rectangle;
//
///**
// *
// * @author Stephanie
// */
//public class Map implements IRender{
//
//    public static final String KEYWORD_FILL = "fill";
//    public static final String KEYWORD_SET = "set";
//    public static final String KEYWORD_SIZE = "size";
//    public static final String KEYWORD_COLL = "coll"; // futere profing.
//    public static final String KEYWORD_SEPERATOR = "-";
//
//    private TileSet tileset;
//
//    private int[] iaFillTileID = null;
//    private int iTilesize = 16;
//    private int mapsizeX = -1;
//    private int mapsizeY = -1;
//    private ArrayList<Tile> mapTiles = new ArrayList<>();
//    private Tile[] GenTiles;
//    private boolean redrawMap = true;
//
//    public Map(File mapFile, TileSet TileSet) {
//        this.tileset = TileSet;
//        try {
//            Scanner input = new Scanner(mapFile);
//            while (input.hasNext()) {
//                String line = input.nextLine();
//                if (!line.startsWith("//")) {
//                    String[] stringParts = line.split(":");
//                    if (stringParts[0].equalsIgnoreCase(KEYWORD_FILL)) {
//                        stringParts = stringParts[1].split(KEYWORD_SEPERATOR);
//                        iaFillTileID = new int[stringParts.length];
//                        for (int i = 0; i < iaFillTileID.length; i++) {
//                            iaFillTileID[i] = Integer.parseInt(stringParts[i]);
//                        }
//
//                    } else if (stringParts[0].equalsIgnoreCase(KEYWORD_SET)) {
//                        stringParts = stringParts[1].split(KEYWORD_SEPERATOR);
//
//                        if (stringParts.length == 3) {
//                            int id = Integer.parseInt(stringParts[0]);
//                            TileSet.BaseTile bt = tileset.getBaseTile(id);
//                            mapTiles.add(new Tile(bt.name,
//                                    bt.sprite,
//                                    Integer.parseInt(stringParts[1]),
//                                    Integer.parseInt(stringParts[2]),
//                                    bt.colidable));
//                        } else {
//                            System.out.println("map format changed at : \"" + line + "\" please update.");
//
//                        }
//                    } else if (stringParts[0].equalsIgnoreCase(KEYWORD_SIZE)) {
//                        stringParts = stringParts[1].split(KEYWORD_SEPERATOR);
//                        if (stringParts.length == 3) {
//                            this.mapsizeX = Integer.parseInt(stringParts[0]);
//                            this.mapsizeY = Integer.parseInt(stringParts[1]);
//                            this.iTilesize = Integer.parseInt(stringParts[2]);
//                            //System.out.println("TileSize : "+ tilesize);
//                        } else {
//                            System.out.println("map format changed at : \"" + line + "\" please update.");
//                        }
//                    }
//                }
//            }
//        } catch (FileNotFoundException | NumberFormatException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public void render(IScreenBuffer handler, int xZoom, int yZoom) {
//        int xIncriment = iTilesize * xZoom;
//        int yIncriment = iTilesize * yZoom;
//        if (redrawMap) {
//            if (iaFillTileID.length > 0) {
//                // static Render.
//                if (mapsizeX >= 0 && mapsizeY >= 0) {
//                    for (int x = 0; x < mapsizeX; x++) {
//                        for (int y = 0; y < mapsizeY; y++) {
//                            int r = (int) (Math.random() * iaFillTileID.length);
//                            int id = iaFillTileID[r];
//                            handler.renderSprite(tileset.getBaseTile(id).sprite, x * xIncriment, y * yIncriment, xZoom, yZoom, redrawMap);
//                        }
//                    }
//                } // fluit rendering 
////                else {
////                    Rectangle camera = handler.getCamara();
////                    for (int y = camera.getY() - yIncriment - (camera.getY() % yIncriment); y < camera.getY() + camera.getHeight(); y += yIncriment) {
////                        for (int x = camera.getX() - xIncriment - (camera.getX() % xIncriment); x < camera.getX() + camera.getWidth(); x += xIncriment) {
////                            int r = (int) (Math.random() * iaFillTileID.length);
////                            Tile t = mapTiles.get(r);
////                            handler.renderSprite(t.sprite, t.getX() * xIncriment, t.getY() * yIncriment, xZoom, yZoom, redrawMap);
////                        }
////                    }
////                }
//            }
//            for (int i = 0; i < mapTiles.size(); i++) {
//                Tile t = mapTiles.get(i);
//                handler.renderSprite(t.sprite, t.getX() * xIncriment, t.getY() * yIncriment, xZoom, yZoom, redrawMap);
//                //ssTiles.renderTile(r.id, handler, m.x * xIncriment, m.y * yIncriment, xZoom, yZoom);
//            }
//            redrawMap = false;
//        }
//    }
//    public boolean checkTileCollision(Rectangle other) {
//        for (Tile mt : mapTiles) {
//            Rectangle r = new Rectangle(mt.getX(), mt.getY(), iTilesize, iTilesize);
//            if (r.getX() > other.getX() && r.getX() < other.getX() + other.getWidth()) {
//                if (r.getY() > other.getY() && r.getY() < other.getY() + other.getHeight()) {
//                    if (r.Overlap(other)) {
//                        System.out.println("tile at X:" + other.getX() + ", Y:" + other.getY() + "Colidable == " + mt.isColiable());
//                        return mt.isColiable();
//                    }
//                }
//            }
//
//        }
//        //System.out.println("Cannot find tile at X:" + rec.getX() + ", Y:" + rec.getY());
//        return false;
//    }
//    public boolean checkTileCollision(int tileX, int tileY) {
////        for (Tile t : mapTiles) {
////            if ((t.getX() <= (tileX / iTilesize) && t.getX()) + iTilesize > (tileX / iTilesize))
////                    && (t.getY() <= (tileY / iTilesize) && t.getY() + iTilesize > (tileY / iTilesize))) {
////                System.out.println("tile at X:" + tileX + ", Y:" + tileY + "Colidable == " + t.collidable());
////                return t.collidable();
////            }
////        }
////        System.out.println("Cannot find tile at X:" + tileX + ", Y:" + tileY);
//        return false;
//    }
//    public void setTile(int tileX, int tileY, int tileId, boolean colidable) {
//        boolean foundTile = false;
//        for (int i = 0; i < mapTiles.size(); i++) {
//            Tile mt = mapTiles.get(i);
//            if (mt.getX() == tileX && mt.getY() == tileY) {
//                TileSet.BaseTile bt = tileset.getBaseTile(tileId);
//                mt.sprite = bt.sprite;
//                mt.name = bt.name;
//                mt.colliable = bt.colidable;
//                foundTile = true;
//                break;
//            }
//        }
//        if (!foundTile) {
//            TileSet.BaseTile bt = tileset.getBaseTile(tileId);
//            mapTiles.add(new Tile(bt.name, bt.sprite, tileX, tileY, bt.colidable));
//        }
//    }
//
//    public int getMapWidth() {
//        return mapsizeX;
//    }
//
//    public int getMapHeigth() {
//        return mapsizeY;
//    }
//}
