package database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

//import com.sun.tools.javac.code.Attribute.Array;

public class MovieDatabase {

	ArrayList<Movie> movieList;
	ArrayList<Actor> actorList;

	MovieDatabase(){
		this.movieList = new ArrayList<Movie>();
		this.actorList = new ArrayList<Actor>();

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


	void addRating(String name, double rating) {
		for(int i = 0 ; i < this.movieList.size(); i++) {

			if(movieList.get(i).getName().equalsIgnoreCase(name)) {
				movieList.get(i).setRating(rating);
				break;
			}}}

	public void updateRating(String name, double rating) {
		addRating(name,rating);
	}

	String getBestActor() {
		int R = 0;
		double r=0.0;
		double best=0.0;
		String  BBest =" ";
		for(int i = 0 ; i < actorList.size() ; i++) {
			for(int j = 0 ; j < actorList.get(i).getMovies().size(); j++ ) {
				R += actorList.get(i).getMovies().get(j).getRating();
			}
			r = R/(actorList.get(i).getMovies().size());
			if (r > best) {
				best = r;
				BBest=actorList.get(i).getName();
			}

		}
		return BBest ;
	}

	String getBestMovie() {
		int R = 0;
		double r=0.0;
		double best=0.0;
		String  BBest =" ";
		for(int i = 0 ; i < movieList.size() ; i++) {
			r = movieList.get(i).getRating();
			if (r > best) {
				best = r;
				BBest=movieList.get(i).getName();
			}
		}
		return BBest ;
	}



	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public ArrayList<Actor> getActorList() {
		return this.actorList;

	}
	public static String getString(String MovieName) {
		char[] MovieNamee = new char[MovieName.length()];
		MovieName.getChars(1, MovieName.length()-1, MovieNamee , 0);
		String str = String.valueOf(MovieNamee);
		return str;
	}


	public static void main(String[] args) {             //throws IOException 
	{}


		MovieDatabase database1 = new MovieDatabase();
		ArrayList<String> array1 = new ArrayList<String>();
		ArrayList<Double> array2 = new ArrayList<Double>();
		ArrayList<String> array3 = new ArrayList<String>();

		ArrayList<Movie> Movies2 = new ArrayList<Movie>();
		
		
		
		
		Scanner keyboard = new Scanner(System.in);
		String filename = keyboard.nextLine();
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);
		
		
		
		

		Scanner input = new Scanner(System.in);
		//input.nextLine()
		File movies = new File("movies.txt");
		File ratings = new File("ratings.txt");
		String[] entries;
		input = new Scanner(movies);

		Scanner scnr;
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
			database1.addActor(actor, actorMovies);
		}



		ArrayList<String[]> eachLine = new ArrayList<String[]>();
		eachLine.add(new String[2]);

		input = new Scanner(ratings);

		scnr = new Scanner(ratings);
		scnr.nextLine();
		while(scnr.hasNextLine()) {
			entries = scnr.nextLine().split("\t");
			assert(Array.getLength(entries)== 2);
			database1.addRating(entries[0],(double)Integer.parseInt(entries[1]));
		}

		/*while (input.hasNextLine()) {


			String line = input.nextLine();
			Scanner scanner = new Scanner(line);
			scanner.useDelimiter("\t");
			ArrayList<String> array= new ArrayList<String>();
			while(scanner.hasNext()){
				array.add(scanner.next());
			}

			String[] moviesName= new String[array.size()-1];


			for(int j= 0 ; j < array.size()-1 ; j++){
				moviesName[j]=array.get(j);
			}
			String movieName = Arrays.toString(moviesName);
			String namewithoutb= getString(movieName);

			double d = Double.parseDouble(array.get(array.size()-1));

			database1.addRating(namewithoutb,d);

		}*/

		for(int i = 0 ; i < database1.movieList.size(); i++) {
			System.out.println(i+"\t"+ database1.movieList.get(i).getRating()
					+"\t"+ database1.movieList.get(i).getName());

		}

		for(int i = 0 ; i < database1.actorList.size(); i++) {
			for(int j = 0 ; j < database1.actorList.get(i).getMovies().size(); j++) {

				System.out.println(i+"\t"+ database1.actorList.get(i).getName() +"\t"+  database1.actorList.get(i).getMovies().get(j).getName());
			}
			System.out.println();
			System.out.println();
		}



		/*for(int i = 0 ; i < database1.movieList.size(); i++) {    //loop through database movieList
			for(int j = 0 ; j < database1.actorList.size(); j++) {    //loop through database actorList
				for(int k = 0 ; k < database1.actorList.get(j).getMovies().size() ; k++) {       // loop through every actor's movies
					// if a movie from the database exists at an actor's movies, add that actor to that movie's actors
					if( database1.movieList.get(i).getName().equalsIgnoreCase(database1.actorList.get(j).getMovies().get(k).getName())) {
						ArrayList<Actor> MovieActos = new ArrayList<Actor>();
						ArrayList<String> MovieActosNames = new ArrayList<String>();
						MovieActos.add(database1.actorList.get(j));

						database1.movieList.get(i).setActors(MovieActos);
					}
				}
			}
		}*/

		System.out.println("the best actor is:\t"+ database1.getBestActor() +"\nthe best movies is:\t"+  database1.getBestMovie());

	
		input.close();
	

	}

}



* 
* 

/*public String readMazeFile(String name ) {

	ArrayList<ArrayList<Character>> MazeRows = new ArrayList<ArrayList<Character>>();
	// ArrayList<Character> MazeCols = new ArrayList<Character>(); 
	Scanner input = new Scanner(System.in);

	//File movies = new File("bigMaze.txt");
	File movies = new File(name);
	Scanner scnr;


	scnr = new Scanner(Maze);

	static int length;
	while(scnr.hasNextLine()) {
		String line = input.nextLine();
		Scanner scanner = new Scanner(line);
		//scanner.useDelimiter("\t");
		ArrayList<Character> cols = new ArrayList<Character>(); 
		for (char c : line.toCharArray()) {
			cols.add(c);
		}
		length = cols.size();
		MazeRows.add(cols);

	}

	char[][] theWholeMaze = new char[MazeRows.size()][length]; 
	for(int i = 0 ; i < MazeRows.size(); i++) {
		for(int j = 0 ; j < MazeRows.get(i).size() ; j++) {
			theWholeMaze[i][j]=MazeRows.get(i).get(j);
		}
	}
}*/
* 
* 
}

