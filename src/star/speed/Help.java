package star.speed;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {

    private String menuText = "STARSPEED";
    private String menuBy = "Created by Matt Barker";

    public Rectangle backButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("courier new", Font.PLAIN, 12);
        g.setFont(fnt0);
        g.setColor(Color.CYAN);
        g.drawString(menuText, Game.WIDTH / 2 + 130, 100);
        g.drawString(menuBy, Game.WIDTH / 2 + 108, 120);
        g.setColor(Color.WHITE);

        g.drawString("Avoid the asteroids and destroy enemy ships to progress to the next wave.", 118, 160);
        g.drawString("Move the WSAD keys. SPACE to fire. SHIFT to power shields.", 158, 180);
        g.drawString("Be careful, shields have limited charge.", 198, 200);
        g.drawString("Collect weapon power ups and extra lives by shooting them.", 158, 220);
        g.setColor(Color.CYAN);
        g.drawString("GOOD LUCK!", 289, 260);
        g.setColor(Color.CYAN);
        g2d.draw(backButton);

        g.drawString("Back", backButton.x + 37, backButton.y + 30);
    }
}
