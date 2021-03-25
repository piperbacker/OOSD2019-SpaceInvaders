package model.AlienLaser;

import controller.Main;
import model.GameFigure;
import model.Alien.Alien;
import model.Text;

import java.awt.*;
import java.awt.geom.Point2D;


public class AlienLaser extends GameFigure {

    public static final int UNIT_MOVE = 7;
    public static final int INIT_LASER_SIZE = 5;
    public static final int MAX_LASER_SIZE = 30;

    public static final int STATE_SHOOTING = 0;
    public static final int STATE_EXPLODING = 1;
    public static final int STATE_DONE = 2;

    int size = INIT_LASER_SIZE;
    Point2D.Float target; // where mouse was pressed
    Color color;
    int state;
    AlienLaserAnimStrategy animStrategy;
    //GameData gameData = new GameData();

    public AlienLaser(int tx, int ty) {
        //Shooter shooter = (Shooter) Main.gameData.enemyObject.get(Main.INDEX_SHOOTER);
        Alien alien = (Alien) Main.gameData.enemyObject.get(Main.INDEX_UFO);
        location.x = (int) alien.location.x;
        location.y = (int) alien.location.y;
        target = new Point2D.Float(tx, ty);
        color = Color.YELLOW;
        state = STATE_SHOOTING;
        animStrategy = new AlienLaserAnimShooting(this);  //passing context so class has access to laser
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        g2.fillOval((int) super.location.x - size / 2, (int) super.location.y - size / 2, size, size);
    }

    @Override
    public void update() {

        updateState();
        animStrategy.animate();
    }

    public void updateState() {
        if (state == STATE_SHOOTING) {
            if (hitCount > 0 ) {
                state = STATE_EXPLODING;
                animStrategy = new AlienLaserAnimExploding(this);
                Main.livesRem--;
                Font font = new Font("Symbol", Font.BOLD, 25);
                Text m = new Text("Lives: " + Main.livesRem, 10, 20, Color.YELLOW, font);
                Main.gameData.fixedObject.add(m);

            }
        } else if (state == STATE_EXPLODING) {
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
