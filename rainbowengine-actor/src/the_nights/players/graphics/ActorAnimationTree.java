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
package the_nights.players.graphics;

import the_nights.players.graphics.AnimatedSprite;

/**
 *
 * @author Stephanie
 */
public class ActorAnimationTree {
    public final int SIZE = 8;
    public final int RUN_LEFT = 0;
    public final int ATTACK_LEFT = 1;
    public final int RUN_UP = 2;
    public final int RUN_RIGHT = 3;
    public final int ATTACK_RIGHT = 4;
    public final int RUN_DOWN = 5;
    public final int SPAWN = 6;
    public final int DIE = 7;
    AnimatedSprite[] animTree;
    public ActorAnimationTree()
    {
        animTree=new AnimatedSprite[SIZE];
    }
    public ActorAnimationTree(AnimatedSprite[] animtree)
    {
        this();
        for(int i=0;i<animtree.length;i++)
        {
            if(i>=this.animTree.length)break;
            this.animTree[i] = animtree[i];
        }
    }
    public void addAnimSprite(int id, AnimatedSprite as)
    {
        if(id>=this.animTree.length)return;
        this.animTree[id] =as;
    }
    public AnimatedSprite getAnim(int id)
    {
        if(id>=this.animTree.length)return null;
        return this.animTree[id];
    }            
}
