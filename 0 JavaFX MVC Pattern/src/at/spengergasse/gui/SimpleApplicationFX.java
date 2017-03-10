package at.spengergasse.gui;



import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Application
 * 
 * @author Leo Fanzott
 *
 */
public class SimpleApplicationFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {	
		new SimpleFrameFX();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}