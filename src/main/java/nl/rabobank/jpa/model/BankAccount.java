package nl.rabobank.jpa.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private Long id;

    private String iban;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<BankUser> owners;

    @OneToMany(mappedBy = "sender")
    private List<BankTransaction> outgoingTransactions;

    @OneToMany(mappedBy = "receiver")
    private List<BankTransaction> incomingTransactions;

    @Transient
    public List<BankTransaction> getAllTransactions() {
        ArrayList<BankTransaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(outgoingTransactions);
        allTransactions.addAll(incomingTransactions);
        allTransactions.sort(Comparator.comparing(BankTransaction::getTimestamp));
        return allTransactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Set<BankUser> getOwners() {
        return owners;
    }

    public void setOwners(Set<BankUser> owners) {
        this.owners = owners;
    }

    public List<BankTransaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public void setOutgoingTransactions(List<BankTransaction> outgoingTransactions) {
        this.outgoingTransactions = outgoingTransactions;
    }

    public List<BankTransaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<BankTransaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }
}
