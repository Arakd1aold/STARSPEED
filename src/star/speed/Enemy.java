package star.speed;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {

    private int direction;

    Random r = new Random();
    private int speed = 3;

    private Textures tex;
    private Game game;
    private Controller c;
    private int fire;

    public Enemy(double x, double y, int direction, Textures tex, Game game, Controller c) {
        super(x, y);

        this.tex = tex;
        this.direction = direction;
        this.game = game;
        this.c = c;
        speed = r.nextInt(3) + 1;
        direction = r.nextInt(2);
    }

    public void tick() {
        
      

        fire = r.nextInt(Game.WIDTH * 2);

        if (Physics.CollisionBullet(this, game.b)) {
            System.out.println("E CLASS BULLET & ENEMY COLLISION DETECTED" + r.nextInt());
            game.soundEnemyDie.play();
            game.b.remove();
              game.createParticle((int)super.x + 16,(int)super.y + 16);
            System.out.println(x +  y );
                game. can_Shoot = true;
            game.setScore();
            

            game.e.remove(this);
            game.enemy_killed++;
            System.out.println("ENEMY KILLED COUNT " + game.getEnemy_killed());

        
        }

        switch (direction) {
            case 0:
                break;
            case 1:
                x += speed;
                break;
            case 2:
                x -= speed;
                break;
            default:
                break;
        }

        y += speed;

        if (y > 480 + 64) {
            y = 0 - 64;
            x = r.nextInt(Game.WIDTH * Game.SCALE);
            direction = r.nextInt(2);

        }

        if (x > Game.WIDTH * Game.SCALE ) {

            direction = 2;
        }
        
        
           if ( x < 0 + 1) {

            direction = 1;
        }
        
        

        if (y > 0 && x == fire || x == fire / 2) {
            c.addBulletEnemy(new BulletEnemy(this.x, this.y, tex, game));
            game.soundEnemyShoot.play();

        }

    }

    public void setY(double y) {

        this.y = y;
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemy, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);

    }
    
    public double getX(){
        return x;
    }
    
      public double getY(){
        return y;
    }

}
