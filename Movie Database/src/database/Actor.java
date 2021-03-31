package database;

import java.util.ArrayList;

public class Actor {
	String name;
	ArrayList<Movie> movies;

	Actor(String name, ArrayList<Movie> movies){
		this.name = name;
		this.movies = movies;
	}
	Actor(String name){
		this(name,new ArrayList<Movie>());
	}
	Actor(){
		this("",new ArrayList<Movie>());
	}


	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Movie> getMovies() {
		return this.movies;
	}
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}


}
