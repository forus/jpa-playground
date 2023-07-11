package nl.rabobank.jpa.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class BankUser {

    @Id
    @Column(length = 50)
    private String username;

    private String fullName;

    @Column(nullable = false, length = 300)
    private String password;

    @ManyToMany(mappedBy = "owners")
    private Set<BankAccount> account;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<BankAccount> getAccount() {
        return account;
    }

    public void setAccount(Set<BankAccount> account) {
        this.account = account;
    }
}
