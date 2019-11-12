package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteServiceDaoImplTemplate implements NoteServiceDao{

    private static final String INSERT_NOTE_SQL =
            "insert into note (book_id, note) values (?, ?)";
    private static final String SELECT_NOTE_BY_ID_SQL =
            "select * from note where note_id = ?";
    private static final String SELECT_ALL_NOTES_SQL =
            "select * from note";
    private static final String SELECT_NOTE_BY_BOOK_ID_SQL =
            "select * from note where book_id = ?";
    private static final String UPDATE_NOTE_SQL =
            "update note set book_id = ?, note = ? where note_id = ? ";
    private static final String DELETE_NOTE_SQL =
            "delete from note where note_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteServiceDaoImplTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Note createNote(Note note) {
        jdbcTemplate.update(INSERT_NOTE_SQL,
                           note.getBook_id(),
                            note.getNote());

        int note_id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        note.setNote_id(note_id);
        return note;
    }

    @Override
    public Note getNote(int note_id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_NOTE_BY_ID_SQL, this::mapRowToNote, note_id);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no task with id " + note_id + "; message: " + erdae.getMessage());
            return null;
        }

    }

    @Override
    public List<Note> getAllNotes() {
        return jdbcTemplate.query(SELECT_ALL_NOTES_SQL, this::mapRowToNote);
    }

    @Override
    public List<Note> getNoteByBook(int book_id) {

        try{
            return jdbcTemplate.query(SELECT_NOTE_BY_BOOK_ID_SQL, this::mapRowToNote, book_id);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no Task with id " + book_id + "; message: " + erdae.getMessage());
            return null;
        }

    }

    @Override
    public void updateNote(Note note) {
        jdbcTemplate.update(UPDATE_NOTE_SQL,
                note.getBook_id(),
                note.getNote(),
                note.getNote_id());
    }

    @Override
    public void deleteNote(int note_id) {
        jdbcTemplate.update(DELETE_NOTE_SQL, note_id);

    }

    private Note mapRowToNote(ResultSet rs, int rowNum) throws SQLException{
        Note note = new Note();
        note.setBook_id(rs.getInt("book_id"));
        note.setNote(rs.getString("note"));
        note.setNote_id(rs.getInt("note_id"));
        return note;
    }
}
