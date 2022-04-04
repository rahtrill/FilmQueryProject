# Film Query Project

## Description

This project was created to use the Java Database Connectivity and apply it to a SQL database to retrieve information about films, actors, etc. The program prompts the user to look up a film by an ID it has in the database, or it can find a film by searching with a keyword.

In order to find the film by an ID, the program refers to a method that sends a SQL prepared statement into the database. It then retrieves all of the information about a film based on the ID given and turns it into a Film Object. All of the data pulled from the database is set accordingly to the variables in the Object. It will then display a given amount of this information to the user to complete the search.

Here is the prepared statement given to find a film by ID:

`SELECT * FROM film WHERE film.id = ?`

In order to find the film by a keyword, the program again refers to a method that sends an SQL prepared statement into the database. However, it will not look for that film by an ID. It will use the keyword input and search both the titles and descriptions of the films for that keyword, and output every film that has it.

Here is the prepared statement given to find a film by keyword:

`SELECT * FROM film WHERE film.title LIKE(?) OR film.description LIKE(?);`

Every time a film is output to the user, it will have the following data:

- Film title.
- Film year released.
- Film rating.
- Film description.
- Film language.
- Film cast.

Although some of this information requires extra methods to retrieve the data, they are all properties of the Film Object.

The cast of a film is a list of Actor Objects that have an ID, first name, and last name variable inside the Object. When it is output alongside the other film data, it will display a list of Actor Objects.

## Lessons Learned

I learned that it can be extremely useful to use the JDBC and pull data from a database using SQL to create Objects and use that data to assign to the Object's variables.

At some point during the program, I attempted to create methods that would return a String, and that would not have properly fulfilled the requirements for the program. Returning a String to assign data would have been much easier, but I instead had to work around that rule to individually call another prepared statement to get the data I needed.

Another lesson I learned was to close the java.sql.Connection and it's sub classes. If I do not close these, then every connection I make will run until there are no longer any connections available. I had this in mind when planning the program but did not think about it when writing the code until after the code was done.

## Technologies Used



## What was the most difficult part of this project?
