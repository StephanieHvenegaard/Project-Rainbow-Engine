/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.rainbow_engine.core.graphics.viewport;

import com.the_nights.rainbow_engine.core.IGame;

/**
 *
 * @author Stephanie
 */
public interface IViewPort {

    public void setGame(IGame game);

    public void start();

    public void shutdown();
}
