package star.speed;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BulletEnemy extends GameObject implements EntityA {

    Random r = new Random();

    private Textures tex;
    private Game game;

    public BulletEnemy(double x, double y, Textures tex, Game game) {
        super(x, y);
        this.tex = tex;
        this.game = game;

    }

    public void tick() {

        y += 8;

        if (Physics.CollisionPlayer(this, game.p) && game.can_Die) {
            System.out.println("A CLASS ASTEROID & PLAYER" + r.nextInt());
          
            game.die();

        }
        
         if (Physics.CollisionPlayer(this, game.p) && !game.can_Die) {
            game.soundShieldHit.play();
            this.y -= 5;
            game.be.remove(this);
            game.createParticleShield((int)this.x, (int)this.y);

        }
        
        

        
    }

    public void render(Graphics g) {
        g.drawImage(tex.missileEnemy, (int) x, (int) y, null);
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int) x, (int) y, 32, 32);

    }
}
