/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.exemplary;

import br.edu.ifnmg.book.Book;
import br.edu.ifnmg.emprestimo.Emprestimo;
import br.edu.ifnmg.entity.Entity;

/**
 *
 * @author joaok
 */
public class Exemplary extends Entity {

    private Book book;
    private Emprestimo emprestimo;
    private Boolean disponivel;

    public Exemplary() {
        setDisponivel(null);
    }

    public Exemplary(Long id, Boolean disponivel, Book book, Emprestimo emprestimo) throws Exception {
        
//        this.id = id;
        setId(id);

//        this.book = book;
        setBook(book);

//        this.emprestimo = emprestimo;
        setEmprestimo(emprestimo);

//        this.disponivel = false;
        setDisponivel(disponivel);
    }

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

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Exemplary{"
                + "book=" + book
                + ", emprestimo=" + emprestimo
                + ", disponivel=" + disponivel
                + '}';
    }

}
