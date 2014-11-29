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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.infinithinks.others.BodyEditorLoader;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.GameState;
import com.infinithinks.pentastic.PortalHandler;

public class Portal {
	
	//main
	public PortalHandler main;
	
	//obstacle stuff
	public Body obstacleBody;
	public Texture obstacleTexture;
	public Sprite obstacleSprite;
	public Vector2 obstacleModelOrigin;
	public Vector2 obstaclePos;
	public Vector2 obstacleBodyPos;
	public Body body;
	
	//Box2d stuff
	public World world;
	public Camera camera;
	
	//identitiy
	public String portalName;
	public int portalId;
	public float rotateValue = 0.01f;
	public float rotation = 0f;
	public int clockwise;
	
	public float veloxityY = 1f;
	public float maxY;
	public float minY;
	public float veloxityX = 1f;
	public float maxX;
	public float minX;
	
	//location
	

	public Portal(PortalHandler main, World world, Camera camera, Vector2 pos,String portal, int clockwise, float speed)
	{
		this.main = main;
		this.world = world;
		this.camera = camera;
		this.obstacleBodyPos = pos;
		this.portalName = portal;
		this.clockwise = clockwise;
		this.rotateValue = speed;
		
		createSprite();
		
		if(portalName == Constant.PORTALRANDOM)
		{
			portalId = Constant.PORTALRANDOMID;
			createCustomBody();
		}
		if(portalName == Constant.PORTAL)
		{
			portalId = Constant.PORTALID;
			createCustomBody();
		}
		
		//createCustomBody();
		
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		switch (main.main.gameState.currentState) {
		case GameState.PAUSE:
						
			break;
		case GameState.LOSE:
			
			break;
		case GameState.FINISH:
			
			break;

		default:
			switch (portalId) {
			case Constant.PORTALID:
				rotation += rotateValue*clockwise;
				break;
			case Constant.PORTALRANDOMID:
				rotation += rotateValue*clockwise;
				break;

			default:
				break;
			}
			break;
		}		
		obstacleBody.setTransform(obstacleBodyPos, rotation);
		
		obstaclePos = obstacleBody.getPosition().sub(obstacleModelOrigin);
		obstacleSprite.setPosition(obstaclePos.x, obstaclePos.y);
		obstacleSprite.setRotation((float)Math.toDegrees(obstacleBody.getAngle()));
		obstacleSprite.setOrigin(obstacleModelOrigin.x, obstacleModelOrigin.y);
		obstacleSprite.draw(batch);
		
		
	}
	
	public void createRoundBody(){
		//set shape
		CircleShape shape = new CircleShape();
		shape.setRadius(16f);
		
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
		obstacleBody = world.createBody(bd);
		obstacleBody.setUserData(this);
		obstacleBody.createFixture(fd);
		
		rotation = 0f;
		
	}
	
	public void createCustomBody()
	{
		//load body
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("obstacle/"+portalName+".json"));
		
		//create bodydef
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		
		//create fixturedef
		FixtureDef fd = new FixtureDef();
		fd.density = 0;
		fd.friction = 0;
		fd.restitution = 0;
		
		//create body
		obstacleBody = world.createBody(bd);
		obstacleBody.setUserData(this);
		obstacleBody.setTransform(obstacleBodyPos, rotation);
		
		//create body fixture
		loader.attachFixture(obstacleBody, "Name", fd, obstacleTexture.getWidth());
		obstacleModelOrigin = loader.getOrigin("Name", obstacleTexture.getWidth()).cpy();
		
	}
	
	public void createSprite()
	{
		obstacleTexture = new Texture(Gdx.files.internal("obstacle/"+portalName+".png"));
		obstacleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		obstacleSprite = new Sprite(obstacleTexture);
		obstacleSprite.setSize(obstacleTexture.getWidth(), obstacleTexture.getHeight());
		obstacleSprite.setOrigin(obstacleTexture.getWidth() / 2, obstacleTexture.getHeight() / 2);
	}

}
