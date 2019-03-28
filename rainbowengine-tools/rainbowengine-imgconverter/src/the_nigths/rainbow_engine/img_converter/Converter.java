/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_nigths.rainbow_engine.img_converter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Stephanie
 */
public class Converter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ImageConver converter = new ImageConver();
        try {
            converter.Start();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
