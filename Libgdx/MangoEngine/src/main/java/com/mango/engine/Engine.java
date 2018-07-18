package com.mango.engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Iterator;

import json.JSONArray;
import json.JSONObject;

public class Engine extends ApplicationAdapter implements Screen
{
    private MangoEngine mangoEngine ;
    private static MangoScene mangoScene ;
    private Game game ;
    public static Stage Canvas ;

    public static AtlasPool atlasPool ;
    public static SpritePool spritePool ;
    public static ActionPool actionPool ;
    JSONObject appObjectData;

    private Viewport viewport;
    public static  OrthographicCamera camera;

    public static final float GAME_WORLD_WIDTH = 1080 ;
    public static final float GAME_WORLD_HEIGHT = 1920 ;

    // GESTURES
    GestureDetector gd ;
    Gestures gestures ;

    static GameScene activeScene ;
    public static GameScene presentScene ;

    JSONObject map = new JSONObject() ;

    public Engine(Game game)
    {
        mangoEngine = (MangoEngine) game;

        this.game = game ;

        game.setScreen(this);

        atlasPool = new AtlasPool() ;
        spritePool = new SpritePool() ;
        actionPool = new ActionPool() ;
        float w = Gdx.graphics.getWidth();
        float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();

        viewport = new FillViewport (GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
        viewport.apply();

        Canvas = new Stage(viewport) ;

        System.out.println("w " + w + " h " + h);

        gestures= new Gestures(this) ;

        gd      = new GestureDetector(gestures) ;
        InputMultiplexer im = new InputMultiplexer(gd);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void create()
    {
        System.out.println("Engine->Create") ;
    }

    // Call when screen show
    @Override
    public void show()
    {

    }

    public void create(String fileName)
    {
        String data = readJSONFromFile(fileName) ;

        readAppFile(data);
    }

    private void readAppFile(String data)
    {
        appObjectData = new JSONObject(data) ;
        String initSceneName = null ;

        // Retrieve all keys
        for(Iterator<String> keys = appObjectData.getJSONObject("app").keys(); keys.hasNext();)
        {
            String key = keys.next() ;

            if(key.compareTo("Atlas") == 0)
            {
                JSONArray atlas = appObjectData.getJSONObject("app").getJSONObject("Atlas").getJSONArray("files") ;

                atlasPool.readAtlas(atlas);
            }
            else if(key.compareTo("Sprites") == 0)
            {
                JSONArray fileName = appObjectData.getJSONObject("app").getJSONObject("Sprites").getJSONArray("files") ;

                spritePool.readSprite(fileName);
            }
            else if(key.compareTo("Actions") == 0)
            {
                JSONArray fileName = appObjectData.getJSONObject("app").getJSONObject("Actions").getJSONArray("files") ;
System.out.println(fileName);
                actionPool.readAction(fileName);
            }
        }

        //activeScene = scenePool.getSceneFromScenePool(initSceneName) ;
        //initWithScene(initSceneName) ;
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Canvas.act(Gdx.graphics.getDeltaTime());
        Canvas.draw();
        camera.update();
        mangoScene.update() ;

        for(Integer i = 0; i < Engine.presentScene.getChildren().size; i++)
        {
            Node node = (Node) Engine.presentScene.getChildren().get(i);

           // node.update();
        }
    }

    @Override
    public void hide()
    {

    }

    // SCENE HANDLING
    public void initWithScene(GameScene mangoScene)
    {
        activeScene = mangoScene ;
        this.renderScene(mangoScene);
    }

    public static void renderScene(GameScene scene)
    {
        presentScene = scene ;

        bindSceneData();

        mangoScene = (MangoScene) scene;
        mangoScene.init();
        Canvas.addActor(scene) ;

        System.out.println("renderScene");
    }

    // Scene Data
    // Caution - check json exit
    public static void bindSceneData()
    {
        JSONObject json ;
        String data = Engine.readJSONFromFile(presentScene.getName()) ;

        JSONObject nodes = new JSONObject(data).getJSONObject(presentScene.getName()) ;

        for(Iterator<String> keys = nodes.keys(); keys.hasNext();)
        {
            String nodeName = keys.next() ;
            JSONObject jsonObject = nodes.getJSONObject(nodeName) ;

            createNode(nodeName, jsonObject);
        }
    }

    private static void createNode(String nodeName, JSONObject jsonObject)
    {
        Node node = new Node() ;

        node.setName(nodeName);
        node.init(jsonObject) ;
        presentScene.addNode(node);
    }

    public static void setPresentScene(GameScene sceneName)
    {
        renderScene(sceneName) ;

        Transitions transitions = new Transitions() ;
        transitions.create(GameScene.SceneTransitions.slideInLeft);
    }

    public static void changeScene(MangoScene mangoScene)
    {
        Engine.mangoScene = mangoScene ;
        mangoScene.init() ;
    }

    public void GestureEvents(Event event)
    {
        if (event.type.equals(Event.TOUCH_DOWN))
        {

        }

        else if(event.type.equals(Event.TAP))
        {

        }

        else if(event.type.equals(Event.PAN_STOP))
        {

        }

        else if(event.type.equals("LONG_PRESS"))
        {

        }

        else if(event.type.equals("PAN"))
        {

        }

        mangoScene.Events(event);
    }

    // JSON
    public static String readJSONFromFile(String fileName)
    {
        FileHandle file = Gdx.files.internal(fileName + ".json");

        return file.readString() ;
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
        float aspectRatio = (float)width / (float)height ;

        if(camera != null)
        {
            Canvas.getViewport().update(width, height);
            camera.update();
        }



        //System.out.println("Ratio " + Math.abs(viewport.getRightGutterX()) + " " + Math.abs(viewport.getTopGutterY()));
        //System.out.println("Ratio " + aspectRatio + " w " + width + " h " + height + " " + ButtonPlay.getY());

        //viewport.update(width, height, true);
        //stage.setViewport(400, 400);
        //Canvas.getCamera().position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);
        //camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0) ;
    }
}
