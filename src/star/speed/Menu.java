
package star.speed;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;



public class Menu {
    
Color textColor = Color.WHITE;
    
    
private String menuText = "STARSPEED";
private String menuBy = "Created by Matt Barker";
public Rectangle playButton = new Rectangle(Game.WIDTH /2 + 120, 150, 100, 50);
public Rectangle helpButton = new Rectangle(Game.WIDTH /2 + 120, 250, 100, 50);
public Rectangle quitButton = new Rectangle(Game.WIDTH /2 + 120, 350, 100, 50);

  
public void render (Graphics g){
    
    Graphics2D g2d =(Graphics2D) g;
    
    //Font fnt0 = new Font("", Font.PLAIN, 20);
   // g.setFont(fnt0);
    g.setColor(Color.CYAN);
    g.drawString(menuText, Game.WIDTH /2 + 130, 100);
    g.drawString(menuBy, Game.WIDTH /2 + 108, 120);
    g.setColor(textColor);
    
    g2d.draw(playButton);
    g2d.draw(helpButton);
    g2d.draw(quitButton);
    
    g.drawString("Play", playButton.x + 37, playButton.y + 30);
    g.drawString("Help", helpButton.x + 37, helpButton.y + 30);
    g.drawString("Quit", quitButton.x + 37, quitButton.y + 30);
}

  public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

}
