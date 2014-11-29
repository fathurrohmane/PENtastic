package com.infinithinks.others;

import com.badlogic.gdx.Gdx;

public class StopWatch {
	
	private int minute;
	private int second;
	private int milisecond;
	
	private float addTimeInMili = 130;
	
	private boolean isPause;
	
	public StopWatch()
	{
		minute = 0;
		second = 0;
		milisecond = 0;
		isPause = true;
		
	}
	
	public void render()
	{
		if(!isPause)
		{
			milisecond+= Gdx.graphics.getDeltaTime()*addTimeInMili;
			if(milisecond > 99)
			{
				second++;
				milisecond = 0;
			}
			if(second > 59)
			{
				minute++;
				second = 0;
			}
		}
	}
	
	public void stop()
	{
		isPause = true;
	}
	
	public void start()
	{
		isPause = false;
	}
	
	public void print()
	{
		System.out.println(minute+":"+second+":"+milisecond);
	}
	
	public void restart()
	{
		minute = 0;
		second = 0;
		milisecond = 0;
	}
	
	public String getTime()
	{
		return minute+":"+second+":"+milisecond;
		
	}
	

}
