/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.book;

import br.edu.ifnmg.repository.Dao;
import br.edu.ifnmg.repository.DbConnection;
import static br.edu.ifnmg.repository.DbConnection.getConnection;
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

    public static final String TABLE = "book";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(title, authors, pages, year, edition)  values (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set title = ?, authors = ?, pages = ?, year = ?, edition = ? WHERE id = ?";
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
                pstmt.setLong(6, e.getId());
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatement() {
        return "select id, title, authors, pages, year, edition"
                + " from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select id, title, authors, pages, year, edition"
                + " from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

//    public Book findByName(String title) throws Exception {
//
//        String sql = "SELECT id, title, authors, pages, year, edition"
//                + " FROM book"
//                + " WHERE title = ?";
//
//        PreparedStatement pstmt = getConnection().prepareStatement(sql);
//        pstmt.setString(1, title);
//
//        ResultSet resultSet = pstmt.executeQuery();
//
//        if (resultSet.next()) {
//            Book book = new Book();
//            book.setId(resultSet.getLong("id"));
//            book.setTitle(resultSet.getString("title"));
//            book.setAuthors(resultSet.getString("authors"));
//            book.setPages(resultSet.getShort("pages"));
//            book.setYear(resultSet.getShort("year"));
//            book.setEdition(resultSet.getByte("edition"));
//
//            return book;
//        } else {
//            return null;
//        }
//    }
    public Book findByid(Long id) throws Exception {

        String sql = "SELECT id, title, authors, pages, year, edition"
                + " FROM book"
                + " WHERE id = ?";

        PreparedStatement pstmt = getConnection().prepareStatement(sql);
        pstmt.setLong(0, id);

        ResultSet resultSet = pstmt.executeQuery();

        if (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthors(resultSet.getString("authors"));
            book.setPages(resultSet.getShort("pages"));
            book.setYear(resultSet.getShort("year"));
            book.setEdition(resultSet.getByte("edition"));

            return book;
        } else {
            return null;
        }
    }

    @Override
    public Book extractObject(ResultSet resultSet) {

        Book book = null;

        try {
            book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthors(resultSet.getString("authors"));
            book.setPages(resultSet.getShort("pages"));
            book.setYear(resultSet.getShort("year"));
            book.setEdition(resultSet.getByte("edition"));
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
                book.setTitle(resultSet.getString("title"));
                book.setAuthors(resultSet.getString("authors"));
                book.setPages(resultSet.getShort("pages"));
                book.setYear(resultSet.getShort("year"));
                book.setEdition(resultSet.getByte("edition"));
                books.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    @Override
    public void delete(Long id) {

        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(getDeleteStatement())) {
            preparedStatement.setLong(1, id);

            System.out.println(">> SQL: " + preparedStatement);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

    }
}
