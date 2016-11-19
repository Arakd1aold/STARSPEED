
package star.speed;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter { //EXTENDS KEY ADAPTER DO SOMTHING EVERYTIME A KEY IS PRESSED 
    
    Game game;
    
    public KeyInput(Game game){
        this.game = game;//CALL KEYBOARD INPUT FROM THE GAME CLASS
        
    }
    
    public void keyPressed(KeyEvent e){
        game.keyPressed(e);//PASSES THE KEY EVENT TO THE MAIN GAME CLASS
    }
    
    public void keyReleased(KeyEvent e){
        game.keyReleased(e);//PASSES THE KEY EVENT TO THE MAIN GAME CLASS
    }
    
}
