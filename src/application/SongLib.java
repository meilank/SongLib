
// code written by:		Meilan Keshava & Nicholas Konopka

package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import application.Song;

public class SongLib extends Application 
{	
	
	static Song s= new Song("", "", "", "");
	static ObservableList<Song> obsList= 
			FXCollections.observableArrayList(s);
	
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
			
			File f = new File("obsList.txt");
			
			if(f.exists()) 
			{ 
				populateList();// first check if the file exists
				SongLibController.onInit(obsList);
			}
			
			primaryStage.show();
		
			obsList= SongLibController.getObsList();
			primaryStage.setOnCloseRequest(e-> onClose());
		} catch (Exception e) {

		    // generic exception handling
		    e.printStackTrace();
		}

	}
	
	private void populateList() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("obslist.txt"));
		try {
			String s;
			while ((s= br.readLine())!= null)
			{
				String[] arr= s.split("\t");
				
			    Song song= new Song(arr[0], arr[1], arr[2], arr[3]);
			    obsList.add(song);
			}
		} finally {
		    br.close();
		}
	}
	
	private void onClose()
	{
		try {
			PrintWriter writer= new PrintWriter("obslist.txt", "UTF-8");
			
			for (Song s: obsList)
			{
				writer.println(s.title + "\t" + s.artist + "\t" + s.album + "\t" + s.year);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args) 
	{
		launch(args);
	}
}
