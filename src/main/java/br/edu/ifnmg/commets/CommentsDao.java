/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.commets;

import br.edu.ifnmg.book.Book;
import br.edu.ifnmg.book.BookDao;
import br.edu.ifnmg.reader.Reader;
import br.edu.ifnmg.reader.ReaderDao;
import br.edu.ifnmg.repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author joaok
 */
public class CommentsDao extends Dao<Comments> {

    public static final String TABLE = "comments";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + " (comments, book_id, reader_id) values(?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set comments = ?, book_id = ?, reader_id = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select comments, book_id, reader_id, id from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select * from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Comments comments) {
        try {
            if (comments.getComentarios() != null) {
                pstmt.setObject(1, comments.getComentarios(), Types.VARCHAR);
            }
            if (comments.getBook().getId() != null) {
                pstmt.setObject(4, comments.getBook().getId(), Types.BIGINT);
            }
            if (comments.getReader().getId() != null) {
                pstmt.setObject(4, comments.getReader().getId(), Types.BIGINT);
            }
            if (comments.getId() != null) {
                pstmt.setObject(5, comments.getId(), Types.BIGINT);
            }
        } catch (Exception ex) {
            System.out.println("Exception in ComposeSave or Update " + ex);
        }
    }

    @Override
    public Comments extractObject(ResultSet rs) {
        Comments comments = null;
        try {
            comments = new Comments();
            comments.setComentarios(rs.getString("commets"));
            comments.setId(rs.getLong("id"));
            Book book = new BookDao().findById(rs.getLong("book_id"));
            comments.setBook(book);
            Reader reader = new ReaderDao().findById(rs.getLong("reader_id"));
            comments.setReader(reader);
        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }
        return comments;
    }
}
