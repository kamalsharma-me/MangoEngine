package com.mango.engine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GameScene extends Group
{
    private String name ;

    public GameScene(String sceneName)
    {
        this.name = sceneName ;
        setName(sceneName);
    }

    public enum SceneTransitions
    {
        slideInLeft,
        slideInRight,
        slideInTop,
        slideInBottom,
        fade,
    }

    @Override
    public void setName(String name)
    {
        super.setName(name);
    }

    @Override
    public String getName()
    {
        return super.getName();
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);

        batch.setColor(getColor());
    }


    public void addNode(String node1, Node node2)
    {
        Node before = this.findActor(node1);

        addActorBefore(before, node2);
    }

    public void getNodeData(String name)
    {

    }

    public void addNode(Actor actor)
    {
        Engine.presentScene.addActor(actor);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }
}