package core;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.persistence.IO;

/**
 * Represents a Note object with an ID, title, text content, tags, and an
 * archived status.
 */
public class Note {

    private final int id;
    private String title;
    private String text;
    private List<String> tags;

    /**
     * Constructor for Note.
     *
     * @param id The ID of the note.
     * @param title The title of the note.
     * @param text The text content of the note.
     * @param tags The tags associated with the note as a List of Strings.
     */
    @JsonCreator
    public Note(@JsonProperty("id") int id,
            @JsonProperty("title") String title,
            @JsonProperty("text") String text,
            @JsonProperty("tags") List<String> tags) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.tags = tags;
    }

    /**
     * Edits the current Note object with new information.
     *
     * @param id The ID of the note.
     * @param title The new title (null to keep the old value).
     * @param text The new text content (null to keep the old value).
     * @param tags The new tags (null to keep the old value).
     * @return The edited Note object.
     */
    public Note editNote(Integer id, String title, String text, List<String> tags) {
        this.title = title != null ? title : this.title;
        this.text = text != null ? text : this.text;
        this.tags = tags != null ? tags : this.tags;
        IO.editEntry(id, title, text, tags);

        return this;
    }

    /**
     * Retrieves the ID of the note.
     *
     * @return The ID of the note.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Retrieves the title of the note.
     *
     * @return The title of the note.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Retrieves the text content of the note.
     *
     * @return The text content of the note.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Retrieves the tags associated with the note.
     *
     * @return The tags associated with the note as a List of Strings.
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Sets the title of the note.
     *
     * @param title The new title of the note.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the text content of the note.
     *
     * @param text The new text content of the note.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Sets the tags associated with the note.
     *
     * @param tags The new tags of the note as a List of Strings.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Returns a string representation of the Note object.
     *
     * @return A string containing the title, text content, and tags of the
     * note.
     */
    @Override
    public String toString() {
        return this.title + " - " + this.text;
    }
}
