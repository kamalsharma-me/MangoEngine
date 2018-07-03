package com.mango.engine.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mango.engine.Engine;
import com.mango.engine.Node;

public class ImageComponent implements Component
{
    TextureRegion textureRegion ;

    public ImageComponent()
    {

    }

    public ImageComponent(Node node)
    {
        TextureRegion textureName = Engine.atlasPool.getTextureFromAtlasPool("GameBG") ;

        textureRegion = new TextureRegion(textureName) ;

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
        //System.out.println("ImageComponent - update");
        //System.out.println(Engine.presentScene.getChildren());

        spriteBatch.draw(textureRegion, node.getX(), node.getY(), node.getOriginX(), node.getOriginY(), node.getWidth(), node.getHeight(), node.getScaleX(), node.getScaleY(), node.getRotation()) ;
    }
}
