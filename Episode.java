import java.io.Serializable;

/**
 * Project #3
 * CS 2334, Section 010
 * Mar 26, 2016
 * <P>
 * Individual episode within a TV series
 * </P>
 */
public class Episode extends Show implements Serializable{
	
	/** SerialID that lets us implement Serializable */
	private static final long serialVersionUID = 1L;
	
	/**Number of episode within series*/
	private String episodeNumber;
	/**Series that this episode is in*/
	private TVSeries series;
	
	/**Constructor for individual episode
	 * 
	 * @param name Name of episode. null if episode has no name.
	 * @param year Year released
	 * @param episodeNumber Number within series
	 */
	public Episode(String name, String year, String episodeNumber, TVSeries series) {
		super(name, year, year);
		this.episodeNumber = episodeNumber;
		this.series = series;
	}
	
	/**
	 * Returns a string representing the episode
	 */
	public String toString() {
		if (getName() == null) {
			return "EPISODE: " + series.getName() + ": " + episodeNumber + " (" + getYear() + ")";
		}
		return "EPISODE: " + series.getName() + ": " + getName() + " (" + getYear() + ")";
	}
}