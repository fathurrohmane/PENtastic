package com.infinithinks.pentastic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.entities.Obstacle;
import com.infinithinks.pentastic.level.Level;

public class ObstacleHandler {
	
	public Level main;
	
	//obstacle stuff
	public Obstacle obstacle[];
	public int numberOfObstacle;
	
	public int level;
	
	
	public ObstacleHandler(int level, Level main)
	{
		this.level = level;
		this.main = main;
		
		obstacle = new Obstacle[10];
		
		switch (level) {
		case Constant.level_1:
			
			break;
		case Constant.level_1_2:
					
			break;
		case Constant.level_1_3:
			
			break;
		case Constant.level_1_4:
			createBox(0, new Vector2(472,966),Constant.COUNTERCLOCKWISE,Constant.SLOWBOX,790,966);
			createBox(1, new Vector2(993,490),Constant.CLOCKWISE,Constant.MEDIUMBOX,285,490);
			createBox(2, new Vector2(804,-398),Constant.CLOCKWISE,Constant.FASTBOX,-600,-398);
			createBox(3, new Vector2(475,122),Constant.CLOCKWISE,Constant.FASTBOX,-70,122);
			numberOfObstacle = 3;
			break;
		case Constant.level_1_5:
			createKincir(0, new Vector2(912,964),Constant.COUNTERCLOCKWISE,Constant.SLOW);
			createKincir(1, new Vector2(1497,519),Constant.COUNTERCLOCKWISE,Constant.MEDIUM);
			createKincir(2, new Vector2(734,105),Constant.CLOCKWISE,Constant.FAST);
			
			numberOfObstacle = 2;
			break;
		case Constant.level_1_6:
			createMoveTrack_1(0, new Vector2(729,1067),Constant.CLOCKWISE,Constant.SLOWBOX,765,1067);
			createMoveTrack_2(1, new Vector2(1014,184),Constant.CLOCKWISE,Constant.MEDIUMBOX,-95,184);
			createMoveTrack_2(2, new Vector2(640,-95),Constant.CLOCKWISE,Constant.SLOWBOX,-95,184);
			
			numberOfObstacle = 2;
			break;
		case Constant.level_1_7:
			
			break;
		case Constant.level_2:
			createBox(0, new Vector2(1008, -131), Constant.CLOCKWISE, Constant.FASTBOX, -131, 203);
			createBox(1, new Vector2(626, 203), -Constant.CLOCKWISE, Constant.FASTBOX, -131, 203);
			numberOfObstacle = 1;
			break;
		case Constant.level_2_2:
			createKincir(0, new Vector2(862,340),Constant.CLOCKWISE,Constant.MEDIUM);
			createKincir(1, new Vector2(1278,825),Constant.CLOCKWISE,Constant.MEDIUM);
			
			numberOfObstacle = 1;
			break;
		case Constant.level_2_3:
			createKincir_2(0, new Vector2(1334,977),Constant.COUNTERCLOCKWISE,Constant.SLOW);
			createKincir_2(1, new Vector2(870,83),Constant.COUNTERCLOCKWISE,Constant.MEDIUM);
			createKincir_2(2, new Vector2(174,900),Constant.COUNTERCLOCKWISE,Constant.FAST);
			numberOfObstacle = 2;
			break;
		case Constant.level_2_4:
			createKincir(0, new Vector2(796,194),Constant.CLOCKWISE,Constant.MEDIUM);
			createKincir(1, new Vector2(530,214),Constant.COUNTERCLOCKWISE,Constant.MEDIUM);
			createKincir(2, new Vector2(330,410),Constant.COUNTERCLOCKWISE,Constant.MEDIUM);
			createKincir(3, new Vector2(215,709),Constant.CLOCKWISE,Constant.MEDIUM);
			createCircle_1(4, new Vector2(1189,912),Constant.CLOCKWISE,Constant.MEDIUMCIRCLE);
			createCircle_2(5, new Vector2(1189,912),Constant.COUNTERCLOCKWISE,Constant.SLOWCIRCLE);
			createCircle_3(6, new Vector2(1189,912),Constant.CLOCKWISE,Constant.SLOWCIRCLE);
			numberOfObstacle = 6;
			break;
		case Constant.level_2_5:
			createKincir_2(0, new Vector2(1265,379),Constant.CLOCKWISE,Constant.MEDIUM);
			createKincir_2(1, new Vector2(1416,532),Constant.CLOCKWISE,Constant.MEDIUM);
			createKincir_2(2, new Vector2(1501,735),Constant.CLOCKWISE,Constant.MEDIUM);
			createKincir_2(3, new Vector2(630,1520),Constant.COUNTERCLOCKWISE,Constant.FAST);
			createKincir_2(4, new Vector2(897,1549),Constant.COUNTERCLOCKWISE,Constant.FAST);
			createKincir_2(5, new Vector2(1164,1481),Constant.COUNTERCLOCKWISE,Constant.FAST);
			createGear(6, new Vector2(850,890),Constant.CLOCKWISE,Constant.SLOWCIRCLE);
			numberOfObstacle = 6;
			
			break;
		case Constant.level_2_6:
			createBox_2(0, new Vector2(1752, 664), Constant.CLOCKWISE, Constant.FASTBOX, 1339, 1753);
			createBox(1, new Vector2(800, 1540), Constant.CLOCKWISE, Constant.FASTBOX, 1268, 1540);
			createBox(2, new Vector2(1605, 1268), -Constant.CLOCKWISE, Constant.FASTBOX, 1268, 1540);
			createBox(3, new Vector2(380, 961), -Constant.CLOCKWISE, Constant.SLOWBOX, 255, 961);
			createBox(4, new Vector2(656, 255), -Constant.CLOCKWISE, Constant.SLOWBOX, 255, 961);
			numberOfObstacle = 4;
			break;
		case Constant.level_2_7:
			createRound(0, new Vector2(812,764),Constant.CLOCKWISE,Constant.SLOWROUND);
			numberOfObstacle = 0;
			break;
		case Constant.level_3:
			
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
			
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			
			break;
		case Constant.level_1_5:

			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			
			break;
		case Constant.level_1_6:

			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			
			break;
		case Constant.level_1_7:
			
			break;
		case Constant.level_2:
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}	
			break;
		case Constant.level_2_2:
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			break;
		case Constant.level_2_3:
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			break;
		case Constant.level_2_4:
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			break;
		case Constant.level_2_5:
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			break;
		case Constant.level_2_6:
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			break;
		case Constant.level_2_7:
			for (int i = 0; i <= numberOfObstacle; i++) 
			{
				obstacle[i].render(delta,batch);
			}
			break;
		case Constant.level_3:
			
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
	
	public void createKincir(int no, Vector2 pos, int clockwise, float speed)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleKincir",clockwise,speed);	
	}
	
