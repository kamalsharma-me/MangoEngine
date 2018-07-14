package com.gametest;

import com.badlogic.gdx.Game;
import com.mango.engine.Engine;
import com.mango.engine.MangoEngine;
import com.mango.engine.SplashScene;

public class Main extends Game implements MangoEngine
{
	@Override
	public void create()
	{
		Engine engine = new Engine(this) ;

		engine.create("data");

		SplashScene splashScene = new SplashScene("SplashScene") ;
		engine.initWithScene(splashScene) ;
	}

	@Override
	public void update()
	{
		System.out.println("Main");
	}
}
