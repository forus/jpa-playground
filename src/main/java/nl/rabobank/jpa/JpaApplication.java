package nl.rabobank.jpa;

import jakarta.transaction.Transactional;
import nl.rabobank.jpa.model.BankAccount;
import nl.rabobank.jpa.model.BankTransaction;
import nl.rabobank.jpa.model.BankUser;
import nl.rabobank.jpa.repository.BankAccountRepository;
import nl.rabobank.jpa.repository.BankTransactionRepository;
import nl.rabobank.jpa.repository.BankUserRepository;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JpaApplication {


	private BankUserRepository bankUserRepository;
	private BankAccountRepository bankAccountRepository;
	private BankTransactionRepository bankTransactionRepository;
	public JpaApplication(BankUserRepository bankUserRepository, BankAccountRepository bankAccountRepository, BankTransactionRepository bankTransactionRepository) {
		this.bankUserRepository = bankUserRepository;
		this.bankAccountRepository = bankAccountRepository;
		this.bankTransactionRepository = bankTransactionRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void doAfterApplicationReady() {
		BankUser bob = new BankUser();
		bob.setUsername("bob");
		bob.setPassword("secret123");
		bob.setFullName("Bob Johnson");

		BankAccount bankAccount = new BankAccount();
		bankAccount.setIban("NL09RABO34534645");


		Set<BankUser> owners = new HashSet<>();
		owners.add(bob);
		bankAccount.setOwners(owners);

		bankAccountRepository.save(bankAccount);
	}
}
