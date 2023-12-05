package br.edu.ifnmg.user;

import br.edu.ifnmg.credential.Credential;
import br.edu.ifnmg.entity.Entity;
import br.edu.ifnmg.librarian.Librarian;
import br.edu.ifnmg.role.Role;

import java.time.LocalDate;

public class User extends Entity {

    private String name;
    private String email;
    private LocalDate birthDate;
    private Role role;
    private Credential credential;

    public User() {
    }

    public User(String name, String email, LocalDate birthDate, Role role, Credential credential) 
            throws Exception {

//        setId(id);
//        this.name = name;
        setName(name);  // Security problem!

//        this.email = email;
        setEmail(email);  // Security problem!

//        this.birthdate = birthdate;
        setBirthDate(birthDate);  // Security problem!

//        this.setCredential(credential);
        setCredential(credential);

//        this.setRole(role);
        setRole(role);
    }
    //<editor-fold defaultstate="collapsed" desc="Get and Set">
    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() > 150) {
            throw new IllegalArgumentException("User: O nome excede 150 caracteres");
        } else if (name == null) {
            throw new IllegalArgumentException("User: nome nulo");
        } else {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email == null) {
            throw new IllegalArgumentException("User: Email nulo");
        } else if (email.length() > 255) {
            throw new IllegalArgumentException("User: O Email excede 255 caracteres");
        }else {
            this.email = email;
        }
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws Exception {
        if (birthDate == null) {
            throw new IllegalArgumentException("User: Data de nascimento nula");
        } else {
            this.birthDate = birthDate;
        }
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public Credential getCredential() {
        return this.credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

//</editor-fold>
    
    @Override
    public String toString() {
        return "Nome: \"" + this.name 
                + "\", Email: \"" + this.email 
                + "\", Data de nascimento: \"" + this.birthDate.toString() 
                + "\", Papel: \"" + this.role.getName()
                + "\", Credencial: \"" + this.credential.getUsername() 
                + "\"";
    }

    public Librarian asLibrarian() {
        return (Librarian) this;
    }

}
