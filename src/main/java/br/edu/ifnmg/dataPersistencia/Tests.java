package br.edu.ifnmg.dataPersistencia;

import br.edu.ifnmg.adm.Adm;
import br.edu.ifnmg.adm.AdmDao;
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

}
