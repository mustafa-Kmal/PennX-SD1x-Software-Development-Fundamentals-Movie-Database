package database;


import java.util.*;


import java.io.*;
import java.lang.reflect.Array;

public class MovieDatabase {
	private ArrayList<Movie> movieList;
	private ArrayList<Actor> actorList;

	char[][] maze;
	int length;


	MovieDatabase(){
		movieList = new ArrayList<Movie>();
		actorList = new ArrayList<Actor>();
	}

	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}

	public ArrayList<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(ArrayList<Actor> actorList) {
		this.actorList = actorList;
	}

	public void addMovie(String name, String[] actors) {
		for(int i = 0; i < this.movieList.size(); i++) {
			if(name.equals(this.movieList.get(i).getName()))
				return;
		}
		Movie movie = new Movie(name);
		LOOP0:for(String otherActor : actors) {
			for (Actor  actor : this.actorList) {
				if(actor.getName().equals(otherActor)) {
					actor.getMovies().add(movie);
					movie.getActors().add(actor);
					continue LOOP0;
				}
			}
			Actor actor = new Actor(otherActor);
			actor.getMovies().add(movie);
			movie.getActors().add(actor);
			this.actorList.add(actor);
		}
		this.movieList.add(movie);
	}
	public void addActor(String name, String[] movies) {
		for(int i = 0; i < this.actorList.size(); i++) {
			if(name.equals(this.actorList.get(i).getName()))
				return;
		}
		Actor actor = new Actor(name);
		LOOP1: for(String otherMovie : movies) {
			for (Movie  movie : this.movieList) {
				if(movie.getName().equals(otherMovie) ) {
					movie.getActors().add(actor);
					actor.getMovies().add(movie);
					continue LOOP1;
				}
			}
			Movie movie = new Movie(otherMovie);
			movie.getActors().add(actor);
			actor.getMovies().add(movie);
			this.movieList.add(movie);
		}
		this.actorList.add(actor);
	}
	public void addRating(String name, double rating) {
		for(Movie movie : this.movieList) {
			if(movie.getName().equals(name)) {
				movie.setRating(rating);
				break;
			}
		}
	}
	public void updateRating(String name, double rating) {
		addRating(name,rating);
	}
	public String getBestActor() {
		String bestActor = "";
		double max = 0;
		for (Actor actor : this.actorList) {
			double sum = 0;
			double avg = 0;
			for(Movie movie : actor.getMovies())
				sum += movie.getRating();
			avg = sum /(double) actor.getMovies().size();
			if (avg > max ) {
				max = avg;
				bestActor = actor.getName();
			}
		}
		return bestActor;
	}
	public String getBestMovie() {
		String bestMovie = "";
		double max = 0;
		double rating = 0;
		for (Movie movie : this.movieList) {
			rating = movie.getRating();
			if (rating > max ) {
				max = rating;
				bestMovie = movie.getName();
			}
		}
		return bestMovie;
	}







	public static char[][] MazeFile(String Name, int rowNum , int colNum) throws FileNotFoundException{

		char[][] maze= new char[rowNum][colNum];

		//	String filename=name;
		File file = new File(Name);
		Scanner inputFile = new Scanner(file);
		int k=0 ;
		int l =0;
	//	while(inputFile.hasNextLine()) {
		
		for(int i = 0 ; i < rowNum ; i++ ) {
		String line = inputFile.nextLine();
			Scanner scanner = new Scanner(line);
			int length = line.length();


			if(l++==k){
				
				int j = 0; 

				for (char c : line.toCharArray()) {
				//	linee[c]=c;
					//array.add(c);
					maze[i][j]=c;
					j++;
				}
			}
		k++;

		}


		for(int i = 0 ; i < maze.length; i++) {
			System.out.print("{");
			for(int j = 0 ; j < maze[0].length ; j++) {
				System.out.print("'"+maze[i][j]+"'"+",");
				//	maze[i][j]=MazeRows.get(i).get(j);
			}
			System.out.println("");

		}
		return maze;
	}



	public static String getString(String MovieName) {
		char[] MovieNamee = new char[MovieName.length()];
		MovieName.getChars(1, MovieName.length()-1, MovieNamee , 0);
		String str = String.valueOf(MovieNamee);
		return str;
	}


	public static void main(String[] args) throws FileNotFoundException {


		//read the filename from the user
		Scanner keyboard = new Scanner(System.in);
		String filename = keyboard.nextLine();
		int rowsNum=keyboard.nextInt();
		int colNum=keyboard.nextInt();


		//ArrayList<ArrayList<Character>> MazeRows = new ArrayList<ArrayList<Character>>();
		//ArrayList<Character[]> MazeRows = new ArrayList<Character[]>();


		MazeFile(filename,   rowsNum,   colNum);



	}







	/*	
		Scanner keyboard = new Scanner(System.in);
		String filename = keyboard.nextLine();
		String filename2 = keyboard.nextLine();
		File movies = new File(filename);
		File ratings = new File(filename2);

		//Scanner inputFile = new Scanner(file);






		MovieDatabase database = new MovieDatabase();
	//	File ratings = new File("ratings.txt");
	//	File movies = new File("movies.txt");
		Scanner scnr;
		String[] entries;
		try {
			scnr = new Scanner(movies);
			String actor;
			String[] actorMovies;
			while(scnr.hasNextLine()) {
				entries = scnr.nextLine().split(", ");
				actor = entries[0];
				actorMovies = new String[Array.getLength(entries) - 1];
				for(int i = 1; i < Array.getLength(entries); i++) {
					actorMovies[i - 1] = entries[i];
				}
				database.addActor(actor, actorMovies);
			}
			scnr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			scnr = new Scanner(ratings);
			scnr.nextLine();
			while(scnr.hasNextLine()) {
				entries = scnr.nextLine().split("\t");
				assert(Array.getLength(entries)== 2);
				database.addRating(entries[0],(double)Integer.parseInt(entries[1]));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("The best actor is:\t"+database.getBestActor()
							+"\nThe best movies is:\t"+database.getBestMovie() );
	 */}


