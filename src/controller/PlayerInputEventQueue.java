package controller;

import model.MousePointer;
import model.laser.Laser;
import model.Spaceship;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class PlayerInputEventQueue {

    public LinkedList<InputEvent> queue = new LinkedList<>();

    public void processInputEvents() {  // events queued then processed

        while (!queue.isEmpty()) {
            InputEvent inputEvent = queue.removeFirst();

            switch (inputEvent.type) {
                case InputEvent.MOUSE_PRESSED:
                    MouseEvent e = (MouseEvent) inputEvent.event;
                    Laser m = new Laser(e.getX(), e.getY());
                    Main.gameData.friendObject.add(m);
                    break;
                case InputEvent.MOUSE_MOVED:
                    MousePointer mp = (MousePointer) Main.gameData.fixedObject.get(0); // typecast
                    MouseEvent me = (MouseEvent) inputEvent.event;
                    mp.location.x = me.getX();
                    mp.location.y = me.getY();
                    break;
                case InputEvent.KEY_PRESSED:
                    var shooter = Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
                    KeyEvent ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if((int)shooter.location.x > 0)
                            shooter.location.x -= Spaceship.UNIT_MOVE; break;
                        case KeyEvent.VK_RIGHT:
                            if((int)shooter.location.x < 700)
                            shooter.location.x += Spaceship.UNIT_MOVE; break;
                    }
                    break;
                /*case InputEvent.UFO_CREATE:
                    UFOCreateEvent ue = (UFOCreateEvent) inputEvent.event;
                    Main.addUFOWithListener(ue.getX(), ue.getY());*/
            }
        }

    }
}
