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

        if(jsonObject.has("Position"))
        {
            PositionComponent positionComponent = new PositionComponent(this) ;

            componentData.put("PositionComponent", positionComponent) ;
        }

        if(jsonObject.has("Animation"))
        {
            AnimationComponent animationComponent = new AnimationComponent(this) ;

            componentData.put("AnimationComponent", animationComponent) ;
        }
    }

    /*
    OPERATIONS
     */

    // Get specific node from present scene -> canvas
    public Node getNodeFromScene(String name)
    {
        return Engine.Canvas.getRoot().findActor(name) ;
    }

    // COMPONENT OPERATIONS
    public void addComponent()
    {

    }

    public JSONObject getComponentData(String componentName)
    {
        JSONObject component = null ;

        if(this.json.has(componentName))
        {
            component = this.json.getJSONObject(componentName) ;
        }

        return component ;
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

        Engine.camera.update();

        batch.setProjectionMatrix(Engine.camera.combined);

        for(Iterator<String> iter = componentData.keys(); iter.hasNext();)
        {
            String key = iter.next();

            getComponent(key).update(this, batch);
        }
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }
}
