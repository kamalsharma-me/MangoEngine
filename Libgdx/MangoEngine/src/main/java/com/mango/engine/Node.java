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
    JSONObject json ;

    public Node()
    {

    }

    public void init(JSONObject jsonObject)
    {
        this.json = jsonObject ;

        FileHandle[] files = Gdx.files.internal("$ANDROID_PROJECT").list();

        //System.out.println(files) ;

        componentData = new JSONObject();

        if(jsonObject.has("Sprite"))
        {
            SpriteComponent spriteComponent = new SpriteComponent(this) ;

            componentData.put("SpriteComponent", spriteComponent) ;
        }

        if(jsonObject.has("Animation"))
        {
            AnimationComponent animationComponent = new AnimationComponent(this) ;

            componentData.put("AnimationComponent", animationComponent) ;
        }
    }

    // COMPONENT OPERATIONS
    public void addComponent()
    {

    }

    public void getComponentData(String componentName)
    {
        if(this.json.has(componentName))
        {
            System.out.println(this.json.getJSONObject(componentName));
        }
        else
        {
            System.out.println("Component data not found.");
        }
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
