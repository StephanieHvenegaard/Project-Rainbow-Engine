
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.the_nights.rainbow_engine.core.graphics.palettes;

import com.the_nights.rainbow_engine.core.graphics.RainbowPalette;

/**
 *
 * @author Stephanie
 */
public final class C64Palette extends RainbowPalette {

    public C64Palette() {
        this.colors = new int[16];
        this.colors[0] = 0x000000;
        this.colors[1] = 0xFFF1E8;
        this.colors[2] = 0x1D2B53;
        this.colors[3] = 0x7E2553;
        this.colors[4] = 0x008751;
        this.colors[5] = 0xAB5236;
        this.colors[6] = 0x5F574F;
        this.colors[7] = 0xC2C3C7;
        this.colors[8] = 0xFF004D;
        this.colors[9] = 0xFFA300;
        this.colors[10] = 0xFFF024;
        this.colors[11] = 0x00E756;
        this.colors[12] = 0x29ADFF;
        this.colors[13] = 0x83769C;
        this.colors[14] = 0xFF77A8;
        this.colors[15] = 0xFFCCAA;
    }
}
