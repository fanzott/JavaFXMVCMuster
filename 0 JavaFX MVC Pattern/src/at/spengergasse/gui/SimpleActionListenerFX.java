package at.spengergasse.gui;
/**
 * 
 */


import java.io.IOException;
import java.util.Locale;

import at.spengergasse.model.Pupil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Leo Fanzott
 *
 */
public class SimpleActionListenerFX implements EventHandler<ActionEvent> {
	
	// reference to panel
	final private SimpleFrameFX simpleFrame;
	// reference to the model
	private Pupil pupil;
	
	/**
	 * 
	 * @param simpleFrame
	 */
	public SimpleActionListenerFX(SimpleFrameFX simpleFrame){
		this.simpleFrame=simpleFrame;
		pupil=new Pupil();
		// set values of the text fields
		simpleFrame.getLastnameTF().setText(pupil.getLastname());
		simpleFrame.getFirstnameTF().setText(pupil.getFirstname());
		simpleFrame.getYearOfBirthTF().setText(String.valueOf(pupil.getYearOfBirth()));
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent arg0) {
		// get component which is source of the event
		Object source=arg0.getSource();
		//**********************************************************************
		// default button
		if (source==simpleFrame.getDefaultBTN()||
		    source==simpleFrame.getMenuDefaultMI()){
			pupil=new Pupil();
			simpleFrame.getLastnameTF().setText(pupil.getLastname());
			simpleFrame.getFirstnameTF().setText(pupil.getFirstname());
			simpleFrame.getYearOfBirthTF().setText(pupil.getYearOfBirth().toString());
		}
		//**********************************************************************
		// save button
		if (source==simpleFrame.getSaveBTN()||
			source==simpleFrame.getMenuSaveMI()){
			try {
				// save pupil data into file
				pupil=new Pupil(simpleFrame.getLastnameTF().getText(),
						        simpleFrame.getFirstnameTF().getText(),
						        Integer.parseInt(simpleFrame.getYearOfBirthTF().getText()));
				boolean text=false;
				// save pupil as text or binary - label of the button defines text or binary
				if (simpleFrame.getTextOrBinaryTBTN().getText().equals("Text")) text=true;
				pupil.save(text);
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Exception");
				alert.setHeaderText("");
				alert.setContentText(e.getMessage());
				alert.show();
				e.printStackTrace();
			}
		}
		//**********************************************************************
		// read button
		if (source==simpleFrame.getReadBTN()){
			try {
				// read Pupil - filename must be entered
				pupil.read(simpleFrame.getFilenameTF().getText());
				simpleFrame.getLastnameTF().setText(pupil.getLastname());
				simpleFrame.getFirstnameTF().setText(pupil.getFirstname());
				simpleFrame.getYearOfBirthTF().setText(pupil.getYearOfBirth().toString());				
			} catch (NumberFormatException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Exception");
				alert.setHeaderText("");
				alert.setContentText(e.getLocalizedMessage());
				alert.show();
				e.printStackTrace();
			}
		}//read
		//**********************************************************************
		// close
		if (source==simpleFrame.getCloseBTN()||
			source==simpleFrame.getMenuCloseMI()){
			simpleFrame.close();
		}
		//**********************************************************************
		// open new frame with same functionality
		if (source==simpleFrame.getMenuNewAppMI()){
			new SimpleFrameFX();
		}
		
	}// handle

	

}
