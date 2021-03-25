package model;

import controller.Main;

import java.awt.*;

public class Text extends GameFigure {

    public static final int STATE_DISPLAY = 0;
    public static final int STATE_DONE = 1;
    int state;

    String text;
    Color color;
    Font font;

    public Text(String text, int x, int y, Color color, Font font) {
        super (x,y);
        this.text = text;
        this.color = color;
        this.font = font;
        state = STATE_DISPLAY;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(text, (int) location.x, (int) location.y);
    }

    @Override
    public void update() {
        if (state == STATE_DISPLAY) {
            if (Main.livesRem == (Main.livesTotal - 1)) {
                //System.out.print("updating");
                Main.livesTotal--;
                state = STATE_DONE;
            }
            if (Main.livesRem == (Main.livesTotal - 2)) {
                Main.livesTotal--;
                state = STATE_DONE;
            }

        } else if(state == STATE_DONE) {
            super.done =true;
        }
    }


    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
