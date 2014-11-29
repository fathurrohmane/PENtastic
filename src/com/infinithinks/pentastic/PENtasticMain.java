package com.infinithinks.pentastic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.infinithinks.others.AndroidOnlyInterface;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.level.Level;
import com.infinithinks.pentastic.screen.LoadingScreen;
import com.infinithinks.pentastic.screen.MainMenu;
import com.infinithinks.pentastic.screen.SplashScreen;

public class PENtasticMain extends Game {

	public MainMenu mainMenu;
	public Level level;
	public SoundHandler sound;
	public AndroidOnlyInterface aInterface;
	public Preferences saveGame;
	
	public PENtasticMain(AndroidOnlyInterface ai)
	{
		this.aInterface = ai;
		//saveGame = Gdx.app.getPreferences("My Preferences");
		
	}
	
	@Override
	public void create() {
		//initiating blending 
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND); 
		//set back button
		Gdx.input.setCatchBackKey(true);
		//setscaling
		setScaling(Gdx.graphics.getHeight());
		//sound loaded at begining apps load
		sound = new SoundHandler();
		
		//initiating
		mainMenu = new MainMenu(this);
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		//super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
	public void changeScreen(int to)
	{
		switch (to) {
		case 0:
			mainMenu.currentMenu = MainMenu.MAIN_MENU;
			setScreen(mainMenu);
			break;
		case MainMenu.MAIN_MENU:
			
			mainMenu.setCamera(MainMenu.MAIN_MENU);
			mainMenu.currentMenu = MainMenu.MAIN_MENU;
			setScreen(mainMenu);
			break;
		case MainMenu.LEVEL_MENU:
			mainMenu.currentMenu = MainMenu.LEVEL_MENU;
			setScreen(mainMenu);
			mainMenu.setCamera(MainMenu.LEVEL_MENU);
			break;

		default:
			break;
		}
	}
	
	public void createLevel(int name)
	{	
		level = new Level(this, name);
		setScreen(level);
	}
	
	public void setScaling(int inputResolution)
	{
		switch (inputResolution) {
		case 720:
			Constant.resolution = "720p";
			break;
		case 1080:
			Constant.resolution = "1080p";
			break;
		default:
			Constant.resolution = "720p";
			break;
		}
	}
}
