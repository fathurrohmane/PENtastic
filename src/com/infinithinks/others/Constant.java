package com.infinithinks.others;

import com.badlogic.gdx.math.Vector2;

public class Constant {

	//player constant
	public final static float MAX_SPEED_PLAYER = 10f;
	public final static int DIVIDE_SPEED_PLAYER = 25;
	
	//Printscreen
	public final static boolean IS_PRINT = false;
	
	//draw sprite
	public final static boolean DRAW_SPRITE = true;
	
	//debug
	public final static boolean INVINCIBLE = false;
	
	//track id
	public final static int level_1 = 1;
	public final static int level_1_2 = 2;
	public final static int level_1_3 = 3;
	public final static int level_1_4 = 4;
	public final static int level_1_5 = 5;
	public final static int level_1_6 = 6;
	public final static int level_1_7 = 7;
	public final static int level_1_8 = 8;
	public final static int level_1_9 = 9;
	public final static int level_1_10 = 10;
	
	public final static int level_2 = 11;
	public final static int level_2_2 = 12;
	public final static int level_2_3 = 13;
	public final static int level_2_4 = 14;
	public final static int level_2_5 = 15;
	public final static int level_2_6 = 16;
	public final static int level_2_7 = 17;
	public final static int level_2_8 = 18;
	public final static int level_2_9 = 19;
	public final static int level_2_10 = 20;
	
	public final static int level_3 = 21;
	public final static int level_3_2 = 22;
	public final static int level_3_3 = 23;
	public final static int level_3_4 = 24;
	public final static int level_3_5 = 25;
	public final static int level_3_6 = 26;
	public final static int level_3_7 = 27;
	public final static int level_3_8 = 28;
	public final static int level_3_9 = 29;
	public final static int level_3_10 = 30;
	
	//id stage
	public final static int stage_1 = 1;
	public final static int stage_2 = 2;
	public final static int stage_3 = 3;
	
	//obstacle kincir
	public final static int CLOCKWISE = -1;
	public final static int COUNTERCLOCKWISE = 1;
	
	public final static float SLOW = 0.01f;
	public final static float MEDIUM = 0.015f;
	public final static float FAST = 0.02f;
	
	public final static float SLOWCIRCLE = 0.005f;
	public final static float MEDIUMCIRCLE = 0.007f;
	public final static float FASTCIRCLE = 0.01f;
	
	public final static float SLOWBOX = 1.5f;
	public final static float MEDIUMBOX = 2f;
	public final static float FASTBOX = 2.5f;
	
	public final static float FASTPORTALRANDOM = 0.15f;
	
	public final static float SLOWROUND = 0.006f;
	
	public final static String KINCIR = "obstacleKincir";
	public final static String BOX = "obstacleBox";
	public final static String MOVETRACK_1 = "obstacleMoveTrack_1";
	public final static String MOVETRACK_2 = "obstacleMoveTrack_2";
	public final static String CIRCLE_1 = "obstacleCircle_1";
	public final static String CIRCLE_2 = "obstacleCircle_2";
	public final static String CIRCLE_3 = "obstacleCircle_3";
	public final static String GEAR = "obstacleGear";
	public final static String KINCIR_2 = "obstacleKincir_2";
	public final static String BOX_2 = "obstacleBox_2";
	public final static String ROUND = "obstacleRound";
	public final static String PORTALRANDOM = "portalRandom";
	public final static String PORTAL = "portal";
	
	public final static int KINCIRID = 1;
	public final static int BOXID = 2;
	public final static int MOVETRACK_1ID = 3;
	public final static int MOVETRACK_2ID = 4;
	public final static int CIRCLE_1ID = 5;
	public final static int CIRCLE_2ID = 6;
	public final static int CIRCLE_3ID = 7;
	public final static int GEARID = 8;
	public final static int KINCIR_2ID = 9;
	public final static int BOX_2ID = 10;
	public final static int ROUNDID = 11;
	public final static int PORTALRANDOMID = 12;
	public final static int PORTALID = 13;
	
	public static String resolution = "720p";
	
	//chaton
	public static final int SHARETIME = 0; 
	public static final int SHAREGAMELINK = 1;
	
	public static Vector2 cameraStartPosition(int trackId)
	{
		Vector2 pos = new Vector2();
		
		switch (trackId) {
		case level_1:
			pos.x = 0;
			pos.y = 0;
			break;
		case level_1_2:
			pos.x = 12;
			pos.y = 680;
			break;
		case level_1_3:
			pos.x = 12;
			pos.y = 816;
			break;
		case level_1_4:
			pos.x = 11;
			pos.y = 849;
			break;
		case level_1_5:
			pos.x = 51;
			pos.y = 948;
			break;
		case level_1_6:
			pos.x = 4;
			pos.y = 945;
			break;
		case level_1_7:
			pos.x = -25;
			pos.y = 1522;
			break;	
		case level_1_8:
			pos.x = -25;
			pos.y = 1522;
			break;	
		case level_1_9:
			pos.x = -25;
			pos.y = 1522;
			break;	
		case level_1_10:
			pos.x = -25;
			pos.y = 1522;
			break;	
		case level_2:
			pos.x = 1435;
			pos.y = 146;
			break;
		case level_2_2:
			pos.x = 157;
			pos.y = 149;
			break;
		case level_2_3:
			pos.x = 131;
			pos.y = 1625;
			break;
		case level_2_4:
			pos.x = 1391;
			pos.y = 66;
			break;
		case level_2_5:
			pos.x = 104;
			pos.y = 260;
			break;
		case level_2_6:
			pos.x = 33;
			pos.y = 1393;
			break;
		case level_2_7:
			pos.x = -25;
			pos.y = 1522;
			break;
		case level_2_8:
			pos.x = -25;
			pos.y = 1522;
			break;
		case level_2_9:
			pos.x = -25;
			pos.y = 1522;
			break;
		case level_2_10:
			pos.x = -25;
			pos.y = 1522;
			break;
		case level_3:
			pos.x = -28;
			pos.y = 1592;
			break;
		case level_3_2:
			pos.x = 12;
			pos.y = 680;
			break;
		case level_3_3:
			pos.x = 12;
			pos.y = 816;
			break;
		case level_3_4:
			pos.x = 11;
			pos.y = 849;
			break;
		case level_3_5:
			pos.x = 51;
			pos.y = 948;
			break;
		case level_3_6:
			pos.x = 4;
			pos.y = 945;
			break;
		case level_3_7:
			pos.x = -25;
			pos.y = 1522;
			break;
		case level_3_8:
			pos.x = -25;
			pos.y = 1522;
			break;
		case level_3_9:
			pos.x = -25;
			pos.y = 1522;
			break;
		case level_3_10:
			pos.x = -25;
			pos.y = 1522;
			break;
		default:
			break;
		}
		
		return pos;
	}
	
