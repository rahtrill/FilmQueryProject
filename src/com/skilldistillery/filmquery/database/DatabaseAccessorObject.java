package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Driver not found.");
			throw new RuntimeException("Unable to load MYSQL driver class.");
		}
	}

	public Film findFilmById(int filmId) {
		String statement = "SELECT * FROM film WHERE film.id = ?";
		Film film = null;
		
		try ( 
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement ps = conn.prepareStatement(statement); ) {
			
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() && filmId > 0) {
				
				Film newFilm = new Film();
				newFilm.setId(rs.getInt("film.id"));
				newFilm.setTitle(rs.getString("film.title"));
				newFilm.setDescription(rs.getString("film.description"));
				newFilm.setRelease_year(rs.getString("film.release_year"));
				newFilm.setLanguage_id(rs.getInt("film.language_id"));
				newFilm.setRental_duration(rs.getInt("film.rental_duration"));
				newFilm.setRental_rate(rs.getDouble("film.rental_rate"));
				newFilm.setLength(rs.getInt("film.length"));
				newFilm.setReplacement_cost(rs.getDouble("film.replacement_cost"));
				newFilm.setRating(rs.getString("film.rating"));
				newFilm.setSpecial_features(rs.getString("film.special_features"));
				newFilm.setCast(findActorsByFilmId(filmId));
				
				film = newFilm;
			
			}
			rs.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return film;
	}
	
	public List<Film> findFilmByKeyword(String keyword) {
		String statement = "SELECT * FROM film WHERE film.title LIKE(?) OR film.description LIKE(?);";
		List<Film> filmList = new ArrayList<>();
		
		try ( 
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement ps = conn.prepareStatement(statement); ) {
			
			ps.setString(1, ("%" + keyword + "%"));
			ps.setString(2, ("%" + keyword + "%"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Film newFilm = new Film();
				newFilm.setId(rs.getInt("film.id"));
				newFilm.setTitle(rs.getString("film.title"));
				newFilm.setDescription(rs.getString("film.description"));
				newFilm.setRelease_year(rs.getString("film.release_year"));
				newFilm.setLanguage_id(rs.getInt("film.language_id"));
				newFilm.setRental_duration(rs.getInt("film.rental_duration"));
				newFilm.setRental_rate(rs.getDouble("film.rental_rate"));
				newFilm.setLength(rs.getInt("film.length"));
				newFilm.setReplacement_cost(rs.getDouble("film.replacement_cost"));
				newFilm.setRating(rs.getString("film.rating"));
				newFilm.setSpecial_features(rs.getString("film.special_features"));
				newFilm.setCast(findActorsByFilmId(newFilm.getId()));
				
				filmList.add(newFilm);
			
			}
			rs.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return filmList;
	}

	public Actor findActorById(int actorId) {
		String statement = "SELECT * FROM actor WHERE actor.id = ?";
		Actor actor = null;
		
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(statement);) {
			
			ps.setInt(1, actorId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() && actorId > 0) {
			
				Actor newActor = new Actor();
				newActor.setId(rs.getInt("actor.id"));
				newActor.setFirst_name(rs.getString("actor.first_name"));
				newActor.setLast_name(rs.getString("actor.last_name"));
				
				actor = newActor;
			
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actor;
	}

	public List<Actor> findActorsByFilmId(int filmId) {
		String statement = "SELECT * FROM film JOIN film_actor ON film.id = film_id JOIN actor "
						 + "ON actor.id = actor_id WHERE film.id = ?;";
		List<Actor> actorList = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(statement);) {
			
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				if (filmId > 0) {
					Actor newActor = new Actor();
					newActor.setId(rs.getInt("actor.id"));
					newActor.setFirst_name(rs.getString("actor.first_name"));
					newActor.setLast_name(rs.getString("actor.last_name"));
					
					actorList.add(newActor);
				}
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actorList;
	}
	
	public String pullLanguageFromId(int langId) {
		String statement = "SELECT language.name FROM language WHERE language.id = ?";
		String language = "";
		try ( 
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement ps = conn.prepareStatement(statement); ) {
			
			ps.setInt(1, langId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() && langId > 0) {
				
				language = rs.getString("language.name");
			
			}
			rs.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return language;
	}

}
