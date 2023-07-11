package nl.rabobank.jpa.repository;

import nl.rabobank.jpa.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, String> {
}
