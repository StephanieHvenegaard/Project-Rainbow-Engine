
import the_nights.rainbow_engine.core.Engine;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stephanie
 */
public class Main {
    public static void main(String[] args) {
        TestGame tg = new TestGame();
        Engine engine = new Engine();
        engine.setGame(tg);
        engine.startEngine();
    }
}
