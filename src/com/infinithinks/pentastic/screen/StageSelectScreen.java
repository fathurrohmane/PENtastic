package com.infinithinks.pentastic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.InputHandler;
import com.infinithinks.pentastic.PENtasticMain;

public class StageSelectScreen implements Screen {

	PENtasticMain main;
	InputHandler touch;
	
	//image staff
	public Texture backgroundTexture;
	public Sprite backgroundSprite;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	
	// back button
	public Texture TextureBack;
	public Rectangle RectangleBack;
	
	
	//stage staff
	public Stage stage;
	public Texture stage1Texture,stage2Texture,stage3Texture;
	public Rectangle stage1Button,stage2Button,stage3Button;
	
	public StageSelectScreen(PENtasticMain main) {
		this.main = main;
		initiation();
	}
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		touchListner();
		camera.update();
		//listener();
		//fadeOut();
		//animator(delta);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundSprite.draw(batch);
		batch.draw(stage1Texture, stage1Button.x, stage1Button.y, stage1Texture.getWidth(), stage1Texture.getHeight());
		batch.draw(stage2Texture, stage2Button.x, stage2Button.y, stage2Texture.getWidth(), stage2Texture.getHeight());
		batch.draw(stage3Texture, stage3Button.x, stage3Button.y, stage3Texture.getWidth(), stage3Texture.getHeight());
		batch.draw(TextureBack, RectangleBack.x, RectangleBack.y, TextureBack.getWidth(), TextureBack.getHeight());

		batch.end();
		stage.act(delta);
		stage.draw();

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
			if(touch.pointInRectangle(stage1Button, touch.touchPoint.x, touch.touchPoint.y)){
				main.setScreen(new LevelSelectScreen1(main));

			}
			
			if(touch.pointInRectangle(stage2Button, touch.touchPoint.x, touch.touchPoint.y)){
				main.setScreen(new LevelSelectScreen2(main));

			}
			
			if(touch.pointInRectangle(RectangleBack, touch.touchPoint.x, touch.touchPoint.y) || Gdx.input.isKeyPressed(Keys.ESCAPE)){
				main.setScreen(new MainMenuScreen(main));
			}
		}
	}
	
	public void initiation(){
		
		//make engine stuff
		camera = new OrthographicCamera(Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
		//camera = new OrthographicCamera(1,Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
		touch = new InputHandler();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.x = Gdx.graphics.getWidth() / 2;
		camera.position.y = Gdx.graphics.getHeight() / 2;
		batch = new SpriteBatch();
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		//setup background
		backgroundTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stageSelect/stageScreen.png"));
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(backgroundTexture.getWidth(), backgroundTexture.getHeight());
		backgroundSprite.setScale(1f);
		backgroundSprite.setPosition(Gdx.graphics.getWidth() / 2 - backgroundTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - backgroundTexture.getHeight() / 2);
				
		//setup button
		stage1Texture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stageSelect/stage1Button.png"));
		stage1Button = new Rectangle(0,
				Gdx.graphics.getHeight() / 2 - stage1Texture.getHeight() ,
				stage1Texture.getWidth(),
				stage1Texture.getHeight());
//		stage1Button.setPosition(Gdx.graphics.getWidth() / 2 - stage1Texture.getWidth() / 2, Gdx.graphics.getHeight() - stage1Texture.getHeight()*3);
//		stage1Button.setWidth(stage1Texture.getWidth());
//		stage1Button.setHeight(stage1Texture.getHeight());
		
		stage2Texture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stageSelect/stage2Button.png"));
		stage2Button = new Rectangle(Gdx.graphics.getWidth() / 2 - stage2Texture.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - stage2Texture.getHeight(),
				stage2Texture.getWidth(),
				stage2Texture.getHeight());
//		stage2Button.setPosition(Gdx.graphics.getWidth() / 2 - stage2Texture.getWidth() / 2, Gdx.graphics.getHeight() - stage2Texture.getHeight()*2);
//		stage2Button.setWidth(stage2Button.getWidth());
//		stage2Button.setHeight(stage2Button.getHeight());
//		
		stage3Texture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/stageSelect/stage3Button.png"));
		stage3Button = new Rectangle(Gdx.graphics.getWidth() - stage3Texture.getWidth()  , 
				Gdx.graphics.getHeight() / 2  - stage3Texture.getHeight(),
				stage3Texture.getWidth(),
				stage3Texture.getHeight());
//		stage3Button.setPosition(Gdx.graphics.getWidth() / 2 - stage3Texture.getWidth() / 2, Gdx.graphics.getHeight() - stage3Button.getHeight()*1);
//		stage3Button.setWidth(stage3Texture.getWidth());
//		stage3Button.setHeight(stage3Texture.getHeight());
		
		
		// back button
				TextureBack = new Texture(Gdx.files.internal(Constant.resolution+"/menu/backButton.png"));
				RectangleBack = new Rectangle(10, Gdx.graphics.getHeight() - TextureBack.getHeight()
						,TextureBack.getWidth(),TextureBack.getHeight());
		
	}

}
