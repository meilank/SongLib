package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;

import java.lang.reflect.InvocationTargetException;

import application.Song;


public class SongLib extends Application 
{
	
	static ListView<Song> list= new ListView<Song>();
	static ListView<String> songNames= new ListView<String>();
	static ObservableList<String> obsList= FXCollections.observableArrayList(" ");
	

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// TODO Auto-generated method stub
		try 
		{
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(getClass().getResource("SongLib.fxml"));
			
			Pane root= (Pane)loader.load();
			
			Scene scene= new Scene(root);
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			
			primaryStage.show();
		} catch (Exception e) {

		    // generic exception handling
		    e.printStackTrace();
		}

	}
	
	public static void main (String[] args) 
	{
		launch(args);
	}
}
