package zad3;

import java.util.List;

// Interfejs odpowiedzialny za obsługę serwisu programu
public interface NotesService {
    void add(Note note);
    float averageOf(String name);
    void clear();
}

// Interfejs obsługujący przechowywanie danych
interface NotesStorage {
    void add(Note note);
    List<Note> getAllNotesOf(String name);
    void clear();
}