
public class Song {
	//
	private String titel;
	private String interpret;
	private String album;
	private int erscheinungsjahr;

	public static void main(String[] args) {
		Song s = new Song();
		Song x = new Song();
		s.setSong("A Hard Rain's A-Gonna Fall;The Best Of Bob Dylan Volume 2;Bob Dylan;2000");
		x = s.clone();
		s.setSong("If Everyone Was Listening;The Very Best Of Supertramp Vol. 2;Supertramp;1992");
		System.out.println(s.toString());
		System.out.println(x.toString());
	}

	/**
	 * Kontrolliert ob der Song mit dem �bergebenem Song �bereinstimmt
	 * 
	 * @param s Song
	 * @return true falls sie geich sind
	 */
	public boolean equals(Song s) {
		boolean ret = false;
		if (s != null) 
			ret = this.titel.equals(s.titel) && this.interpret.equals(s.interpret) && this.album.equals(s.album) && this.erscheinungsjahr == s.erscheinungsjahr;
		return ret;
	}

	/**
	 * Comperes the strings in the objekt "Song"
	 * 
	 * @param s Song to be compared with
	 * @return 0 if equal, a number if not
	 */
	public int compareTo(Song s) {
		int ret = 0;
		ret = this.titel.compareTo(s.titel);
		if (ret == 0)
			ret = this.interpret.compareTo(s.interpret);
		if (ret == 0)
			ret = this.album.compareTo(s.album);
		return ret;
	}

	/**
	 * @return Eine kopie vom �bergebenen Songobjekt
	 */
	public Song clone() {
		Song tmp = new Song();
		tmp.titel = this.titel;
		tmp.album = this.album;
		tmp.interpret = this.interpret;
		tmp.erscheinungsjahr = this.erscheinungsjahr;
		return tmp;
	}

	/**
	 * @return die eingenschaften des Songs in ein Strig
	 */
	@Override
	public String toString() {
		char sep = ';';
		return titel + sep + album + sep + interpret + sep + erscheinungsjahr;
	}

	public void setSong(String str) {
		String[] tmp = str.split(";");
		this.titel = tmp[0];
		this.album = tmp[1];
		this.interpret = tmp[2];
		this.erscheinungsjahr = Integer.parseInt(tmp[3]);
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getInterpret() {
		return interpret;
	}

	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getErscheinungsjahr() {
		return erscheinungsjahr;
	}

	public void setErscheinungsjahr(int erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
		if (erscheinungsjahr < 0)
			this.erscheinungsjahr = 0;
	}

}
