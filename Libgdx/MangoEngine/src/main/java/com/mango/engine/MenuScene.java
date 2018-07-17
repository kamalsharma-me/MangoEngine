package com.mango.engine;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import json.JSONObject;

public class MenuScene extends GameScene implements MangoScene
{
    SplashScene  splashScene ;
    public JSONObject json ;

    MenuScene(String sceneName)
    {
        super(sceneName);
    }

    @Override
    public void init()
    {
        System.out.println("Menu Scene Init.");

        /*String data = Engine.readJSONFromFile("SplashScene") ;

        json = new JSONObject(data).getJSONObject("MenuScene") ;

        JSONObject BackgroundObject = json.getJSONObject("Background") ;

        System.out.println(BackgroundObject.has("Sprite"));

        Sprite sprite = new Sprite() ;
        sprite.setPosition(0,0);
        sprite.setWidth(sprite.getWidth());
        sprite.setHeight(sprite.getHeight());
        sprite.create("GameBG");

        Engine.presentScene.addActor(sprite);*/
    }

    @Override
    public void update()
    {

    }

    @Override
    public void Events(Event event)
    {
        if (event.type.equals(Event.TOUCH_DOWN))
        {
            splashScene = new SplashScene("SplashScene") ;
            Engine.setPresentScene(splashScene);
        }
    }
}
