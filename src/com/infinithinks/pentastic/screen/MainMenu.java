package com.infinithinks.pentastic.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Circ;
import aurelienribon.tweenengine.equations.Cubic;
import aurelienribon.tweenengine.equations.Expo;
import aurelienribon.tweenengine.equations.Quad;
import aurelienribon.tweenengine.equations.Quart;
import aurelienribon.tweenengine.equations.Quint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.infinithinks.others.CameraAccessor;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.GameState;
import com.infinithinks.pentastic.PENtasticMain;
import com.infinithinks.pentastic.level.Level;

public class MainMenu implements Screen {

	PENtasticMain main;
	
	//splash stuff
	public Texture mainMenuTexture,stageTexture,settingTexture,levelTexture,loadingTexture,tutorialTexture;
	public Sprite mainMenuSprite,stageSprite,settingSprite,levelSprite,loadingSprite,tutorialSprite;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	
	//ui stuff
	public Stage stage;
	public Texture buttonTexture,settingButtonTexture;
	public Button playButton,settingButton;
	
	////stage select ui stuff
	public Texture stage1Texture,stage2Texture,stage3Texture;
	public Button stage1Button,stage2Button,stage3Button;
	
	///setting button
	public Texture backButtonTexture;
	public Button backButton;
	public BitmapFont menuText;
	public TextButton soundButton;
	public TextureAtlas buttonAtlas;
	public Skin buttonSkin;
	
	
	////level menu button
	public TextureAtlas levelButtonAtlas;
	public Skin levelButtonSkin;
	public TextButtonStyle levelButtonStyle;
	public BitmapFont levelTextFont;
	public TextButton level_0Button;
	public TextButton level_1Button;
	public TextButton level_2Button;
	public TextButton level_3Button;
	public TextButton level_4Button;
	public TextButton level_5Button;
	public TextButton level_6Button;
	public TextButton level_7Button;
	
	//animation
	public TweenManager tweenManager;
	
	//menu
	public final static int SETTING_MENU = 0;
	public final static int MAIN_MENU = 1;
	public final static int STAGE_MENU = 2;
	public final static int LEVEL_MENU = 3;
	public final static int LOADING_MENU = 4;
	public final static int TUTORIAL_MENU = 5;
	public int currentMenu = MAIN_MENU;
	public int currentStage = 0;
	
	//exit handler
	public boolean backButtonIsPressed = true;
	public boolean stageDisposeable = true;
	
	
	
