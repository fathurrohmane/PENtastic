package com.infinithinks.pentastic.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Cubic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
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
import com.infinithinks.others.AndroidOnlyInterface;
import com.infinithinks.others.CameraAccessor;
import com.infinithinks.others.Constant;
import com.infinithinks.others.StopWatch;
import com.infinithinks.pentastic.GameState;
import com.infinithinks.pentastic.PENtasticMain;
import com.infinithinks.pentastic.level.Level;

public class HUD {
	
	private PENtasticMain main;
	
	//ready set go
	private BitmapFont readySetGoFont;
	private float opacity = 1f;
	private float size = 1f;
	private float time = 0f;
	private float second = 3;
	
	//blackbackground
	private Texture bgTexture;
	private Sprite bgSprite;
	private Color bgColor;
	private float bgOpacity;
	
	//gamestate
	private GameState gameState;
	
	//header text plus stopwatch
	private BitmapFont headerFont;

	//Timer
	public StopWatch stopwatch;
	
	//menu 
	private Stage stageLoseScreen;
	private BitmapFont subTitleFont;
	private Texture titleFinishTexture;
	private Sprite titleFinishSprite;
	private Texture titlePauseTexture;
	private Sprite titlePauseSprite;
	private Texture titleLoseTexture;
	private Sprite titleLoseSprite;
	private Texture circle_1Texture;
	private Sprite circle_1Sprite;
	private float circle_1Rotation = 0f;
	private Texture circle_2Texture;
	private Sprite circle_2Sprite;
	private float circle_2Rotation = 0f;
	
	//button
	private TextureAtlas buttonAtlas;
	private Skin buttonSkin;
	private TextButton retryButton;
	private TextButton exitButton;
	private Texture shareChatOnTexture;
	private Button shareChatOnButton;
	
	private boolean backButtonIsPressed = true;
	private boolean holdTouch = false;
	public static boolean chatONSTatus = true;
	
	//chaton
	
