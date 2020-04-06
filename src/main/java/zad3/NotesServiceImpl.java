package zad3;

import com.google.common.base.Preconditions;

import java.util.Collection;

public class NotesServiceImpl implements NotesService
{
    
    public static NotesServiceImpl createWith(final NotesStorage storageService)
    {
        return new NotesServiceImpl(storageService);
    }
    
    @Override
    public void add(Note note)
    {
        storageService.add(note);
    }
    
    @Override
    public float averageOf(String name)
    {
        Preconditions.checkArgument(name != null, "Imię ucznia nie może być null");
        Preconditions.checkArgument(!name.trim().isEmpty(), "Imię ucznia nie może być puste");
        
        float sum = 0.0f;
        final Collection<Note> notes = storageService.getAllNotesOf(name);
        for (final Note note : notes)
        {
            sum += note.getNote();
        }
        return sum / notes.size();
        
    }
    
    @Override
    public void clear()
    {
        storageService.clear();
    }
    
    private NotesServiceImpl(final NotesStorage storageService)
    {
        this.storageService = storageService;
    }
    
    private final NotesStorage storageService;
}