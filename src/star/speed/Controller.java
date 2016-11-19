package star.speed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.paint.Color.color;

public class Controller {

    private LinkedList<EntityA> ea = new LinkedList<EntityA>();
    private LinkedList<EntityB> eb = new LinkedList<EntityB>();
    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    private LinkedList<Enemy> e = new LinkedList<Enemy>();
    private LinkedList<Star> s = new LinkedList<Star>();
    private LinkedList<Asteroid> a = new LinkedList<Asteroid>();
    private LinkedList<AsteroidFast> af = new LinkedList<AsteroidFast>();
    private LinkedList<BulletEnemy> be = new LinkedList<BulletEnemy>();
    private LinkedList<Bonus> bo = new LinkedList<Bonus>();
    private LinkedList<Life> li = new LinkedList<Life>();
    private LinkedList<EnemyFast> ef = new LinkedList<EnemyFast>();
    private LinkedList<Particle> pa = new LinkedList<Particle>();
    EntityA enta;
    EntityB entb;

    Random r = new Random();

    private int direction;

    Bullet TempBullet;
    Enemy TempEnemy;
    Star TempStar;
    Asteroid TempAsteroid;
    AsteroidFast TempAsteroidFast;
    BulletEnemy TempBulletEnemy;
    Bonus TempBonus;
    Life TempLife;
    EnemyFast TempEnemyFast;
    Particle TempParticle;

    Game game;
    Textures tex;

    public Controller(Game game, Textures tex) {
        this.game = game;
        this.tex = tex;

        addBulletEnemy(new BulletEnemy(100, 100, tex, game));

        for (int i = 0; i < game.getNum_Stars(); i++) {
            addStar(new Star(r.nextInt(Game.WIDTH * Game.SCALE), r.nextInt(Game.HEIGHT * Game.SCALE)));
        }

    }

    public void createEnemy(int enemy_count) {
        for (int i = 0; i < enemy_count; i++) {
            addEnemy(new Enemy(r.nextInt(Game.WIDTH * Game.SCALE), -100, r.nextInt(3), tex, game, this));

        }
    }

    public void createEnemyFast(int enemy_fast_count) {
        for (int i = 0; i < enemy_fast_count; i++) {
            addEnemyFast(new EnemyFast(0, 0, tex, game, this));

        }
    }

    public void createAsteroidFast(int num_fast_asteroids) {
        for (int i = 0; i < num_fast_asteroids; i++) {
            addAsteroidFast(new AsteroidFast(r.nextInt(game.WIDTH * game.SCALE), 0, r.nextInt(3), tex, game));;

        }
    }

    public void createBonus(int bonus_count) {
        for (int i = 0; i < bonus_count; i++) {
            addBonus(new Bonus(game.WIDTH * game.SCALE + r.nextInt(1000), 100, tex, game, this));

        }

    }

    public void createLife(int life_count) {
        for (int i = 0; i < life_count; i++) {
            addLife(new Life(0 - r.nextInt(1000), 100, tex, game, this));

        }

    }

    public void createAsteroid(int num_asteroids) {
        for (int i = 0; i < num_asteroids; i++) {
            direction = r.nextInt(2);
            int aXStart = r.nextInt(game.HEIGHT * game.SCALE);

            addAsteroid(new Asteroid(r.nextInt(Game.WIDTH * Game.SCALE) - 30000, aXStart, direction, tex, game));

        }
    }

    public void createParticle(int num_particles, int x, int y) {
        for (int i = 0; i < num_particles; i++) {

            addParticle(new Particle( x, y, r.nextInt(10) -5, r.nextInt(10) -5, r.nextInt(4), 60, Color.YELLOW));
            
            //int x, int y, int dx, int dy, int size, int life, Color c

        }
    }
    
    public void createParticleShield(int num_particles, int x, int y) {
        for (int i = 0; i < num_particles; i++) {

            addParticle(new Particle( x, y, r.nextInt(10) -5, r.nextInt(10), r.nextInt(4), 120, Color.CYAN));
            
            //int x, int y, int dx, int dy, int size, int life, Color c

        }
    }
    
     public void createParticleBullet(int num_particles, int x, int y) {
        for (int i = 0; i < num_particles; i++) {

            addParticle(new Particle( x, y, 0, r.nextInt(5) -5, r.nextInt(3), 1, Color.CYAN));
            
            //int x, int y, int dx, int dy, int size, int life, Color c

        }
    }
     
       public void createParticleEngine(int num_particles, int x, int y) {
        for (int i = 0; i < num_particles; i++) {

            addParticle(new Particle( x, y, r.nextInt(3) -1, r.nextInt(10) + 1 , r.nextInt(5) +3, 30, Color.CYAN));
            
            //int x, int y, int dx, int dy, int size, int life, Color c

        }
    }

       
           public void createParticleEngineIdle(int num_particles, int x, int y) {
        for (int i = 0; i < num_particles; i++) {

            addParticle(new Particle( x, y, 0, r.nextInt(10) + 1 , r.nextInt(3 ) +3, 30, Color.CYAN));
            
            //int x, int y, int dx, int dy, int size, int life, Color c

        }
    }
           
                 public void createParticleAsteroid(int num_particles, int x, int y) {
        for (int i = 0; i < num_particles; i++) {

            addParticle(new Particle( x, y, r.nextInt(5) -2, r.nextInt(5) -2, r.nextInt(12), 60, Color.GRAY));
            
            //int x, int y, int dx, int dy, int size, int life, Color c

        }
    }


