package br.edu.ifnmg.reader;

import java.time.LocalDate;

import br.edu.ifnmg.credential.Credential;
import br.edu.ifnmg.role.Role;
import br.edu.ifnmg.user.User;

public class Reader extends User{
    public Reader(){
        super();
    }

    public Reader(String name, String email, LocalDate birthDate, Role role, Credential credential) throws Exception{
        super(name, email, birthDate, role, credential);
    }
}
