import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
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

	static final long serialVersionUID = 1L;
	
	/** List that stores information about actors  */
	public static ArrayList<Creator> actors = new ArrayList<Creator>();
	/** List that stores information about directors */
	public static ArrayList<Creator> directors = new ArrayList<Creator>();
	/** List that stores information about producers */
	public static ArrayList<Creator> producers = new ArrayList<Creator>();
	/** A <code>LinkedHashMap</code> of a list of creators */
	private static LinkedHashMap<String, ArrayList<Creator>> creatorList = new LinkedHashMap<String, ArrayList<Creator>>();
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
			String type, String role, String archiveFootage, String credit, String charName, String billingOrder) {
		super(name, year, year);
		this.title = title;
		this.episodeTitle = episodeTitle;
		this.episodeNumber = episodeNumber;
		this.type = type;
		this.role = role;
		this.archiveFootage = archiveFootage;
		this.credit = credit;
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
		
		// Initializing string that keeps track of the lines
		String nextLine = null;
		// Creating empty strings to be filled in later
		String name = "";
		String title = "";
		String episodeTitle = "";
		String episodeNumber = "";
		String year = "";
		String type = "";
		String role = "";
		String archiveFootage = "";
		String credit = "";
		String charName = "";
		String billingOrder = "";
		
		// stops reader if the file is out of bounds
		if (br.ready()) {
			// reads in a line from the file
			nextLine = br.readLine();
			
			// year is always constant for each type of line being parsed
			year = nextLine.substring(nextLine.indexOf("("), nextLine.indexOf(")"));
			
			// aquires the line after the "\n" line, that has the actor's name and has a space followed by a tab
			if (nextLine.contains(",") && nextLine.contains("\\s\t")) {
			
				// if the charName is present
				if (nextLine.contains("[")) {
				charName = nextLine.substring(nextLine.indexOf("["), nextLine.indexOf("]"));
				}
				
				// if the billingOrder is present
				if (nextLine.contains("<")) {
				billingOrder = nextLine.substring(nextLine.indexOf("<"), nextLine.indexOf(">"));
				}
				
				// if archiveFootage is present
				if (nextLine.contains("(archive footage)")) {
					archiveFootage = "(archive footage)";
				}
				
				// if episode information is present
				if (nextLine.contains("{")) {
					episodeTitle = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}"));
					
					
					// checks if episode number is in the episode title
					if (episodeTitle.contains("#")) {
						
					// gives episode number the correct value
					episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
							episodeTitle.indexOf(")"));
					// removes episode number value from episode title
					// if episode number is only present, then assigns empty string to episodeTitle
					episodeTitle = episodeTitle.substring(episodeTitle.indexOf("{"),
							episodeTitle.indexOf("(") - 1);
					}
				}
				
				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value 
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}
				
				// assigns title a value, name will always be followed by \\s\t
				title = nextLine.substring(nextLine.indexOf("\t"),nextLine.indexOf("(") + 1).trim();
				
				// assigns name a value
				name = nextLine.substring(nextLine.length(), nextLine.indexOf("\t")).trim();
				
				// if role is present, if not role remains empty
				if (nextLine.contains("(voice)")) {
				role = "(voice)";
				}
				
				// if credit is present, if not credit remains empty
				if (nextLine.contains("(uncredited)")) {
				credit = "(uncredited)";
				}
				
				actors.add(new Creator(name, title, year, episodeTitle, episodeNumber,
						type, role, archiveFootage, credit, charName, billingOrder));
			}
			
			// aquires line that has the actor's name present and is followed by a tab
			if (nextLine.contains(",") && nextLine.contains("\t")) {
			
				// if billingOrder is present
				if (nextLine.contains("<")) {
				billingOrder = nextLine.substring(nextLine.indexOf("<"), nextLine.indexOf(">"));
				}
				
				// if charName is present
				if (nextLine.contains("[")) {
					charName = nextLine.substring(nextLine.indexOf("["), nextLine.indexOf("]"));
				}
				
				// if episode information is present
				if (nextLine.contains("{")) {
					episodeTitle = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}"));
					
					// checks if episode number is in the episode title
					if (episodeTitle.contains("#")) {
						
					// gives episode number the correct value
					episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
							episodeTitle.indexOf(")"));
					// removes episode number value from episode title
					// if episode number is only present, then assigns empty string to episodeTitle
					episodeTitle = episodeTitle.substring(episodeTitle.indexOf("{"),
							episodeTitle.indexOf("(") - 1);
					}
				}
				
				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value 
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}
				
				// if role is present, if not role remains empty
				if (nextLine.contains("(voice)")) {
				role = "(voice)";
				}
				
				// if credit is present, if not credit remains empty
				if (nextLine.contains("(uncredited)")) {
				credit = "(uncredited)";
				}
				
				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"),nextLine.indexOf("(") + 1).trim();
				
				// assigns name a value
				name = nextLine.substring(nextLine.length(), nextLine.indexOf("\t")).trim();
				
				actors.add(new Creator(name, title, year, episodeTitle, episodeNumber,
						type,role, archiveFootage,credit , charName, billingOrder));
			}
			
			// if current line is followed by 3 tabs
			if (nextLine.contains("\t\t\t")) {
				
				// if billingOrder is present
				if (nextLine.contains("<")) {
					billingOrder = nextLine.substring(nextLine.indexOf("<"), nextLine.indexOf(">"));
				}
				
				// if charName is present
				if (nextLine.contains("[")) {
					charName = nextLine.substring(nextLine.indexOf("["), nextLine.indexOf("]"));
				}
				
				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value 
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}
				
				// if episode information is present
				if (nextLine.contains("{")) {
					episodeTitle = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}"));
					
					// checks if episode number is in the episode title
					if (episodeTitle.contains("#")) {
						
					// gives episode number the correct value
					episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
							episodeTitle.indexOf(")"));
					// removes episode number value from episode title
					// if episode number is only present, then assigns empty string to episodeTitle
					episodeTitle = episodeTitle.substring(episodeTitle.indexOf("{"),
							episodeTitle.indexOf("(") - 1);
					}
				}
				
				// if episode information is "SUSPENDED"
				if (nextLine.contains("SUSPENDED")) {
					episodeTitle = "SUSPENDED";
					episodeNumber = "";
				}
				
				// checks if episode information is given by YYYY-MM-DD
				if (episodeTitle.contains("-")) {
					// if this is present, then assume there is no episode number and episodeTitle = episodeDate
					episodeTitle = episodeTitle.substring(episodeTitle.indexOf("("),
							episodeTitle.indexOf(")"));
					episodeNumber = "";
				}
				
				// if role is present, if not role remains empty
				if (nextLine.contains("(voice)")) {
				role = "(voice)";
				}
				
				// if credit is present, if not credit remains empty
				if (nextLine.contains("(uncredited)")) {
				credit = "(uncredited)";
				}
				
				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"),nextLine.indexOf("(") + 1).trim();
				
				actors.add(new Creator(name, title, year, episodeTitle, episodeNumber,
						type, role, archiveFootage, credit, charName, billingOrder));
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}
			
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
			
			// year is always constant for each type of line being parsed
			year = nextLine.substring(nextLine.indexOf("("), nextLine.indexOf(")"));
			if (nextLine.contains(",") && nextLine.contains("\t")) {
				
				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value 
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}
				
				// if episode information is present
				if (nextLine.contains("{")) {
					episodeTitle = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}"));
					
					// checks if episode number is in the episode title
					if (episodeTitle.contains("#")) {
						
					// gives episode number the correct value
					episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
							episodeTitle.indexOf(")"));
					// removes episode number value from episode title
					// if episode number is only present, then assigns empty string to episodeTitle
					episodeTitle = episodeTitle.substring(episodeTitle.indexOf("{"),
							episodeTitle.indexOf("(") - 1);
					}
				}
				
				// if episode information is "SUSPENDED"
				if (nextLine.contains("SUSPENDED")) {
					episodeTitle = "SUSPENDED";
					episodeNumber = "";
				}
				
				// assigns credit a value
				credit = nextLine.substring(nextLine.lastIndexOf("("), nextLine.lastIndexOf(")"));
				// checks if credit has the right information, if not then there is not credit to be given
				if (credit.equals(year) || credit.equals(type)) {
					credit = "";
				}
				
				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"),nextLine.indexOf("(") + 1).trim();
				
				// assigns name a value
				name = nextLine.substring(nextLine.length(), nextLine.indexOf("\t")).trim();
				
				directors.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, credit));
				
			}
			if (nextLine.contains("\t\t\t")) {
				
				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value 
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}
				
				// if episode information is present
				if (nextLine.contains("{")) {
					episodeTitle = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}"));
					
					// checks if episode number is in the episode title
					if (episodeTitle.contains("#")) {
						
					// gives episode number the correct value
					episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
							episodeTitle.indexOf(")"));
					// removes episode number value from episode title
					// if episode number is only present, then assigns empty string to episodeTitle
					episodeTitle = episodeTitle.substring(episodeTitle.indexOf("{"),
							episodeTitle.indexOf("(") - 1);
					}
				}
				
				// if episode information is "SUSPENDED"
				if (nextLine.contains("SUSPENDED")) {
					episodeTitle = "SUSPENDED";
					episodeNumber = "";
				}
				
				// assigns credit a value
				credit = nextLine.substring(nextLine.lastIndexOf("("), nextLine.lastIndexOf(")"));
				// checks if credit has the right information, if not then there is not credit to be given
				if (credit.equals(year) || credit.equals(type)) {
					credit = "";
				}
				
				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"),nextLine.indexOf("(") + 1).trim();
				
				directors.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, credit));
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}
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
			
			// year is always constant for each type of line being parsed
			year = nextLine.substring(nextLine.indexOf("("), nextLine.indexOf(")"));
			
