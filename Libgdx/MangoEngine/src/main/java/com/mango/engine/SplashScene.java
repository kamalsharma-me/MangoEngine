package com.mango.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Iterator;

public class SplashScene extends GameScene implements MangoScene
{
    MenuScene menuScene ;

    public SplashScene(String sceneName)
    {
        super(sceneName);
    }

    @Override
    public void init()
    {
        System.out.println("Splash Scene Init.");

    }

    @Override
    public void update()
    {
        if (Gdx.input.justTouched())
        {
            Engine.atlasPool.getTextureFromAtlasPool("GameBG") ;
            //
        }
    }

    @Override
    public void Events(Event event)
    {
        if (event.type.equals(Event.TOUCH_DOWN))
        {
            menuScene = new MenuScene("MenuScene") ;
            Engine.setPresentScene(menuScene);



            //System.out.println(event.nodeName);
        }
    }
}
