package com.infinithinks.pentastic;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.entities.Obstacle;
import com.infinithinks.pentastic.entities.Portal;
import com.infinithinks.pentastic.level.Level;

public class PortalHandler {


	public Level main;
	
	//obstacle stuff
	public Portal portal[];
	public int numberOfObstacle;
	
	public int level;
	
	
	public PortalHandler(int level, Level main)
	{
		this.level = level;
		this.main = main;
		
		portal = new Portal[10];
		
		switch (level) {
		case Constant.level_1:
			
			break;
		case Constant.level_1_2:
					
			break;
		case Constant.level_1_3:
			
			break;
		case Constant.level_1_4:
			
			break;
		case Constant.level_1_5:
			
			
			break;
		case Constant.level_1_6:
			
			break;
		case Constant.level_1_7:
			
			break;
		case Constant.level_2:
			
			break;
		case Constant.level_2_2:
			
			break;
		case Constant.level_2_3:
			
			break;
		case Constant.level_2_4:
			
			break;
		case Constant.level_2_5:
			
			break;
		case Constant.level_2_6:
			
			break;
		case Constant.level_2_7:
			
			break;
		case Constant.level_3:
			//1368.6378 : 1289.1993
			//1547.2797 : 1085.5974
			//1284.8771 : 868.8778
			
			// -17.203018 : 808.1582
			
			createPortalRandom(0, new Vector2(1368,1300), Constant.CLOCKWISE,Constant.FASTPORTALRANDOM);
			createPortalRandom(1, new Vector2(1570,1085), Constant.CLOCKWISE,Constant.FASTPORTALRANDOM);
			createPortalRandom(2, new Vector2(1284,868), Constant.CLOCKWISE,Constant.FASTPORTALRANDOM);
			createPortalRandom(3, new Vector2(-17,808), Constant.CLOCKWISE,Constant.FASTPORTALRANDOM);
			numberOfObstacle = 3;
			break;
		case Constant.level_3_2:
			
			break;
		case Constant.level_3_3:
			
			break;
		case Constant.level_3_4:
			
			break;
		case Constant.level_3_5:
			
			break;
		case Constant.level_3_6:
			
			break;
		case Constant.level_3_7:
			
			break;
			
		default:
			break;
		}
		
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		switch (level) {
		case Constant.level_1:
			
			break;
		case Constant.level_1_2:
					
			break;
		case Constant.level_1_3:
			
			break;
		case Constant.level_1_4:
			
			break;
		case Constant.level_1_5:			
			break;
		case Constant.level_1_6:			
			break;
		case Constant.level_1_7:
			
			break;
		case Constant.level_2:
		
			break;
		case Constant.level_2_2:
			
			break;
		case Constant.level_2_3:
			
			break;
		case Constant.level_2_4:
			
			break;
		case Constant.level_2_5:
			
			break;
		case Constant.level_2_6:
			
			break;
		case Constant.level_2_7:

			break;
		case Constant.level_3:
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				portal[i].render(delta,batch);
			}
			break;
		case Constant.level_3_2:
			
			break;
		case Constant.level_3_3:
			
			break;
		case Constant.level_3_4:
			
			break;
		case Constant.level_3_5:
			
			break;
		case Constant.level_3_6:
			
			break;
		case Constant.level_3_7:
			
			break;
		default:
			break;
		}
		
	}
	
	public void createPortalRandom(int no, Vector2 pos, int clockwise, float speed)
	{
		portal[no] = new Portal(this, main.world, main.camera, pos, "portalRandom",clockwise,speed);
	}
	
	public Vector2 getRandomCoordinate()
	{
		Vector2 newCoordinate = new Vector2();
		
		Random random = new Random();
		newCoordinate = portal[random.nextInt(numberOfObstacle) + 1].body.getPosition();
		
		return newCoordinate;
	}
	
}
