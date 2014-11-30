package com.infinithinks.pentastic.screen;

import sun.rmi.runtime.Log;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.infinithinks.others.CameraAccessor;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.InputHandler;
import com.infinithinks.pentastic.PENtasticMain;

public class MainMenuScreen implements Screen {

	PENtasticMain main;
	
	InputHandler touch;
	
	//image staff
	public Texture backgroundTexture,secondBackgroundTexture,logoTexture,gearTransitionTexture;
	public Sprite backgroundSprite,secondBackgroundSprite,logoSprite,gearTransitionSprite;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	
	//stage staff
	public Stage stage;
	public Texture playButtonTexture,settingButtonTexture,backButtonTexture;
	public Rectangle playButton,settingButton,backButton;
	
	//animation
	public TweenManager tweenManager;
	
	//exit handler
	public boolean backButtonIsPressed = true;
	public boolean stageDisposeable = true;
	
	//transition
	public float speedRotationVelocity;
	public float opacity;
	public float scale;
	public boolean buttonIsPressed;
	
	public MainMenuScreen(PENtasticMain main)
	{
		this.main = main;
		initiation();
	}
	
	public void initiation(){
		//make engine stuff
		camera = new OrthographicCamera(Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
		touch = new InputHandler();
		//camera = new OrthographicCamera(1,Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.x = Gdx.graphics.getWidth() / 2;
		camera.position.y = Gdx.graphics.getHeight() / 2;
		batch = new SpriteBatch();
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		//setup background
		backgroundTexture = new Texture(Gdx.files.internal(Constant.resolution+"/level/background_r.jpg"));
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(backgroundTexture.getWidth(), backgroundTexture.getHeight());
		backgroundSprite.setScale(1.2f);
		backgroundSprite.setPosition(Gdx.graphics.getWidth() / 2 - backgroundTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - backgroundTexture.getHeight() / 2);
		
		secondBackgroundTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/main/secondBackgroundMainMenu.png"));
		secondBackgroundSprite = new Sprite(secondBackgroundTexture);
		secondBackgroundSprite.setSize(secondBackgroundTexture.getWidth(), secondBackgroundTexture.getHeight());
		secondBackgroundSprite.setPosition(Gdx.graphics.getWidth() / 2 - secondBackgroundTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - secondBackgroundTexture.getHeight() / 2);
		
		logoTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/main/logoMainMenu.png"));
		logoSprite = new Sprite(logoTexture);
		logoSprite.setSize(logoTexture.getWidth(), logoTexture.getHeight());
		logoSprite.setPosition(Gdx.graphics.getWidth() / 2 - logoTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - logoTexture.getHeight() / 2);
		
		gearTransitionTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/transition/gearTransition.png"));
		gearTransitionSprite = new Sprite(gearTransitionTexture);
		opacity=0f;
		gearTransitionSprite.setSize(gearTransitionTexture.getWidth(), gearTransitionTexture.getHeight());
		gearTransitionSprite.setPosition(Gdx.graphics.getWidth() / 2 - gearTransitionTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gearTransitionTexture.getHeight() / 2);
		gearTransitionSprite.setScale(0f);
		gearTransitionSprite.setColor(gearTransitionSprite.getColor().r,
				gearTransitionSprite.getColor().g,
				gearTransitionSprite.getColor().b,
				opacity);
		
		
		//setup button
		playButtonTexture = new Texture(Gdx.files.internal(Constant.resolution+"/homebuttonplay19.png"));
		playButton = new Rectangle(Gdx.graphics.getWidth() / 2 - playButtonTexture.getWidth() / 2, Gdx.graphics.getHeight() / 4 - playButtonTexture.getHeight()
				,playButtonTexture.getWidth(),playButtonTexture.getHeight());	
		
		backButtonTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/backButton.png"));
		backButton = new Rectangle(0, Gdx.graphics.getHeight() - backButtonTexture.getHeight()
				,backButtonTexture.getWidth(),backButtonTexture.getHeight());
		
		settingButtonTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/settingButton.png"));
		settingButton = new Rectangle(Gdx.graphics.getWidth() - settingButtonTexture.getWidth(), Gdx.graphics.getHeight() - settingButtonTexture.getHeight()
				,settingButtonTexture.getWidth(),settingButtonTexture.getHeight());
		
		//transition
		buttonIsPressed = false;

		//animation
				tweenManager = new TweenManager();
				Tween.registerAccessor(OrthographicCamera.class, new CameraAccessor());
						
	}
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		camera.update();
		touchListner();
		fadeOut();
		animator(delta);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundSprite.draw(batch);
		secondBackgroundSprite.draw(batch);
		batch.draw(playButtonTexture,playButton.x,playButton.y,playButtonTexture.getWidth(),playButtonTexture.getHeight());
		batch.draw(settingButtonTexture,settingButton.x,settingButton.y,settingButtonTexture.getWidth(),settingButtonTexture.getHeight());
		batch.draw(backButtonTexture,backButton.x,backButton.y,backButtonTexture.getWidth(),backButtonTexture.getHeight());
		logoSprite.draw(batch);
		gearTransitionSprite.draw(batch);
		
		batch.end();
//		sstage.draw();
		
	}

	@Override
	public void resize(int width, int height) {


	}

	@Override
	public void show() {

		
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


	}
	
	public void touchListner(){
		if(Gdx.input.justTouched()){
			touch.camera.unproject(touch.touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			if(touch.pointInRectangle(playButton, touch.touchPoint.x, touch.touchPoint.y)){
				buttonIsPressed = true;
			}
			
			if(touch.pointInRectangle(backButton, touch.touchPoint.x, touch.touchPoint.y) || Gdx.input.isKeyPressed(Keys.ESCAPE)){
				Gdx.app.exit();
			}
		}
	}
	
	public void animator(float delta){
		//logoSprite.setY((float) (logoSprite.getY()+(Math.sin(delta)*10f)));
	}
	
	public void fadeOut(){
		if(buttonIsPressed){
			gearTransitionSprite.rotate(gearTransitionSprite.getRotation()+speedRotationVelocity);
			speedRotationVelocity+=0.1f;
			gearTransitionSprite.setColor(gearTransitionSprite.getColor().r,
					gearTransitionSprite.getColor().g,
					gearTransitionSprite.getColor().b,
					opacity);
			opacity+=0.01f;
			scale+=0.015f;
			gearTransitionSprite.setScale(scale);
			
			if(gearTransitionSprite.getColor().a <= 1){
				//buttonIsPressed = false;
				System.out.println(gearTransitionSprite.getColor().a);
				gearTransitionSprite.rotate(0f);
				gearTransitionSprite.setColor(gearTransitionSprite.getColor().r,
						gearTransitionSprite.getColor().g,
						gearTransitionSprite.getColor().b,
						opacity);
				gearTransitionSprite.setScale(scale);
				main.setScreen(new StageSelectScreen(main));
			}else{
				main.setScreen(new StageSelectScreen(main));
			}
		}
	}

}
