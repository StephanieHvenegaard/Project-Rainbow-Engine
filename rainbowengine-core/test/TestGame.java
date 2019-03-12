
import the_nights.rainbow_engine.core.Engine;
import the_nights.rainbow_engine.core.interfaces.IGame;
import the_nights.rainbow_engine.core.interfaces.IScreenBuffer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stephanie
 */
public class TestGame implements IGame{

    @Override
    public void update(Engine engine) {
    
    }

    @Override
    public void render(IScreenBuffer screenbuffer) {
    }

    @Override
    public void loadAssets() {
    
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getVersionNumber() {
return "";
    }

    @Override
    public void startGame(Engine engine) {
    
    }

    @Override
    public void resetGame(Engine engine) {
    
    }

    @Override
    public void pauseGame(Engine engine) {
    
    }

    @Override
    public void esc(Engine engine) {
    
    }

    @Override
    public void quitGame(Engine engine) {

    }

    @Override
    public int countGameObjects() {
        return 0;
    }
}
