package star.speed;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

Game game;   

    public MouseInput(Game game) {
        this.game = game;//CALL KEYBOARD INPUT FROM THE GAME CLASS

    }

    public void mousePressed(MouseEvent e) {
        game.mousePressed(e);//PASSES THE KEY EVENT TO THE MAIN GAME CLASS

        int mx = e.getX();
        int my = e.getY();

        if (Game.state == Game.state.MENU) {

            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                if (my >= 150 && my <= 200) {
                    Game.state = Game.state.GAME;
                }
            }

            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                if (my >= 250 && my <= 300) {
                    Game.state = Game.state.HELP;
                }
            }

            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                if (my >= 350 && my <= 400) {
                    System.exit(1);
                }
            }

        }

        if (Game.state == Game.state.HELP) {

            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                if (my >= 350 && my <= 400) {
                    Game.state = Game.state.MENU;
                }
            }

        }

        if (Game.state == Game.state.GAMEOVER) {

            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                if (my >= 350 && my <= 400) {

                    Game.state = Game.state.MENU;

                }
            }

        }

//    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
//    public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
//    public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
    }

    public void mouseReleased(MouseEvent e) {
        game.mouseReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
