package star.speed;

import java.util.LinkedList;

public class Physics {

    public static boolean CollisionBullet(GameObject go, LinkedList<Bullet> b) {

        for (int i = 0; i < b.size(); i++) {

            if (go.getBounds(16, 16).intersects(b.get(i).getBounds(i, i))) {
                return true;
            }

        }
        return false;

    }

    public static boolean CollisionEnemy(GameObject go, LinkedList<Enemy> e) {

        for (int i = 0; i < e.size(); i++) {

            if (go.getBounds(32, 32).intersects(e.get(i).getBounds(i, i))) {
                return true;
            }

        }
        return false;
    }

    public static boolean CollisionAsteroid(GameObject go, LinkedList<Asteroid> a) {

        for (int i = 0; i < a.size(); i++) {

            if (go.getBounds(32, 32).intersects(a.get(i).getBounds(i, i))) {
                return true;
            }

        }
        return false;

    }

    public static boolean CollisionPlayer(GameObject go, Player p) {

        if (go.getBounds(32, 32).intersects(p.getBounds(32, 32))){
        

            return true;
        }

        return false;
    
    }
    
        public static boolean CollisionBonus(GameObject go, LinkedList<Bonus> bo) {

          for (int i = 0; i < bo.size(); i++) {

            if (go.getBounds(32, 32).intersects(bo.get(i).getBounds(i, i))) {
                return true;
            }

        }
        return false;
    
    }
        
        
          public static boolean CollisionLife(GameObject go, LinkedList<Life> li) {

          for (int i = 0; i < li.size(); i++) {

            if (go.getBounds(32, 32).intersects(li.get(i).getBounds(i, i))) {
                return true;
            }

        }
        return false;
    
    }
          
              public static boolean CollisionEnemyFast(GameObject go, LinkedList<EnemyFast> ef) {

          for (int i = 0; i < ef.size(); i++) {

            if (go.getBounds(32, 32).intersects(ef.get(i).getBounds(i, i))) {
                return true;
            }

        }
        return false;
    
    }



}
