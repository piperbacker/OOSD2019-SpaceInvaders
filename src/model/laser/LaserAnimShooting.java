package model.laser;

public class LaserAnimShooting implements LaserAnimStrategy {

    Laser context;

    public LaserAnimShooting(Laser context) {
        this.context = context;
    }

    @Override
    public void animate() {
        double rad = Math.atan2(context.target.y - context.location.y, context.target.x - context.location.x);
        context.location.x += context.UNIT_MOVE * Math.cos(rad);
        context.location.y += context.UNIT_MOVE * Math.sin(rad);
    }
}
