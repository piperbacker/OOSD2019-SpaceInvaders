package model.Alien;

import java.awt.*;

public class AlienAnimFalling implements AlienAnimStrategy {

    Alien context;

    public AlienAnimFalling(Alien context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color = Color.RED;
        context.location.y += context.UNIT_MOVE_FALLING;

    }
}