	public HUD(GameState gameState,PENtasticMain main)
	{
		this.main = main;
		this.gameState = gameState;
		
		//initiating font
		readySetGoFont = new BitmapFont(Gdx.files.internal("font/123.fnt"),Gdx.files.internal("font/123_0.png"),false);
		readySetGoFont.setColor(1f, 1f, 1f, opacity);
		readySetGoFont.setScale(size);
		
		//initiating button
		buttonAtlas = new TextureAtlas(Gdx.files.internal(Constant.resolution+"/menu/button/button.atlas"));
		buttonSkin = new Skin(buttonAtlas);
		

		//initiating header
		headerFont = new BitmapFont(Gdx.files.internal("font/freestylefont.fnt"),Gdx.files.internal("font/freestylefont_0.png"),false);
		titleLoseTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/hud/youlose.png"));
		titleLoseTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		titleLoseSprite = new Sprite(titleLoseTexture);
		titleLoseSprite.setPosition(Gdx.graphics.getWidth() / 2 - titleLoseSprite.getWidth() / 2, Gdx.graphics.getHeight() / 4 - titleLoseSprite.getHeight() / 2);
		titlePauseTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/hud/pause.png"));
		titlePauseTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		titlePauseSprite = new Sprite(titlePauseTexture);
		titlePauseSprite.setPosition(Gdx.graphics.getWidth() / 2 - titlePauseSprite.getWidth() / 2, Gdx.graphics.getHeight() / 4 - titlePauseSprite.getHeight() / 2);
		titleFinishTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/hud/finish.png"));
		titleFinishTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		titleFinishSprite = new Sprite(titleFinishTexture);
		titleFinishSprite.setPosition(Gdx.graphics.getWidth() / 2 - titleFinishSprite.getWidth() / 2, Gdx.graphics.getHeight() / 4 - titleFinishSprite.getHeight() / 2);
		
		circle_1Texture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/hud/circledalem.png"));
		circle_1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		circle_1Sprite = new Sprite(circle_1Texture);
		circle_1Sprite.setPosition(Gdx.graphics.getWidth() / 2 - circle_1Sprite.getWidth() / 2, Gdx.graphics.getHeight() / 2 - circle_1Sprite.getHeight() / 2);
		circle_2Texture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/hud/circleluar.png"));
		circle_2Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		circle_2Sprite = new Sprite(circle_2Texture);
		circle_2Sprite.setPosition(Gdx.graphics.getWidth() / 2 - circle_2Sprite.getWidth() / 2, Gdx.graphics.getHeight() / 2 - circle_2Sprite.getHeight() / 2);
		
		//initiating stopwatch
		stopwatch = new StopWatch();
		stopwatch.start();
		
		//initiating background
		bgTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/blackBackground.png"));
		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bgSprite = new Sprite(bgTexture);
		bgOpacity = 0.8f;
		
		//initiating button loose screen
		stageLoseScreen = new Stage();
		Gdx.input.setInputProcessor(stageLoseScreen);
		Table tableLoseScreen = new Table();		
		Table tableShareChatOn = new Table();

		subTitleFont = new BitmapFont(Gdx.files.internal("font/freestylefont.fnt"),Gdx.files.internal("font/freestylefont_0.png"),false);
		
		//initiating button
		buttonAtlas = new TextureAtlas(Gdx.files.internal(Constant.resolution+"/menu/button/button.atlas"));
		buttonSkin = new Skin(buttonAtlas);
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = buttonSkin.getDrawable("buttonUp");
		textButtonStyle.down = buttonSkin.getDrawable("buttonDown");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = subTitleFont;
		
		retryButton = new TextButton("RETRY", textButtonStyle);
		exitButton = new TextButton("Exit", textButtonStyle);
		
		//initiating button shareChatON
		shareChatOnTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/button/shareon.png"));
		TextureRegion shareChatOnButtonRegion = new TextureRegion(shareChatOnTexture);
		TextButtonStyle shareChatOnButtonStyle = new TextButtonStyle();
		shareChatOnButtonStyle.up = new TextureRegionDrawable(shareChatOnButtonRegion);
		shareChatOnButtonStyle.down = new TextureRegionDrawable(shareChatOnButtonRegion);
		shareChatOnButton = new Button();
		shareChatOnButton.setStyle(shareChatOnButtonStyle);
		
		//arrange table button
		tableShareChatOn.setTransform(true);
		tableShareChatOn.setFillParent(true);
		tableShareChatOn.add(shareChatOnButton).center();
		tableShareChatOn.setPosition(0, -100);
		
		tableLoseScreen.setTransform(true);
		tableLoseScreen.setFillParent(true);
		tableLoseScreen.add(retryButton).center().bottom().expandY();
		tableLoseScreen.add(exitButton).center().bottom().expandY();
		
		stageLoseScreen.addActor(tableLoseScreen);
		stageLoseScreen.addActor(tableShareChatOn);
		
	}
	
