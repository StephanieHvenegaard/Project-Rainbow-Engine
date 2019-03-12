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
package the_nights.rainbow_engine.core.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import the_nights.rainbow_engine.core.Engine;

/**
 *
 * @author Stephanie
 */
public class RELogger {

    private static DecimalFormat df2 = new DecimalFormat("[0000.00]");
    private static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
    private static final long startTime = System.nanoTime();
    private static Date dNow = new Date( );   
    
    public static void writelog(String Message, Object caller)
    {
        writelog(Message, caller.getClass().getSimpleName());
    }
    public static void writelog(String Message, String caller)
    {         
        Message = Message.toLowerCase();
        Message = Message.trim();
        long time = System.nanoTime() - startTime;
        String msg = df2.format(time / Engine.NANOSEC_TO_SEC) + " [ " + caller + " ] : " + Message;
        writeToLog(msg);       
        System.out.println(msg);        
    }
    private static void writeToLog(String msg) {
        try {            
            FileWriter fw = new FileWriter(ft.format(dNow)+"run.rlog", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(msg);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
