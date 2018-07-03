package com.mango.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import json.JSONArray;
import json.JSONObject;

public class Gestures implements GestureDetector.GestureListener
{
    Engine engine ;

    JSONObject eventPool ;

    Event touchDownEvent ;
    Event tapEvent ;
    Event panEvent ;
    Event panStopEvent ;
    Event longPressEvent ;

    public JSONArray nodes ;

    public Gestures(Engine engine)
    {
        this.engine = engine ;

        touchDownEvent  = new Event() ;
        tapEvent        = new Event() ;
        panEvent        = new Event() ;
        panStopEvent    = new Event() ;
        longPressEvent  = new Event() ;

        nodes = new JSONArray() ;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button)
    {
        Vector2 point = Engine.Canvas.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

        Actor actor = Engine.Canvas.hit(point.x, point.y, true) ;

        touchDownEvent.type = "TOUCH_DOWN" ;
        touchDownEvent.x = x ;
        touchDownEvent.y = y ;

        touchDownEvent.nodeName = "null" ;

        if (actor != null)
        {
            touchDownEvent.node = (Node) actor;
            nodes.put(actor) ;
            touchDownEvent.nodeName = actor.getName() ;
        }

        engine.GestureEvents(touchDownEvent);

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button)
    {
        Vector2 point = Engine.Canvas.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

        Actor actor = Engine.Canvas.hit(point.x, point.y, true) ;

        tapEvent.type = "TAP" ;
        tapEvent.x = x ;
        tapEvent.y = y ;

        tapEvent.nodeName = "null" ;

        if (actor != null && actor.getName() != null)
        {
            tapEvent.node = (Node) actor;

            for(Integer i = 0; i < nodes.length(); i++)
            {
                if(nodes.get(i) != actor)
                {
                    nodes.put(actor) ;
                }
            }

            //System.out.println("nodes " + nodes);

            tapEvent.nodeName = actor.getName() ;
        }

        engine.GestureEvents(tapEvent);

        nodes = null ;
        nodes = new JSONArray() ;

        return false;
    }

    @Override
    public boolean longPress(float x, float y)
    {
        longPressEvent.type = "LONG_PRESS" ;
        longPressEvent.x = x ;
        longPressEvent.y = y ;

        //appaliteEngine.GestureEvents(longPressEvent);

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button)
    {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY)
    {
        panEvent.type = "PAN" ;
        panEvent.x = x ;
        panEvent.y = y ;

        //appaliteEngine.GestureEvents(panEvent);

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button)
    {
        Vector2 point = Engine.Canvas.screenToStageCoordinates(new Vector2(x, y));

        Actor actor = Engine.Canvas.hit(point.x, point.y, true) ;

        panStopEvent.type = "PAN_STOP" ;
        panStopEvent.x = x ;
        panStopEvent.y = y ;

        panStopEvent.nodeName = "null" ;

        if (actor != null)
        {
            nodes.put(actor) ;
            panStopEvent.node = (Node) actor;
            panStopEvent.nodeName = actor.getName() ;
        }

        engine.GestureEvents(panStopEvent);

        nodes = null ;
        nodes = new JSONArray() ;

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance)
    {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2)
    {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
