import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Project #2																				This is a temporary class
 * CS 2334, Section 011																			Will eventually become part of MovieDatabase.
 * Feb 26, 2016
 * <P>
 * Maintains the list of all shows and movies. Enabling the program
 * to parse and add shows and search for them
 * </P>
 */
public class CreatorDatabase {
	
	/**List containing all the movies and TV shows*/
	private ArrayList<Creator> cDb = new ArrayList<Creator>();
	
	/**Constructor for MDb class. 
	 * 
	 * @param moviesFile Name of file containing movies
	 * @param TVFile Name of file containing TV shows
	 */
	public CreatorDatabase(String actorFile, String directorFile, String producerFile) {
		try {
			cDb.addAll(readCreatorsIntoDB(actorFile, true, false));
			cDb.addAll(readCreatorsIntoDB(directorFile, false, true));
			cDb.addAll(readCreatorsIntoDB(producerFile, false, false));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**Takes file of movies, parses them, and add them to database
	 * 
	 * @param file Name of File containing shows
	 * @param isMovie true if file contains movies, false if file containing TV
	 * @throws FileNotFoundException 
	 */
	private ArrayList<Creator> readCreatorsIntoDB(String file, boolean isActor, boolean isDirector) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
	
		if (isActor) {
			ArrayList<Creator> actors = new ArrayList<Creator>();
			
			String line = br.readLine();
			while (line != null) {
				actors.add(parseActor(line));
				line = br.readLine();
			}
			br.close();
			fr.close();
			return actors;
		} else if (isDirector) {
			ArrayList<Creator> directors = new ArrayList<Creator>();
			String line = br.readLine();
			while (line != null) {
				directors.add(parseDirector(line));
				line = br.readLine();
			}
			br.close();
			fr.close();
			return directors;
		}
		else {
			ArrayList<Creator> producers = new ArrayList<Creator>();
			String line = br.readLine();
			while (line != null) {
				producers.add(parseProducer(line));
				line = br.readLine();
			}
			br.close();
			fr.close();
			return producers;
		}
	}
	
	/**Search database for specific shows
	 * 
	 * @param movies Search for movies?
	 * @param tv Search for TV shows?
	 * @param episodes Search for specific episodes? (Can't be true if tv is false)
	 * @param exactMatch Only allow if title exactly matches search
	 * @param sortByTitle Sort by title (if true) or by year (if false)
	 * @param title Title to search for. "'-1'" will be if any
	 * @param years Years to search for. "-1" will be in first index if any
	 * @return
	 */
	public ArrayList<Creator> searchFiles(boolean actor, boolean director, 
			boolean producer, boolean exactMatch, String name) {
		
		ArrayList<Creator> creators = new ArrayList<Creator>();
		
				if (actor)
				{
					for (Creator creator : Creator.actors)
					{
						if (creator.getName().toLowerCase().matches(".*" + name.toLowerCase() + ".*"))
						{
							creators.add(creator);
							if (exactMatch) 
								return creators;
						}
					}
				}
				
				else if (director)
				{
					for (Creator creator : Creator.directors)
					{
						if (creator.getName().toLowerCase().matches(".*" + name.toLowerCase() + ".*"))
						{
							creators.add(creator);
							if (exactMatch) 
								return creators;
						}
					}
				}
				
				else if (producer)
				{
					for (Creator creator : Creator.actors)
					{
						if (creator.getName().toLowerCase().matches(".*" + name.toLowerCase() + ".*"))
						{
							creators.add(creator);
							if (exactMatch) 
								return creators;
						}
					}
				}
		Collections.sort(creators, Show.TITLE_COMPARATOR);
		return creators;
	}
}
