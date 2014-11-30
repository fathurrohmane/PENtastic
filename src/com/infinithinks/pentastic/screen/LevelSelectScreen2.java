package com.infinithinks.pentastic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.infinithinks.others.Constant;
import com.infinithinks.pentastic.InputHandler;
import com.infinithinks.pentastic.PENtasticMain;

public class LevelSelectScreen2 implements Screen{
	PENtasticMain main;
	
	InputHandler touch;
		//image staff
		public Texture backgroundTexture;
		public Sprite backgroundSprite;
		public SpriteBatch batch;
		public OrthographicCamera camera;
		
		public boolean isNext, isPrev;
		public float velocity;
		
		//stage staff
		public Stage stage;
		public Texture Texture1_1,Texture1_2,Texture1_3,Texture1_4,Texture1_5, TextureNext, texturePath;
		public Texture Texture1_6,Texture1_7,Texture1_8,Texture1_9,Texture1_10, TexturePrevious, texturePath2;
		public Texture TextureBack;
		public Rectangle RectangleBack;
		public Rectangle Rect1_1,Rect1_2,Rect1_3,Rect1_4,Rect1_5, RectNext, RectPath;
		public Rectangle Rect1_6,Rect1_7,Rect1_8,Rect1_9,Rect1_10, RectPrevious, RectPath2;
		private BitmapFont Font1_1, Font1_2, Font1_3, Font1_4, Font1_5; 
		public LevelSelectScreen2(PENtasticMain main) {
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
			transitionPath();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			backgroundSprite.draw(batch);
			batch.draw(texturePath, RectPath.x, RectPath.y, texturePath.getWidth(), texturePath.getHeight());
			batch.draw(Texture1_1, Rect1_1.x, Rect1_1.y, Texture1_1.getWidth(), Texture1_1.getHeight());
			Font1_1.draw(batch,"2.1", Rect1_1.x +(Font1_1.getBounds("1.1").width / 2) + 10, Rect1_1.y + Font1_1.getBounds("1.1").height * 2);
			batch.draw(Texture1_2, Rect1_2.x, Rect1_2.y, Texture1_2.getWidth(), Texture1_2.getHeight());
			batch.draw(Texture1_3, Rect1_3.x, Rect1_3.y, Texture1_3.getWidth(), Texture1_3.getHeight());
			batch.draw(Texture1_4, Rect1_4.x, Rect1_4.y, Texture1_4.getWidth(), Texture1_4.getHeight());
			batch.draw(Texture1_5, Rect1_5.x, Rect1_5.y, Texture1_5.getWidth(), Texture1_5.getHeight());
			batch.draw(TextureNext, RectNext.x, RectNext.y, TextureNext.getWidth(), TextureNext.getHeight());
			
			batch.draw(texturePath2, RectPath2.x, RectPath2.y, texturePath2.getWidth(), texturePath2.getHeight());
			batch.draw(Texture1_6, Rect1_6.x, Rect1_6.y, Texture1_6.getWidth(), Texture1_6.getHeight());
			batch.draw(Texture1_7, Rect1_7.x, Rect1_7.y, Texture1_7.getWidth(), Texture1_7.getHeight());
			batch.draw(Texture1_8, Rect1_8.x, Rect1_8.y, Texture1_8.getWidth(), Texture1_8.getHeight());
			batch.draw(Texture1_9, Rect1_9.x, Rect1_9.y, Texture1_9.getWidth(), Texture1_9.getHeight());
			batch.draw(Texture1_10, Rect1_10.x, Rect1_10.y, Texture1_10.getWidth(), Texture1_10.getHeight());
			batch.draw(TexturePrevious, RectPrevious.x, RectPrevious.y, TexturePrevious.getWidth(), TexturePrevious.getHeight());
			
			
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
				if(touch.pointInRectangle(RectNext, touch.touchPoint.x, touch.touchPoint.y)){
					isNext = true;
					isPrev = false;
				}
				
				if(touch.pointInRectangle(RectPrevious, touch.touchPoint.x, touch.touchPoint.y)){
					isPrev = true;
					isNext = false;
				}
				
				if(touch.pointInRectangle(RectangleBack, touch.touchPoint.x, touch.touchPoint.y) || Gdx.input.isKeyPressed(Keys.ESCAPE)){
					main.setScreen(new StageSelectScreen(main));
				}
				
				if(touch.pointInRectangle(Rect1_1, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2);
				}
				if(touch.pointInRectangle(Rect1_2, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_2);
				}
				if(touch.pointInRectangle(Rect1_3, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_3);
				}
				if(touch.pointInRectangle(Rect1_4, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_4);
				}
				if(touch.pointInRectangle(Rect1_5, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_5);
				}
				if(touch.pointInRectangle(Rect1_6, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_6);
				}
				if(touch.pointInRectangle(Rect1_7, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_7);
				}
				
				if(touch.pointInRectangle(Rect1_8, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_8);
				}
				
				if(touch.pointInRectangle(Rect1_9, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_9);
				}
				
				if(touch.pointInRectangle(Rect1_10, touch.touchPoint.x, touch.touchPoint.y)){
					main.createLevel(Constant.level_2_10);
				}
			}
		}
		
		public void transitionPath(){
			if(isNext && RectPath2.x >  Gdx.graphics.getWidth() / 2 - backgroundTexture.getWidth() / 2){
				Rect1_1.x -= velocity; Rect1_2.x -= velocity; Rect1_3.x -= velocity; Rect1_4.x -= velocity;
				Rect1_5.x -= velocity; RectNext.x -= velocity; RectPath.x -= velocity;
				
				Rect1_6.x -= velocity; Rect1_7.x -= velocity; Rect1_8.x -= velocity; Rect1_9.x -= velocity;
				Rect1_10.x -= velocity; RectPrevious.x -= velocity; RectPath2.x -= velocity;
				
			}
			
			if(isPrev && RectPath2.x <  (Gdx.graphics.getWidth() + Gdx.graphics.getWidth() / 2 - backgroundTexture.getWidth() / 2) ){
				Rect1_1.x += velocity; Rect1_2.x += velocity; Rect1_3.x += velocity; Rect1_4.x += velocity;
				Rect1_5.x += velocity; RectNext.x += velocity; RectPath.x += velocity;
				
				Rect1_6.x += velocity; Rect1_7.x += velocity; Rect1_8.x += velocity; Rect1_9.x += velocity;
				Rect1_10.x += velocity; RectPrevious.x += velocity; RectPath2.x += velocity;
				
			}
			
		}
		
		public void initiation(){
			
			//make engine stuff
			camera = new OrthographicCamera(Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
			//camera = new OrthographicCamera(1,Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
			camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			camera.position.x = Gdx.graphics.getWidth() / 2;
			camera.position.y = Gdx.graphics.getHeight() / 2;
			touch = new InputHandler();
			batch = new SpriteBatch();
			
			stage = new Stage();
			Gdx.input.setInputProcessor(stage);
			
			velocity = 20f;
			
			//setup background
			backgroundTexture = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/stage2sublevelback.png"));
			backgroundSprite = new Sprite(backgroundTexture);
			backgroundSprite.setSize(backgroundTexture.getWidth(), backgroundTexture.getHeight());
			backgroundSprite.setScale(1f);
			backgroundSprite.setPosition(Gdx.graphics.getWidth() / 2 - backgroundTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 - backgroundTexture.getHeight() / 2);
					
			//setup button
			Texture1_1 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/level0star.png"));
			Rect1_1 = new Rectangle(Gdx.graphics.getWidth() / 2 - Texture1_1.getWidth() * 2,
					Gdx.graphics.getHeight() - (Texture1_1.getHeight() * 1.5f) ,
					Texture1_1.getWidth(),
					Texture1_1.getHeight());
			
			Texture1_2 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_2 = new Rectangle(Gdx.graphics.getWidth() / 2 - 15,
					Gdx.graphics.getHeight() - (Texture1_2.getHeight() * 1.6f),
					Texture1_2.getWidth(),
					Texture1_2.getHeight());
			
			Texture1_3 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_3 = new Rectangle(Gdx.graphics.getWidth() - (Texture1_3.getWidth() * 1.6f),
					Gdx.graphics.getHeight() - (Texture1_3.getHeight() * 1.6f),
					Texture1_3.getWidth(),
					Texture1_3.getHeight());
			
			Texture1_4 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_4 = new Rectangle(Gdx.graphics.getWidth() / 2 - (Texture1_4.getWidth() * 2.8f),
					(Gdx.graphics.getHeight()  / 2 )- (Texture1_4.getHeight() * 1.4f ) ,
					Texture1_4.getWidth(),
					Texture1_4.getHeight());
			
			Texture1_5 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_5 = new Rectangle(Gdx.graphics.getWidth() / 2 - (Texture1_5.getWidth() * 1f),
					(Gdx.graphics.getHeight() / 2)- (Texture1_5.getHeight() * 1.4f),
					Texture1_5.getWidth(),
					Texture1_5.getHeight());
			
			TextureNext= new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/sublevelButtonNextBack.png"));
			RectNext = new Rectangle(Gdx.graphics.getWidth() / 2 + (TextureNext.getWidth() * 1.0f),
					(Gdx.graphics.getHeight() / 2)- (TextureNext.getHeight() * 1.4f),
					TextureNext.getWidth(),
					TextureNext.getHeight());
			
			texturePath = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/stage2sublevel path.png"));
			RectPath = new Rectangle(Gdx.graphics.getWidth() / 2 - backgroundTexture.getWidth() / 2, 
					Gdx.graphics.getHeight() / 2 - backgroundTexture.getHeight() / 2,
					texturePath.getWidth(),
					texturePath.getHeight());
			
			
			// screen 2
			
			TexturePrevious = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/sublevelButtonNextBack.png"));
			RectPrevious= new Rectangle( Gdx.graphics.getWidth() +  (Gdx.graphics.getWidth() / 2 - TexturePrevious.getWidth() * 2),
					Gdx.graphics.getHeight() - (TexturePrevious.getHeight() * 1.5f) ,
					TexturePrevious.getWidth(),
					TexturePrevious.getHeight());
			
			Texture1_6 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_6 = new Rectangle(Gdx.graphics.getWidth() + (  Gdx.graphics.getWidth() / 2 - 15),
					Gdx.graphics.getHeight() - (Texture1_6.getHeight() * 1.6f),
					Texture1_6.getWidth(),
					Texture1_6.getHeight());
			
			Texture1_7 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_7 = new Rectangle(Gdx.graphics.getWidth() +  (Gdx.graphics.getWidth() - (Texture1_7.getWidth() * 1.7f)),
					Gdx.graphics.getHeight() - (Texture1_7.getHeight() * 1.6f),
					Texture1_7.getWidth(),
					Texture1_7.getHeight());
			
			Texture1_8 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_8 = new Rectangle(Gdx.graphics.getWidth() +  (Gdx.graphics.getWidth() / 2 - (Texture1_8.getWidth() * 2.8f)),
					(Gdx.graphics.getHeight()  / 2 )- (Texture1_8.getHeight() * 1.4f ) ,
					Texture1_8.getWidth(),
					Texture1_8.getHeight());
			
			Texture1_9 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_9 = new Rectangle(Gdx.graphics.getWidth() +  (Gdx.graphics.getWidth() / 2 - (Texture1_9.getWidth() * 1f)),
					(Gdx.graphics.getHeight() / 2)- (Texture1_9.getHeight() * 1.4f),
					Texture1_9.getWidth(),
					Texture1_9.getHeight());
			
			Texture1_10= new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/levelLocked19.png"));
			Rect1_10 = new Rectangle(Gdx.graphics.getWidth() +  (Gdx.graphics.getWidth() / 2 + (Texture1_10.getWidth() * 1.0f)),
					(Gdx.graphics.getHeight() / 2)- (Texture1_10.getHeight() * 1.4f),
					Texture1_10.getWidth(),
					Texture1_10.getHeight());
			
			texturePath2 = new Texture(Gdx.files.internal(Constant.resolution+"/menu/levelSelect/stage2sublevel2 path.png"));
			RectPath2 = new Rectangle(Gdx.graphics.getWidth() +  (Gdx.graphics.getWidth() / 2 - backgroundTexture.getWidth() / 2), 
					Gdx.graphics.getHeight() / 2 - backgroundTexture.getHeight() / 2,
					texturePath2.getWidth(),
					texturePath2.getHeight());
			
			// setup FOnt
			Font1_1 = new BitmapFont(Gdx.files.internal("font/freestylefont.fnt"),Gdx.files.internal("font/freestylefont_0.png"),false);
			Font1_1.setColor(0, 0, 0, 1f);
			Font1_1.setScale(2);
			
			//back button
			TextureBack = new Texture(Gdx.files.internal(Constant.resolution+"/menu/backButton.png"));
			RectangleBack = new Rectangle(10, Gdx.graphics.getHeight() - TextureBack.getHeight()
					,TextureBack.getWidth(),TextureBack.getHeight());
			
		}
}
