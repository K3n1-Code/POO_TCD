package br.edu.ifnmg.dataPersistencia;

import br.edu.ifnmg.adm.Adm;
import br.edu.ifnmg.adm.AdmDao;
import br.edu.ifnmg.book.Book;
import br.edu.ifnmg.book.BookDao;
import java.time.LocalDate;

import br.edu.ifnmg.credential.Credential;
import br.edu.ifnmg.librarian.Librarian;
import br.edu.ifnmg.librarian.LibrarianDao;
import br.edu.ifnmg.reader.Reader;
import br.edu.ifnmg.reader.ReaderDao;
import br.edu.ifnmg.role.Role;
import br.edu.ifnmg.role.RoleDao;

public class Tests {

    public static void Administrador() {
        System.out.println("Administrador");
        try {
            Role role = new Role("Administrador");
            Long role_id = new RoleDao().saveOrUpdate(role);
            role.setId(role_id);

            Credential cred0 = new Credential(null, "admin", "123", LocalDate.now(), null, null);

            Adm adm = null;
            adm = new Adm(
                    "ana",
                    "ana_maria_das_bragas@email.com",
                    LocalDate.of(1999, 01, 30),
                    role,
                    cred0);

            cred0.setUser(adm);
            Long user_id = new AdmDao().saveOrUpdate(adm);

            Adm a = null;
            a = new AdmDao().findById(user_id);
            new AdmDao().saveOrUpdate(a);

            System.out.println("Administrador Cadastrado!!");
            System.out.println(a.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void Bibliotecario() {
        System.out.println("\nBibliotecário");
        try {
            Role role = new Role("Bibliotecário");
            Long role_id = new RoleDao().saveOrUpdate(role);
            role.setId(role_id);

            Credential cred1 = new Credential(null, "biblio1", "asdf", LocalDate.now(), null, null);

            Librarian ana = null;
            ana = new Librarian(
                    "ana",
                    "ana_maria_das_bragas@email.com",
                    LocalDate.of(1999, 01, 30),
                    role,
                    cred1);

            cred1.setUser(ana);
            Long user_id = new LibrarianDao().saveOrUpdate(ana);

            Librarian L = null;
            L = new LibrarianDao().findById(user_id);
            new LibrarianDao().saveOrUpdate(L);

            System.out.println("Bibliotecário Cadastrado!!");
            System.out.println(L.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void Leitor() {
        System.out.println("\nLeitor");
        try {
            Role role = new Role("Leitor");
            Long role_id = new RoleDao().saveOrUpdate(role);
            role.setId(role_id);

            Reader joao = null;
            joao = new Reader(
                    "joao",
                    "joaoK@email.com",
                    LocalDate.of(2003, 05, 06),
                    role,
                    new Credential(null, "leitor1", "qwerty", LocalDate.now(), true, joao));
            joao.getCredential().setUser(joao);
            Long user_id = new ReaderDao().saveOrUpdate(joao);

            joao = new ReaderDao().findById(user_id);
            new ReaderDao().saveOrUpdate(joao);

            System.out.println("Leitor Cadastrado!!");
            System.out.println(joao.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void Livro() {
        Long book1Id = null;

        try {
            Book book1 = new Book(
                    null, // id
                    "A cabana", // title
                    "J.R.R. Tolkien", // author
                    (short) 240, // pages
                    (short) 2008, // year
                    (byte) 1 // edition
            );

            System.out.println("Livro 1 incluido: " + book1);

            book1Id = new BookDao().saveOrUpdate(book1);
            book1.setId(book1Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void TdsLivros() {
        try {
            BookDao BuscaBooks = new BookDao();
            System.out.println("Todos Livros: ");
            for (Book e : BuscaBooks.findAll()) {
                System.out.println(">> " + e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DeleteLivros() {
        try {
            String tituloParaDeletar = "A Cabana";
            new BookDao().deleteByTitle(tituloParaDeletar);
            System.out.println("Livro '" + tituloParaDeletar + "' deletado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void AtualizaLivros() {
        String novoTitulo = "c";
        String novoAutor = "c";
        Short novasPaginas = 200;
        Short novoAno = 2023;
        Byte novaEdicao = 2;

        try {
            // Recupera o livro pelo título do banco de dados
            BookDao bookDao = new BookDao();
            String tituloParaAtualizar = "b";

            Book livroAtualizar = bookDao.findByName(tituloParaAtualizar);

            if (livroAtualizar != null) {
                System.out.println("Antes da Atualização: " + livroAtualizar);

                livroAtualizar.setTitle(novoTitulo);
                livroAtualizar.setAuthors(novoAutor);
                livroAtualizar.setPages(novasPaginas);
                livroAtualizar.setYear(novoAno);
                livroAtualizar.setEdition(novaEdicao);

                bookDao.saveOrUpdate(livroAtualizar);

                System.out.println("Depois da Atualização: " + livroAtualizar);
            } else {
                System.out.println("Livro não encontrado para atualização.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
