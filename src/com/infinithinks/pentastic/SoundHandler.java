package com.infinithinks.pentastic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundHandler {	
	//sound stuff
	public Music bgm;
	public Sound win;
	public Sound lose;
	
	public SoundHandler()
	{
		//initiating sound
		bgm = Gdx.audio.newMusic(Gdx.files.internal("gameplay.ogg"));
		bgm.setLooping(true);
		win = Gdx.audio.newSound(Gdx.files.internal("win.mp3"));
		lose = Gdx.audio.newSound(Gdx.files.internal("lose.mp3"));
		
	}
	
	public void dispose()
	{
		bgm.dispose();
		win.dispose();
		lose.dispose();
	}

}
