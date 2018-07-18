package com.mango.engine;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;

import java.util.Iterator;

import json.JSONArray;
import json.JSONObject;

public class ActionPool
{
    public JSONObject actionPool = new JSONObject() ;

    public void readAction(JSONArray array)
    {
        for(Integer i = 0; i < array.length(); i++)
        {
            setActionToActionPool(array.getString(i)) ;
        }
    }

    public void setActionToActionPool(String name)
    {
        String file             = Engine.readJSONFromFile(name) ;
        JSONObject actionsData  = new JSONObject(file) ;

        for(Iterator<String> keys = actionsData.getJSONObject("Actions").keys(); keys.hasNext();)
        {
            String actionName = keys.next() ;

            actionPool.put(actionName, actionsData.getJSONObject("Actions").getJSONObject(actionName)) ;
        }

        System.out.println(actionPool.getJSONObject("moveBy")) ;
    }

    public JSONObject getActionFromEffectPool(String actionName)
    {
        return actionPool.getJSONObject(actionName) ;
    }

    public JSONObject get()
    {
        return actionPool ;
    }

}
