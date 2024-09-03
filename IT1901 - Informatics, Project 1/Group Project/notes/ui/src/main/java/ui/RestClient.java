package ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Note;

public class RestClient {

    private static final String API_URL = "http://localhost:8080";

    /**
     * Retrieves a list of all notes from the server.
     *
     * @return A list of all notes.
     */
    public static List<Note> getNotes() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(API_URL + "/getNotes"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder()
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            // convert JSON array to list of notes
            List<Note> notes = mapper.readValue(response.body(), new TypeReference<List<Note>>() {
            });
            return notes;
        } catch (InterruptedException | URISyntaxException | IOException e) {
            System.err.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Method to send a new note to the server
     *
     * @param title title of the new note
     * @param text content of the new note
     * @param tags tags of the new note
     */
    public static void createNote(String title, String text, List<String> tags) {
        String body = title + "&" + text + "&" + String.join(",", tags);
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(API_URL + "/addNote"))
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpClient client = HttpClient.newBuilder()
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.statusCode());
        } catch (InterruptedException | URISyntaxException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Method to edit a note on the server
     *
     * @param id id of the note to edit
     * @param title title of the note
     * @param text content of the note
     * @param tags tags of the note
     */
    public static void editNote(int id, String title, String text, List<String> tags) {
        String body = title + "&" + text + "&" + String.join(",", tags);
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(API_URL + "/editNote/" + id))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpClient client = HttpClient.newBuilder()
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.statusCode());
        } catch (InterruptedException | URISyntaxException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Method to delete a note on the server
     *
     * @param id id of the note to delete
     */
    public static void deleteNote(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(API_URL + "/deleteNote/" + id))
                    .DELETE()
                    .build();
            HttpClient client = HttpClient.newBuilder()
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.statusCode());
        } catch (InterruptedException | URISyntaxException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
