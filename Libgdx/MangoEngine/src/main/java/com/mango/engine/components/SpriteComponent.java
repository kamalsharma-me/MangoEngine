package com.mango.engine.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mango.engine.Engine;
import com.mango.engine.Node;
import com.mango.engine.SpritePool;

import json.JSONArray;
import json.JSONObject;

public class SpriteComponent implements Component
{
    TextureRegion textureRegion ;

    public SpriteComponent()
    {

    }

    public SpriteComponent(Node node)
    {
        JSONObject sprite                       = node.getComponentData("Sprite");
        JSONArray getTextureFromSpritePool      = SpritePool.getSpriteFromSpritePool(sprite.getString("name"));

        TextureRegion getTextureFromAtlasPool   = Engine.atlasPool.getTextureFromAtlasPool((String) getTextureFromSpritePool.get(0)) ;

        textureRegion                           = new TextureRegion(getTextureFromAtlasPool) ;

        node.setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        node.setOrigin(textureRegion.getRegionWidth() / 2, textureRegion.getRegionHeight()/2);
    }

    // Interface Methods
    @Override
    public void init()
    {
        System.out.println("ImageComponent init.");
    }

    @Override
    public void update(Node node, Batch spriteBatch)
    {
        spriteBatch.draw(textureRegion, node.getX(), node.getY(), node.getOriginX(), node.getOriginY(), node.getWidth(), node.getHeight(), node.getScaleX(), node.getScaleY(), node.getRotation()) ;
    }
}
