/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.commets;

import br.edu.ifnmg.book.Book;
import br.edu.ifnmg.entity.Entity;
import br.edu.ifnmg.reader.Reader;

/**
 *
 * @author joaok
 */
public class Comments extends Entity {

    private String comentarios;
    private Book book;
    private Reader reader;

    public Comments() {
    }

    public Comments(Long id, String comentarios, Book book, Reader reader) throws Exception {
//        this.id = id;
        setId(id);
        
//        this.comentarios = comentarios;
        setComentarios(comentarios);
        
//        this.book = book;
        setBook(book);
        
//        this.reader = reader;
        setReader(reader);
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        if (comentarios == null) {
            throw new IllegalArgumentException("comments: comentarios nulo");
        } else if (comentarios.length() > 300) {
            throw new IllegalArgumentException("comments: O comentario excede 300 caracteres");
        }
        this.comentarios = comentarios;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "Comments{"
                + "comentarios=" + comentarios
                + ", book=" + book
                + ", reader=" + reader
                + '}';
    }

}
