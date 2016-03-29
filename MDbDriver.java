import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;

/**
 * Project #3
 * CS 2334, Section 010
 * Mar 26, 2016
 * <P>
 * Runs the program. Contains both the main method and initializes the database.
 * Also handles the output and searching of creators.
 * </P>
 */
public class MDbDriver implements Serializable{
	
	/** SerialID that lets us implement Serializable */
	private static final long serialVersionUID = 1L;
	
	/**Initializes the database and runs program based on user input
	 * @param args Useless for this program
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		BufferedReader inputReader= new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Creator> actorList = new ArrayList<Creator>();
		ArrayList<Creator> directorList = new ArrayList<Creator>();
		ArrayList<Creator> producerList = new ArrayList<Creator>();
		LinkedHashMap<String, ArrayList<Creator>> actorMap = new LinkedHashMap<String, ArrayList<Creator>>();
		LinkedHashMap<String, ArrayList<Creator>> directorMap = new LinkedHashMap<String, ArrayList<Creator>>();
		LinkedHashMap<String, ArrayList<Creator>> producerMap = new LinkedHashMap<String, ArrayList<Creator>>();
		try {
			System.out.println("Read (t)ext or (b)inary data?");
			String textOrBinary = inputReader.readLine();
			MovieDatabase mDb = new MovieDatabase();
			
			if (textOrBinary.equals("t"))
			{
				//to see if the user presses enter twice
				int counter = 0;
				
				System.out.println("What is the movie file?");
				String movieFile = inputReader.readLine();
				File movies = new File(movieFile);
				while (!movies.exists()) {
					if (movieFile.equals(""))
					{
						counter++;
					}
					else
					{
						counter = 0;
					}
					if (counter == 2)
					{
						System.exit(0);
					}
					System.out.println("Sorry, not valid input. Try again");
					movieFile = inputReader.readLine();
					if (!(movieFile.equals("")))
					{
						counter = 0;
					}
					movies = new File(movieFile);
				}
				System.out.println("What is the TV file?");
				String TVFile = inputReader.readLine();
				File TV = new File(TVFile);
				while (!TV.exists()) {
					if (TVFile.equals(""))
					{
						counter++;
					}
					else
					{
						counter = 0;
					}
					if (counter == 2)
					{
						System.exit(0);
					}
					System.out.println("Sorry, not valid input. Try again");
					TVFile = inputReader.readLine();
					if (!(TVFile.equals("")))
					{
						counter = 0;
					}
					TV = new File(TVFile);
				}
				System.out.println("What is the Actor file?");
				String actorFile = inputReader.readLine();
				File actors = new File(actorFile);
				while (!actors.exists()) {
					if (actorFile.equals(""))
					{
						counter++;
					}
					else
					{
						counter = 0;
					}
					if (counter == 2)
					{
						System.exit(0);
					}
					System.out.println("Sorry, not valid input. Try again");
					actorFile = inputReader.readLine();
					if (!(actorFile.equals("")))
					{
						counter = 0;
					}
				}
				actors = new File(actorFile);
				actorList = Creator.fillActors(actors);
				System.out.println("ActorList Size:" + actorList.size());
				for (int i = 1; i < actorList.size(); ++i) {
					if (!actorList.get(i).getName().equals("") && 
							!actorList.get(i).getName().equals(actorList.get(i - 1).getName())) {
						actorMap.put(actorList.get(i).getName(), new ArrayList<Creator>
								(actorList.subList(actorList.get(i).getName().indexOf(actorList.get(i).getName()),
										actorList.get(i).getName().indexOf(actorList.get(i+1).getName()))));
					}
				}
				System.out.println("What is the Director file?");
				String directorFile = inputReader.readLine();
				File directors = new File(directorFile);
				while (!directors.exists()) {
					if (directorFile.equals(""))
					{
						counter++;
					}
					else
					{
						counter = 0;
					}
					if (counter == 2)
					{
						System.exit(0);
					}
					System.out.println("Sorry, not valid input. Try again");
					directorFile = inputReader.readLine();
					if (!(directorFile.equals("")))
					{
						counter = 0;
					}
					directors = new File(directorFile);
				}
				directorList = Creator.fillDirectors(directors);
				for (int i = 1; i < directorList.size(); ++i) {
					if (!directorList.get(i).getName().equals("") && 
							!directorList.get(i).getName().equals(directorList.get(i - 1).getName())) {
						directorMap.put(directorList.get(i).getName(), new ArrayList<Creator>
								(directorList.subList(directorList.get(i).getName().indexOf(directorList.get(i).getName()),
										directorList.get(i).getName().indexOf(directorList.get(i - 1).getName()))));
					}
				}
				System.out.println("What is the Producer file?");
				String producerFile = inputReader.readLine();
				File producers = new File(producerFile);
				while (!producers.exists()) {
					if (producerFile.equals(""))
					{
						counter++;
					}
					else
					{
						counter = 0;
					}
					if (counter == 2)
					{
						System.exit(0);
					}
					System.out.println("Sorry, not valid input. Try again");
					producerFile = inputReader.readLine();
					if (!(producerFile.equals("")))
					{
						counter = 0;
					}
					producers = new File(producerFile);
				}
				producerList = Creator.fillProducers(producers);
				for (int i = 1; i < producerList.size(); ++i) {
					if (!producerList.get(i).getName().equals("") && 
							!producerList.get(i).getName().equals(producerList.get(i - 1).getName())) {
						producerMap.put(producerList.get(i).getName(), new ArrayList<Creator>
								(producerList.subList(producerList.get(i).getName().indexOf(producerList.get(i).getName()),
										producerList.get(i).getName().indexOf(producerList.get(i - 1).getName()))));
					}
				}
				
				mDb = new MovieDatabase(movieFile, TVFile);
				mDb.putHashMapIntoDB(actorMap, directorMap, producerMap);
			}
			else
			{
				System.out.println("Enter a file name to read in");
				String inputFile = inputReader.readLine();
				mDb = readDatabase(inputFile);
				
			}
			
			while (true) {
				String userContinue = "Y";
				ArrayList<Show> results = new ArrayList<Show>();
				ArrayList<Creator> creatorResults = new ArrayList<Creator>();
				System.out.println("Search (M)edia or (P)eople?");
				String MoP = inputReader.readLine();
				while (!MoP.equalsIgnoreCase("M") && !MoP.equalsIgnoreCase("P"))
				{
					System.out.println("Sorry, not valid input. Try again");
					MoP = inputReader.readLine();
				}
				
				if (MoP.equalsIgnoreCase("M"))
				{
				
					System.out.println("Search (M)ovies, (S)eries, or (B)oth? (n to quit)");
					String MSB = inputReader.readLine();
					while (!MSB.equalsIgnoreCase("m") && !MSB.equalsIgnoreCase("s") && !MSB.equalsIgnoreCase("b") && !MSB.equalsIgnoreCase("n")) {
						System.out.println("Sorry, not valid input. Try again");
						MSB = inputReader.readLine();
					}
					if (MSB.equalsIgnoreCase("n")) {
						System.out.println("Thank you for using MDb");
						System.exit(0);
					}
				
					System.out.println("Search (T)itle, (Y)ear, or (B)oth?");
					String TYB = inputReader.readLine();
					while (!TYB.equalsIgnoreCase("t") && !TYB.equalsIgnoreCase("y") && !TYB.equalsIgnoreCase("b")) {
						System.out.println("Sorry, not valid input. Try again");
						TYB = inputReader.readLine();
					}
			    
					String episodes = "n";
					if ((MSB.equalsIgnoreCase("s") || MSB.equalsIgnoreCase("b"))) {
						System.out.println("Include episodes in search and output (Y/N)?");
						episodes = inputReader.readLine();
						while (!episodes.equalsIgnoreCase("y") && !episodes.equalsIgnoreCase("n")) {
							System.out.println("Sorry, not valid input. Try again");
							episodes = inputReader.readLine();
						}
					}
			    
					String exactPartial = "";
					String title = "-1";
					if (TYB.equalsIgnoreCase("t") || TYB.equalsIgnoreCase("b")) {
						System.out.println("Search for (e)xact or (p)artial matches?");
						exactPartial = inputReader.readLine();
						while (!exactPartial.equalsIgnoreCase("e") && !exactPartial.equalsIgnoreCase("p")) {
							System.out.println("Sorry, not valid input. Try again");
							exactPartial = inputReader.readLine();
						}
						System.out.println("Title?");
						title = inputReader.readLine();
					}
			    
					ArrayList<String> y = new ArrayList<String>();
					String years = "-1";
					if (TYB.equalsIgnoreCase("y") || TYB.equalsIgnoreCase("b")) {
						System.out.println("Year(s)?");
						years = inputReader.readLine();
						while (!years.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]")
								&& !years.matches("([0-9][0-9][0-9][0-9])(,[0-9][0-9][0-9][0-9])*")) {
							System.out.println("Sorry, not valid input. Try Again");
							years = inputReader.readLine();
						}
			    	
						if (years.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]")) {
							int startYear = Integer.parseInt(years.substring(0, 4));
							int endYear   = Integer.parseInt(years.substring(5, 9));
							if (startYear > endYear) {
								int hold = startYear;
								startYear = endYear;
								endYear = hold;
							}
							for (int i = startYear; i <= endYear; i++) {
								y.add(String.valueOf(i));
							}
						} else if (years.matches("([0-9][0-9][0-9][0-9])(,[0-9][0-9][0-9][0-9])*")) {
							int numberOfYears = 1 + (years.length() - 4) / 5;
							for (int i = 0; i < numberOfYears; i++) {
								y.add(String.valueOf(years.substring(i * 5, i * 5 + 4)));
							}
						}
					} else {
						y.add("-1");
					}
			    
					String sort = "";
					if (TYB.equalsIgnoreCase("b")) {
						System.out.println("Sort by (T)itle or (Y)ear?");
						sort = inputReader.readLine();
			    		while (!sort.equalsIgnoreCase("t") && !sort.equalsIgnoreCase("y")) {
			    			System.out.println("Sorry, not valid input. Try again");
			    			sort = inputReader.readLine();
			    		}
					} else if (TYB.equalsIgnoreCase("t")) {
						sort = "t";
					} else if (TYB.equalsIgnoreCase("y")) {
						sort = "y";
					}
			    
					results = mDb.searchFiles((MSB.equalsIgnoreCase("m") || MSB.equalsIgnoreCase("b")), 
							(MSB.equalsIgnoreCase("s") || MSB.equalsIgnoreCase("b")), 
							episodes.equalsIgnoreCase("y"), exactPartial.equalsIgnoreCase("e"),
							sort.equalsIgnoreCase("t"), title, y);
			    
					if (MSB.equalsIgnoreCase("m")) System.out.println("Searched for Movies");
					if (MSB.equalsIgnoreCase("s")) System.out.println("Searched for TV Series");
					if (MSB.equalsIgnoreCase("b")) System.out.println("Searched for Movies and TV Series");
					if (episodes.equalsIgnoreCase("y")) System.out.println("Includes episodes");
					if (exactPartial.equalsIgnoreCase("e")) System.out.println("Exact Match: " + (title.equalsIgnoreCase("-1") ? "Any" : title));
					if (exactPartial.equalsIgnoreCase("p")) System.out.println("Partial Match: " + (title.equalsIgnoreCase("-1") ? "Any" : title));
					if (years.equalsIgnoreCase("-1")) System.out.println("Years: Any");
					else System.out.println("Years: " + years);
					if (sort.equalsIgnoreCase("t")) System.out.println("Sorted by Title");
					if (sort.equalsIgnoreCase("y")) System.out.println("Sorted by Years");
					System.out.println("================================================================================");
			    
			    
					for (Show show : results) {
						System.out.println(show.toString());
					}
				}
				else
				{
					String exactPartial = "";
					System.out.println("Search for (e)xact or (p)artial matches?");
					exactPartial = inputReader.readLine();
					while (!exactPartial.equalsIgnoreCase("e") && !exactPartial.equalsIgnoreCase("p")) {
						System.out.println("Sorry, not valid input. Try again");
						exactPartial = inputReader.readLine();
					}
					System.out.println("Enter the name of the person you want to search for");
					String personName = inputReader.readLine();
					
					System.out.println("Display (t)ext or (g)raph?");
					String ToG = inputReader.readLine();
					while (!ToG.equalsIgnoreCase("t") && !ToG.equalsIgnoreCase("g")) {
						System.out.println("Sorry, not valid input. Try again");
						ToG = inputReader.readLine();
					}
					if (ToG.equalsIgnoreCase("t"))
					{
						//Output text format
						//Output text format
						System.out.println("SEARCHED PEOPLE");
						if (exactPartial.equalsIgnoreCase("e")) System.out.println("EXACT NAME: " + personName);
						if (exactPartial.equalsIgnoreCase("p")) System.out.println("PARTIAL NAME: " + personName);
						System.out.println("================================================================================");
						if (exactPartial.equalsIgnoreCase("e"))
							{
							
							creatorResults = actorMap.get(personName);
							for (Creator c : creatorResults)
							{
								if (c.getName().equals(personName))
								{
									System.out.println(c.getName());
									System.out.println("ACTING");
									mDb.getActorMap().get(c.getName()).toString();
								}
							}
							
							creatorResults = directorMap.get(personName);
							for (Creator c : creatorResults)
							{
								if (c.getName().equals(personName))
								{
									System.out.println(c.getName());
									System.out.println("DIRECTING");
									mDb.getDirectorMap().get(c.getName()).toString();
								}
							}
							creatorResults = producerMap.get(personName);
							for (Creator c : creatorResults)
							{
								if (c.getName().equals(personName))
								{
									System.out.println(c.getName());
									System.out.println("PRODUCING");
									mDb.getProducerMap().get(c.getName()).toString();
								}
							}
						}
						else
						{
							//Code for partial matches
							for (Map.Entry<String, ArrayList<Creator>> map: mDb.getActorMap().entrySet()) {
								String key = map.getKey();
								creatorResults = map.getValue();
								if (key.contains(personName)) {
								System.out.println(key);
								System.out.println("DIRECTING");
								mDb.getActorMap().get(key).toString();
								}
							}
						for (Map.Entry<String, ArrayList<Creator>> map: mDb.getDirectorMap().entrySet()) {
							String key = map.getKey();
							creatorResults = map.getValue();
							if (key.contains(personName)) {
							System.out.println(key);
							System.out.println("DIRECTING");
							mDb.getDirectorMap().get(key).toString();
							}
						}
						for (Map.Entry<String, ArrayList<Creator>> map: mDb.getProducerMap().entrySet()) {
							String key = map.getKey();
							creatorResults = map.getValue();
							System.out.println(key);
							if (key.contains(personName)) {
							System.out.println("PRODUCING");
							mDb.getProducerMap().get(key).toString();
							}
						}
						}
					}
					else
					{

						System.out.println("Display data in a (P)ie Chart or (H)istogram?");
						String PoH = inputReader.readLine();
						while (!PoH.equalsIgnoreCase("p") && !PoH.equalsIgnoreCase("h")) {
							System.out.println("Sorry, not valid input. Try again");
						}
						if (PoH.equalsIgnoreCase("p")) {
							Slice[] slices = { new Slice(Creator.getMPC(), Color.black), new Slice(Creator.getSPC(), Color.green),
						 		      new Slice(Creator.getSAC(), Color.yellow), new Slice(Creator.getMAC(), Color.red), 
						 		      new Slice(Creator.getSDC(), Color.blue), new Slice(Creator.getMDC(), Color.pink)};
							JFrame frame = new JFrame();
						      frame.getContentPane().add(new MyPieChart());
						      frame.setSize(500, 500);
						      frame.setVisible(true);
						}
						else {
							if (mDb.getActorMap().containsKey(personName)) {
								MyHistogram histogram = new MyHistogram(mDb.getActorMap(), Creator.getMAC() + Creator.getSAC());
							}
							if (mDb.getDirectorMap().containsKey(personName)) {
								MyHistogram histogram = new MyHistogram(mDb.getDirectorMap(), Creator.getMDC() + Creator.getSDC());
							}
							if (mDb.getProducerMap().containsKey(personName)) {
								MyHistogram histogram = new MyHistogram(mDb.getProducerMap(), Creator.getMPC() + Creator.getSPC());
							}
					}
				}

				}
			    
			    System.out.println("Save (y/n)?");
			    String yn = inputReader.readLine();
			    
			    while (!yn.equalsIgnoreCase("y") && !yn.equalsIgnoreCase("n")) {
		    		System.out.println("Sorry, not valid input. Try Again");
		    		yn = inputReader.readLine();
			    }
	    		if(yn.equalsIgnoreCase("y")){
	    			System.out.println("Write (t)ext or (b)inary data?");
	    			String ToB = inputReader.readLine();
	    			while (!ToB.equalsIgnoreCase("y") && !ToB.equalsIgnoreCase("n")) {
			    		System.out.println("Sorry, not valid input. Try Again");
			    		ToB = inputReader.readLine();
	    			}
	    			if (ToB.equalsIgnoreCase("t"))
	    			{
	    				System.out.println("Enter a file to save to:");
	    				File saved = new File(inputReader.readLine());
			    	
	    				PrintWriter out = new PrintWriter(saved);
			    	
	    				System.out.println("Saving...");
	    				if (MoP.equalsIgnoreCase("M"))
	    				{
	    					for(Show show : results)
	    						out.println(show.toString());
	    				}
	    				else
	    				{
	    					for (Creator creator : creatorResults)
			    				out.println(creator.toString());
	    				}
	    				System.out.println("Done");
	    				out.close();
	    			}
	    			else
	    			{
	    				System.out.println("Enter a file to save to:");
	    				String fileName = inputReader.readLine();
	    				writeDatabase(fileName, mDb);
	    				
	    				System.out.println("Saving...");
	    				System.out.println("Done");
	    			}
			    	
			    } else if (yn.equalsIgnoreCase("n")){
			    	System.out.println("Your results will not be saved.");
			    }
	    		
	    		System.out.println("Continue? (Y/N)");
	    		userContinue = inputReader.readLine();
	    		if (userContinue.equalsIgnoreCase("N"))
	    			System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	        
	}
	
	
	/**Writes database object to a file specified by filename
	 * @param String
	 * @param MovieDatabase
	 * @throws IOException
	 */
	public static void writeDatabase(String filename, MovieDatabase database) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(database);
		objectOutputStream.close();
	}
	
	/**Reads the database object from a specified filename into the program.
	 * @return MovieDatabase
	 * @throws IOException, ClassNotFoundException
	 */
	public static MovieDatabase readDatabase(String filename) throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(filename);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		MovieDatabase database = (MovieDatabase) objectInputStream.readObject();
		objectInputStream.close();
		return database;
	}
}
