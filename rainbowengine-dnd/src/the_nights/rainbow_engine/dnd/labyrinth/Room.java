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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import the_nights.players.Actor;
import the_nights.rainbow_engine.dnd.Item;

/**
 *
 * @author Stephanie
 */
public class Room implements Serializable {
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Door> doors;
    //private Actor player; // Copy
    //private Actor monster; // Copy
    //private Actor ghost; // Copy
    private ArrayList<Item> lootList;
    private ArrayList<Actor> MonstersList;
    private ArrayList<Actor> PlayersList;
    //private int pictureID_3D;
    //private int pictureID_MiniMap;
    private boolean playerVissited;
    private boolean isExit; // one room is the exit of the maze

    /**
     * basic constructor for a room set the description, if the player has
     * visited to false because no the player has not yet, and sets the Hashmaps
     * of doors and exits.
     *
     * @param description the rooms decription
     */
    public Room(String description) {
        this.description = description;
        this.playerVissited = false;
        exits = new HashMap<>();
        doors = new HashMap<>();
        lootList = new ArrayList<>();
        MonstersList = new ArrayList<>();
        PlayersList = new ArrayList<>();
//        this.pictureID_3D = 0;
//        this.pictureID_MiniMap = 0;
    
    }

    /**
     * set the description, if the player has visited to false because no the
     * player has not yet, and sets the Hashmaps of doors and exits.
     *
     * takes an array of items to add to its loot list
     *
     * @param description the rooms decription
     * @param items the array of loot to add
     */
    public Room(String description, Item[] items) {
        this(description);
        Collections.addAll(lootList, items);
    }

    /**
     * set the description, if the player has visited to false because no the
     * player has not yet, and sets the Hashmaps of doors and exits and adds a
     * single item to the loot list.
     *
     * @param description the rooms decription
     * @param item item to add to loot list
     */
    public Room(String description, Item item) {
        this(description);
        lootList.add(item);
    }

    /**
     * set if the room is final exit.
     *
     * @param isExit true ?
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * get if the room is Exit
     *
     * @return true if the room is exit if not false.
     */
    public boolean isExit() {
        return isExit;
    }

//    /**
//     *
//     * @param pictureID_3D
//     */
//    public void setPictureID_3D(int pictureID_3D) {
//        this.pictureID_3D = pictureID_3D;
//    }
//
//    public void setPictureID_MiniMap(int pictureID_MiniMap) {
//        this.pictureID_MiniMap = pictureID_MiniMap;
//    }
//
//    public int getPictureID_3D() {
//        return pictureID_3D;
//    }
//
//    public int getPictureID_MiniMap() {
//        return pictureID_MiniMap;
//    }

    public boolean isPlayerVissited() {
        return playerVissited;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     *
     * Gets the description of the Room
     *
     * @return Description
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Gets a formated string with the description of the room and it's exists
     *
     * @return Long Decription of the room
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * a string with all the exits of the room
     *
     * @return Exists
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     *
     * @param direction you want to go in, North, South, West and east
     * @return Room at the Exit
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

//    public void setPlayer(Actor actor) {
//        player = actor;
//        playerVissited = true;
//    }
//
//    public void setMonster(Actor actor) {
//        monster = actor;
//    }
//
//    public Actor getPlayer() {
//        return player;
//    }
//
//    public Actor getMonster() {
//
//        return monster;
//    }
//
//    public boolean isConflict() {
//        return (player != null && monster != null);
//    }

    public boolean hasExit(String direction) {
        return exits.containsKey(direction);
    }

    public boolean hasAnyExits() {
        return !exits.isEmpty();
    }

//    public char getMonsterMapCode() {
//        if (isConflict()) {
//            return 'C';
//        } else if (this.player != null) {
//            return player.getMapCode();
//        } else if (this.monster != null) {
//            return monster.getMapCode();
//        } else {
//            return ' ';
//        }
//    }

    /**
     * gets the exits
     *
     * @return stirng array of exit directions.
     */
    public String[] getExits() {
        Set<String> keys = exits.keySet();
        int i = 0;
        String[] e = new String[keys.size()];
        for (String s : keys) {
            e[i] = s;
            i++;
        }
        return e;
    }

    /**
     * Changes the rooms description
     *
     * @param desc new string description.
     */
    public void changeDescription(String desc) {
        this.description = desc;
    }

    /**
     * does the room has any items
     *
     * @return true id lootlist size is greater then 0 - false if not.
     */
    public boolean hasItems() {
        return (lootList.size() > 0);
    }

    /**
     * get the loot list
     *
     * @return array of loot in the room.
     */
    public Item[] itemList() {
        Item itemList[] = new Item[lootList.size()];
        return lootList.toArray(itemList);
    }

//    /**
//     * pick up and item and places it in an inventory
//     *
//     * @param id id of item
//     * @param inventory inventory that the item is added to.
//     */
//    public void pickupItem(int id, Inventory inventory) {
//        Item returnItem = lootList.get(id);
//        if (inventory.addItem(returnItem)) {
//            lootList.remove(id);
//        }
//    }

    /**
     * adds item to the loot list
     *
     * @param item
     */
    public void dropItem(Item item) {
        lootList.add(item);
    }

    /**
     * adds a door in direction
     *
     * @param direction direction that the door is in
     */
    public void setDoor(String direction) {
        doors.put(direction, new Door(true));
    }

    public boolean hasDoor(String direction) {
        return doors.containsKey(direction);
    }

    public Door getDoor(String direction) {
        if (hasDoor(direction)) {
            return doors.get(direction);

        }
        return null;
    }

    public void setPlayerVisisted() {
        playerVissited = true;
    }
//
//    public void useItem(int id, Player p) {
//        if (itemList()[id].useItem(p)) {
//            lootList.remove(id);
//        }
//    }

//    public Actor getGhoust() {
//        return ghost;
//    }
//    public void setGhoust(Actor gaust) {
//        this.ghost = gaust;
//    }
//    public boolean isOccupied() {
//        boolean o = (ghost != null || monster != null);
//        return o;
//    }
}
