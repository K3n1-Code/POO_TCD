/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.book;

import br.edu.ifnmg.repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaok
 */
public class BookDao extends Dao<Book> {

    public static final String TABLE = "books";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(titulo, autor, paginas, ano, edicao)  values (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set titulo = ?, autor = ?"
                + " paginas = ?, ano = ?, edicao = ? WHERE id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Book e) {
        try {
            pstmt.setString(1, e.getTitle());
            pstmt.setString(2, e.getAuthors());
            pstmt.setShort(3, e.getPages());
            pstmt.setShort(4, e.getYear());
            pstmt.setByte(5, e.getEdition());

            if (e.getId() != null) {
                pstmt.setLong(7, e.getId());
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatement() {
        return "select id, titulo, autor, paginas, ano, edicao"
                + " from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select id, titulo, autor, paginas, ano, edicao"
                + " from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public Book extractObject(ResultSet resultSet) {

        Book book = null;

        try {
            book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("titulo"));
            book.setAuthors(resultSet.getString("autor"));
            book.setPages(resultSet.getShort("paginas"));
            book.setYear(resultSet.getShort("ano"));
            book.setEdition(resultSet.getByte("edicao"));
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return book;
    }

    @Override
    public ArrayList<Book> extractObjects(ResultSet resultSet) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("titulo"));
                book.setAuthors(resultSet.getString("autor"));
                book.setPages(resultSet.getShort("paginas"));
                book.setYear(resultSet.getShort("ano"));
                book.setEdition(resultSet.getByte("edicao"));
                books.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

}
