package br.edu.ifnmg.role;

import br.edu.ifnmg.entity.Entity;

public class Role extends Entity {

    private String name;

    public Role() {
    }

    public Role(String name) throws Exception {
//        this.name = name;
        setName(name);  // Security problem!
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null) {
            throw new IllegalArgumentException("Role: nome nulo");
        } else if (name.length() > 20) {
            throw new IllegalArgumentException("Role: O nome excede 20 caracteres");
        }

        this.name = name.toLowerCase();
    }

    @Override
    public String toString() {
        return "Role{" + "name=" + name + '}';
    }

}
