package model.AlienLaser;

public class AlienLaserAnimShooting implements AlienLaserAnimStrategy {

    AlienLaser context;

    public AlienLaserAnimShooting(AlienLaser context) {
        this.context = context;
    }

    @Override
    public void animate() {
        // maybe add missile timer here
        //double rad = Math.atan2(context.target.y - context.location.y, context.target.x - context.location.x);
        //context.location.x += context.UNIT_MOVE //* Math.cos(rad);
        //while (context.location.y != 500) {
        context.location.y += context.UNIT_MOVE; //* Math.sin(rad);
        //}
    }
}
