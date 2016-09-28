package application;

public class Song 
{

	String title;
	String artist;
	String album= null;
	String year= null;
	
	public Song(String title, String artist, String album, String year)
	{
		this.title= title;
		this.artist= artist;
		this.album= album;
		this.year= year;
	}
	
	public String toString()
	{
		return title;
	}
	
	public int compareTo(Object s)
	{
		if (s == null || (!(s instanceof Song))) {
				 return -1;
		}
		int x= (this.title).compareTo(((Song) s).title);
		if (x!= 0)
		{
			return x;
		}
		else
		{
			return (this.artist).compareTo(((Song) s).artist);
		}
	}
	
}
