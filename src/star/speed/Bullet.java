package star.speed;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bullet extends GameObject implements EntityA {

    Random r = new Random();

    private Textures tex;
    private Game game;
    private Controller c;

    public Bullet(double x, double y, Textures tex, Game game, Controller c) {
        super(x, y);
        this.tex = tex;
        this.game = game;
        this.c = c;
      

    }

    public void tick() {

        if (Physics.CollisionEnemy(this, game.e)) {
            System.out.println("BULLET & ENEMY COLLISION DETECTED" + r.nextInt());
            game.soundEnemyDie.play();
            game.b.remove();
          
            
            game.can_Shoot = true;
            game.e.remove(this);

            game.setEnemy_killed(+1);
            game.setEnemy_Count(+1);
        }

        if (Physics.CollisionAsteroid(this, game.a)) {
            System.out.println("BULLET & ASTEROIDD" + r.nextInt());
            game.soundHit.play();
            game.b.remove();
            game.can_Shoot = true;

        }

        if (Physics.CollisionBonus(this, game.bo)) {
            System.out.println("bonus" + r.nextInt());

            game.b.remove();
            game.bo.remove();
            game.can_Shoot = true;
            if (game.powerUp < 4) {
                game.powerUp++;
            }

            game.soundPowerUp.play();

        }

        if (Physics.CollisionLife(this, game.li)) {
            System.out.println("bonus" + r.nextInt());

            game.b.remove();
            game.li.remove();
            game.can_Shoot = true;
            game.lives++;
            game.soundLife.play();

           

        }
        
         if (Physics.CollisionEnemyFast(this, game.ef)) {
          

        }

        y -= 10;

    }

    public void render(Graphics g) {
        g.drawImage(tex.missile, (int) x, (int) y, null);
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int) x, (int) y, 32, 32);

    }
    
    public double getX(){
        return x;
    }
}
