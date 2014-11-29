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
import com.infinithinks.pentastic.screen.MainMenuScreen;
import com.infinithinks.pentastic.screen.SplashScreen;
import com.infinithinks.pentastic.screen.StageSelectScreen;

public class PENtasticMain extends Game {
	//menu screen
	public MainMenuScreen mainMenu;
	public StageSelectScreen stageMenu;
	
	public Level level;
	public SoundHandler sound;
	public AndroidOnlyInterface aInterface;
	public Preferences saveGame;
	
	//enum screen select
	public enum menuScreen{mainMenu,stageSelect,levelSelectStage1,levelSelectStage2,levelSelectStage3};
	
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
		mainMenu = new MainMenuScreen(this);
		stageMenu = new StageSelectScreen(this);
		
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
	
	public void changeScreen(menuScreen to)
	{
		switch (to) {
		case mainMenu:
			setScreen(mainMenu);
			break;
		case stageSelect:
			setScreen(new StageSelectScreen(this));
			break;
		case levelSelectStage1:
			setScreen(stageMenu);
			break;
		case levelSelectStage2:
			setScreen(stageMenu);
			break;
		case levelSelectStage3:
			setScreen(stageMenu);
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
