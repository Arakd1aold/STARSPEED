package star.speed;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameOver {

    Color textColor = Color.WHITE;
    
    
    private String menuText = "GAME OVER";
    

    public Rectangle backButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        //Font fnt0 = new Font("", Font.PLAIN, 20);
        // g.setFont(fnt0);
        g.setColor(Color.RED);
        g.drawString(menuText, Game.WIDTH / 2 + 130, 100);
       
        g.setColor(textColor);

      
        g2d.draw(backButton);
        
    
        g.drawString("Restart", backButton.x + 30, backButton.y + 30);
    }
    
      public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
