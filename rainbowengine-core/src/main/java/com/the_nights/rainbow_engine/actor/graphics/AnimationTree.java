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
package com.the_nights.rainbow_engine.actor.graphics;

/**
 *
 * @author Stephanie
 */
public class AnimationTree {

    public static final int SIZE = 8;
    public static final int RUN_LEFT = 0;
    public static final int ATTACK_LEFT = 1;
    public static final int RUN_UP = 2;
    public static final int RUN_RIGHT = 3;
    public static final int ATTACK_RIGHT = 4;
    public static final int RUN_DOWN = 5;
    public static final int SPAWN = 6;
    public static final int DIE = 7;
    private AnimatedSprite[] animTree;
    private int currentSprite = -1;

    public AnimationTree() {
        animTree = new AnimatedSprite[SIZE];
    }

    public AnimationTree(AnimatedSprite[] animtree) {
        this();
        for (int i = 0; i < animtree.length; i++) {
            if (i >= this.animTree.length) {
                break;
            }
            this.animTree[i] = animtree[i];
        }
    }

    public void addAnimSprite(int id, AnimatedSprite as) {
        if (id >= this.animTree.length) {
            return;
        }
        this.animTree[id] = as;
    }

    public AnimatedSprite getAnim() {
        if (currentSprite > this.animTree.length || currentSprite < 0) {
            return null;
        }
        return this.animTree[currentSprite];
    }

    public void setcurrentID(int id) {
        if (id >= this.animTree.length) {
            currentSprite = id;
        }
    }
}
