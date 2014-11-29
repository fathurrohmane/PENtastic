package com.infinithinks.pentastic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.infinithinks.others.BodyEditorLoader;
import com.infinithinks.others.Constant;

public class Track {
	
	//level stuff
	private Body trackModel;
	private Texture trackTexture;
	private Sprite trackSprite;
	private Vector2 trackModelOrigin;
	private Vector2 trackPos;
	private Vector2 trackModelPos;
	private String trackName;
	
	//box2d stuff
	private World world;
	
	public Track()
	{
		
	}
	
	public Track(String trackName, World world)
	{
		this.trackName = trackName;
		this.world = world;
		
		//initiating
		createSprites();
		createLevelBody();
		
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		//set move model
		trackModel.setTransform(trackModelPos,0f);
		
		Color c = batch.getColor();
		
		//set pos track model to sprite
		trackPos = trackModel.getPosition().sub(trackModelOrigin);
		trackSprite.setPosition(trackPos.x, trackPos.y);
		trackSprite.setOrigin(trackModelOrigin.x, trackModelOrigin.y);
		//trackSprite.setColor(c.r, c.a, c.b, 0.3f);
		
		if(Constant.DRAW_SPRITE)
		{
			trackSprite.draw(batch);
		}
		
		
	}
	
	public void dispose()
	{
		world.dispose();
		trackTexture.dispose();
	}
	
	private void createLevelBody()
	{
		//load
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("level/"+trackName+".json"));
		
		//create bodydef
		BodyDef bd = new BodyDef();
		//bd.position.set(0, 0);
		bd.type = BodyType.DynamicBody;
		
		//create fixturedef
		FixtureDef fd = new FixtureDef();
		fd.density = 0;
		fd.friction = 1;
		fd.restitution = 0;
		
		//create body		
		trackModel = world.createBody(bd);
		trackModel.setUserData(this);
		trackModelPos = new Vector2(-200, -200);
		trackModel.setTransform(trackModelPos,0f);
		
		//create body fixture
		loader.attachFixture(trackModel,"Name",fd , trackTexture.getWidth());
		trackModelOrigin = loader.getOrigin("Name", trackTexture.getWidth()).cpy();
				
		
	}
	
	private void createSprites()
	{
		// create texture and sprite for track
		trackTexture = new Texture(Gdx.files.internal("level/"+trackName+".png"));
		trackTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);		
		trackSprite = new Sprite(trackTexture);
		trackSprite.setSize(trackTexture.getWidth(), trackTexture.getHeight());
		
	}

}
