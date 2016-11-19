package star.speed;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class AsteroidFast extends GameObject {

    Random r = new Random();

    private int speed = r.nextInt(7) + 3;
    private Game game;
    private Textures tex;
    private int direction;

    public AsteroidFast(double x, double y, int direction, Textures tex, Game game) {
        super(x, y);
        this.direction = direction;
        this.tex = tex;
        this.game = game;
        direction = r.nextInt(2);

    }

    public void tick() {

        if (Physics.CollisionBullet(this, game.b)) {
            System.out.println("AF CLASS ASTEROIDFAST & BULLET COLLISION DETECTED" + r.nextInt());
            game.soundHit.play();
            game.b.remove();
               game. can_Shoot = true;

        }

        if (Physics.CollisionPlayer(this, game.p)&& game.can_Die) {
            System.out.println("A CLASS ASTEROID & PLAYER" + r.nextInt());
            game.soundEnemyDie.play();
            game.die();

        }
        
           if (Physics.CollisionPlayer(this, game.p) && !game.can_Die) {
            game.soundShieldHit.play();
            

        }
        

        y += 6;

        if (direction == 0) {
            x = 0;
        } else if (direction == 1) {
            x += speed;
        } else if (direction == 2) {
            x -= speed;
        }

        if (y > 480 * 2 + 32) {
            y = 0;
            x = r.nextInt(Game.WIDTH * Game.SCALE);
            direction = r.nextInt(2);

        }
        
           if (x > Game.WIDTH * Game.SCALE ) {
            
            direction = 2;
        }
        
        
           if ( x < 0 + 1) {
               

            direction = 1;
        }
           
           if ( x == Game.WIDTH * game.SCALE / 2 && y < game.HEIGHT * game.SCALE && y > 0){
               game.soundAsteroidFastFly.play();
           }
           
       

    }

    public void render(Graphics g) {
        g.drawImage(tex.asteroidFast, (int) x, (int) y, null);
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {

        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);

    }

}
