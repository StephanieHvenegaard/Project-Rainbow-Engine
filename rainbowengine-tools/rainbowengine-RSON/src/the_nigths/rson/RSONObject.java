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
package the_nigths.rson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Stephanie
 */
public class RSONObject {
    HashMap<String, String> elements = new HashMap<>();
    public static RSONObject parse(File file) throws FileNotFoundException, IllegalArgumentException {
        RSONObject robj = new RSONObject();
        int linecounter = 0;
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            String line = input.nextLine();
            linecounter++;
            if (line.startsWith("#")) // # is comment                
            {
                continue;
            } else if (line.isEmpty()) {
                continue;
            } else {
                String[] sp = line.split("#"); // end of line comments.
                sp = sp[0].split(":");
                if (sp.length != 2) {
                    throw new IllegalArgumentException("missing a ':' at line: " +linecounter);
                }
                if (sp[0].isEmpty()) {
                    throw new IllegalArgumentException("Missing element name at line: " +linecounter);
                }
                robj.put(sp[0], sp[1]);
            }
        }
        return robj;
    }
    
    public String get(String key) {
        return elements.get(key);
    }
    
    public void put(String key, String value) {
        elements.put(key, value);
    }
    
    public void save(String path) throws IOException
    {            
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for ( String key : elements.keySet() ) {    
            String value = elements.get(key);
            writer.write(key+":"+value+"\n");            
        }     
        writer.close();
    }
}
