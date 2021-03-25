package model.Shield;

import model.GameFigure;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Shield extends GameFigure {

    public Rectangle2D.Float shield;
    public final int SHIELD_SIZE = 20;

    public Shield(int x, int y) {
         shield = new Rectangle2D.Float(x - SHIELD_SIZE, y - SHIELD_SIZE /2,
                 SHIELD_SIZE * 2, SHIELD_SIZE);
    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(7)); // line thickness
        g2.draw(shield);
    }

    @Override
    public void update() {
        // when hit size changes
        /*if (state == STATE_FLYING) {
            if (hitCount > 0) {
                state = STATE_FALLING;
                animStrategy = new AlienAnimFalling(this);
            }
        } else if (state == STATE_FALLING) {
            if (location.y >= Main.win.canvas.height) state = STATE_DONE;
        } else if (state == STATE_DONE) {
            super.done = true;
            //notifyEvent(); // UFO died
        }  */

    }

    @Override
    public int getCollisionRadius() {
        return (int) (SHIELD_SIZE/2 * .75);
    }
}
