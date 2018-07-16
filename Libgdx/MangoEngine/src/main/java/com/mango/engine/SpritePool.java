package com.mango.engine;

import java.util.Iterator;

import json.JSONArray;
import json.JSONObject;

public class SpritePool
{
    public static JSONObject spritePool = new JSONObject() ;

    public void readSprite(JSONArray array)
    {
        for(Integer i = 0; i < array.length(); i++)
        {
            setSpriteToSpritePool(array.getString(i)) ;
        }
    }

    public void setSpriteToSpritePool(String name)
    {
        String file = Engine.readJSONFromFile(name) ;

        JSONObject sprite = new JSONObject(file) ;

        for(Iterator<String> keys = sprite.getJSONObject("Sprites").keys(); keys.hasNext();)
        {
            String spriteName = keys.next() ;

            spritePool.put(spriteName, sprite.getJSONObject("Sprites").getJSONArray(spriteName)) ;
        }
    }

    public JSONObject get()
    {
        return spritePool;
    }

    public static JSONArray getSpriteFromSpritePool(String name)
    {
        return (JSONArray) spritePool.get(name);
    }
}
