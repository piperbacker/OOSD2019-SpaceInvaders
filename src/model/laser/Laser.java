package model.laser;

import controller.Main;
import model.GameFigure;
import model.Spaceship;

import java.awt.*;
import java.awt.geom.Point2D;


public class Laser extends GameFigure {

    public static final int UNIT_MOVE = 5;
    public static final int INIT_LASER_SIZE = 5;
    public static final int MAX_LASER_SIZE = 15;

    public static final int STATE_SHOOTING = 0;
    public static final int STATE_EXPLODING = 1;
    public static final int STATE_DONE = 2;

    int size = INIT_LASER_SIZE;
    Point2D.Float target; // where mouse was pressed
    Color color;
    int state;
    LaserAnimStrategy animStrategy;

    public Laser(int tx, int ty) {
        Spaceship spaceship = (Spaceship) Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
        super.location.x = spaceship.barrel.x2;
        super.location.y = spaceship.barrel.y2;
        target = new Point2D.Float(tx, ty);
        color = Color.CYAN;
        state = STATE_SHOOTING;
        animStrategy = new LaserAnimShooting(this);  //passing context so class has access to missile
    }

    @Override
    public void render(Graphics2D g2) {

        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.fillOval((int) super.location.x - size / 2, (int) super.location.y - size / 2, size, size);

    }

    @Override
    public void update() {

        updateState();
        animStrategy.animate(); // whatever is assigned will be used, shooting/ exploding
        // strategy design pattern
    }

    public void updateState() {
        if (state == STATE_SHOOTING) {
            if (hitCount > 0 || target.distance(location) <= 3.0) {
                state = STATE_EXPLODING;
                animStrategy = new LaserAnimExploding(this);
            }
        } else if (state == STATE_EXPLODING) {
            //System.out.print("laser exploding");
            if (size >= MAX_LASER_SIZE) {
                state = STATE_DONE;
            }
        } else if (state == STATE_DONE) {
            super.done = true;
        }
    }

    @Override
    public int getCollisionRadius() {
        return size/2;
    }
}
