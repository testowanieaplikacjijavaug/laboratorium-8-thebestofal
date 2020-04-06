package zad3;
import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.easymock.EasyMock.*;

import static org.assertj.core.api.Assertions.*;

public class NotesTest
{
    private NotesService notesService;
    private NotesStorage notesStorage;
    
    @BeforeEach
    void setup()
    {
        notesStorage = mock(NotesStorage.class);
        notesService = mock(NotesService.class);
    }
    
    @AfterEach
    public void tearDown() {
        notesStorage = null;
        notesService = null;
    }

    @Test
    void addOne()
    {
        Note note = Note.of("Steve", 3.0f);
        ArrayList<Note> expected = new ArrayList<Note>();
        
        notesStorage.add(note);
        EasyMock.expectLastCall().andAnswer(() -> {
            expected.add(note);
            return null;
        }).times(1);
        replay(notesStorage);
    
        notesStorage.add(note);
    
        assertThat(expected.get(0).getName()).isEqualTo("Steve");
        EasyMock.verify(notesStorage);
    }
    
    @Test
    void avarageOfEmptyStudent()
    {
        EasyMock.expect(notesService.averageOf(""))
                .andThrow(new IllegalArgumentException("Imię ucznia nie może być puste"));
        EasyMock.replay(notesService);
        assertThatThrownBy(()->{
            notesService.averageOf(""); })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być puste");
    
        EasyMock.verify(notesService);
    }
    
    @Test
    void avarageOfNullStudent()
    {
        EasyMock.expect(notesService.averageOf(null)).andThrow(new IllegalArgumentException("Imię ucznia nie może być null"));
        EasyMock.replay(notesService);
        assertThatThrownBy(()->{
            notesService.averageOf(null); })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być null");
        
        EasyMock.verify(notesService);
    }
    
    @Test
    public void averageOfFloatingNumber() {
        
        expect(notesService.averageOf("Carl")).andReturn(4.1f);
        replay(notesService);
    
        assertThat(notesService.averageOf("Carl")).isEqualTo(4.1f);
        EasyMock.verify(notesService);
    }
    
    @Test
    public void testClear()
    {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(Note.of("Carl", 4.0f));
        
        notesService.clear();
        EasyMock.expectLastCall().andAnswer(() -> {
            notes.clear();
            return null;
        }).times(1);
        
        replay(notesService);
        
        notesService.clear();
        assertThat(notes.isEmpty()).isTrue();
        EasyMock.verify(notesService);
    }
    
}
