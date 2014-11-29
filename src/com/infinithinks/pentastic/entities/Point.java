package com.infinithinks.pentastic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Point {
	

	//Point stuff	
	private Texture pointTexture;
	private Sprite pointSprite;
	private Vector2 pointModelOrigin;
	private Vector2 pointPos = new Vector2();
	private Body body;
	private String type;
	
	//Box2d stuff
	private World world;
	
	public Point(){
		
	}
	
	public Point(World world,Vector2 startPoint,String type){
		
		this.world = world;
		this.pointPos = startPoint;
		this.type = type;
		createPlayerBody();
		//createSprites();
			
	}
	
	public void render(float delta, SpriteBatch batch){
		
		//startPointPos.x = camera.position.x;
		//startPointPos.y = camera.position.y;
		//body.setTransform(startPointPos, 0f);
		//startPointSprite.setPosition(startPointPos.x - startPointTexture.getWidth() / 2, startPointPos.y - startPointTexture.getHeight() / 2);
		//startPointSprite.draw(batch);
		
		
	}
	
	private void createPlayerBody(){
		//set shape
		CircleShape shape = new CircleShape();
		shape.setRadius(60f);
		
		//create bodydef
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		
		//create fixture
		FixtureDef fd = new FixtureDef();
		fd.density = 0;
		fd.friction = 1;
		fd.restitution = 0;
		fd.shape = shape;
		
		//create body
		body = world.createBody(bd);
		body.setUserData(this);
		body.createFixture(fd);
		
		//set posisi
		body.setTransform(pointPos, 0f);
		
	}
	
	private void createSprites()
	{
		//create texture and sprite for track
		
		if(type == "start")
		{
			pointTexture = new Texture(Gdx.files.internal("player/player.png"));
			pointTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		if(type == "finish")
		{
			pointTexture = new Texture(Gdx.files.internal("player/player.png"));
			pointTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		pointSprite = new Sprite(pointTexture);
		pointSprite.setSize(pointTexture.getWidth(), pointTexture.getHeight());
		
	}
	

}
