package com.mango.engine.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mango.engine.Engine;
import com.mango.engine.Node;

import json.JSONObject;

public class PositionComponent implements Component
{
    public PositionComponent(Node node)
    {
        JSONObject position = node.getComponentData("Position");

        node.setPosition((float) position.getDouble("x"), (float)position.getDouble("y"));
    }

    @Override
    public void init()
    {

    }

    @Override
    public void update(Node node, Batch spriteBatch)
    {
        //System.out.println("PositionComponent - update");
    }
}
