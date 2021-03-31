package database;

import java.util.ArrayList;

public class Movie {
	String name;
	ArrayList<Actor> actors;
	

	double  rating;


	Movie(String name, ArrayList<Actor> actors, double rating){
		this.name = name;
		this.actors = actors;
		if(rating >= 0 && rating <= 100)
			this.rating = rating;
	}
	Movie(String name){
		this(name,new ArrayList<Actor>(),0.0);
	}
	Movie(){
		this("",new ArrayList<Actor>(), 0.0);
	}


	public double getRating() {
		return this.rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Actor> getActors() {
		return this.actors;
	}
	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}
	

}
