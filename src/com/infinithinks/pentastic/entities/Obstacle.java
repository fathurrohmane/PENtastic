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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.infinithinks.others.BodyEditorLoader;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.GameState;
import com.infinithinks.pentastic.ObstacleHandler;

public class Obstacle {
	
	//main
	public ObstacleHandler main;
	
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
	public String obstacleName;
	public int obstacleId;
	public float rotateValue = 0.01f;
	public float rotation = 0f;
	public int clockwise;
	
	public float veloxityY = 1f;
	public float maxY;
	public float minY;
	public float veloxityX = 1f;
	public float maxX;
	public float minX;
	
	public Obstacle(ObstacleHandler main, World world, Camera camera, Vector2 pos,String obstacle, int clockwise, float speed)
	{
		this.main = main;
		this.world = world;
		this.camera = camera;
		this.obstacleBodyPos = pos;
		this.obstacleName = obstacle;
		this.clockwise = clockwise;
		this.rotateValue = speed;
		
		if(obstacleName == Constant.KINCIR)
		{
			obstacleId = Constant.KINCIRID;
		}else if(obstacleName == Constant.BOX)
		{
			obstacleId = Constant.BOXID;
		}else if(obstacleName == Constant.MOVETRACK_1)
		{
			obstacleId = Constant.MOVETRACK_1ID;
		}else if(obstacleName == Constant.MOVETRACK_2)
		{
			obstacleId = Constant.MOVETRACK_2ID;
		}else if(obstacleName == Constant.CIRCLE_1)
		{
			obstacleId = Constant.CIRCLE_1ID;
		}else if(obstacleName == Constant.CIRCLE_2)
		{
			obstacleId = Constant.CIRCLE_2ID;
		}else if(obstacleName == Constant.CIRCLE_3)
		{
			obstacleId = Constant.CIRCLE_3ID;
		}else if(obstacleName == Constant.KINCIR_2)
		{
			obstacleId = Constant.KINCIR_2ID;
		}else if(obstacleName == Constant.GEAR)
		{
			obstacleId = Constant.GEARID;
		}else if(obstacleName == Constant.BOX_2)
		{
			obstacleId = Constant.BOX_2ID;
		}else if(obstacleName == Constant.BOX_2)
		{
			obstacleId = Constant.BOX_2ID;
		}else if(obstacleName == Constant.ROUND)
		{
			obstacleId = Constant.ROUNDID;
		}
		
		createSprite();
		createObstacleBody();
		
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
			switch (obstacleId) {
			case Constant.KINCIRID:
				rotation += rotateValue*clockwise;
				break;
			case Constant.BOXID:
				obstacleBodyPos.y -= rotateValue;
				if(obstacleBodyPos.y > maxY || obstacleBodyPos.y < minY)
				{
					rotateValue *= -1;
				}
				break;
			case Constant.MOVETRACK_1ID:
				obstacleBodyPos.y -= rotateValue;
				if(obstacleBodyPos.y > maxY || obstacleBodyPos.y < minY)
				{
					rotateValue *= -1;
				}
				break;
			case Constant.MOVETRACK_2ID:
				obstacleBodyPos.y -= rotateValue;
				if(obstacleBodyPos.y > maxY || obstacleBodyPos.y < minY)
				{
					rotateValue *= -1;
				}
				break;
			case Constant.CIRCLE_1ID:
				rotation += rotateValue*clockwise;
				break;
			case Constant.CIRCLE_2ID:
				rotation += rotateValue*clockwise;
				break;
			case Constant.CIRCLE_3ID:
				rotation += rotateValue*clockwise;
				break;
			case Constant.GEARID:
				rotation += rotateValue*clockwise;
				break;
			case Constant.KINCIR_2ID:
				rotation += rotateValue*clockwise;
				break;
			case Constant.BOX_2ID:
				obstacleBodyPos.x -= rotateValue;
				if(obstacleBodyPos.x > maxX || obstacleBodyPos.x < minX)
				{
					rotateValue *= -1;
				}
				break;
			case Constant.ROUNDID:
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
	
	public void createObstacleBody()
	{
		//load body
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("obstacle/"+obstacleName+".json"));
		
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
		obstacleTexture = new Texture(Gdx.files.internal("obstacle/"+obstacleName+".png"));
		obstacleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		obstacleSprite = new Sprite(obstacleTexture);
		obstacleSprite.setSize(obstacleTexture.getWidth(), obstacleTexture.getHeight());
		obstacleSprite.setOrigin(obstacleTexture.getWidth() / 2, obstacleTexture.getHeight() / 2);
	}

}
