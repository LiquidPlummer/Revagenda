package com.revature.revagenda_server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class Auth {
    @Id
    private String username;

    @Column
    private String password;

    public Auth() {
    }

    public Auth(String username, String password) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String plainPassword) {
        this.password = hashPassword(plainPassword);
    }
}
