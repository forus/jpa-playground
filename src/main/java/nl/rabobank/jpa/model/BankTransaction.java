package nl.rabobank.jpa.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BankTransaction {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_bank_account_id")
    private BankAccount sender;

    @ManyToOne
    @JoinColumn(name = "receiver_bank_account_id")
    private BankAccount receiver;

    private Date timestamp = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount getSender() {
        return sender;
    }

    public void setSender(BankAccount sender) {
        this.sender = sender;
    }

    public BankAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(BankAccount receiver) {
        this.receiver = receiver;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
