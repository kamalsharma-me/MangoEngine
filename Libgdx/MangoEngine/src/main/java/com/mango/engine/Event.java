package com.mango.engine;

public class Event
{
    public String type ;
    public float x ;
    public float y ;
    public String nodeName ;
    public Node node ;


    public static final String TOUCH_DOWN   = "TOUCH_DOWN";
    public static final String TAP          = "TAP";
    public static final String PAN_STOP     = "PAN_STOP";

    public enum Gesture
    {
        TOUCH_DOWN,
        TAP,
        PAN,
        LONG_PRESS,
        SWIPE_LEFT,
        SWIPE_RIGHT,
        SWIPE_UP,
        SWIPE_DOWN
    }
}
