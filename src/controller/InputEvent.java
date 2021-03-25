package controller;

import java.util.EventObject;

public class InputEvent {  // to store events from input devices

    public static final int MOUSE_PRESSED = 0;
    public static final int MOUSE_MOVED = 1;
    public static  final int KEY_PRESSED = 2;
    //public static final int UFO_CREATE = 3;

    public EventObject event;
    public int type;
}
