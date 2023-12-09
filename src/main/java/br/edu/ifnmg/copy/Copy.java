/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.copy;

import br.edu.ifnmg.book.Book;
import br.edu.ifnmg.emprestimo.Emprestimo;
import br.edu.ifnmg.entity.Entity;

/**
 *
 * @author joaok
 */
public class Copy extends Entity {

    private Book book;
    private Emprestimo emprestimo;
    private Boolean disponivel;

    public Copy() {
        setDisponivel(null);
    }

    public Copy(Long id, Book book) throws Exception {   
        setId(id);
        setBook(book);
        setEmprestimo(null);
        setDisponivel();
    }
    
    public Copy(Long id, Boolean disponivel, Book book, Emprestimo em) throws Exception {   
        setId(id);
        setBook(book);
        setEmprestimo(em);
        setDisponivel(disponivel);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Get and set">
        public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel() {
        disponivel=(emprestimo==null);
    }
    
    public void setDisponivel(Boolean disponivel) {
        this.disponivel=disponivel;
    }

//</editor-fold>

    @Override
    public String toString() {
        return "Exemplary{"
                + "book=" + book
                + ", emprestimo=" + emprestimo
                + ", disponivel=" + disponivel
                + '}';
    }

}
