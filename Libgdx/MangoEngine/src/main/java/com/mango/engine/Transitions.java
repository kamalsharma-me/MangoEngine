package com.mango.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Transitions
{
    Engine engine ;

    GameScene presentScene ;
    GameScene activeScene ;
    Boolean removeScene;

    public Integer isTransitionActive = 0 ;

    public Transitions(Boolean removeScene)
    {
        this.presentScene = engine.presentScene ;
        this.activeScene = Engine.activeScene ;

        this.removeScene = removeScene ;

    }

    public void create(GameScene.SceneTransitions transition)
    {
        if(transition == GameScene.SceneTransitions.slideInLeft)
        {
            this.slideInLeft();
        }
        if(transition == GameScene.SceneTransitions.slideInTop)
        {
            this.slideInTop();
        }
        if(transition == GameScene.SceneTransitions.fade)
        {
            this.fadeWithDuration();
        }
    }

    public void slideInLeft()
    {


        MoveToAction slideInLeft = Actions.moveTo(Gdx.graphics.getWidth(), 0) ;

        Engine.presentScene.setX(Gdx.graphics.getWidth());

        slideInLeft.setX(0);
        slideInLeft.setDuration(0.5f);

        Action actions = sequence(slideInLeft, completeAction);

        Engine.presentScene.addAction(actions);

        System.out.println("slideInLeft");
    }

    public void slideInRight()
    {
        MoveToAction slideInRight = Actions.moveTo(-Gdx.graphics.getWidth(), 0) ;

        presentScene.setX(-Gdx.graphics.getWidth());

        slideInRight.setX(0);
        slideInRight.setDuration(0.5f);

        Action actions = sequence(slideInRight, completeAction);

        presentScene.addAction(actions);
    }

    public void slideInTop()
    {
        System.out.println(Engine.activeScene.name + " " + Engine.presentScene.name);
        MoveToAction slideInTop = Actions.moveTo(0, Gdx.graphics.getHeight()) ;

        Engine.presentScene.setY(Gdx.graphics.getHeight());

        slideInTop.setY(0);
        slideInTop.setDuration(0.5f);

        Action actions = sequence(slideInTop, completeAction);

        Engine.presentScene.addAction(actions);
    }

    public void slideInBottom()
    {
        MoveToAction slideInBottom = Actions.moveTo(0, -Gdx.graphics.getHeight()) ;

        Engine.presentScene.setY(-Gdx.graphics.getHeight());

        slideInBottom.setY(0);
        slideInBottom.setDuration(0.5f);

        Action actions = sequence(slideInBottom, completeAction);

        Engine.presentScene.addAction(actions);
    }

    // Active fadeOut
    // Present FadeIn
    public void fadeWithDuration()
    {

        Color color = new Color();

        Engine.presentScene.setColor(color.r, color.g, color.b, 1);

        Engine.activeScene.addAction(Actions.fadeOut(1.0f));

        Action fadeIn = sequence(Actions.fadeIn(1.0f), completeAction);
        Engine.presentScene.addAction(fadeIn);
    }

    protected void noTransition()
    {
        Action action = sequence(completeAction);

        Engine.presentScene.addAction(action);
    }

    Action completeAction = new Action()
    {
        public boolean act( float delta )
        {
            Color color = new Color() ;
            System.out.println("completeAction");

            Engine.activeScene.clearActions() ;
            Engine.activeScene.clearChildren() ;
            Engine.activeScene.remove() ;

            if(!removeScene)
            {
                //engine.scenePool.getSceneFromScenePool("GameScene").remove() ;
            }

            engine.activeScene = Engine.presentScene ;

            // Resetting the alpha values
            //presentScene.setColor(color.r, color.g, color.b, 1) ;
            //activeScene.setColor(color.r, color.g, color.b, 1) ;

            Engine.presentScene.clearActions() ;

            isTransitionActive = 0 ;

            return true ;
        }
    };
}
