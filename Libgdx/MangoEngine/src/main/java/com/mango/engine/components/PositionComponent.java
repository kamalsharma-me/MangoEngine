package com.mango.engine.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mango.engine.Engine;
import com.mango.engine.Node;

public class PositionComponent implements Component
{
    TextureRegion textureRegion ;

    public PositionComponent(Node node)
    {
        System.out.println("PositionComponent - init");
    }

    @Override
    public void init()
    {

    }

    @Override
    public void update(Node node, Batch spriteBatch)
    {
        System.out.println("PositionComponent - update");
    }
}
