package com.mango.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;
import json.JSONArray;
import json.JSONObject;

public class AtlasPool
{
    private JSONObject atlasPool = new JSONObject() ;
    private TextureAtlas textureAtlas ;

    public AtlasPool() {}

    public void readAtlas(JSONArray array)
    {
        for(Integer i = 0; i < array.length(); i++)
        {
            setTextureToAtlasPool(array.getString(i)) ;
        }
    }

    private void setTextureToAtlasPool(String atlasName)
    {
        textureAtlas = new TextureAtlas(atlasName) ;

        Array<TextureAtlas.AtlasRegion> atlasRegionArray = textureAtlas.getRegions() ;

        for (TextureAtlas.AtlasRegion key : atlasRegionArray)
        {
            atlasPool.put(key.name, key);
        }
    }

    public TextureRegion getTextureFromAtlasPool(String textureName)
    {
        return (TextureRegion) atlasPool.get(textureName) ;
    }
}
