import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Project #3
 * CS 2334, Section 011
 * 24 March 2016
 * <P>
 * Class for storing information about a creator
 *</P>
 */
public class Creator extends Show implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	/** List that stores information about actors  */
	public static ArrayList<Creator> actors = new ArrayList<Creator>();
	/** List that stores information about directors */
	public static ArrayList<Creator> directors = new ArrayList<Creator>();
	/** List that stores information about producers */
	public static ArrayList<Creator> producers = new ArrayList<Creator>();
	/** A <code>LinkedHashMap</code> of a list of creators */
	private static LinkedHashMap<String, ArrayList<Creator>> creatorList;
	/** The name of the creator */
	private String name;
	/** The title of the work that is associated with its creator */
	private String title;
	/** The title of an episode from within a series */
	private String episodeTitle;
	/** The season number and episode number from a given series */
	private String episodeNumber;
	/** The release year */
	private String year;
	/** The type of work (TV or V) */
	private String type;
	/** Determines whether the footage was archived */
	private String archiveFootage;
	/** The name of a character */
	private String charName;
	/** Employee's paycheck */
	private String billingOrder;
	/** Determines if the creator was credited or not */
	private String credit;
	/** The role the creator had in the production */
	private String role;
	
	/**
	 * Constructor for Creator: actor
	 * @param name
	 * @param title
	 * @param year
	 * @param type
	 * @param archiveFootage
	 * @param charName
	 * @param billingOrder
	 */
	public Creator(String name, String title, String year, String episodeTitle, String episodeNumber,
			String type, String archiveFootage, String charName, String billingOrder) {
		super(name, year, year);
		this.title = title;
		this.episodeTitle = episodeTitle;
		this.episodeNumber = episodeNumber;
		this.type = type;
		this.archiveFootage = archiveFootage;
		this.charName = charName;
		this.billingOrder = billingOrder;
	}
	
	/**
	 * Constructor for Creator: director
	 * @param name
	 * @param title
	 * @param year
	 * @param type
	 * @param credit
	 */
	public Creator(String name, String title, String year, String episodeTitle,
			String episodeNumber, String type, String credit) {
		super(name, year, year);
		this.title = title;
		this.episodeTitle = episodeTitle;
		this.episodeNumber = episodeNumber;
		this.type = type;
		this.credit = credit;
	}
	
	/**
	 * Constructor for Creator: producer
	 * @param name
	 * @param title
	 * @param year
	 * @param type
	 * @param role
	 * @param credit
	 */
	public Creator(String name, String title, String year, String episodeTitle, String episodeNumber,
			String type, String role, String credit) {
		super(name, year, year);
		this.title = title;
		this.type = type;
		this.episodeTitle = episodeTitle;
		this.episodeNumber = episodeNumber;
		this.role = role;
		this.credit = credit;
	}
	/**
	 * 
	 * @param actor
	 * @param br
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Creator> fillActors (File actor, BufferedReader br) throws IOException {
		
		String nextLine = null;
		String name = "";
		String title = "";
		String episodeTitle = "";
		String episodeNumber = "";
		String year = "";
		String type = "";
		String archiveFootage = "";
		String charName = "";
		String billingOrder = "";
		
		if (br.ready()) {
			nextLine = br.readLine();
			
			if (nextLine.contains(",")) {
				
			}
			if (nextLine.contains(",") && nextLine.contains("\t")) {
				
			}
			if (nextLine.contains("\t")) {
				
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}
			
			actors.add(new Creator(name, title, year, episodeTitle, episodeNumber,
					type, archiveFootage, charName, billingOrder));
		}
		creatorList.put("ACTING", actors);
		return actors;
	}
	
	/**
	 * 
	 * @param director
	 * @param br
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Creator> fillDirectors (File director, BufferedReader br) throws IOException {
		
		String nextLine = null;
		String name = "";
		String title = "";
		String year = "";
		String episodeTitle = "";
		String episodeNumber = "";
		String type = "";
		String credit = "";
		
		if (br.ready()) {
			nextLine = br.readLine();
			
			if (nextLine.contains(",")) {
				
			}
			if (nextLine.contains(",") && nextLine.contains("\t")) {
				
			}
			if (nextLine.contains("\t")) {
				
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}
			directors.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, credit));
		}
		creatorList.put("DIRECTING", directors);
		return directors;
	}
	
	/**
	 * 
	 * @param producer
	 * @param br
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Creator> fillProducers (File producer, BufferedReader br) throws IOException {
		
		String nextLine = null;
		String name = "";
		String title = "";
		String episodeTitle = "";
		String episodeNumber = "";
		String year = "";
		String type = "";
		String role = "";
		String credit = "";
		
		if (br.ready()) {
			nextLine = br.readLine();
			
			if (nextLine.contains(",")) {
				
			}
			if (nextLine.contains(",") && nextLine.contains("\t")) {
				
			}
			if (nextLine.contains("\t")) {
				
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}
			producers.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, role, credit));
		}
		creatorList.put("PRODUCING", producers);
		return producers;
	}
	
	/**
	 * Returns a string that represent the creators
	 */
	public String toString() {
		return "";
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getType() {
		return type;
	}
	
	public String getArchiveFootage() {
		return archiveFootage;
	}
	
	public String getCharName() {
		return charName;
	}
	
	public String getBillingOrder() {
		return billingOrder;
	}
	
	public String getCredit() {
		return credit;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getEpisodeTitle() {
		return episodeTitle;
	}
	
	public String getEpisodeNumber() {
		return episodeNumber;
	}
}
