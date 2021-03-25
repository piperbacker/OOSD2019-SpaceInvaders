package model.Alien;

import controller.Main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.AlienLaser.AlienLaser;
import model.GameFigure;

import java.awt.*;
import java.util.ArrayList;

public class Alien extends GameFigure implements Subject {

    public int UNIT_MOVE;
    public static int UNIT_MOVE_FALLING = 5;
    public static final int STATE_FLYING = 0;
    public static final int STATE_FALLING = 1;
    public static final int STATE_DONE = 2;

    public static int size = 40;
    int width, height;
    boolean movingRight = true;
    int state;
    Color color;
    AlienAnimStrategy animStrategy;

    ArrayList<Observer> listeners  = new ArrayList<>();

    public Alien(int x, int y, int size, int UNIT_MOVE) {
        super(x,y);
        width = size;
        height = size/2;
        state = STATE_FLYING;
        color = Color.GREEN;
        this.UNIT_MOVE = UNIT_MOVE;
        animStrategy = new AlienAnimFlying(this);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        g2.fillOval((int)location.x - width/2, (int)location.y - height/2, width, height);

        g2.setStroke(new BasicStroke(3)); // line thickness
        g2.drawLine((int)location.x, (int)location.y - size/3,
                (int)location.x, (int)location.y);
        g2.fillOval((int)location.x - width/2 + 8, (int)location.y - height/2 - 10, width/2, height/2);

    }

    @Override
    public void update() {
        updateState();
        animStrategy.animate(); // strategy design pattern

    }

    private void updateState() {
        if (state == STATE_FLYING) {
            if (hitCount > 0) {
                state = STATE_FALLING;
                animStrategy = new AlienAnimFalling(this);
            }
        } else if (state == STATE_FALLING) {
                if (location.y >= Main.win.canvas.height) state = STATE_DONE;
        } else if (state == STATE_DONE) {
                super.done = true;
                //notifyEvent(); // UFO died
        }
    }

    @Override
    public int getCollisionRadius() {
        return (int)(size/4);
    }

    @Override
    public void attachListener(Observer o) {
        listeners.add(o);
    }

    @Override
    public void detachListener(Observer o) {
        listeners.remove(o);
    }

    @Override
    public void notifyEvent() {
        for (var o: listeners) {
            o.eventRecieved();
        }
    }
}