	public void createBox(int no, Vector2 pos, int clockwise, float speed, int min, int max)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleBox",clockwise,speed);
		obstacle[no].minY = min;
		obstacle[no].maxY = max;
	}
	
	public void createBox_2(int no, Vector2 pos, int clockwise, float speed, int min, int max)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleBox_2",clockwise,speed);
		obstacle[no].minX = min;
		obstacle[no].maxX = max;
	}
	
	public void createMoveTrack_1(int no, Vector2 pos, int clockwise, float speed, int min, int max)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleMoveTrack_1",clockwise,speed);
		obstacle[no].minY = min;
		obstacle[no].maxY = max;
	}
	public void createMoveTrack_2(int no, Vector2 pos, int clockwise, float speed, int min, int max)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleMoveTrack_2",clockwise,speed);
		obstacle[no].minY = min;
		obstacle[no].maxY = max;
	}
	public void createCircle_1(int no, Vector2 pos, int clockwise, float speed)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleCircle_1",clockwise,speed);
	}
	public void createCircle_2(int no, Vector2 pos, int clockwise, float speed)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleCircle_2",clockwise,speed);
	}
	public void createCircle_3(int no, Vector2 pos, int clockwise, float speed)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleCircle_3",clockwise,speed);
	}
	public void createGear(int no, Vector2 pos, int clockwise, float speed)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleGear",clockwise,speed);
	}
	
	public void createKincir_2(int no, Vector2 pos, int clockwise, float speed)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleKincir_2",clockwise,speed);	
	}
	
	public void createRound(int no, Vector2 pos, int clockwise, float speed)
	{
		obstacle[no] = new Obstacle(this, main.world, main.camera, pos, "obstacleRound",clockwise,speed);
		obstacle[no].rotation = -10;
	}

}
