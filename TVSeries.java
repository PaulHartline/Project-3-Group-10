import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project #3
 * CS 2334, Section 010
 * Mar 26, 2016
 * <P>
 * Class for storing information about TVShows
 * </P>
 */
public class TVSeries extends Show implements Serializable{
	
	/** SerialID that lets us implement Serializable */
	private static final long serialVersionUID = 1L;

	/**ArrayList of episodes within a series*/
	ArrayList<Episode> episodes = new ArrayList<Episode>();
	
	/**Constructor for TV Series
	 * 
	 * @param name Name of series
	 * @param year Year series was first produced
	 * @param endYear Year series was last produced
	 */
	public TVSeries(String name, String year, String endYear) {
		super(name, year, endYear);
	}
	
	/**Constructor for TV Series
	 * 
	 * @param name Name of series
	 * @param year Year series was first produced
	 * @param endYear Year series was last produced
	 * @param episodes Individual episodes within a series
	 */
	public TVSeries(String name, String year, String endYear, ArrayList<Episode> episodes) {
		super(name, year, endYear);
		this.episodes = episodes;
	}

	/**
	 * Adds episode to the series
	 * 
	 * @param episode Episode object to add
	 */
	public void addEpisode(Episode episode) {
		episodes.add(episode);
	}
	/**
	 * Return string version of series
	 */
	public String toString() {
		return "SERIES: " + getName() + " (" + getYear() + "-" + getEndYear() + ")";
	}
	
}
