package star.speed;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyFast extends GameObject implements EntityB {

    private int direction;

    Random r = new Random();
    private int speed = 4;

    private Textures tex;
    private Game game;
    private Controller c;
    private int fire;
    public int health = 30;

    public EnemyFast(double x, double y, Textures tex, Game game, Controller c) {
        super(x, y);

        this.tex = tex;
        this.direction = direction;
        this.game = game;
        this.c = c;
        speed = 3;
        direction = r.nextInt(1);
    }

    public void tick() {
        
      

        fire = r.nextInt(Game.WIDTH * 2);

        if (Physics.CollisionBullet(this, game.b)) {
            
           
            game.b.remove();
                game. can_Shoot = true;
           
             
                 game.createParticle((int)super.x + 16,(int)super.y + 16);
            game.b.remove(this);
            if (this.health > 0){
                this.health -= 10;
                System.out.println("HIT" + this.health);
                game.soundEnemyFastHit.play();
            }
            
            if(this.health == 0){
                 game.soundEnemyDie.play();
                game.ef.remove();
                 game.setScore();

          
            game.enemy_killed++;
                
            }

        
        }

        switch (direction) {
       
            case 0:
                x += speed;
                break;
            case 1:
                x -= speed;
                break;
            default:
                break;
        }

        //y += speed;

        if (y > 480 + 64) {
            y = 0 - 64;
            x = r.nextInt(Game.WIDTH * Game.SCALE);
            direction = r.nextInt(1);

        }

        if (x > Game.WIDTH * Game.SCALE ) {

            direction = 1;
        }
        
        
           if ( x < 0 + 1) {

            direction = 0;
        }
        
        

        if ( x % 64 == 0){
            c.addBulletEnemy(new BulletEnemy(this.x -16, this.y, tex, game));
            c.addBulletEnemy(new BulletEnemy(this.x +16, this.y, tex, game));
           game.soundEnemyFastShoot.play();
           
               


        }

    }

    public void setY(double y) {

        this.y = y;
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemyfast, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);

    }
    
   

    public double getY() {
        return y;
    }
    
      public double getX() {
        return x;
    }
      
      public int getHealth(){
          return health;
      }

}
