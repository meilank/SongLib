package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SongLibController 
{
	@FXML Button add;
	@FXML Button delete;
	@FXML TextField name;
	@FXML TextField artist;
	@FXML TextField album;
	@FXML TextField year;
	@FXML ListView<Song> list;
	
	Song s= new Song("", "", "", "");
	ObservableList<Song> obsList= 
			FXCollections.observableArrayList(s);
	
	boolean ifFirst= true;
	
	public static <T extends Comparable<T>> void BubbleSortAL(ObservableList<Song> obsList2)
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
		
		if(b== add)
		{
			if (!name.equals("") || !artist.equals(""))
			{
				Song song= new Song(name.getText(), artist.getText(), album.getText(), year.getText());

				obsList.add(song);
				BubbleSortAL(obsList);
				list.setItems(obsList);
			}
			if (ifFirst)
			{
				ifFirst= false;
				obsList.remove(0);
				list.getSelectionModel().select(0);
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
			}
		}
	}
	
}
