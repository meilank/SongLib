
// code written by:		Meilan Keshava & Nicholas Konopka

package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class SongLib extends Application 
{	
	
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
