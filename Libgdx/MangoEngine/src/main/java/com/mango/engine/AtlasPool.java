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
    public JSONObject atlasPool = new JSONObject() ;
    private TextureAtlas textureAtlas ;

    public AtlasPool() {}

    public void readAtlas(JSONArray array)
    {
        for(Integer i = 0; i < array.length(); i++)
        {
            setTextureToAtlasPool(array.getString(i)) ;
        }
    }

    public void setTextureToAtlasPool(String atlasName)
    {
        textureAtlas = new TextureAtlas(atlasName) ;

        Array<TextureAtlas.AtlasRegion> atlasRegionArray = textureAtlas.getRegions() ;

        for(Iterator<TextureAtlas.AtlasRegion> keys = atlasRegionArray.iterator(); keys.hasNext();)
        {
            TextureAtlas.AtlasRegion key = keys.next() ;

            // Setting the texture filter
            // @

            /*JSONObject textureData = new JSONObject() ;
            textureData.put("texture", key.getTexture()) ;
            textureData.put("x", key.getRegionX()) ;
            textureData.put("y", key.getRegionY()) ;
            textureData.put("w", key.getRegionWidth()) ;
            textureData.put("h", key.getRegionHeight()) ;*/

            atlasPool.put(key.name, key) ;
        }

        //System.out.println(atlasPool) ;
    }

    public TextureRegion getTextureFromAtlasPool(String textureName)
    {
        TextureRegion textureData = (TextureRegion) atlasPool.get(textureName);

        return textureData ;

        //return (TextureRegion) textureData.get("texture") ;
    }
}
