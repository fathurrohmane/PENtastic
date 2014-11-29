package com.infinithinks.pentastic.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.infinithinks.others.Constant;
import com.infinithinks.others.StopWatch;
import com.infinithinks.pentastic.GameState;
import com.infinithinks.pentastic.ObstacleHandler;
import com.infinithinks.pentastic.PENtasticMain;
import com.infinithinks.pentastic.PortalHandler;
import com.infinithinks.pentastic.SoundHandler;
import com.infinithinks.pentastic.TouchHandler;
import com.infinithinks.pentastic.entities.Obstacle;
import com.infinithinks.pentastic.entities.Player;
import com.infinithinks.pentastic.entities.Point;
import com.infinithinks.pentastic.entities.Portal;
import com.infinithinks.pentastic.entities.Track;
import com.infinithinks.pentastic.screen.HUD;

public class Level implements Screen{

	private PENtasticMain main;
	
	// Render general
	public SpriteBatch batch;
	public OrthographicCamera camera;
	
	//Box2d stuff
	public World world;
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_TO_WORLD = 100f;
	public Box2DDebugRenderer debugRenderer;
	
	//level stuff
	public Track track;
	
	//player stuff
	public Player player;
	
	//start point
	public Point finishPoint;
	
	//touch handler
	public TouchHandler touch;
	
	//background stuff
	public Texture bgTexture;
	public Sprite bgSprite;
	
	//game state handler
	public GameState gameState;
	
	//HUD Stuff
	public HUD hud;
	
	//obstcle stuff
	public ObstacleHandler obstacleHandler;
	
	//portal stuff
	public PortalHandler portalHandler;
	
	public int trackId;
	
	public Level(PENtasticMain main, int trackId)
	{
		this.main = main;
		this.trackId = trackId;
	}
	
	@Override
	public void render(float delta) {
		//create blackcolor BG
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		//set box2d world
		world.step(1/60f,10 , 10);
		//debugRenderer.render(world, camera.combined);

		//camera 
		camera.update();
	
		//draw stuff
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		//background
		if(Constant.DRAW_SPRITE)
		{
			bgSprite.draw(batch);
		}
		bgSprite.setPosition(camera.position.x - bgTexture.getWidth() / 2, camera.position.y - bgTexture.getHeight() / 2);
		track.render(delta, batch);
		
		//finishPoint.render(delta, batch);

		//obstacle
		obstacleHandler.render(delta, batch);
		
		//portal
		portalHandler.render(delta, batch);
		
		//player
		player.render(delta, batch, camera, touch);
		
		//hud
		hud.render(delta, batch, camera);
		

		switch (gameState.currentState) {
		case GameState.START:
			
			break;
		case GameState.GAME_PLAY:
			//touch
			touch.render(delta);
			
			//set back button
			//if (Gdx.input.isButtonPressed(Keys.BACK)){
			//    gameState.currentState = gameState.PAUSE;
			//    this.pause();
			//}
			
			break;
		case GameState.PAUSE:
			
			//set back button
			//if (Gdx.input.isButtonPressed(Keys.BACK)){
			//    gameState.currentState = gameState.GAME_PLAY;
			//    this.resume();
			//}
			
			break;
		case GameState.LOSE:
			
			break;
		case GameState.FINISH:
			
			break;

		default:
			break;
		}
		
		batch.end();
		
	}
	
	@Override
	public void resize(int width, int height) {
		//handle resize screen
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
		
	}

	@Override
	public void show() {
		
		//engine stuff
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.x = Constant.cameraStartPosition(trackId).x;
		camera.position.y = Constant.cameraStartPosition(trackId).y;
		batch = new SpriteBatch();
		
		//initiating box2d stuff
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();
		
		//initiating level stuff
		track = new Track(Constant.trackIdToName(trackId), world);
		finishPoint = new Point(world, Constant.posFinishPoint(trackId),"finish");

		//initiating touch 
		touch = new TouchHandler(camera);
		
		//initiating player
		player = new Player(world, camera);
		
		//initiating collusion listener
		createCollisionListener();
		
		//initiating bg
		bgTexture = new Texture(Gdx.files.internal(Constant.resolution+"/level/background_r.jpg"));
		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bgSprite = new Sprite(bgTexture);
		bgSprite.setSize(bgTexture.getWidth(), bgTexture.getHeight());
		bgSprite.setPosition(-bgTexture.getWidth() / 2, -bgTexture.getHeight() / 2);
		
		//initiating gamestate
		gameState = new GameState();
		
		//initiating HUD
		hud = new HUD(gameState,main);
		
		//initiating Sound
		main.sound.bgm.play();
		
		//initiating obstacle
		obstacleHandler = new ObstacleHandler(trackId, this);
		
		//initiating portal
		portalHandler = new PortalHandler(trackId, this);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		if(gameState.currentState != GameState.FINISH && gameState.currentState != GameState.LOSE)
		{
			gameState.currentState = GameState.PAUSE;
		}
		
	}

	@Override
	public void resume() {
		hud.resume();
	}

	@Override
	public void dispose() {
		// do dispose stuff
		//world.dispose();
		batch.dispose();
		track.dispose();
	}
	
	private void createCollisionListener() {
        world.setContactListener(new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void endContact(Contact contact) {
				System.out.println("Collusion END!");
			}
			
			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub
				Body a = contact.getFixtureA().getBody();
				Body b = contact.getFixtureB().getBody();
				
				System.out.println("Collusion! BEGIN");

				if(a.getUserData() instanceof Track && b.getUserData() instanceof Player)
				{
					System.out.println("Col : Track And Player");
					if(!Constant.INVINCIBLE)
					{
						gameState.currentState = GameState.LOSE;
					}
					main.sound.bgm.stop();
					main.sound.lose.play();
				}
				if(a.getUserData() instanceof Point && b.getUserData() instanceof Player)
				{
					System.out.println("Col : Player And Point");
					if(gameState.currentState != GameState.FINISH)
					{
						main.sound.bgm.stop();
						main.sound.win.play();
					}
					if(!Constant.INVINCIBLE)
					{
						gameState.currentState = GameState.FINISH;
					}
				}
				if(a.getUserData() instanceof Obstacle && b.getUserData() instanceof Player)
				{
					System.out.println("Col : Obstacle And Player");
					if(gameState.currentState != GameState.LOSE)
					{
						main.sound.bgm.stop();
						main.sound.lose.play();
					}
					if(!Constant.INVINCIBLE)
					{
						gameState.currentState = GameState.LOSE;
					}
				}
				if(a.getUserData() instanceof Track && b.getUserData() instanceof Point)
				{
					System.out.println("Col : Track & Point");
				}
				if(a.getUserData() instanceof Portal && b.getUserData() instanceof Player)
				{
					System.out.println("Col : Portal And Player");
					System.out.println(a.getUserData().toString());				
				}
			}
		});
    }

	public void retry()
	{
		camera.position.x = Constant.cameraStartPosition(trackId).x;
		camera.position.y = Constant.cameraStartPosition(trackId).y;
		hud.stopwatch.restart();
		gameState.currentState = gameState.START;
		main.sound.bgm.play();
	}
}
