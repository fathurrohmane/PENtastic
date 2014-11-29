package com.infinithinks.pentastic;

public class GameState {
	
	public static final int START = -1;
	public static final int GAME_PLAY = 0;
	public static final int LOSE = 1;
	public static final int PAUSE = 2;
	public static final int FINISH = 3;
	
	public int currentState;
	
	public GameState()
	{
		currentState = START;
	}
	
	public void changeState(int state)
	{
		currentState = state;
	}
	

}
