package model;

import controller.Main;
import model.AlienLaser.AlienLaser;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Spaceship extends GameFigure {

    public final int BASE_SIZE = 15;
    public final int BARREL_LEN = 25;
    public static final int UNIT_MOVE = 10; //10 pixels by 4 arrow keys
    public static final int STATE_SHOOTING = 0;
    public static final int STATE_HIT = 1;
    public static final int STATE_DONE = 2;
    public Rectangle2D.Float base;
    public Line2D.Float barrel;
    int state;
    Color color;
    int i = 0;

    int lives = Main.livesRem;

    public Spaceship(int x, int y) {
        super(x, y);
        state = STATE_SHOOTING;
        base = new Rectangle2D.Float(x - BASE_SIZE/2, y - BASE_SIZE/2,
                BASE_SIZE, BASE_SIZE);
        barrel = new Line2D.Float(x, y, x, y - BARREL_LEN);
        color = Color.CYAN;
    }

    @Override
    public void render(Graphics2D g2) {

        g2.setColor(color);
        g2.setStroke(new BasicStroke(7)); // line thickness
        g2.draw(barrel);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        g2.fillOval((int) location.x - BASE_SIZE / 2 * 4, (int) location.y - BASE_SIZE / 2,
                    BASE_SIZE * 4, BASE_SIZE * 2);
    }

    @Override
    public void update() {
        updateState();
    }

    public void updateState() {
        if (state == STATE_SHOOTING) {
            if (Main.livesRem < lives) {
                color = Color.RED;
                state = STATE_HIT;
                i++;
                if (i == 6){
                    lives--;
                    i = 0;
                }

            }
            MousePointer mousePointer = (MousePointer) Main.gameData.fixedObject.get(Main.INDEX_MOUSE_POINTER);
            float tx = mousePointer.location.x;
            float ty = mousePointer.location.y;
            double rad = Math.atan2(ty - super.location.y, tx - super.location.x);
            float barrel_y = (float) (BARREL_LEN * Math.sin(rad));
            float barrel_x = (float) (BARREL_LEN * Math.cos(rad));

            barrel.x1 = super.location.x;
            barrel.y1 = super.location.y;
            barrel.x2 = super.location.x + barrel_x;
            barrel.y2 = super.location.y + barrel_y;

            base.x = location.x - BASE_SIZE / 2;
            base.y = location.y - BASE_SIZE / 2;
        } else if (state == STATE_HIT) {
            color = Color.CYAN;
            state = STATE_SHOOTING;
        } else if (Main.livesRem == 0) {
            state = STATE_DONE;
            super.done = true;
        }

    }

    @Override
    public int getCollisionRadius() {
        return BASE_SIZE;
    }
}
