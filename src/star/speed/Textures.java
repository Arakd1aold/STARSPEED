
package star.speed;

import java.awt.image.BufferedImage;


public class Textures {
    
    public BufferedImage player, missile, enemy, asteroid, asteroidFast, missileEnemy, bonus, life, enemyfast;
    private SpriteSheet ss;
    
    public Textures(Game game){
         ss = new SpriteSheet(game.getSpriteSheet());
        
        getTextures();
        
    }
    
    private void getTextures(){
        player = ss.grabImage(1, 1, 32, 32);
        missile = ss.grabImage(2, 1, 32, 32);
        enemy = ss.grabImage(3, 1, 32, 32);
        asteroid = ss.grabImage(4, 1, 32, 32);
        asteroidFast = ss.grabImage(3, 2, 32, 32);
        missileEnemy = ss.grabImage(4, 2, 32, 32);
        bonus = ss.grabImage(2, 2, 32, 32);
        life = ss.grabImage(1, 3, 32, 32);
        enemyfast = ss.grabImage(5, 1, 32, 32);

}
}
