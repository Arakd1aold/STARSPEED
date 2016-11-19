package star.speed;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Life extends GameObject implements EntityA {

    Random r = new Random();

    private Textures tex;
    private Game game;
    private Controller c;

    public Life (double x, double y, Textures tex, Game game, Controller c) {
        super(x, y);
        this.tex = tex;
        this.game = game;
        this.c = c;

    }

    public void tick() {



        x += 1;

    }

    public void render(Graphics g) {
        g.drawImage(tex.life, (int) x, (int) y, null);

    }

    public double getY() {
        return y;
    }
    
      public double getX() {
        return x;
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int) x, (int) y, 32, 32);

    }
}
