package model.AlienLaser;

import java.awt.*;

public class AlienLaserAnimExploding implements AlienLaserAnimStrategy {

    AlienLaser context;

    public AlienLaserAnimExploding(AlienLaser context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color = Color.RED;
        ++context.size;

    }
}
