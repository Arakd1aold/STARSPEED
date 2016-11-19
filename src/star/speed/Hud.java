package star.speed;

import java.awt.Color;
import java.awt.Graphics;

public class Hud {

    private int hudXPadding = 10;
    private int hudYPadding = 5;
    int score;
    int lives;
    int time;
    int gTime;

    private String hudTitle = "STAR SPEED";
    private String hudVersion = "Pre Alpha 1.01";
    private String hudAuthor = "By Matt Barker";
    public String hudScore = "Score: ";
    private String hudLives = "Lives:";
    private String hudTime = "Time: ";
    private String hudShields = "";
    private String hudGameOver ="GAME OVER";
    private String hudIncomingWave = "[ INCOMING ]";
    private int shield = 0;
    Game game;

    public Hud() {
        this.game = game;

    }

    public void render(Graphics g) {

        g.drawString(hudTitle, hudXPadding, 20);
        g.drawString(hudVersion, hudXPadding, 35);
        g.drawString(hudAuthor, hudXPadding, 50);
        g.drawString(hudScore + score, game.WIDTH * game.SCALE - 80, 20);
        g.drawString(hudLives + lives, game.WIDTH * game.SCALE - 80, 35);
       // g.drawString(hudTime + time, 0 + hudXPadding, game.HEIGHT * game.SCALE - hudXPadding);
        g.setColor(Color.CYAN);
       // g.drawString(hudShields,  0 + hudXPadding, game.HEIGHT * game.SCALE - hudYPadding);
        
        g.setColor(Color.RED);
        g.drawString(hudGameOver, game.WIDTH * game.SCALE /2 - 32, game.HEIGHT * game.SCALE / 2);
        g.setColor(Color.WHITE);
       g.drawRect(20, game.HEIGHT * game.SCALE / 2 + 120 , 10, 100);
       g.setColor(Color.CYAN);
       g.fillRect(20, game.HEIGHT * game.SCALE / 2 + 120 , 10, shield);
       
       
       
       
        if (gTime > 0 && gTime < 6  && gTime == 1 || gTime == 3  ){
            g.setColor(Color.RED);
            g.drawString(hudIncomingWave, game.WIDTH * game.SCALE - 80, game.HEIGHT * game.SCALE - hudYPadding);
   
       
        }
        
          
        if (gTime > 0 && gTime < 6 && gTime == 2 || gTime == 4 ){
    
            g.setColor(Color.CYAN);
            g.drawString(hudIncomingWave, game.WIDTH * game.SCALE - 80, game.HEIGHT * game.SCALE - hudYPadding);
       
        }
        

    }

    public void tick(Game game) {
        shield = game.getShieldPower();

        score = game.getScore();
        lives = game.getLives();
        time = game.getTime();
        gTime =game.gameTime;
        
        
        if (game.can_Die){
            hudShields = "";
                    
        } else{
              hudShields = "SHIELDS UP";
        }
        
        if(game.game_Over){
            hudGameOver = "GAME OVER";
        }else {
             hudGameOver = "";
        }
        

    }

}
