package model;

import java.awt.*;

public class MousePointer extends GameFigure {

    public final int SIZE = 10;

    public MousePointer(int x, int y) {
        super(x,y);
    }

    @Override
    public void render(Graphics2D g2) {

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(1));
        g2.fillOval((int) super.location.x - SIZE / 2, (int) super.location.y - SIZE / 2, SIZE, SIZE);

        g2.setColor(Color.RED);
        // renders cross shape for mouse pointer
        g2.drawLine((int)location.x - SIZE, (int)location.y,
                (int)location.x + SIZE, (int)location.y);
        g2.drawLine((int)location.x, (int)location.y - SIZE,
                (int)location.x, (int)location.y + SIZE);

    }

    @Override
    public void update() {
        // N/A
    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}