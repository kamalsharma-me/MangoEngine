package com.mango.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite extends Node
{
    TextureRegion textureRegion ;

    public void create(String atlasRegion)
    {
        TextureRegion textureName = Engine.atlasPool.getTextureFromAtlasPool("GameBG") ;

        textureRegion = new TextureRegion(textureName) ;

        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

        setOrigin(textureRegion.getRegionWidth() / 2, textureRegion.getRegionHeight()/2);
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * alpha);

        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation()) ;

        batch.setColor(color.r, color.g, color.b, 1f);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta) ;
    }
}
