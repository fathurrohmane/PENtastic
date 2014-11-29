package Backup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.infinithinks.pentastic.PENtasticMain;

public class SplashScreen implements Screen{

	PENtasticMain main;
	
	//splash stuff
	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	Camera camera;
	
	//effect stuff
	Color c;
	float oppacity = 0f;
	boolean back = false;
	
	public SplashScreen(PENtasticMain main)
	{
		this.main = main;
	}
	
	@Override
	public void render(float delta) {

		c = batch.getColor();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		splashSprite.setColor(c.r, c.g, c.b, oppacity);
		splashSprite.draw(batch);
		batch.end();
		
		if(!back)
		{
			if(oppacity <= 1-0.005f)
			{
				oppacity += 0.005f;
			}else
			{
				back= true;
			}
		}else
		{
			if(oppacity >= 0+0.005f)
			{
				oppacity -= 0.005f;
			}else
			{
				this.dispose();
				main.changeScreen(1);
			}
		}
		
		
		
	}

	@Override
	public void resize(int width, int height) {

		
	}

	@Override
	public void show() {
		//make engine stuff
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		
		//make sprite and texture 
		splashTexture = new Texture(Gdx.files.internal("menu/logo.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		splashSprite = new Sprite(splashTexture);
		splashSprite.setSize(splashTexture.getWidth(), splashTexture.getHeight());
		splashSprite.setPosition(Gdx.graphics.getWidth() / 2 - splashTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - splashTexture.getHeight() / 2);
		
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
		splashTexture.dispose();
		
	}

}