	public static String trackIdToName(int trackId)
	{
		String name = "";
		
		switch (trackId) {
		case level_1:
			name = "level_1";
			break;
		case level_1_2:
			name = "level_1.2";
			break;
		case level_1_3:
			name = "level_1.3";
			break;
		case level_1_4:
			name = "level_1.4";
			break;
		case level_1_5:
			name = "level_1.5";
			break;
		case level_1_6:
			name = "level_1.6";
			break;
		case level_1_7:
			name = "level_1.7";
			break;
		case level_1_8:
			name = "level_1.8";
			break;
		case level_1_9:
			name = "level_1.9";
			break;
		case level_1_10:
			name = "level_1.10";
			break;
		case level_2:
			name = "level_2";
			break;
		case level_2_2:
			name = "level_2.2";
			break;
		case level_2_3:
			name = "level_2.3";
			break;
		case level_2_4:
			name = "level_2.4";
			break;
		case level_2_5:
			name = "level_2.5";
			break;
		case level_2_6:
			name = "level_2.6";
			break;
		case level_2_7:
			name = "level_2.7";
			break;
		case level_2_8:
			name = "level_2.8";
			break;
		case level_2_9:
			name = "level_2.9";
			break;
		case level_2_10:
			name = "level_2.10";
			break;
		case level_3:
			name = "level_3";
			break;
		case level_3_2:
			name = "level_3.2";
			break;
		case level_3_3:
			name = "level_3.3";
			break;
		case level_3_4:
			name = "level_3.4";
			break;
		case level_3_5:
			name = "level_3.5";
			break;
		case level_3_6:
			name = "level_3.6";
			break;
		case level_3_7:
			name = "level_3.7";
			break;
		case level_3_8:
			name = "level_3.8";
			break;
		case level_3_9:
			name = "level_3.9";
			break;
		case level_3_10:
			name = "level_3.10";
			break;
			
		}
		
		return name;
	}
	
	public static Vector2 posFinishPoint(int trackId)
	{
		Vector2 pos = new Vector2();
		
		switch (trackId) {
		case level_1:
			pos.x = 1550;
			pos.y = 0;
			break;
		case level_1_2:
			pos.x = 159;
			pos.y = 221;
			break;
		case level_1_3:
			pos.x = 153;
			pos.y = 376;
			break;
		case level_1_4:
			pos.x = 153;
			pos.y = 376;
			break;
		case level_1_5:
			pos.x = 153;
			pos.y = 500;
			break;
		case level_1_6:
			pos.x = 153;
			pos.y = 500;
			break;
		case level_1_7:
			pos.x = 812;
			pos.y = 764;
			break;
		case level_1_8:
			pos.x = 1424;
			pos.y = 58;
			break;
		case level_1_9:
			pos.x = 869;
			pos.y = 60;
			break;
		case level_1_10:
			pos.x = 504;
			pos.y = 63;
			break;
		case level_2:	
			pos.x = 1428;
			pos.y = 610;
			break;
		case level_2_2:
			pos.x = 142;
			pos.y = 586;
			break;
		case level_2_3:
			pos.x = 796;
			pos.y = 698;
			break;
		case level_2_4:
			pos.x = 1189;
			pos.y = 912;
			break;
		case level_2_5:
			pos.x = 1518;
			pos.y = 1501;
			break;
		case level_2_6:
			pos.x = 8;
			pos.y = 157;
			break;
		case level_2_7:
			pos.x = 812;
			pos.y = 764;
			break;
		case level_2_8:
			pos.x = 812;
			pos.y = 764;
			break;
		case level_2_9:
			pos.x = 812;
			pos.y = 764;
			break;
		case level_2_10:
			pos.x = 812;
			pos.y = 764;
			break;
		case level_3:
			pos.x = 1395;
			pos.y = -41;
			break;
		case level_3_2:
			pos.x = 159;
			pos.y = 221;
			break;
		case level_3_3:
			pos.x = 153;
			pos.y = 376;
			break;
		case level_3_4:
			pos.x = 153;
			pos.y = 376;
			break;
		case level_3_5:
			pos.x = 153;
			pos.y = 500;
			break;
		case level_3_6:
			pos.x = 153;
			pos.y = 500;
			break;
		case level_3_7:
			pos.x = 812;
			pos.y = 764;
			break;
		case level_3_8:
			pos.x = 812;
			pos.y = 764;
			break;
		case level_3_9:
			pos.x = 812;
			pos.y = 764;
			break;
		case level_3_10:
			pos.x = 812;
			pos.y = 764;
			break;
		default:
			break;
		}
		
		return pos;
		
	}
}
