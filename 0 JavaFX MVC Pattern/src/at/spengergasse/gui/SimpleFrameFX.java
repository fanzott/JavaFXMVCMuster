package at.spengergasse.gui;
/**
 * 
 */


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * 
 * Frame
 * 
 * @author Leo Fanzott
 *
 */
public class SimpleFrameFX extends Stage {

	// buttons
	final private Button defaultBTN;
	final private Button closeBTN;
	final private Button saveBTN;
	final private Button readBTN;
	
	final private ToggleButton textOrBinaryTBTN;
	
	// menu items
	final MenuItem menuDefaultMI, menuSaveMI, menuCloseMI, menuNewAppMI;
	
	// textfield
	final private TextField lastnameTF;
	final private TextField firstnameTF;
	final private TextField yearOfBirthTF;
	final private TextField filenameTF;

	// reference to the listener
	final private SimpleActionListenerFX simpleListener;	
	
	/**
	 * 
	 */
	public SimpleFrameFX(){		
	
		// instanciate text fields
		lastnameTF=new TextField();
		firstnameTF=new TextField();
		yearOfBirthTF= new TextField();

		// instanciate listener
		simpleListener=new SimpleActionListenerFX(this);
		
		// top level pane: includes menubar + borderpane
		VBox vBox=new VBox();
		
		// borderpane
		BorderPane borderPane=new BorderPane();
		
		
		// gridpane contains rows and columns
		GridPane gridPane=new GridPane();
		gridPane.setPadding(new Insets(10,10,10,10));

		// gap between the components
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		gridPane.add(new Label("Lastname:"),0,0);
		gridPane.add(lastnameTF, 1, 0);
		gridPane.add(new Label("Firstname:"),0,1);
		gridPane.add(firstnameTF, 1, 1);
		gridPane.add(new Label("Year of birth:"),0,2);
		gridPane.add(yearOfBirthTF, 1, 2);
		
		//flowpane for buttons
		FlowPane buttonPane=new FlowPane();
		// default button
		defaultBTN=new Button("Default");
		// add action handler
		defaultBTN.addEventHandler(ActionEvent.ACTION, simpleListener);
		// close button
		closeBTN=new Button("Close");
		// add action handler
		closeBTN.addEventHandler(ActionEvent.ACTION, simpleListener);
		// save button
		saveBTN=new Button("Save pupil");
		// add action handler
		saveBTN.addEventHandler(ActionEvent.ACTION, simpleListener);
		// toggle button to define the file type text or binary
		textOrBinaryTBTN=new ToggleButton("Text");
		textOrBinaryTBTN.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
		// EventHandler for toggle button change text to binary
		textOrBinaryTBTN.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {	
				if (textOrBinaryTBTN.getText().equals("Text")){
					textOrBinaryTBTN.setText("Binary");
					textOrBinaryTBTN.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
				}
				else{
					textOrBinaryTBTN.setText("Text");
					textOrBinaryTBTN.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));
				}
			}
		});
		// hbox for read pupil button and text field to enter filename
		HBox hBox=new HBox();
		hBox.setPadding(new Insets(2, 2, 2, 2));

		// read button
		readBTN=new Button("Read pupil");
		readBTN.setMaxWidth(80);
		// add action handler
		readBTN.addEventHandler(ActionEvent.ACTION, simpleListener);
		// text field to enter filename when read pupil
		filenameTF=new TextField("Enter filename");
		filenameTF.setMaxWidth(120);
		hBox.setPrefWidth(readBTN.getMaxWidth()+filenameTF.getMaxWidth());
		// add button and textfield to hbox
		hBox.getChildren().addAll(readBTN,filenameTF);
		// set border around hbox
		hBox.setBorder(new Border(new BorderStroke(
			    Color.RED, BorderStrokeStyle.DOTTED, null, null)));

		buttonPane.setHgap(10);
		// add components
		buttonPane.getChildren().add(defaultBTN);
		buttonPane.getChildren().add(saveBTN);
		buttonPane.getChildren().add(textOrBinaryTBTN);
		buttonPane.getChildren().add(hBox);
		buttonPane.getChildren().add(closeBTN);
		
		// add panes to borderpane
		borderPane.setBottom(buttonPane);
		borderPane.setCenter(gridPane);
		
		// set properties of the frame
		setTitle("Save/Read pupil");
		setResizable(false);
		centerOnScreen();

		// create and add a menu
		MenuBar menuBar = new MenuBar();
        // Menu file, the underscore defines the following character for mnemonic
		// there is method setMnemonicParsing - default is true
        Menu menuFile = new Menu("_File");
 
        // menu item Save
        menuSaveMI = new MenuItem("_Save pupil");
        // add listener
		menuSaveMI.addEventHandler(ActionEvent.ACTION, simpleListener);
        menuSaveMI.setAccelerator(
                KeyCombination.keyCombination("SHORTCUT+S")
        );
        // menu close
        menuCloseMI = new MenuItem("_Close");
        // add listener
		menuCloseMI.addEventHandler(ActionEvent.ACTION, simpleListener);
		menuCloseMI.setMnemonicParsing(true);
        menuCloseMI.setAccelerator(
                KeyCombination.keyCombination("SHORTCUT+C")
        );
        // add menu items to menu file
        menuFile.getItems().addAll(menuSaveMI,menuCloseMI);
        // menu edit
        Menu menuEdit = new Menu("_Edit");
        // menu default
        menuDefaultMI = new MenuItem("Default");
        // add listener
		menuDefaultMI.addEventHandler(ActionEvent.ACTION, simpleListener);
        menuEdit.getItems().addAll(menuDefaultMI);
        // menu window
        Menu menuWindow = new Menu("_Window");
        // menu default
        menuNewAppMI = new MenuItem("New app");
        // add listener
		menuNewAppMI.addEventHandler(ActionEvent.ACTION, simpleListener);
        menuWindow.getItems().addAll(menuNewAppMI);
        // add menus to menu bar 
        menuBar.getMenus().addAll(menuFile,menuEdit,menuWindow);
        
        // add menu bar and borderpane to vbox
        vBox.getChildren().addAll(menuBar,borderPane);

		// set the scene and add borderpane to the scene
		Scene scene=new Scene(vBox, 460, 170);
		setScene(scene);

		// show frame
		show();

	}

	/**
	 * @return the defaultBTN
	 */
	public Button getDefaultBTN() {
		return defaultBTN;
	}


	/**
	 * @return the closeBTN
	 */
	public Button getCloseBTN() {
		return closeBTN;
	}


	/**
	 * @return the saveBTN
	 */
	public Button getSaveBTN() {
		return saveBTN;
	}


	/**
	 * @return the lastnameTF
	 */
	public TextField getLastnameTF() {
		return lastnameTF;
	}


	/**
	 * @return the firstnameTF
	 */
	public TextField getFirstnameTF() {
		return firstnameTF;
	}


	/**
	 * @return the yearOfBirthTF
	 */
	public TextField getYearOfBirthTF() {
		return yearOfBirthTF;
	}


	/**
	 * @return the readBTN
	 */
	public Button getReadBTN() {
		return readBTN;
	}


	/**
	 * @return the filenameTF
	 */
	public TextField getFilenameTF() {
		return filenameTF;
	}




	/**
	 * @return the menuDefaultMI
	 */
	public MenuItem getMenuDefaultMI() {
		return menuDefaultMI;
	}


	/**
	 * @return the menuSaveMI
	 */
	public MenuItem getMenuSaveMI() {
		return menuSaveMI;
	}


	/**
	 * @return the menuCloseMI
	 */
	public MenuItem getMenuCloseMI() {
		return menuCloseMI;
	}

	/**
	 * @return the textOrBinaryTBTN
	 */
	public ToggleButton getTextOrBinaryTBTN() {
		return textOrBinaryTBTN;
	}

	/**
	 * @return the menuNewAppMI
	 */
	public MenuItem getMenuNewAppMI() {
		return menuNewAppMI;
	}

	
}
