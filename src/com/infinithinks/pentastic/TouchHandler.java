package com.infinithinks.pentastic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.infinithinks.others.Constant;

public class TouchHandler {
	
	//touch stuff
	private float midX = Gdx.graphics.getWidth() / 2;
	private float midY = Gdx.graphics.getHeight() / 2;
	private float touchPosX;
	private float touchPosY;
	private float velocityX = 0;
	private float velocityY = 0;
	private float speedX = 0;
	private float speedY = 0;
	
	//camera stuff
	private Camera camera;
	
	public TouchHandler(Camera camera)
	{
		this.camera = camera;
	}
	
	public void render(float delta)
	{
		if(Gdx.input.isTouched())
		{
			//get touch value
			touchPosX = Gdx.input.getX();
			touchPosY = Gdx.input.getY();
			
			//set value to velocity
			speedX = (touchPosX - midX) / Constant.DIVIDE_SPEED_PLAYER;
			speedY = (touchPosY - midY) / Constant.DIVIDE_SPEED_PLAYER;
			
			//speed limiter
			//speed is positif
			if(speedX > 0)
			{
				if(speedX > Constant.MAX_SPEED_PLAYER)
				{
					speedX = Constant.MAX_SPEED_PLAYER;
				}
			}else
			{
				if(speedX < -Constant.MAX_SPEED_PLAYER)
				{
					speedX = -Constant.MAX_SPEED_PLAYER;
				}
			}
			if(speedY > 0)
			{
				if(speedY > Constant.MAX_SPEED_PLAYER)
				{
					speedY = Constant.MAX_SPEED_PLAYER;
				}
			}else
			{
				if(speedY < -Constant.MAX_SPEED_PLAYER)
				{
					speedY = -Constant.MAX_SPEED_PLAYER;
				}
			}
			
			
			if(Constant.IS_PRINT)
			{
				System.out.println("V:"+velocityX+","+velocityY);
				System.out.println("S:"+speedX+","+speedY);
				
			}
			
			camera.position.x += speedX;
			camera.position.y -= speedY;
			
		}else
		{
			speedX = 0;
			speedY = 0;
		}
	}
	
	public float getRotationSpeed()
	{	
		return (float) Math.sqrt((midX - touchPosX)*(midX - touchPosX) + (midY - touchPosY)*(midY - touchPosY));
	}
	
	public float getRotationSpeedX()
	{
		if(speedX == 0 && speedY == 0)
		{
			return -1;
		}else
		{
			return -(Math.abs(speedX) + Math.abs(speedY));
		}
		
	}

}