if (nextLine.contains(",") && nextLine.contains("\t")) {
				
				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value 
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}
				
				// if episode information is present
				if (nextLine.contains("{")) {
					episodeTitle = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}"));
					
					// checks if episode number is in the episode title
					if (episodeTitle.contains("#")) {
						
					// gives episode number the correct value
					episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
							episodeTitle.indexOf(")"));
					// removes episode number value from episode title
					// if episode number is only present, then assigns empty string to episodeTitle
					episodeTitle = episodeTitle.substring(episodeTitle.indexOf("{"),
							episodeTitle.indexOf("(") - 1);
					}
				}
				
				// if episode information is "SUSPENDED"
				if (nextLine.contains("SUSPENDED")) {
					episodeTitle = "SUSPENDED";
					episodeNumber = "";
				}
				
				// if role is present with 4 given options
				if (nextLine.contains("(producer)") || nextLine.contains("(executive producer)") ||
						nextLine.contains("(co-producer)") || nextLine.contains("(segment producer)")) {
							
							// Stores producer
							if (nextLine.contains("(producer)")) {
								role = "(producer)";
							}
							
							// Stores executive producer
							if (nextLine.contains("(executive producer)")) {
								role = "(executive producer)";
							}
							
							// Stores co-producer
							if (nextLine.contains("(co-producer)")) {
								role = "(co-producer)";
							}
							
							// Stores segment producer
							if (nextLine.contains("(segment producer)")) {
								role = "(segment producer)";
							}
						}
				
				// assigns credit a value
				credit = nextLine.substring(nextLine.lastIndexOf("("), nextLine.lastIndexOf(")"));
				// checks if credit has the right information, if not then there is not credit to be given
				if (credit.equals(year) || credit.equals(type) || credit.equals(role)) {
					credit = "";
				}
				
				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"),nextLine.indexOf("(") + 1).trim();
				
				// assigns name a value
				name = nextLine.substring(nextLine.length(), nextLine.indexOf("\t")).trim();
				
				producers.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, role, credit));
				
			}
			if (nextLine.contains("\t\t\t")) {
				
				// if type is present
				if (nextLine.contains("(TV)") || nextLine.contains("(V)")) {
					// gives type the corresponding value 
					if (nextLine.contains("(TV)")) {
						type = "(TV)";
					}
					// gives type the corresponding value
					if (nextLine.contains("(V)")) {
						type = "(V)";
					}
				}
				
				// if episode information is present
				if (nextLine.contains("{")) {
					episodeTitle = nextLine.substring(nextLine.indexOf("{"), nextLine.indexOf("}"));
					
					// checks if episode number is in the episode title
					if (episodeTitle.contains("#")) {
						
					// gives episode number the correct value
					episodeNumber = episodeTitle.substring(episodeTitle.indexOf("("),
							episodeTitle.indexOf(")"));
					// removes episode number value from episode title
					// if episode number is only present, then assigns empty string to episodeTitle
					episodeTitle = episodeTitle.substring(episodeTitle.indexOf("{"),
							episodeTitle.indexOf("(") - 1);
					}
				}
				
				// if episode information is "SUSPENDED"
				if (nextLine.contains("SUSPENDED")) {
					episodeTitle = "SUSPENDED";
					episodeNumber = "";
				}
				
				// if role is present with 4 given options
				if (nextLine.contains("(producer)") || nextLine.contains("(executive producer)") ||
						nextLine.contains("(co-producer)") || nextLine.contains("(segment producer)")) {
							
							// Stores producer
							if (nextLine.contains("(producer)")) {
								role = "(producer)";
							}
							
							// Stores executive producer
							if (nextLine.contains("(executive producer)")) {
								role = "(executive producer)";
							}
							
							// Stores co-producer
							if (nextLine.contains("(co-producer)")) {
								role = "(co-producer)";
							}
							
							// Stores segment producer
							if (nextLine.contains("(segment producer)")) {
								role = "(segment producer)";
							}
						}
				
				// assigns credit a value
				credit = nextLine.substring(nextLine.lastIndexOf("("), nextLine.lastIndexOf(")"));
				// checks if credit has the right information, if not then there is not credit to be given
				if (credit.equals(year) || credit.equals(type) || credit.equals(role)) {
					credit = "";
				}
				
				// assigns title a value
				title = nextLine.substring(nextLine.indexOf("\t"),nextLine.indexOf("(") + 1).trim();
				
				producers.add(new Creator(name, title, year, episodeTitle, episodeNumber, type, role, credit));
			}
			if (nextLine.equals("\n")) {
				nextLine = br.readLine();
			}
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
	
	public static final Comparator<Creator> TITLE_COMPARATOR = new Comparator<Creator>() {
		@Override
		public int compare(Creator o1, Creator o2) {
			return o1.name.compareTo(o2.name); 
		}
	};
	
	// for testing purposes
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
