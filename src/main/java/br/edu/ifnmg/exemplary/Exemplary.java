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
public class Exemplary extends Entity{
    private Book book;
    private Emprestimo emprestimo;
    private Boolean disponivel;

    public Exemplary() {
        setEnabled(null);
    }
    
    
    

    public Exemplary(Long id, Boolean disponivel, Book book, Emprestimo emprestimo) {
        this.id = id;
        this.livro = livro;
        this.emprestado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public boolean estaDisponivel() {
        return !emprestado;
    }

}
