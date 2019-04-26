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
package com.the_nights.rainbow_engine.core.settings;

import com.the_nights.rainbow_engine.rson.RSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Stephanie
 */
public class EngineSettings {

    private String path = "enginesettings.rson";
    private RSONObject savefile;
    
    public int desiredSpeed = 30;
    public boolean fullscreen = false;
    public boolean borderless = false;
    public ScreenRessolution resolution = ScreenRessolution.CGA;    
//    public RainbowPalette palette = new RainbowPalette();

    public EngineSettings() throws IOException {
        try {
            savefile = RSONObject.parse(new File(path));
        } catch (FileNotFoundException ex) {
            savefile = new RSONObject();
            savefile.put("desiredSpeed", "30");
            savefile.put("fullscreen", "false");
            savefile.put("borderless", "false");
            savefile.put("resolution", ScreenRessolution.CGA.index +"");
//            savefile.put("palette",palette.getName());            
                savefile.save(path);
        }

        
        this.desiredSpeed = Integer.parseInt(savefile.get("desiredSpeed"));
        this.borderless = Boolean.parseBoolean(savefile.get("fullscreen"));
        this.borderless = Boolean.parseBoolean(savefile.get("borderless"));
        this.resolution = ScreenRessolution.fromId(Integer.parseInt(savefile.get("resolution")));
//        this.palette = RainbowPalette.LoadPallete("palette/"+savefile.get("palette")+RainbowPalette.EXTENSION);
    }

    public String get(String key) {
        return savefile.get(key);
    }

    public void put(String key, String value) {
        savefile.put(key, value);
    }
}
