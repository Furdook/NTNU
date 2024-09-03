package ui;

import java.util.ArrayList;
import java.util.List;

import core.NoteList;

/**
 * Class for ListView.
 */
public class SortListView {

    private static final List<String> sortedNotes = new ArrayList<>();

    /**
     * Sorts notes based on tags in param.
     *
     * @param tags List of String for tags
     * @return the sorted list of notes
     */
    public static List<String> sortNotes(List<String> tags) {
        sortedNotes.clear();
        NoteList.getNotes().stream().forEach(note -> {
            note.getTags().stream().forEach(tag -> {
                if (tags.contains(tag) && !sortedNotes.contains(note.toString())) {
                    sortedNotes.add(note.toString());
                }
            });
        });
        return sortedNotes;
    }
}
