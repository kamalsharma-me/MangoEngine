package com.mango.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mango.engine.components.*;

import java.util.Iterator;

import json.JSONObject;

public class Node extends Actor
{
    TextureRegion textureRegion ;
    SpriteBatch sprBatch ;

    JSONObject componentData ;

    public Node()
    {

    }

    public void init(JSONObject jsonObject)
    {
        FileHandle[] files = Gdx.files.internal("$ANDROID_PROJECT").list();

        System.out.println(files) ;

        componentData = new JSONObject();

        if(jsonObject.has("Sprite"))
        {
            ImageComponent imageComponent = new ImageComponent(this) ;

            componentData.put("ImageComponent", imageComponent) ;
        }

        if(jsonObject.has("Animation"))
        {
            AnimationComponent animationComponent = new AnimationComponent(this) ;

            componentData.put("AnimationComponent", animationComponent) ;
        }
    }

    public void addComponent()
    {

    }

    public Component getComponent(String componentName)
    {
        Component component = (Component) componentData.get(componentName);

        return component ;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);

        for(Iterator<String> iter = componentData.keys();iter.hasNext();)
        {
            String key = iter.next();

            getComponent(key).update(this, batch);
        }
    }

    @Override
    public void act(float delta)
    {

    }
}
