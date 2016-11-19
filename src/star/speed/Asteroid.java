package star.speed;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Asteroid extends GameObject {

    Random r = new Random();

    private int speed = r.nextInt(7) + 3;
    private float direction;
    private Game game;
    private Textures tex;

    public Asteroid(double x, double y, int direction, Textures tex, Game game) {
        super(x, y);
        this.tex = tex;
        this.game = game;
        this.direction = direction;
    }

    public void tick() {

        if (direction == 0) {

        } else if (direction == 1) {
            x += 0.25;
        } else if (direction == 2) {
            x -= 0.25;
        }

        if (Physics.CollisionBullet(this, game.b)) {
            System.out.println("A CLASS ASTEROID & BULLET COLLISION DETECTED" + r.nextInt());
            game.soundAsteroidHit.play();
            game.b.remove();
            game.can_Shoot = true;

            y -= 15;
            x -= r.nextInt(15);
            x += r.nextInt(15);

        }

        if (Physics.CollisionPlayer(this, game.p) && game.can_Die) {
            System.out.println("A CLASS ASTEROID & PLAYER" + r.nextInt());

            game.die();

        }

        if (Physics.CollisionPlayer(this, game.p) && !game.can_Die) {
            game.soundShieldHit.play();
            game.a.remove(this);
             game.createParticleAsteroid((int)super.x + 16,(int)super.y + 16);

        }

        y += 2;

        if (y > 480 + 32) {
            y = 0;
            x = r.nextInt(Game.WIDTH * Game.SCALE);

        }

    }

    public void render(Graphics g) {
        g.drawImage(tex.asteroid, (int) x, (int) y, null);
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);

    }

}
