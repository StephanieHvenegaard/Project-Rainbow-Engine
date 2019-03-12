/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the_nights.rainbow_engine.core.graphics.pallates;

/**
 *
 * @author Stephanie
 */
public abstract class BasePalette implements IColorPalette {
    public static int ALPHA_RGB = 0xFF00DD;  
    protected int[] colors;   
    @Override
    public int getColor(int id) {
        if(id == -1)
            return ALPHA_RGB;
        return colors[id];
    }    
    @Override
    public int getPalleteSize() {
        return colors.length;
    }
}
