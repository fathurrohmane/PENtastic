package com.infinithinks.pentastic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.infinithinks.pentastic.PENtasticMain;

public class LoadingScreen implements Screen {

	private PENtasticMain main;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	//background stuff
	private Texture loadingTexture;
	private Sprite loadingSprite;
	
	public LoadingScreen(PENtasticMain main)
	{
		this.main = main;
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		loadingSprite.draw(batch);
		batch.end();


	}

	@Override
	public void resize(int width, int height) {


	}

	@Override
	public void show() {
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		
		loadingTexture = new Texture(Gdx.files.internal("menu/loadingScreen.png"));
		loadingTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		loadingSprite = new Sprite(loadingTexture);
		
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

}
