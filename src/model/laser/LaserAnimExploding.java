package model.laser;

import java.awt.*;

public class LaserAnimExploding implements LaserAnimStrategy {

    Laser context;

    public LaserAnimExploding(Laser context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color = Color.RED;
        ++context.size;

    }
}
