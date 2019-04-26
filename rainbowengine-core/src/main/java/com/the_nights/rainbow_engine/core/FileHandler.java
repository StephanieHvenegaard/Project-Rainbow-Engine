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
package com.the_nights.rainbow_engine.core;

import com.the_nights.rainbow_engine.core.logging.RELogger;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Stephanie
 */
public class FileHandler {    
    public static BufferedImage loadImage(String path) {
        try {
            BufferedImage loaded = ImageIO.read(loadFile(path));
            BufferedImage formatted = new BufferedImage(loaded.getWidth(), loaded.getHeight(), BufferedImage.TYPE_INT_RGB);
            formatted.getGraphics().drawImage(loaded, 0, 0, null);
            return formatted;
        } catch (IOException e) {
            RELogger.writelog(e.getMessage(), "FileHandler");
            return null;
        }
    }
    public static File loadFile(String path) {
        File loaded = null;
        try {
            loaded = new File(path);
        } catch (Exception e) {
            RELogger.writelog(e.getMessage(), "FileHandler");
        }
        return loaded;
    }
}