    public void tick() {

        direction = r.nextInt(2);

        for (int i = 0; i < b.size(); i++) {
            TempBullet = b.get(i);

            if (TempBullet.getY() < 0) {
                removeBullet(TempBullet);
                game.can_Shoot = true;
            }

            TempBullet.tick();
        }

        for (int i = 0; i < e.size(); i++) {
            TempEnemy = e.get(i);

            TempEnemy.tick();
        }

        for (int i = 0; i < s.size(); i++) {
            TempStar = s.get(i);

            if (TempStar.getY() < 0) {
                removeStar(TempStar);
            }

            TempStar.tick();
        }

        for (int i = 0; i < a.size(); i++) {
            TempAsteroid = a.get(i);

            if (TempAsteroid.getY() < 0) {
                removeAsteroid(TempAsteroid);
            }

            TempAsteroid.tick();
        }

        for (int i = 0; i < af.size(); i++) {
            TempAsteroidFast = af.get(i);

            if (TempAsteroidFast.getY() < 0) {
                removeAsteroidFast(TempAsteroidFast);
            }

            TempAsteroidFast.tick();
        }

        for (int i = 0; i < be.size(); i++) {
            TempBulletEnemy = be.get(i);

            if (TempBulletEnemy.getY() > 1000) {
                removeBulletEnemy(TempBulletEnemy);
            }

            TempBulletEnemy.tick();
        }

        for (int i = 0; i < bo.size(); i++) {
            TempBonus = bo.get(i);

            if (TempBonus.getY() < 0) {
                removeBonus(TempBonus);
            }

            TempBonus.tick();
        }

        for (int i = 0; i < li.size(); i++) {
            TempLife = li.get(i);

            if (TempLife.getY() < 0) {
                removeLife(TempLife);
            }

            TempLife.tick();
        }

        for (int i = 0; i < ef.size(); i++) {
            TempEnemyFast = ef.get(i);

            if (TempEnemyFast.getY() < 0) {
                removeEnemyFast(TempEnemyFast);
            }

            TempEnemyFast.tick();
        }

        for (int i = 0; i < pa.size(); i++) {
            TempParticle = pa.get(i);

            if (TempParticle.getY() < 0 || TempParticle.getY() > game.HEIGHT * Game.SCALE ) {
                removeParticle(TempParticle);
            }
            
            

            TempParticle.tick();
            if(TempParticle.tick()){
                this.removeParticle(TempParticle);
            };
            
        }

    }

    public void render(Graphics g) {
        for (int i = 0; i < b.size(); i++) {
            TempBullet = b.get(i);

            TempBullet.render(g);
        }

        for (int i = 0; i < e.size(); i++) {
            TempEnemy = e.get(i);

            TempEnemy.render(g);
        }

        for (int i = 0; i < s.size(); i++) {
            TempStar = s.get(i);

            TempStar.render(g);

        }

        for (int i = 0; i < a.size(); i++) {
            TempAsteroid = a.get(i);

            TempAsteroid.render(g);

        }

        for (int i = 0; i < af.size(); i++) {
            TempAsteroidFast = af.get(i);

            TempAsteroidFast.render(g);

        }

        for (int i = 0; i < be.size(); i++) {
            TempBulletEnemy = be.get(i);

            TempBulletEnemy.render(g);

        }

        for (int i = 0; i < bo.size(); i++) {
            TempBonus = bo.get(i);

            TempBonus.render(g);

        }

        for (int i = 0; i < li.size(); i++) {
            TempLife = li.get(i);

            TempLife.render(g);

        }

        for (int i = 0; i < ef.size(); i++) {
            TempEnemyFast = ef.get(i);

            TempEnemyFast.render(g);

        }

        for (int i = 0; i < pa.size(); i++) {
            TempParticle = pa.get(i);

            TempParticle.render(g);

        }

    }

    public void addBullet(Bullet block) {
        b.add(block);
    }

    public void removeBullet(Bullet block) {
        b.remove(block);
    }

    public void addEnemy(Enemy block) {
        e.add(block);
    }

    public void removeEnemy(Enemy block) {
        e.remove(block);
    }

    public void addStar(Star block) {
        s.add(block);
    }

    public void removeStar(Star block) {
        s.remove(block);
    }

    public void addAsteroid(Asteroid block) {
        a.add(block);
    }

    public void removeAsteroid(Asteroid block) {
        a.remove(block);
    }

    public LinkedList<Bullet> getBullet() {
        return b;
    }

    public void RemoveBulletEnemy(BulletEnemy block) {
        be.remove(block);
    }

    public LinkedList<BulletEnemy> getBulletEnemy() {
        return be;
    }

    public LinkedList<Asteroid> getAsteroid() {
        return a;
    }

    public LinkedList<Enemy> getEnemy() {
        return e;
    }

    public void removeAsteroidFast(AsteroidFast block) {
        af.remove(block);
    }

    public void addAsteroidFast(AsteroidFast block) {
        af.add(block);
    }

    public void removeBulletEnemy(BulletEnemy block) {
        be.remove(block);
    }

    public void addBulletEnemy(BulletEnemy block) {
        be.add(block);
    }

    public void removeBonus(Bonus block) {
        bo.remove(block);
    }

    public void addBonus(Bonus block) {
        bo.add(block);
    }

    public void removeLife(Life block) {
        li.remove(block);
    }

    public void addLife(Life block) {
        li.add(block);
    }

    public void removeEnemyFast(EnemyFast block) {
        ef.remove(block);
    }

    public void addEnemyFast(EnemyFast block) {
        ef.add(block);
    }

    public void removeParticle(Particle block) {
        pa.remove(block);
    }

    public void addParticle(Particle block) {
        pa.add(block);
    }

    public void kill() {

    }

    public LinkedList<Bonus> getBonus() {
        return bo;
    }

    public LinkedList<Life> getLife() {
        return li;
    }

    public LinkedList<EnemyFast> getEnemyFast() {
        return ef;
    }

    public LinkedList<Particle> getParticle() {
        return pa;
    }

}
