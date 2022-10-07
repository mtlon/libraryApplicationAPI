package com.libraryapplication.libraryapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryapplication.libraryapplication.classes.Book;
import com.libraryapplication.libraryapplication.classes.PasswordEncryption;
import com.libraryapplication.libraryapplication.classes.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("/")
public class LibraryClient {
    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(JdbcConfig.getMysqlDataSource());
    ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/users")
    public Response getUsers() throws JsonProcessingException {
        List users = jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));

        return Response.status(300).header("Access-Control-Allow-Origin", "*")
                .entity(objectMapper.writeValueAsString(users))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Response addUser(@RequestBody User user) {
        String addUserQuery = "INSERT INTO users(username, password) VALUES(:username, :password)";

        PasswordEncryption encryption = new PasswordEncryption(5);

        String encryptedPassword = encryption.encryptPassword(user.getPassword());

        MapSqlParameterSource addUserParameters = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", encryptedPassword);

        jdbcTemplate.update(addUserQuery, addUserParameters);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user/auth")
    public Response authenticateUser(@RequestBody User user) {
        List<User> users = jdbcTemplate.query("SELECT username, password FROM users", new BeanPropertyRowMapper<>(User.class));

        PasswordEncryption encryption = new PasswordEncryption(5);

        String typedUsername = user.getUsername();
        String typedPassword = user.getPassword();

        for (User element : users) {
            String decryptedPassword = encryption.decryptPassword(element.getPassword());

            if (element.getUsername().equals(typedUsername) && decryptedPassword.equals(typedPassword)) {
                return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
            }
        }

        return Response.status(403).header("Access-Control-Allow-Origin", "*").build();
    }

    @OPTIONS
    @Path("/user/auth")
    public Response authenticateUserAllowCors() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept")
                .build();
    }

    @DELETE
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        String deleteUserQuery = "DELETE FROM users WHERE id = :id";
        MapSqlParameterSource deleteUserParameters = new MapSqlParameterSource().addValue("id", id);

        jdbcTemplate.update(deleteUserQuery, deleteUserParameters);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/books")
    public Response getBooks() throws JsonProcessingException {
        List books = jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .entity(objectMapper.writeValueAsString(books))
                .build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/books")
    public Response addBook(@RequestBody Book book) {
        String addBookQuery = "INSERT INTO books(title, releaseYear, authorName, authorSurname, isBorrowed)" +
                              "VALUES(:title, :releaseYear, :authorName, :authorSurname, :isBorrowed)";
        MapSqlParameterSource addBookParameters = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("releaseYear", book.getReleaseYear())
                .addValue("authorName", book.getAuthorName())
                .addValue("authorSurname", book.getAuthorSurname())
                .addValue("isBorrowed", book.getIsBorrowed());

        jdbcTemplate.update(addBookQuery, addBookParameters);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
    }

    @OPTIONS
    @Path("/books")
    public Response addBookStateCorsAllow() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept")
                .build();
    }

    @DELETE
    @Path("/books/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        String deleteBookQuery = "DELETE FROM books WHERE id = :id";
        MapSqlParameterSource deleteUserParameters = new MapSqlParameterSource().addValue("id", id);

        jdbcTemplate.update(deleteBookQuery, deleteUserParameters);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
    }

    @PATCH
    @Path("/books/{id}")
    public Response updateBookState(@PathParam("id") int id, @RequestBody Book book) {
        String updateBookQuery = "UPDATE books SET isBorrowed = :isBorrowed WHERE id = :id";
        MapSqlParameterSource updateBookParameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("isBorrowed", book.getIsBorrowed());

        jdbcTemplate.update(updateBookQuery, updateBookParameters);

        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "PATCH")
                .build();
    }

    @OPTIONS
    @Path("/books/{id}")
    public Response updateBookStateCorsAllow() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "PATCH")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept")
                .build();
    }
 }