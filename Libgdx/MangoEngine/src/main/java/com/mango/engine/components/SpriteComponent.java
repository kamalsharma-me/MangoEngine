package com.mango.engine.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mango.engine.Engine;
import com.mango.engine.Node;

public class SpriteComponent
{
    TextureRegion textureRegion ;

    public SpriteComponent(Node node)
    {
        TextureRegion textureName = Engine.atlasPool.getTextureFromAtlasPool("BackgroundMenu") ;

        textureRegion = new TextureRegion(textureName) ;

        node.setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

        node.setOrigin(textureRegion.getRegionWidth() / 2, textureRegion.getRegionHeight()/2);
    }

    public void render(Node node, Batch spriteBatch)
    {
        spriteBatch.draw(textureRegion, node.getX(), node.getY(), node.getOriginX(), node.getOriginY(), node.getWidth(), node.getHeight(), node.getScaleX(), node.getScaleY(), node.getRotation()) ;
    }
}
