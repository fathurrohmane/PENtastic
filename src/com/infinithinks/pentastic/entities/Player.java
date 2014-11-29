package com.infinithinks.pentastic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.TouchHandler;
import com.infinithinks.pentastic.level.Level;

public class Player{

	private Level main;
	
	//player stuff
	private Body playerModel;
	private Texture playerTexture;
	private Sprite playerSprite;
	private Vector2 playerModelOrigin;
	private Vector2 playerPos;
	private Body body;
	private float rotation;
	
	//Box2d stuff
	private World world;
	private Camera camera;
	
	public Player(Level main){
		this.main = main;
	}
	
	public Player(World world,Camera camera){
		
		this.world = world;
		this.camera = camera;
		
		createPlayerBody();
		createSprites();
			
	}
	
	public void render(float delta, SpriteBatch batch, Camera camera,TouchHandler touch){
		
		playerPos.x = camera.position.x;
		playerPos.y = camera.position.y;
		body.setTransform(playerPos, 0f);
		playerSprite.setPosition(playerPos.x - playerTexture.getWidth() / 2, playerPos.y - playerTexture.getHeight() / 2);
		
		if(Constant.DRAW_SPRITE)
		{
			playerSprite.draw(batch);
		}
		//rotation
		playerSprite.setRotation(rotation);
		rotation += touch.getRotationSpeedX();
		
		//pos
		if(Constant.IS_PRINT)
		{
			System.out.println(camera.position.x+" : "+camera.position.y);
		}
		
		
	}
	
	private void createPlayerBody(){
		//set shape
		CircleShape shape = new CircleShape();
		shape.setRadius(36f);
		
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
		playerPos = new Vector2();
		playerPos.x = camera.position.x;
		playerPos.y = camera.position.y;
		body.setTransform(playerPos, 0f);
		
		rotation = 0f;
		
	}
	
	private void createSprites()
	{
		//create texture and sprite for track
		playerTexture = new Texture(Gdx.files.internal("player/player_5.png"));
		playerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		playerSprite = new Sprite(playerTexture);
		playerSprite.setSize(playerTexture.getWidth(), playerTexture.getHeight());
		
	}
	
	
}
