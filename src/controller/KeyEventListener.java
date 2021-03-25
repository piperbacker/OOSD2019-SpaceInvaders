package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyEventListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        InputEvent inputEvent = new InputEvent();
        inputEvent.event = e;
        inputEvent.type = inputEvent.KEY_PRESSED;
        Main.playerInputEventQueue.queue.add(inputEvent);

      /*  switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: System.out.println("up key"); break;
            case KeyEvent.VK_DOWN: System.out.println("down key"); break;
            case KeyEvent.VK_LEFT: System.out.println("left key"); break;
            case KeyEvent.VK_RIGHT: System.out.println("right key"); break;
        } */

    }
}
