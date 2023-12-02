/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.emprestimo;

import br.edu.ifnmg.entity.Entity;
import br.edu.ifnmg.librarian.Librarian;
import br.edu.ifnmg.reader.Reader;
import java.time.LocalDate;

/**
 *
 * @author joaok
 */
public class Emprestimo extends Entity {

    private LocalDate diaEmprestimo;
    private LocalDate diaRetorno;
    private Librarian librarian;
    private Reader reader;

    public Emprestimo() {
    }

    public Emprestimo(Long id, LocalDate diaEmprestimo, LocalDate diaRetorno, Librarian librarian, Reader reader)
            throws Exception {

//        setId(id);
//        this.setDiaEmprestimo(diaEmprestimo);
        setDiaEmprestimo(diaEmprestimo);  // Security problem!

//        this.setDiaRetorno(diaRetorno);
        setDiaRetorno(diaRetorno);  // Security problem!

//        this.setLibrarian(librarian);
        setLibrarian(librarian);  // Security problem!

//        this.setCredential(credential);
        setReader(reader);

    }

    public LocalDate getDiaEmprestimo() {
        return diaEmprestimo;
    }

    public void setDiaEmprestimo(LocalDate diaEmprestimo) {
        if (diaEmprestimo == null) {
            throw new IllegalArgumentException("Emprestimo: Data de empreestimo nula");
        } else {
            this.diaEmprestimo = diaEmprestimo;
        }
    }

    public LocalDate getDiaRetorno() {
        return diaRetorno;
    }

    public void setDiaRetorno(LocalDate diaRetorno) {
        if (diaRetorno == null) {
            throw new IllegalArgumentException("Emprestimo: Data de Retorno nula");
        } else {
            this.diaRetorno = diaRetorno;
        }
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "Empreestimo{"
                + "diaEmprestimo=" + this.diaEmprestimo
                + ", diaRetorno=" + this.diaRetorno
                + ", librarian=" + this.librarian
                + ", reader=" + this.reader
                + '}';
    }

}
