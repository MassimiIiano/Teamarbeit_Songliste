
import java.io.*;

public class Songliste {

	private Song[] songs; // liste von Songs
	private static final int DEFAULT_MAXANZAHL = 1000;
	private int nummerAktueller; // nummer vom Aktuellen song
	private int anzahl; // anzahl der Songs
	private String pfad; // pfad der csv-Datei

	/**
	 * Inizialisiert das Objekt Songliste
	 * 
	 * @param max_anzahl
	 */
	public Songliste(int max_anzahl) {
		this.songs = new Song[max_anzahl];
		this.nummerAktueller = 0;
	}

	/**
	 * Inizialisiert das Objekt Songliste mit defoult groese
	 * 
	 */
	public Songliste() {
		this(DEFAULT_MAXANZAHL);
	}

	/**
	 * 
	 * @return the maximum ammount of Songs in the objekt
	 */
	public int getMaxanzahl() {
		return songs.length;
	}

	/**
	 * 
	 * @return the number of songs in the objekt
	 */
	public int getAnzahl() {
		return anzahl;
	}

	/**
	 * 
	 * @return the Song at the current position
	 */
	public Song getAktueller() {
		return this.songs[this.nummerAktueller];
	}

	/**
	 * 
	 * @return the Song at the next position
	 */
	public Song getNaechster() {
		Song ret = this.songs[this.nummerAktueller];
		if (this.nummerAktueller < this.anzahl - 1) {
			this.nummerAktueller++;
			ret = this.songs[this.nummerAktueller];
		}
		return ret;
	}

	/**
	 * 
	 * @return the Song at the previus position
	 */
	public Song getVorherigen() {
		Song ret = this.songs[this.nummerAktueller];
		if (this.nummerAktueller - 1 >= 0) {
			this.nummerAktueller--;
			ret = this.songs[this.nummerAktueller];
		}
		return ret;
	}

	/**
	 * 
	 * @return the first Song
	 */
	public Song getErster() {
		this.nummerAktueller = 0;
		return this.songs[0];
	}

	/**
	 * 
	 * @return the last song in the list
	 */
	public Song getLetzter() {
		if (anzahl > 0)
			nummerAktueller = anzahl - 1;
		return this.songs[nummerAktueller];
	}

	/**
	 * add a new Song at he last position of the list
	 * 
	 * @param s new song`
	 */
	public void anfuegenNeuen(Song s) {
		if (this.anzahl < this.songs.length) {
			this.songs[anzahl] = s;
			this.anzahl++;
			sort();
			aendernAktuellen(s);
		}
	}

	/**
	 * Changes the number of the current song to the song s
	 * 
	 * @param s new current song
	 */
	public Song aendernAktuellen(Song s) {
		Song ret = songs[nummerAktueller];
		int i = 0;
		while (songs[i].compareTo(s) != 0 && i < anzahl) {
			i++;
		}
		if (i < anzahl) {
			nummerAktueller = i;
			ret = songs[nummerAktueller];
		}
		return ret;
	}

	/**
	 * delite the current song
	 * 
	 * @return the next song
	 */
	public Song loeschenAktuellen() {
		Song ret = new Song();
		for (int i = nummerAktueller; i < anzahl - 1; i++)
			songs[i] = songs[i + 1];
		if (anzahl > 0)
			anzahl--;
		songs[anzahl] = null;
		if (nummerAktueller >= anzahl && anzahl > 0)
			ret = songs[anzahl - 1];
		else
			ret = songs[nummerAktueller];

		return ret;
	}

	/**
	 * removes all songs in the objekt songlist
	 */
	public void loeschenAlle() {
		for (int i = 0; i < anzahl; i++) {
			songs[i] = null;
		}
		nummerAktueller = 0;
		anzahl = 0;
	}

	/**
	 * reads songs from a file
	 */
	public void lesenSongs() {
		int i = 0;
		Song[] tmp = readSongs(pfad, getLines(pfad));

		while (i < tmp.length && i < this.songs.length) {
			this.songs[i] = tmp[i];
			i++;
		}
		if (tmp.length > this.songs.length) {
			anzahl = songs.length;
		} else {
			anzahl = tmp.length;
		}
		// anzahl = getLines(pfad);
		this.nummerAktueller = 0;
	}

	/**
	 * schreibt alle songs in eine csv datei
	 */
	public void schreibenSongs() {
		write(songs, pfad);
	}

	// geters und setters
	public String getPfad() {
		return pfad;
	}

	public void setPfad(String pfad) {
		this.pfad = pfad;
	}

	/**
	 * writes the song in the file with the paht ziel
	 * 
	 * @param s    text
	 * @param ziel zieldatei
	 */
	public static void write(Song[] s, String ziel) {
		try {
			// new FileWriter(ziel, false).close(); // delite conntent of ziel
			BufferedWriter writer = new BufferedWriter(new FileWriter(ziel));
			for (Song song : s) {
				if (song != null)
					writer.write(song.toString() + "\n");
				// else
				// writer.write("");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Datei nicht angelegt");
		}
	}

	/**
	 * Sortiert ein Songarray
	 * 
	 * @param s songarray
	 * @return geordnetes Songarray
	 */
	public static Song[] sort(Song[] s) {

		for (int i = 1; i < s.length; i++) {
			while (s[i].compareTo(s[i - 1]) < 0) {
				s = swapSongs(s, i);
				if (i > 1)
					i--;
			}
		}

		return s;
	}

	public void sort() {
		for (int i = 1; i < anzahl; i++) {
			while (songs[i].compareTo(songs[i - 1]) < 0) {
				songs = swapSongs(songs, i);
				if (i > 1)
					i--;
			}
		}
	}

	/**
	 * Swap the song at index "index" with the previous song
	 * 
	 * @param s     Songlist
	 * @param index
	 * @return A list of Songs with swapped Songs
	 */
	public static Song[] swapSongs(Song[] s, int index) {
		if (index - 1 >= 0) {
			Song tmp = s[index].clone();
			s[index] = s[index - 1];
			s[index - 1] = tmp.clone();
		}
		return s;
	}

	/**
	 * 
	 * @param quelle path of document
	 * @return the number of lines in a document
	 */
	public static int getLines(String quelle) {
		int ret = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quelle));
			while (reader.readLine() != null)
				ret++;
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		} catch (IOException e) {
			System.out.println("Lesefehler in Datei");
		}
		return ret;
	}

	/**
	 * liest den document und macht daraus eine liste von songs
	 * 
	 * @param quelle pfad vom document
	 * @param lines  anzahl der zeilen
	 * @return liste von songs im document
	 */
	public static Song[] readSongs(String quelle, int lines) {
		Song[] ret = new Song[lines];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quelle));
			int i = 0;
			// String zeile = reader.readLine();
			while (true) {
				String zeile = reader.readLine();
				if (zeile == null)
					// Dateiende erkannt
					break;
				else {
					ret[i] = new Song();
					ret[i].setSong(zeile);
				}
				i++;
			}
			reader.close();
		}
		// Fehler
		catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		} catch (IOException e) {
			System.out.println("Lesefehler in Datei");
		}
		return ret;
	}
}
