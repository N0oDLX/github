package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Plotter extends Application {
	
	private double x, y, x2, y2, speed;
	private boolean up, down, left, right, up2, down2, left2, right2, faster, slower;
	
	@Override
	public void start( Stage stage ) {
		Group root = new Group();
		Scene scene = new Scene(root);
		
		Canvas canvas = new Canvas(900,700);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GraphicsContext gc2 = canvas.getGraphicsContext2D();
		
		x = 450;
		y = 350;
		x2 = 550;
		y2 = 450;
		speed = 7;
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if ( t.getCode() == KeyCode.W )
					up = true;
				if ( t.getCode() == KeyCode.UP )
					up2 = true;
				if ( t.getCode() == KeyCode.S )
					down = true;
				if ( t.getCode() == KeyCode.DOWN )
					down2 = true;
				if ( t.getCode() == KeyCode.A )
					left = true;
				if ( t.getCode() == KeyCode.LEFT )
					left2 = true;
				if ( t.getCode() == KeyCode.D )
					right = true;
				if ( t.getCode() == KeyCode.RIGHT )
					right2 = true;
				
				if ( t.getCode() == KeyCode.DIGIT1 )
					gc.setFill(Color.BLACK);
				else if ( t.getCode() == KeyCode.DIGIT2 )
					gc.setFill(Color.RED);
				else if ( t.getCode() == KeyCode.DIGIT3 )
					gc.setFill(Color.GREEN);
				else if ( t.getCode() == KeyCode.DIGIT4 )
					gc.setFill(Color.BLUE);
				
				if (t.getCode() == KeyCode.L )
					slower = true;
				if (t.getCode() == KeyCode.P )
					faster = true;
 			}
		});
		
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if ( t.getCode() == KeyCode.W )
					up = false;
				if ( t.getCode() == KeyCode.UP )
					up2 = false;
				if ( t.getCode() == KeyCode.S )
					down = false;
				if ( t.getCode() == KeyCode.DOWN )
					down2 = false;
				if ( t.getCode() == KeyCode.A )
					left = false;
				if ( t.getCode() == KeyCode.LEFT )
					left2 = false;
				if ( t.getCode() == KeyCode.D )
					right = false;
				if ( t.getCode() == KeyCode.RIGHT )
					right2 = false;
				
				if ( t.getCode() == KeyCode.L )
					slower = false;
				if ( t.getCode() == KeyCode.P )
					faster = false;
			}
		});
		
		root.getChildren().add(canvas);
		
		stage.setTitle("Plotter");
		stage.setScene(scene);
		stage.show();
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				
				if ( slower )
					speed = 2;
				else if ( faster )
					speed = 12;
				else
					speed = 7;
				
				if ( up )
					y -= speed;
				if ( up2 )
					y2 -= speed;
				if ( down )
					y += speed;
				if ( down2 )
					y2 += speed;
				if ( left )
					x -= speed;
				if ( left2 )
					x2 -= speed;
				if ( right )
					x += speed;
				if ( right2 ) 
					x2 += speed;
				
				gc.fillRect(x, y, 20, 20);
				gc2.fillRect(x2, y2, 20, 20);
			}
		}.start();
	}
	
	public static void main( String[] args ) { launch(args); }
}
