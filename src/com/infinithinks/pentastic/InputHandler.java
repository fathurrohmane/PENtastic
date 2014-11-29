package com.infinithinks.pentastic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class InputHandler {

	public Vector3 touchPoint;
	public OrthographicCamera camera;
	
	public InputHandler(){
		touchPoint = new Vector3();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public boolean pointInRectangle(Rectangle r, float x, float y){
		return r.x <= x && r.x +r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	
}
