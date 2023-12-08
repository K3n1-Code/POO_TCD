/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.book;

import br.edu.ifnmg.entity.Entity;

/**
 *
 * @author joaok
 */
public class Book extends Entity {

    private String title;
    private String authors;
    private Short pages;
    private Short year;
    private Byte edition;

    public Book() {
    }

    public Book(Long id, String title, String authors, Short pages, Short year,
            Byte edition) throws Exception {
        setId(id);

//        this.title = title;
        setTitle(title);  // Security problem!

//        this.authors = authors;
        setAuthors(authors);  // Security problem!

//        this.pages = pages;
        setPages(pages);  // Security problem!

//        this.year = year;
        setYear(year);  // Security problem!

//        this.edition = edition;
        setEdition(edition);  // Security problem!

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Titulo nulo");
        } else if (title.length() > 150) {
            throw new IllegalArgumentException("O titulo excede 150 caracteres");
        }

        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        if (authors == null) {
            throw new IllegalArgumentException("Autor nulo");
        } else if (authors.length() > 250) {
            throw new IllegalArgumentException("O autor excede 250 caracteres");
        }

        this.authors = authors;
    }

    public Short getPages() {
        return pages;
    }

    public void setPages(Short pages) {
        if (pages == null) {
            throw new IllegalArgumentException("Numero nulo de paginas");
        } else if (pages < 1) {
            throw new IllegalArgumentException("O numero de paginas deve ser maior que zero");
        }

        this.pages = pages;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        if (year == null) {
            throw new IllegalArgumentException("Ano nulo");
        }
        else if (year < 1) {
            throw new IllegalArgumentException("O ano deve ser maior que zero");
        }

        this.year = year;
    }

    public Byte getEdition() {
        return edition;
    }

    public void setEdition(Byte edition) {
        if (edition == null) {
            throw new IllegalArgumentException("Ediçao nula");
        } else if (edition < 1) {
            throw new IllegalArgumentException("O numero da edição deve ser maior que zero");
        }

        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title
                + ", authors=" + authors
                + ", pages=" + pages
                + ", year=" + year
                + ", edition=" + edition
                + '}';
    }

}