	public MainMenu(PENtasticMain main)
	{
		this.main = main;
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		mainMenuSprite.draw(batch);
		stageSprite.draw(batch);
		settingSprite.draw(batch);
		levelSprite.draw(batch);
		loadingSprite.draw(batch);
		tutorialSprite.draw(batch);
		batch.end();
		stage.act(delta);
		stage.draw();
		tweenManager.update(delta);
		
		switch (currentMenu) {
		case SETTING_MENU:
			
			if((backButton.isPressed() || Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK)) && backButtonIsPressed)
			{
				backButtonIsPressed = false;
				//this.dispose();
				//main.changeScreen(2);
				if(stageDisposeable)
				{
					stageDisposeable = false;
					stage.dispose();
				}
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(0 + Gdx.graphics.getHeight() / 2)
			    .ease(Quart.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = MAIN_MENU;
						stageDisposeable = false;
						createButtonMainMenu();
					}
				})
			    .start(tweenManager);
				
			}else
			{
				backButtonIsPressed = true;
			}
			break;
		case MAIN_MENU:
			
			if(backButton.isPressed() || Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK) && backButtonIsPressed)
			{
				backButtonIsPressed = false;
				Gdx.app.exit();
				
			}else
			{
				backButtonIsPressed = true;
			}
			if(playButton.isPressed())
			{
				//this.dispose();
				//main.changeScreen(2);
				if(stageDisposeable)
				{
					stageDisposeable = false;
					stage.dispose();
				}
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(mainMenuSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Quart.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = STAGE_MENU;
						stageDisposeable = true;
						createButtonStage();
					}
				})
			    .start(tweenManager);				
			}
			if(settingButton.isPressed())
			{
				//this.dispose();
				//main.changeScreen(2);
				if(stageDisposeable)
				{
					stageDisposeable = false;
					stage.dispose();
				}
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(settingSprite.getY() + Gdx.graphics.getHeight() / 2)
			    .ease(Quart.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = SETTING_MENU;
						stageDisposeable = true;
						createButtonSetting();
						
					}
				})
			    .start(tweenManager);				
			}
			
			break;
		case STAGE_MENU:
			if((backButton.isPressed() || Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK)) && backButtonIsPressed)
			{
				backButtonIsPressed = false;
				//this.dispose();
				//main.changeScreen(2);
				if(stageDisposeable)
				{
					stageDisposeable = false;
					stage.dispose();
				}
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(mainMenuSprite.getY() + Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = MAIN_MENU;
						stageDisposeable = true;
						createButtonMainMenu();
						
					}
				})
			    .start(tweenManager);				
			}else
			{
				backButtonIsPressed = true;
			}
			
			if(stage1Button.isPressed())
			{
				//this.dispose();
				//main.changeScreen(2);
				currentStage = Constant.stage_1;
				if(stageDisposeable)
				{
					stageDisposeable = false;
					stage.dispose();
				}
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(stageSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = LEVEL_MENU;
						stageDisposeable = true;
						initialButtonLevel(Constant.stage_1);
						createButtonLevel();
					}
				})
			    .start(tweenManager);				
			}
			if(stage2Button.isPressed())
			{
				//this.dispose();
				//main.changeScreen(2);
				currentStage = Constant.stage_2;
				if(stageDisposeable)
				{
					stageDisposeable = false;
					stage.dispose();
				}
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(stageSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = LEVEL_MENU;
						stageDisposeable = true;
						initialButtonLevel(Constant.stage_2);
						createButtonLevel();
					}
				})
			    .start(tweenManager);				
			}
	
			break;
		case LEVEL_MENU:			
			if((backButton.isPressed() || Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK)) && backButtonIsPressed)
			{
				backButtonIsPressed = false;
				//this.dispose();
				//main.changeScreen(2);
				if(stageDisposeable)
				{
					stageDisposeable = false;
					stage.dispose();
				}
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(stageSprite.getY() + Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = STAGE_MENU;
						stageDisposeable = true;
						createButtonStage();
					}
				})
			    .start(tweenManager);	
			}else
			{
				backButtonIsPressed = true;
			}
			if(level_0Button.isPressed())
			{
				stage.dispose();
				Tween.to(camera, CameraAccessor.POSITION_X, 1f)
				.target(levelSprite.getWidth() + Gdx.graphics.getWidth() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = TUTORIAL_MENU;
						createButtonTutorial();
						
					}
				})
			    .start(tweenManager);				
			}
			
			if(level_1Button.isPressed())
			{
				currentMenu = LOADING_MENU;
				stage.dispose();
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(levelSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						switch (currentStage) {
						case Constant.stage_1:
							main.createLevel(Constant.level_1);
							break;
						case Constant.stage_2:
							main.createLevel(Constant.level_2);
							break;
						default:
							break;
						}
						
						
					}
				})
			    .start(tweenManager);				
			}
			if(level_2Button.isPressed())
			{
				currentMenu = LOADING_MENU;
				stage.dispose();
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(levelSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						switch (currentStage) {
						case Constant.stage_1:
							main.createLevel(Constant.level_1_2);
							break;
						case Constant.stage_2:
							main.createLevel(Constant.level_2_2);
							break;
						default:
							break;
						}
					}
				})
			    .start(tweenManager);				
			}
			if(level_3Button.isPressed())
			{
				currentMenu = LOADING_MENU;
				stage.dispose();
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(levelSprite.getY() - Gdx.graphics.getHeight() / 2 )
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						switch (currentStage) {
						case Constant.stage_1:
							main.createLevel(Constant.level_1_3);
							break;
						case Constant.stage_2:
							main.createLevel(Constant.level_2_3);
							break;
						default:
							break;
						}
						
					}
				})
			    .start(tweenManager);				
			}
			if(level_4Button.isPressed())
			{
				currentMenu = LOADING_MENU;
				stage.dispose();
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(levelSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						switch (currentStage) {
						case Constant.stage_1:
							main.createLevel(Constant.level_1_4);
							break;
						case Constant.stage_2:
							main.createLevel(Constant.level_2_4);
							break;
						default:
							break;
						}
						
					}
				})
			    .start(tweenManager);				
			}
			if(level_5Button.isPressed())
			{
				currentMenu = LOADING_MENU;
				stage.dispose();
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(levelSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						switch (currentStage) {
						case Constant.stage_1:
							main.createLevel(Constant.level_1_5);
							break;
						case Constant.stage_2:
							main.createLevel(Constant.level_2_5);
							break;
						default:
							break;
						}
						
					}
				})
			    .start(tweenManager);				
			}
			if(level_6Button.isPressed())
			{
				currentMenu = LOADING_MENU;
				stage.dispose();
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(levelSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						switch (currentStage) {
						case Constant.stage_1:
							main.createLevel(Constant.level_1_6);
							break;
						case Constant.stage_2:
							main.createLevel(Constant.level_2_6);
							break;
						default:
							break;
						}

					}
				})
			    .start(tweenManager);				
			}
			if(level_7Button.isPressed())
			{
				currentMenu = LOADING_MENU;
				stage.dispose();
				Tween.to(camera, CameraAccessor.POSITION_Y, 1f)
				.target(levelSprite.getY() - Gdx.graphics.getHeight() / 2)
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						switch (currentStage) {
						case Constant.stage_1:
							main.createLevel(Constant.level_1_7);
							break;
						case Constant.stage_2:
							main.createLevel(Constant.level_2_7);
							break;
						default:
							break;
						}
						
					}
				})
			    .start(tweenManager);				
			}
			
			break;
			
		case TUTORIAL_MENU:
			
			if((backButton.isPressed() || Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK)) && backButtonIsPressed)
			{
				if(stageDisposeable)
				{
					stageDisposeable = false;
					stage.dispose();
				}
				Tween.to(camera, CameraAccessor.POSITION_X, 1f)
				.target(Gdx.graphics.getWidth() / 2 )
			    .ease(Cubic.IN)
			    .setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						currentMenu = LEVEL_MENU;
						stageDisposeable = true;
						createButtonLevel();
						
					}
				})
			    .start(tweenManager);			
			}else
			{
				backButtonIsPressed = true;
			}
			break;

		default:
			break;
		}
		
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		
		//make engine stuff
		camera = new OrthographicCamera(Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
		//camera = new OrthographicCamera(1,Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.x = Gdx.graphics.getWidth() / 2;
		camera.position.y = Gdx.graphics.getHeight() / 2;
		batch = new SpriteBatch();
		
		//make sprite and texture 
		mainMenuTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/home.jpg"));
		mainMenuTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		mainMenuSprite = new Sprite(mainMenuTexture);
		mainMenuSprite.setSize(mainMenuTexture.getWidth(), mainMenuTexture.getHeight());
		mainMenuSprite.setPosition(Gdx.graphics.getWidth() / 2 - mainMenuTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - mainMenuTexture.getHeight() / 2);
		
		buttonTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/home_play.png"));
		buttonTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		settingTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/settingScreen.png"));
		settingTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		settingSprite = new Sprite(settingTexture);
		settingSprite.setPosition(Gdx.graphics.getWidth() / 2 - settingSprite.getWidth() / 2, settingTexture.getHeight());
		
		stageTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stageScreen.png"));
		stageTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stageSprite = new Sprite(stageTexture);
		stageSprite.setPosition(Gdx.graphics.getWidth() / 2 - stageSprite.getWidth() / 2, mainMenuSprite.getY() - Gdx.graphics.getHeight() / 2 - stageSprite.getHeight() / 2);
		
		levelTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stage1/sublevel bg.jpg"));
		levelTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		levelSprite = new Sprite(levelTexture);
		levelSprite.setPosition(Gdx.graphics.getWidth() / 2 - levelSprite.getWidth() / 2, stageSprite.getY() - Gdx.graphics.getHeight() / 2 - levelSprite.getHeight() / 2);
		
		loadingTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/loadingScreen.png"));
		loadingTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		loadingSprite = new Sprite(loadingTexture);
		loadingSprite.setPosition(Gdx.graphics.getWidth() / 2 - loadingSprite.getWidth() / 2, levelSprite.getY() - Gdx.graphics.getHeight() / 2 - loadingSprite.getHeight() / 2);
		
		tutorialTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/tutorial.jpg"));
		tutorialTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tutorialSprite = new Sprite(tutorialTexture);
		tutorialSprite.setPosition(levelSprite.getX() + tutorialSprite.getWidth(), levelSprite.getY());
		
		//setting menu button initial
		menuText = new BitmapFont(Gdx.files.internal("font/freestylefont.fnt"),Gdx.files.internal("font/freestylefont_0.png"),false);
		buttonAtlas = new TextureAtlas(Gdx.files.internal(Constant.resolution+"/menu/button/button.atlas"));
		buttonSkin = new Skin(buttonAtlas);
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = buttonSkin.getDrawable("buttonUp");
		textButtonStyle.down = buttonSkin.getDrawable("buttonDown");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = menuText;
		
		soundButton = new TextButton("ON", textButtonStyle);
		
		// main menu initial button
		TextureRegion playButtonRegion = new TextureRegion(buttonTexture);
		TextButtonStyle style = new TextButtonStyle();
		style.up = new TextureRegionDrawable(playButtonRegion);
		style.down = new TextureRegionDrawable(playButtonRegion);
		
		playButton = new Button();
		playButton.setStyle(style);
		
		settingButtonTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/settingButton.png"));
		TextureRegion settingButtonRegion = new TextureRegion(settingButtonTexture);
		TextButtonStyle settingButtonStyle = new TextButtonStyle();
		settingButtonStyle.up = new TextureRegionDrawable(settingButtonRegion);
		settingButtonStyle.down = new TextureRegionDrawable(settingButtonRegion);
		settingButton = new Button();
		settingButton.setStyle(settingButtonStyle);
		
		//stage select button intial
		stage1Texture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stage1Button.png"));
		stage1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stage2Texture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stage2Button.png"));
		stage2Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stage3Texture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stage3Button.png"));
		stage3Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		backButtonTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/backButton.png"));
		backButtonTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion stage1Region = new TextureRegion(stage1Texture);
		TextureRegion stage2Region = new TextureRegion(stage2Texture);
		TextureRegion stage3Region = new TextureRegion(stage3Texture);
		TextureRegion backButtonRegion = new TextureRegion(backButtonTexture);
		
		TextButtonStyle stage1Style = new TextButtonStyle();
		TextButtonStyle stage2Style = new TextButtonStyle();
		TextButtonStyle stage3Style = new TextButtonStyle();
		TextButtonStyle backButtonStyle = new TextButtonStyle();
		
		stage1Style.up = new TextureRegionDrawable(stage1Region);
		stage1Style.down = new TextureRegionDrawable(stage1Region);
		stage2Style.up = new TextureRegionDrawable(stage2Region);
		stage2Style.down = new TextureRegionDrawable(stage2Region);
		stage3Style.up = new TextureRegionDrawable(stage3Region);
		stage3Style.down = new TextureRegionDrawable(stage3Region);
		backButtonStyle.up = new TextureRegionDrawable(backButtonRegion);
		backButtonStyle.down = new TextureRegionDrawable(backButtonRegion);
		
		stage1Button = new Button();
		stage2Button = new Button();
		stage3Button = new Button();
		backButton = new Button();
		
		stage1Button.setStyle(stage1Style);
		stage2Button.setStyle(stage2Style);
		stage3Button.setStyle(stage3Style);
		backButton.setStyle(backButtonStyle);
		
		//level select button
		levelButtonAtlas = new TextureAtlas(Gdx.files.internal(Constant.resolution+"/menu/button/buttonLevel.atlas"));
		levelButtonSkin = new Skin(levelButtonAtlas);
		levelButtonStyle = new TextButtonStyle();
		levelButtonStyle.up = levelButtonSkin.getDrawable("buttonLevelUp");
		levelButtonStyle.down = levelButtonSkin.getDrawable("buttonLevelDown");
		levelButtonStyle.pressedOffsetX = 1;
		levelButtonStyle.pressedOffsetY = -1;
		levelTextFont = new BitmapFont(Gdx.files.internal("font/freestylefont.fnt"),Gdx.files.internal("font/freestylefont_0.png"),false);
		levelButtonStyle.font = levelTextFont;
		levelButtonStyle.fontColor = Color.ORANGE;
		TextureRegion stageSelectRegion = new TextureRegion(stage1Texture);
		
		//animation
		tweenManager = new TweenManager();
		Tween.registerAccessor(OrthographicCamera.class, new CameraAccessor());
		
		createButtonMainMenu();
	}

	@Override
	public void hide() {


	}

	@Override
	public void pause() {


	}

	@Override
	public void resume() {


	}

	@Override
	public void dispose() {
		//stage.dispose();

	}
	
	public void createButtonMainMenu()
	{
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setTransform(true);
		table.setFillParent(true);
		table.setPosition(0,0);
		
		table.add(backButton).expand().left().top();
		table.add(settingButton).right().top();
		table.row();
		table.add(playButton).center().bottom();
		
		stage.addActor(table);
		
	}
	
	public void createButtonStage()
	{
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setTransform(true);
		table.setFillParent(true);
		table.setPosition(0, 0);
		stage.addActor(table);
		table.add(backButton).expand().left().top();
		table.row();
		table.add(stage1Button).expandY().center();
		table.add(stage2Button).expandY().center();
		table.add(stage3Button).expandY().center();
	}
	
	public void createButtonSetting()
	{
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setTransform(true);
		table.setFillParent(true);
		table.setPosition(0, 0);
		stage.addActor(table);
		
		table.add(backButton).expand().left().top();
		table.row();
		//table.add(soundButton).center().expand();
		
	}
	
	public void initialButtonLevel(int stage)
	{
		level_0Button = new TextButton("?", levelButtonStyle);
		level_1Button = new TextButton(stage+".1", levelButtonStyle);
		level_2Button = new TextButton(stage+".2", levelButtonStyle);
		level_3Button = new TextButton(stage+".3", levelButtonStyle);
		level_4Button = new TextButton(stage+".4", levelButtonStyle);
		level_5Button = new TextButton(stage+".5", levelButtonStyle);
		level_6Button = new TextButton(stage+".6", levelButtonStyle);
		level_7Button = new TextButton(stage+".7", levelButtonStyle);
		
	}
	
	public void createButtonLevel()
	{
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setTransform(true);
		table.setFillParent(true);
		table.setPosition(0, 0);
		stage.addActor(table);
		
		table.add(backButton).expand().left().top();
		table.row();
//		table.add(level_0Button).expand();table.add(level_1Button).expand();table.add(level_2Button).expand();table.add(level_3Button).expand();table.add(level_4Button).expand();
//		table.row();
//		table.add(level_5Button).expand();table.add(level_6Button).expand();table.add(level_7Button).expand();
		table.add(level_0Button).expand();table.add(level_1Button).expand();table.add(level_2Button).expand();
		table.row();table.add(level_3Button).expand();table.add(level_4Button).expand();table.add(level_5Button).expand();
		//table.row();
		//table.add(level_5Button).expand();table.add(level_6Button).expand();table.add(level_7Button).expand();
		
		
	}
	
	public void createButtonTutorial()
	{
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setTransform(true);
		table.setFillParent(true);
		table.setPosition(0, 0);
		stage.addActor(table);
		
		table.add(backButton).expand().left().top();
		table.row();
		
	}
	
	public void setCamera(int state)
	{
		switch (state) {
		case MAIN_MENU:
			camera.position.x = mainMenuSprite.getX() / 2;
			camera.position.y = mainMenuSprite.getY() / 2;
			break;
		case LEVEL_MENU:
			createButtonLevel();
			camera.position.x = Gdx.graphics.getWidth() / 2;
			camera.position.y = stageSprite.getY() - Gdx.graphics.getHeight() / 2;
			break;
		

		default:
			break;
		}
	}

}
