package star.speed;

import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    private static final long serialVerisionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "STAR SPEED";
    public int gameTime = 0;

    public boolean running = false;
    private Thread thread;

    public static int levelTime = 120; //120 seconds = 2 minutes

    Timer timer = new Timer();

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    public boolean is_Shooting = false;
    public boolean can_Shoot = true;
    public boolean can_Die = true;
    public boolean game_Over = false;
    private boolean hud_On = true;
    public boolean dead = false;
    public boolean shieldsUp;
    private int level = 0;
    public int enemy_killed = 0;
    private int num_stars = 25;
    private int enemy_count = 2;
    private int num_asteroids = 2;
    public int num_fast_asteroids = 0;
    public int waveNumber = 1;
    public int score = 0;
    public int tickTime = 0;
    public int time = 0;
    public int lives = 5;
    public int powerUp = 0;
    public int shieldPower = 100;

    public Player p;
    private Controller c;
    private Star s;
    private Hud h;

    private Textures tex;
    private Menu menu;
    private Help help;
    private GameOver gameOver;
    
    private HighScores highScores;

    public LinkedList<Bullet> b;
    public LinkedList<Enemy> e;
    public LinkedList<Asteroid> a;
    public LinkedList<AsteroidFast> af;
    public LinkedList<Bonus> bo;
    public LinkedList<Life> li;
    public LinkedList<BulletEnemy> be;
    public LinkedList<EnemyFast> ef;
    public LinkedList<Particle> pa;

    public AudioPlayer bgMusic;
    public AudioPlayer soundShoot;
    public AudioPlayer soundHit;
    public AudioPlayer soundEnemyDie;
    public AudioPlayer soundEnemyShoot;
    public AudioPlayer soundAsteroidHit;
    public AudioPlayer soundPlayerDie;
    public AudioPlayer soundBeep;
    public AudioPlayer soundShield;
    public AudioPlayer soundPowerUp;
    public AudioPlayer soundSuperShoot;
    public AudioPlayer soundLife;
    public AudioPlayer soundAsteroidFastFly;
    public AudioPlayer soundShieldHit;
    public AudioPlayer soundDie;
    public AudioPlayer soundEnemyFastShoot;
    public AudioPlayer soundEnemyFastHit;
    public AudioPlayer soundShieldsUp;
    public AudioPlayer soundShieldsDown;

    private BufferedImage player;

    public enum STATE {
        MENU,
        GAME,
        HELP,
        GAMEOVER,

    };

    public static STATE state = STATE.MENU;

    public void init() {
        
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try {

            spriteSheet = loader.loadImage("/sprite_sheet.png");
            background = loader.loadImage("/background.png");
        } catch (IOException e) {
            System.out.println("CANT LOAD SS");
        }

        addKeyListener(new KeyInput(this));
        addMouseListener(new MouseInput(this));

        tex = new Textures(this);

        p = new Player(WIDTH, HEIGHT * 2 - 200, tex);
        c = new Controller(this, tex);
        s = new Star(100, 100);
        h = new Hud();
        menu = new Menu();
        help = new Help();
        gameOver = new GameOver();

        b = c.getBullet();
        e = c.getEnemy();
        a = c.getAsteroid();
        bo = c.getBonus();
        li = c.getLife();
        be = c.getBulletEnemy();
        ef = c.getEnemyFast();
        pa = c.getParticle();

        

        soundShoot = new AudioPlayer("/shoot.wav");
        soundHit = new AudioPlayer("/hit.wav");
        soundEnemyDie = new AudioPlayer("/enemydie.wav");
        soundEnemyShoot = new AudioPlayer("/enemy_shoot.wav");
        soundAsteroidHit = new AudioPlayer("/asteroid_hit.wav");
        soundPlayerDie = new AudioPlayer("/playerdie.wav");
        soundBeep = new AudioPlayer("/beep.wav");
        soundShield = new AudioPlayer("/shield.wav");
        soundPowerUp = new AudioPlayer("/powerup.wav");
        soundSuperShoot = new AudioPlayer("/supershoot.wav");
        soundLife = new AudioPlayer("/life.wav");
        soundAsteroidFastFly = new AudioPlayer("/asteroidfastfly.wav");
        soundShieldHit = new AudioPlayer("/shieldhit.wav");
        soundDie = new AudioPlayer("/soundDie.wav");
        soundEnemyFastShoot = new AudioPlayer("/enemyfastshoot.wav");
        soundEnemyFastHit = new AudioPlayer("/enemyfasthit.wav");
        soundShieldsUp = new AudioPlayer("/shieldsup.wav");
        soundShieldsDown = new AudioPlayer("/shieldsdown.wav");

        bgMusic = new AudioPlayer("/bg_music.mp3");
         bgMusic.play();
    }

    public synchronized void start() {

        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public synchronized void stop() {

        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);

    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                updates = 0;
                frames = 0;

            }

        }
        stop();
    }

    private void tick() {
        requestFocus();

        if (state == STATE.GAME) {

            p.tick();
            c.tick();
            h.tick(this);
            tickTime++;

            if (shieldPower < 100 && can_Die) {
                shieldPower++;
            }

            if (can_Die) {
                createParticleEngineIdle((int) p.getX() + 16, (int) p.getY() + 32);
            }

            if (!can_Die) {
                
                
                
                if (shieldPower > 0){
                    shieldPower--; 
                }else {
                    can_Die = true;
                }
                
                if (shieldPower == 0){
                        can_Die = true; 
                }
               

               

                if (tickTime == 60) {
                    time++;
                    soundShield.play();
                    tickTime = 0;

                }

                if (tickTime == 30) {
                    soundShield.play();
                }

                if (time == 3) {
                    time = 0;
                   // can_Die = true;
                }
            }

            if (tickTime == 60) {
                gameTime++;
                System.out.println("Game Time: " + gameTime);
                score++;
                tickTime = 0;

            }

            if (gameTime == 5) {
                c.createEnemy(enemy_count);
                gameTime++;

            }

            if (e.isEmpty() && ef.isEmpty() && gameTime > 5) {

                c.createEnemy(enemy_count);
                waveNumber++;
                soundBeep.play();
                gameTime = 0;
            }

            if (waveNumber % 3 == 0 && waveNumber > 1) {
                num_asteroids++;
                enemy_count++;
                c.createAsteroid(num_asteroids);
                waveNumber++;
                gameTime = 0;
            }

            if (waveNumber % 5 == 0) {
                num_fast_asteroids++;
                c.createAsteroidFast(1);
                c.createEnemyFast(1);
                waveNumber++;
                c.createBonus(1);
            }

            if (waveNumber % 6 == 0) {
                num_fast_asteroids++;
                c.createAsteroidFast(1);
                waveNumber++;
                c.createLife(1);
            }

        } else if (state == STATE.HELP || state == STATE.MENU || state == STATE.GAMEOVER) {

        }

    }

    //EVERYTHING IN THE GAME THAT NEEDS TO BE RENDERED 
    private void render() {

        BufferStrategy bs = this.getBufferStrategy();//THE WORLD THIS IS FROM THE EXTENDED CANVAS CLASS

        if (bs == null) {
            createBufferStrategy(3);//IF THERE IS NO BS CREATE ONE WITH 3 BUFFERS
            return;
        }

        Graphics g = bs.getDrawGraphics();//THIS IS WHAT DRAWS OUT THE BUFFERS
        //ANYTHING THAT NEEDS TO BE DRAWN IN HERE
        ////////////////////////////////////////////////////////////////////// 
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (state == STATE.GAME) {

            if (!game_Over) {

                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                p.render(g);
                c.render(g);

                g.setColor(Color.CYAN);
                if (!can_Die) {
                    g.setColor(Color.CYAN);
                    g.drawOval((int) p.getX() - 10, (int) p.getY() - 10, 50, 50);
                }

            }
            h.render(g);

        } else if (state == STATE.MENU) {

            menu.render(g);

        } else if (state == STATE.HELP) {

            help.render(g);

        } else if (state == STATE.GAMEOVER) {

            gameOver.render(g);
        }

        //////////////////////////////////////////////////////////////////////
        g.dispose();//UNLOAD THE GRAPHICS
        bs.show();//SHOW THE BS

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_D:
                p.setVelX(8);
                break;
            case KeyEvent.VK_A:
                p.setVelX(-8);
                break;
            case KeyEvent.VK_S:
                p.setVelY(5);
                break;
            case KeyEvent.VK_W:
                if(can_Die){
                    
                
                p.setVelY(-5);
                createParticleEngine((int) p.getX() + 16, (int) p.getY() + 32);
                
                }

                break;
            case KeyEvent.VK_SHIFT:
                if (shieldPower > 0) {

                    can_Shoot = false;
                    can_Die = false;
                    if (!shieldsUp) {
                        soundShieldsUp.play();
                        shieldsUp = true;
                    }

                }

                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            default:
                break;

        }

        if (key == KeyEvent.VK_SPACE && !is_Shooting && can_Shoot) {     //NOT IN SWITCH AS NEEDS BOOL
            is_Shooting = true;
            can_Shoot = false;

            if (powerUp == 0) {

                c.addBullet(new Bullet(p.getX(), p.getY() - 16, tex, this, c));
                soundShoot.play();

            }

            if (powerUp == 1) {

                c.addBullet(new Bullet(p.getX() - 12, p.getY(), tex, this, c));
                c.addBullet(new Bullet(p.getX() + 12, p.getY(), tex, this, c));
                soundShoot.play();

            }

            if (powerUp == 2) {

                c.addBullet(new Bullet(p.getX() - 12, p.getY(), tex, this, c));
                c.addBullet(new Bullet(p.getX(), p.getY() - 16, tex, this, c));
                c.addBullet(new Bullet(p.getX() + 12, p.getY(), tex, this, c));

                soundShoot.play();

            }

            if (powerUp == 3) {

                c.addBullet(new Bullet(p.getX() - 24, p.getY() + 16, tex, this, c));
                c.addBullet(new Bullet(p.getX() - 12, p.getY(), tex, this, c));
                c.addBullet(new Bullet(p.getX(), p.getY() - 16, tex, this, c));
                c.addBullet(new Bullet(p.getX() + 12, p.getY(), tex, this, c));
                c.addBullet(new Bullet(p.getX() + 24, p.getY() + 16, tex, this, c));
                soundSuperShoot.play();

            }

            if (powerUp == 4) {
                c.addBullet(new Bullet(p.getX() - 32, p.getY() + 32, tex, this, c));
                c.addBullet(new Bullet(p.getX() - 24, p.getY() + 16, tex, this, c));
                c.addBullet(new Bullet(p.getX() - 12, p.getY(), tex, this, c));
                c.addBullet(new Bullet(p.getX(), p.getY() - 16, tex, this, c));
                c.addBullet(new Bullet(p.getX() + 12, p.getY(), tex, this, c));
                c.addBullet(new Bullet(p.getX() + 24, p.getY() + 16, tex, this, c));
                c.addBullet(new Bullet(p.getX() + 32, p.getY() + 32, tex, this, c));
                soundSuperShoot.play();
            }

        }

    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_D:
                p.setVelX(0);
                break;
            case KeyEvent.VK_A:
                p.setVelX(0);
                break;
            case KeyEvent.VK_S:
                p.setVelY(0);
                break;
            case KeyEvent.VK_W:
                p.setVelY(0);

                break;
            case KeyEvent.VK_SPACE:

                break;

            case KeyEvent.VK_SHIFT:
                can_Shoot = true;
                can_Die = true;
                shieldsUp = false;

                soundShieldsDown.play();
                break;
            case KeyEvent.VK_ESCAPE:

                break;
            default:
                break;
        }

        if (key == KeyEvent.VK_SPACE) {     //NOT IN SWITCH AS NEEDS BOOL
            is_Shooting = false;

        }
    }

    public void mousePressed(MouseEvent e) {

        int but = e.getButton();

        switch (but) {
            case MouseEvent.BUTTON1:

                break;
            case MouseEvent.BUTTON3:

                can_Shoot = false;
                can_Die = false;
                if (!shieldsUp) {
                    soundShieldsUp.play();
                    shieldsUp = true;
                }

                break;
            default:
                break;

        }

    }

    public void mouseReleased(MouseEvent e) {

        int but = e.getButton();

        switch (but) {
            case MouseEvent.BUTTON1:

                break;
            case MouseEvent.BUTTON3:
                can_Shoot = true;
                can_Die = true;
                shieldsUp = false;

                soundShieldsDown.play();
                break;
            default:
                break;

        }

    }

    public static void main(String[] args) {

        Game game = new Game();//CREATE A NEW GAME OBJECT

        //CONSTRUCTION DIMENSON OBJECT 
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);// CREATE A NEW FRAME OBJECT WITH TITLE STRING

        frame.add(game);// ADD  THE GAME OBJECT TO THE FRAME 
        frame.pack();//WILL SHRINK THE FRAME BUT KEEP BIG ENOUGH FOR THE CONTENTS
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//KILL THE GAME ON CLOSE
        frame.setResizable(false);//PREVENTS RESIZING
        frame.setLocationRelativeTo(null);//CENTER THE FRAME IN THE SCREEN
        frame.setVisible(true);//UNHIDE THE FRAME

        game.start(); // START THE GAME!

    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public int getEnemy_count() {
        return enemy_count;
    }

    public void setEnemy_Count(int enemy_count) {
        this.enemy_count = enemy_count;
    }

    public int getEnemy_killed() {
        return enemy_killed;
    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_count = enemy_killed;
    }

    public int getNum_Stars() {
        return num_stars;
    }

    public int getNum_Asteroids() {
        return num_asteroids;
    }

    public boolean getHud_On() {
        return hud_On;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void setScore() {
        this.score = score + 10;
        System.out.println(score);
    }

    public void removelife() {
        this.lives = lives - 1;
        if (lives <= 0) {

        }
        System.out.println(score);
    }

    public void die() {
        soundPlayerDie.play();
        createParticle((int) p.x + 16, (int) p.y - 16);
        if (lives > 0) {

            can_Die = false;
            removelife();

            p.setX(WIDTH * 2 / 2);
            p.setY(HEIGHT * 2 - 200);

            try {

                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            dead = true;
            p.setX(-1000000);
            System.out.println(dead);

        }

        if (lives == 0) {
            soundDie.play();

            state = STATE.GAMEOVER;

        }

    }

    public int getTime() {
        return time;
    }

    public boolean isCan_Die() {
        return can_Die;
    }

    public void setCan_Die(boolean can_Die) {
        this.can_Die = can_Die;
    }

    public int getPowerUp() {
        return powerUp;
    }

    public void createParticle(int x, int y) {
        c.createParticle(15, x, y);
    }

    public void createParticleShield(int x, int y) {
        c.createParticleShield(15, x, y);
    }

    public void createParticleEngine(int x, int y) {
        c.createParticleEngine(0, x, y);
    }

    public void createParticleEngineIdle(int x, int y) {
        c.createParticleEngineIdle(1, x, y);
    }

    void keyPressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getShieldPower() {
        return shieldPower;
    }

    public void createParticleAsteroid(int x, int y) {
        c.createParticleAsteroid(5, x, y);
    }

}
