package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//    app.test();
    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
    Actor actor = db.findActorById(1);
    System.out.println(actor);
    List<Actor> actors = db.findActorsByFilmId(1);
    System.out.println(actors);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  boolean inMenu = true;
	  
	  while (inMenu) {
		  System.out.println();
		  System.out.println("=======================================");
		  System.out.println("=     Film Query Application Menu     =");
		  System.out.println("======================================="); 
		  System.out.println("= (Please choose an option by number) =");
		  System.out.println("======================================="); 
		  System.out.println("= 1. Look up a film by it's ID        =");
		  System.out.println("= 2. Look up a film by a keyword      =");
		  System.out.println("= 3. Exit this application            =");
		  System.out.println("======================================="); 
		  System.out.println();
		  int answer = input.nextInt();
		  
		  switch (answer) {
		  
		  case 1:
			  
			  System.out.println("Please enter the film ID: ");
			  int id = input.nextInt();
			  
			  System.out.println("Finding film ID...");
			  System.out.println();
			  
			  Film film = db.findFilmById(id);
			  
			  displayFilm(film);
			  
			  break;
			  
		  case 2:
			  
			  System.out.println("Please enter the keyword for your search: ");
			  System.out.println();
			  String keyword = input.next();
			  keyword = keyword + input.nextLine();
			  
			  System.out.println("Finding film(s) by keyword...");
			  System.out.println();
			  
			  List<Film> filmList = db.findFilmByKeyword(keyword);
			  
			  if (filmList.size() == 0) {
				  System.out.println();
				  System.out.println("There were no films found matching your search.");
			  } else {
				  for (Film film2 : filmList) {
					  displayFilm(film2);
				  }
			  }
			  
			  break;
			  
		  case 3:
			  
			  System.out.println("==================================================="); 
			  System.out.println("= Thank you for using the Film Query Application! =");
			  System.out.println("==================================================="); 
			  System.out.println("=                     Goodbye!                    =");
			  System.out.println("===================================================");
			  System.out.println();
			  inMenu = false;
			  
			  break;
			  
		  default:
				 
			  System.out.println("==================================================="); 
			  System.out.println("=    Your input was invalid. Please try again.    =");
			  System.out.println("==================================================="); 
			  System.out.println();
			  
			  break;
		  
		  }
		  
	  }
	  
  }
  
  private void displayFilm(Film film) {
	  System.out.println("==================================================="); 
	  System.out.println("Title: " + film.getTitle());
	  System.out.println("Year released: " + film.getRelease_year());
	  System.out.println("Rating: " + film.getRating());
	  System.out.println("Description: " + film.getDescription());
	  System.out.println("Language: " + film.getLanguage());
	  System.out.print("Cast: " + film.getCast());
	  System.out.println("==================================================="); 
	  System.out.println();
	  System.out.println();
	  
  }

}
