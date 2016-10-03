
// code written by:		Meilan Keshava & Nicholas Konopka

package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SongLibController 
{
	@FXML Button add;
	@FXML Button delete;
	@FXML Button edit;
	@FXML TextField name;
	@FXML TextField artist;
	@FXML TextField album;
	@FXML TextField year;
	@FXML ListView<Song> list;
	
	static Song s= new Song(" ", " ", " ", " ");
	static ObservableList<Song> obsList= 
			FXCollections.observableArrayList(s);
	
	boolean ifFirst= true;

	public static <T extends Comparable<T>> void 
		BubbleSortAL(ObservableList<Song> obsList2)
	{
		Song temp;
		for (int i=0;i<obsList2.size();i++)
		{
            for (int j=1;j<(obsList2.size()-i);j++)
            {
                if (obsList2.get(j-1).compareTo(obsList2.get(j) )>=0)
                {
                    temp=obsList2.get(j-1);
                    obsList2.remove(j-1);//remove j-1
                    obsList2.add(j, temp);//insert it at index j
                }
            }
        }
	}
	
	public void manageItems(ActionEvent e)
	{
		Button b= (Button)e.getSource();
				
		if(b== add && !(name.getText().trim().isEmpty() ||
					artist.getText().trim().isEmpty()))
		{	
			Song song= new Song(name.getText().trim().toLowerCase(), artist.getText().trim().toLowerCase(), 
					album.getText().trim().toLowerCase(), year.getText().trim().toLowerCase());
			
			if (song.linearSearch(obsList)== 0)
			{
				obsList.add(song);
				BubbleSortAL(obsList);
				list.setItems(obsList);
				
				if (ifFirst)
				{
					ifFirst= false;
					obsList.remove(0);
					list.getSelectionModel().select(0);
				}
				
				list.getSelectionModel()
				.select(obsList.lastIndexOf(song));
				
				Song s2= obsList.get(obsList.lastIndexOf(song));
				name.setText(s2.title);
				artist.setText(s2.artist);
				album.setText(s2.album);
				year.setText(s2.year);
				
				s.title= new String(s2.title);
				s.artist= new String(s2.artist);
			}
			else
			{
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error!");
				a.setHeaderText(null);
				a.setContentText("This song is a duplicate!");
				a.show();
			}
			
						
		}
		else if (b== delete)
		{
			if (!obsList.isEmpty())
			{
				int i= list
						.getSelectionModel()
						.getSelectedIndex();
				obsList.remove(i);	
				
				if (!obsList.isEmpty())
				{
					if(i>0)
						i--;
	
					Song s2= obsList.get(i);
					name.setText(s2.title);
					artist.setText(s2.artist);
					album.setText(s2.album);
					year.setText(s2.year);
					
					s.title= new String(s2.title);
					s.artist= new String(s2.artist);
				}
				
			}
			if (obsList.isEmpty())
			{
				name.clear();
				artist.clear();
				album.clear();
				year.clear();
				
			}
		}
		else if (b== edit && !obsList.isEmpty())
		{
			
			Song selectedSong = null;
			
			selectedSong=s.editSearch(obsList);
			int errorCheck = (new Song(name.getText().trim().toLowerCase(), artist.getText().trim().toLowerCase(), "", "")).linearSearch(obsList);
			
			if(selectedSong != null)
			{
				if(errorCheck==0||(errorCheck==1&&selectedSong.title.compareTo(name.getText().trim().toLowerCase())==0&&selectedSong.artist.compareTo(artist.getText().trim().toLowerCase())==0)){
				
					selectedSong.title= name.getText().trim().toLowerCase();
					selectedSong.artist= artist.getText().trim().toLowerCase();
					selectedSong.album= album.getText().trim().toLowerCase();
					selectedSong.year= year.getText().trim().toLowerCase();
	
					BubbleSortAL(obsList);
					
					/*
					 * this is where weird stuff happens
					 */
					Song temp= new Song("", "", "", "");
					obsList.add(temp);
					BubbleSortAL(obsList);
					
					list.setItems(obsList);
					obsList.remove(0);
					
					list.setItems(obsList);
					
					list.getSelectionModel()
					.select(obsList.lastIndexOf(selectedSong));
				}
				else
				{
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Error!");
					a.setHeaderText(null);
					a.setContentText("Your edit will make this song a duplicate!");
					a.show();
				}
				
				
			}
		}
	}
	
	public void onSelected(MouseEvent e)
	{
		int index= list.getSelectionModel()
				.getSelectedIndex();
		
		Song s2= obsList.get(index);
		name.setText(s2.title);
		artist.setText(s2.artist);
		album.setText(s2.album);
		year.setText(s2.year);
		
		s.title= new String(s2.title);
		s.artist= new String(s2.artist);
	}
	
	public static ObservableList<Song> getObsList() { return obsList; }
	
	public static void onInit(ObservableList<Song> fromFileList)
	{
		obsList= fromFileList;
	}
}
