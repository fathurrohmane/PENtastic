package Backup;

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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.infinithinks.others.BodyEditorLoader;
import com.infinithinks.pentastic.PENtasticMain;

public class Level_1 implements Screen{

	private PENtasticMain main;
	
	// Render general
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	
	//Box2d stuff
	private World world;
	private static final float WORLD_TO_BOX = 0.01f;
	private static final float BOX_TO_WORLD = 100f;
	private Box2DDebugRenderer debugRenderer;
	
	//level stuff
	private Body trackModel;
	private Texture trackTexture;
	private Sprite trackSprite;
	private Vector2 trackModelOrigin;
	private Vector2 trackPos;
	
	public Level_1(PENtasticMain main)
	{
		this.main = main;
	}
	
	
	@Override
	public void render(float delta) {
		//create blackcolor BG
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//set box2d world
		world.step(1/60f,10 , 10);
		debugRenderer.render(world, camera.combined);
		
		//set pos track
		trackPos = trackModel.getPosition().sub(trackModelOrigin);
		trackSprite.setPosition(trackPos.x, trackPos.y);
		trackSprite.setOrigin(trackModelOrigin.x, trackModelOrigin.y);
		
		//camera
		camera.position.x+= 0.1f;
		camera.update();
		//draw stuff
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//trackSprite.draw(batch);
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
		batch = new SpriteBatch();
		
		//initiating box2d stuff
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();
		
		//initiating level stuff
		createLevelBody();
		
		//initiating texture and sprite
		createSprites();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// do dispose stuff
		world.dispose();
		trackTexture.dispose();
		batch.dispose();
		
	}
	
	private void createLevelBody()
	{
		//load
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("level/Level_1_track.json"));
		
		//create bodydef
		BodyDef bd = new BodyDef();
		//bd.position.set(0, 0);
		bd.type = BodyType.KinematicBody;
		
		//create fixturedef
		FixtureDef fd = new FixtureDef();
		fd.density = 0;
		fd.friction = 1;
		fd.restitution = 0;
		
		//create body
		trackModel = world.createBody(bd);
		
		//create body fixture
		loader.attachFixture(trackModel,"Name",fd , 1683);
		trackModelOrigin = loader.getOrigin("Name", 1683).cpy();
				
		
	}
	
	private void createSprites()
	{
		// create texture and sprite for track
		trackTexture = new Texture(Gdx.files.internal("level/Level_1_track.jpg"));
		trackTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);		
		trackSprite = new Sprite(trackTexture);
		trackSprite.setSize(trackTexture.getWidth(), trackTexture.getHeight());
		
	}

}