	public void render(float delta, SpriteBatch batch, Camera camera)
	{
		//draw text
		headerFont.setColor(1f, 1f, 1f, 1f);
		headerFont.setColor(Color.ORANGE);
		headerFont.draw(batch, "Level "+main.level.trackId, camera.position.x - Gdx.graphics.getWidth() / 2 + 100, camera.position.y + Gdx.graphics.getHeight() / 2);
		headerFont.draw(batch, stopwatch.getTime(), camera.position.x + Gdx.graphics.getWidth() / 5, camera.position.y + Gdx.graphics.getHeight() / 2);
		switch (gameState.currentState) {
		case GameState.START:
			
			//draw black background
			bgColor = batch.getColor();
			bgSprite.setColor(bgColor.r,bgColor.g,bgColor.b,bgOpacity);
			bgSprite.setPosition(camera.position.x - bgTexture.getWidth() / 2, camera.position.y - bgTexture.getHeight() / 2);
			bgSprite.draw(batch);
			
			//draw circle
			circle_1Sprite.draw(batch);
			circle_1Sprite.setPosition(camera.position.x - circle_1Sprite.getWidth() / 2, camera.position.y - circle_1Sprite.getHeight() / 2);
			circle_1Sprite.setRotation(circle_1Rotation+=0.1f);
			circle_2Sprite.draw(batch);
			circle_2Sprite.setPosition(camera.position.x - circle_2Sprite.getWidth() / 2, camera.position.y - circle_2Sprite.getHeight() / 2);
			circle_2Sprite.setRotation(circle_2Rotation-=0.15f);
			
			time+= Gdx.graphics.getDeltaTime()*130;
			if(time > 99)
			{
				time = 0;
				second--;
			}
			if(second == 0)
			{
				gameState.currentState = gameState.GAME_PLAY;
				second = 3;
			}
			
			readySetGoFont.setColor(1f,1f,1f,1f);
			readySetGoFont.setScale(size);
			readySetGoFont.draw(batch,second+"", camera.position.x -readySetGoFont.getBounds(second+"").width / 2,camera.position.y  + readySetGoFont.getBounds(second+"").height / 2);
			
			break;
		case GameState.GAME_PLAY:
			//stopwatch
			stopwatch.start();
			stopwatch.render();
			
			if((Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK)) && backButtonIsPressed)
			{
				backButtonIsPressed = false;
				gameState.currentState = GameState.PAUSE;
			}else
			{
				backButtonIsPressed = true;
			}
			
			if(Gdx.input.isTouched())
			{
				holdTouch = true;
				System.out.println(holdTouch);
			}
			
			break;
		case GameState.PAUSE:
			stopwatch.stop();
			main.sound.bgm.pause();
			
			//draw background
			bgColor = batch.getColor();
			bgSprite.setColor(bgColor.r,bgColor.g,bgColor.b,bgOpacity);
			bgSprite.setPosition(camera.position.x - bgTexture.getWidth() / 2, camera.position.y - bgTexture.getHeight() / 2);
			bgSprite.draw(batch);
			
			//draw circle
			circle_1Sprite.draw(batch);
			circle_1Sprite.setPosition(camera.position.x - circle_1Sprite.getWidth() / 2, camera.position.y - circle_1Sprite.getHeight() / 2);
			circle_1Sprite.setRotation(circle_1Rotation+=0.1f);
			circle_2Sprite.draw(batch);
			circle_2Sprite.setPosition(camera.position.x - circle_2Sprite.getWidth() / 2, camera.position.y - circle_2Sprite.getHeight() / 2);
			circle_2Sprite.setRotation(circle_2Rotation-=0.15f);
			
			//draw title
			titlePauseSprite.setPosition(camera.position.x - titlePauseSprite.getWidth() / 2 , camera.position.y - titlePauseSprite.getHeight() / 2 + Gdx.graphics.getHeight() / 3);
			titlePauseSprite.draw(batch);
		
			//button set up
			retryButton.setText("CONTINUE");
			exitButton.setText("EXIT");
			
			//draw button
			batch.end();
			stageLoseScreen.act(delta);
			stageLoseScreen.draw();
			batch.begin();
			
			if(!holdTouch)
			{
				if(retryButton.isPressed() && backButtonIsPressed)
				{
					main.sound.bgm.play();
					backButtonIsPressed = false;
					gameState.currentState = GameState.GAME_PLAY;
				}else
				{
					backButtonIsPressed = true;
				}			
				if(exitButton.isPressed())
				{
					main.sound.bgm.stop();
					main.changeScreen(MainMenu.MAIN_MENU);
				}
				if(shareChatOnButton.isPressed() && chatONSTatus)
				{
					HUD.chatONSTatus = false;
					holdTouch = false;
					main.aInterface.shareChatOn(Constant.SHAREGAMELINK, main.level.trackId+"", stopwatch.getTime());
				}
			}
			if(!Gdx.input.isTouched())
			{
				holdTouch = false;
			}
			if(!shareChatOnButton.isPressed())
			{
				chatONSTatus = true;
			}
			break;
		case GameState.LOSE:
			
			//draw background
			bgColor = batch.getColor();
			bgSprite.setColor(bgColor.r,bgColor.g,bgColor.b,bgOpacity);
			bgSprite.setPosition(camera.position.x - bgTexture.getWidth() / 2, camera.position.y - bgTexture.getHeight() / 2);
			bgSprite.draw(batch);
			
			//draw circle
			circle_1Sprite.draw(batch);
			circle_1Sprite.setPosition(camera.position.x - circle_1Sprite.getWidth() / 2, camera.position.y - circle_1Sprite.getHeight() / 2);
			circle_1Sprite.setRotation(circle_1Rotation+=0.1f);
			circle_2Sprite.draw(batch);
			circle_2Sprite.setPosition(camera.position.x - circle_2Sprite.getWidth() / 2, camera.position.y - circle_2Sprite.getHeight() / 2);
			circle_2Sprite.setRotation(circle_2Rotation-=0.15f);
			
			//draw title
			titleLoseSprite.setPosition(camera.position.x - titleLoseSprite.getWidth() / 2 , camera.position.y - titleLoseSprite.getHeight() / 2 + Gdx.graphics.getHeight() / 3);
			titleLoseSprite.draw(batch);
			subTitleFont.draw(batch, "Ops.. Don't hit the edge of track.", camera.position.x - subTitleFont.getBounds("Ops.. Dont hit the edge of track.").width / 2, camera.position.y + subTitleFont.getBounds("Ops.. Dont hit the edge of track.").height / 2 + Gdx.graphics.getHeight() * 0.1f);

			//button set up
			retryButton.setText("RETRY");
			exitButton.setText("EXIT");
			
			//draw button
			batch.end();
			stageLoseScreen.act(delta);
			stageLoseScreen.draw();
			batch.begin();
			
			if(!holdTouch)
			{
				if(retryButton.isPressed())
				{
					holdTouch = false;
					main.level.retry();
				}
				if(exitButton.isPressed())
				{
					holdTouch = false;
					main.changeScreen(MainMenu.MAIN_MENU);
				}
				if(shareChatOnButton.isPressed() && chatONSTatus)
				{
					HUD.chatONSTatus = false;
					holdTouch = false;
					main.aInterface.shareChatOn(Constant.SHAREGAMELINK, main.level.trackId+"", stopwatch.getTime());
				}
			}
			if(!Gdx.input.isTouched())
			{
				holdTouch = false;
			}
			if(!shareChatOnButton.isPressed())
			{
				chatONSTatus = true;
			}

			break;
		case GameState.FINISH:
			
			//draw black background
			bgColor = batch.getColor();
			bgSprite.setColor(bgColor.r,bgColor.g,bgColor.b,bgOpacity);
			bgSprite.setPosition(camera.position.x - bgTexture.getWidth() / 2, camera.position.y - bgTexture.getHeight() / 2);
			bgSprite.draw(batch);
			
			//draw circle
			circle_1Sprite.draw(batch);
			circle_1Sprite.setPosition(camera.position.x - circle_1Sprite.getWidth() / 2, camera.position.y - circle_1Sprite.getHeight() / 2);
			circle_1Sprite.setRotation(circle_1Rotation+=0.1f);
			circle_2Sprite.draw(batch);
			circle_2Sprite.setPosition(camera.position.x - circle_2Sprite.getWidth() / 2, camera.position.y - circle_2Sprite.getHeight() / 2);
			circle_2Sprite.setRotation(circle_2Rotation-=0.15f);
			
			//draw title
			titleFinishSprite.setPosition(camera.position.x - titleFinishSprite.getWidth() / 2 , camera.position.y - titleFinishSprite.getHeight() / 2 + Gdx.graphics.getHeight() / 3);
			titleFinishSprite.draw(batch);
			subTitleFont.draw(batch, "Your Time Is "+stopwatch.getTime(), camera.position.x - subTitleFont.getBounds("The Time Is "+stopwatch.getTime()).width / 2, camera.position.y + subTitleFont.getBounds("The Time Is "+stopwatch.getTime()).height / 2 + Gdx.graphics.getHeight() * 0.1f);

			//button set up
			retryButton.setText("OK");
			exitButton.setText("EXIT");
			
			//draw button
			batch.end();
			stageLoseScreen.act(delta);
			stageLoseScreen.draw();
			batch.begin();
			
			if(!holdTouch)
			{
				if(retryButton.isPressed())
				{
					holdTouch = false;
					main.changeScreen(MainMenu.LEVEL_MENU);
				}
				
				if(exitButton.isPressed())
				{
					holdTouch = false;
					main.changeScreen(MainMenu.MAIN_MENU);
				}
				
				if(shareChatOnButton.isPressed() && chatONSTatus)
				{
					HUD.chatONSTatus = false;
					holdTouch = false;
					main.aInterface.shareChatOn(Constant.SHARETIME, main.level.trackId+"", stopwatch.getTime());
				}
			}
			
			if(!Gdx.input.isTouched())
			{
				holdTouch = false;
			}
			if(!shareChatOnButton.isPressed())
			{
				chatONSTatus = true;
			}
			
			break;

		default:
			break;
		}
		
	}
	
	public void resume()
	{

	}
	
	

}
