package Backup;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
	
	//menu loose
	private Stage stageLoseScreen;
	private Texture buttonUpTexture;
	private Texture buttonDownTexture;
	private Button retryButton; 
	private Button exitButton;
	private BitmapFont titleFont;
	private BitmapFont subTitleFont;
	private TextButtonStyle style;
	
	//button
	private TextureAtlas buttonAtlas;
	private Skin buttonSkin;
	
	
	
	
	public HUD(GameState gameState,PENtasticMain main)
	{
		this.main = main;
		this.gameState = gameState;
		
		//initiating font
		readySetGoFont = new BitmapFont(Gdx.files.internal("font/123.fnt"),Gdx.files.internal("font/123_0.png"),false);
		readySetGoFont.setColor(1f, 1f, 1f, opacity);
		readySetGoFont.setScale(size);
		
		//initiating button
		buttonAtlas = new TextureAtlas(Gdx.files.internal("menu/button/button.atlas"));
		buttonSkin = new Skin(buttonAtlas);
		

		//initiating font header
		headerFont = new BitmapFont(Gdx.files.internal("font/freestylefont.fnt"),Gdx.files.internal("font/freestylefont_0.png"),false);
		
		//initiating stopwatch
		stopwatch = new StopWatch();
		stopwatch.start();
		
		//initiating background
		bgTexture = new Texture(Gdx.files.internal("menu/blackBackground.png"));
		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bgSprite = new Sprite(bgTexture);
		bgOpacity = 0.8f;
		
		//initiating button loose screen
		stageLoseScreen = new Stage();
		Gdx.input.setInputProcessor(stageLoseScreen);
		Table tableLoseScreen = new Table();
		tableLoseScreen.setTransform(true);
		tableLoseScreen.setFillParent(true);
		tableLoseScreen.setPosition(0, -200);
		stageLoseScreen.addActor(tableLoseScreen);
		titleFont = new BitmapFont(Gdx.files.internal("font/freestylefontbig.fnt"),Gdx.files.internal("font/freestylefontbig_0.png"),false);
		subTitleFont = new BitmapFont(Gdx.files.internal("font/freestylefont.fnt"),Gdx.files.internal("font/freestylefont_0.png"),false);
		
		//initiating button
		buttonUpTexture = new Texture(Gdx.files.internal("menu/buttonUp.png"));
		buttonDownTexture = new Texture(Gdx.files.internal("menu/buttonDown.png"));
		
		TextureRegion buttonUpRegion = new TextureRegion(buttonUpTexture);
		TextureRegion buttonDownRegion = new TextureRegion(buttonDownTexture);
		
		style = new TextButtonStyle();
		style.up = new TextureRegionDrawable(buttonUpRegion);
		style.down = new TextureRegionDrawable(buttonDownRegion);
		retryButton = new Button();
		retryButton.setStyle(style);
		exitButton = new Button();
		exitButton.setStyle(style);
		tableLoseScreen.add(retryButton).bottom();
		tableLoseScreen.row();
		tableLoseScreen.add(exitButton).bottom();
		
		
	}
	
	public void render(float delta, SpriteBatch batch, Camera camera)
	{
		//draw text
		headerFont.setColor(1f, 1f, 1f, 1f);
		headerFont.draw(batch, "Level "+main.level.trackId, camera.position.x - Gdx.graphics.getWidth() / 2 + 100, camera.position.y + Gdx.graphics.getHeight() / 2);
		headerFont.draw(batch, stopwatch.getTime(), camera.position.x + Gdx.graphics.getWidth() / 5, camera.position.y + Gdx.graphics.getHeight() / 2);
		
		switch (gameState.currentState) {
		case GameState.START:
			
			bgColor = batch.getColor();
			bgSprite.setColor(bgColor.r,bgColor.g,bgColor.b,bgOpacity);
			bgSprite.setPosition(camera.position.x / 2 - bgTexture.getWidth() / 2, camera.position.y / 2);
			bgSprite.draw(batch);
			
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
			
			//opacity -=  Gdx.graphics.getDeltaTime()*130/3;
			//size -= Gdx.graphics.getDeltaTime()*130/6;111
			readySetGoFont.setColor(1f,1f,1f,1f);
			readySetGoFont.setScale(size);
			readySetGoFont.draw(batch,second+"", camera.position.x -readySetGoFont.getBounds(second+"").width / 2,camera.position.y  + readySetGoFont.getBounds(second+"").height / 2);
			
			break;
		case GameState.GAME_PLAY:
			//stopwatch
			stopwatch.start();
			stopwatch.render();
			
			break;
		case GameState.PAUSE:
			stopwatch.stop();
			//draw background
			bgColor = batch.getColor();
			bgSprite.setColor(bgColor.r,bgColor.g,bgColor.b,bgOpacity);
			bgSprite.setPosition(camera.position.x - bgTexture.getWidth() / 2, camera.position.y - bgTexture.getHeight() / 2);
			bgSprite.draw(batch);
			
			//draw title
			titleFont.setColor(1f,1f,1f,1f);
			titleFont.draw(batch, "PAUSE",camera.position.x - titleFont.getBounds("PAUSE").width / 2 , camera.position.y - titleFont.getBounds("LOOSE").height / 2 + Gdx.graphics.getHeight() * 0.7f);

			//draw button
			batch.end();
			stageLoseScreen.act(delta);
			stageLoseScreen.draw();
			batch.begin();

			break;
		case GameState.LOSE:
			//draw background
			bgColor = batch.getColor();
			bgSprite.setColor(bgColor.r,bgColor.g,bgColor.b,bgOpacity);
			bgSprite.setPosition(camera.position.x - bgTexture.getWidth() / 2, camera.position.y - bgTexture.getHeight() / 2);
			bgSprite.draw(batch);
			
			//draw title
			titleFont.setColor(1f,1f,1f,1f);			
			titleFont.draw(batch, "LOOSE",camera.position.x - titleFont.getBounds("LOOSE").width / 2 , camera.position.y - titleFont.getBounds("LOOSE").height / 2 + Gdx.graphics.getHeight() * 0.7f);
			subTitleFont.draw(batch, "Ops.. Dont hit the edge of track.", camera.position.x - subTitleFont.getBounds("Ops.. Dont hit the edge of track.").width / 2, camera.position.y + subTitleFont.getBounds("Ops.. Dont hit the edge of track.").height / 2 + Gdx.graphics.getHeight() * 0.1f);

			//draw button
			batch.end();
			stageLoseScreen.act(delta);
			stageLoseScreen.draw();
			batch.begin();
			
			if(retryButton.isPressed())
			{
				main.level.retry();
			}
			
			if(exitButton.isPressed())
			{
				main.changeScreen(1);
			}

			
			break;
		case GameState.FINISH:
			bgColor = batch.getColor();
			bgSprite.setColor(bgColor.r,bgColor.g,bgColor.b,bgOpacity);
			bgSprite.setPosition(camera.position.x - bgTexture.getWidth() / 2, camera.position.y - bgTexture.getHeight() / 2);
			bgSprite.draw(batch);
			
			//draw title
			titleFont.setColor(1f,1f,1f,1f);
			titleFont.draw(batch, "FINISH",camera.position.x - titleFont.getBounds("FINISH").width / 2 , camera.position.y - titleFont.getBounds("FINISH").height / 2 + Gdx.graphics.getHeight() * 0.7f);
			subTitleFont.draw(batch, "The Time Is "+stopwatch.getTime(), camera.position.x - subTitleFont.getBounds("The Time Is "+stopwatch.getTime()).width / 2, camera.position.y + subTitleFont.getBounds("The Time Is "+stopwatch.getTime()).height / 2 + Gdx.graphics.getHeight() * 0.1f);

			//draw button
			batch.end();
			stageLoseScreen.act(delta);
			stageLoseScreen.draw();
			batch.begin();
			
			break;

		default:
			break;
		}
		
	}
	
	

}
