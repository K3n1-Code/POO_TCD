package br.edu.ifnmg.adm;

import br.edu.ifnmg.credential.Credential;
import br.edu.ifnmg.role.Role;
import br.edu.ifnmg.user.User;
import java.time.LocalDate;

public class Adm extends User {

    public Adm() {
        super();
    }

    public Adm(String name, String email, LocalDate birthDate, Role role, Credential credential) throws Exception {
        super(name, email, birthDate, role, credential);
    }

}
