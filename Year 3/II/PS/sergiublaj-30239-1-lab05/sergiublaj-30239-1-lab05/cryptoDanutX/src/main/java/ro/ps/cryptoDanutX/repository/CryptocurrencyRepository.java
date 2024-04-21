package ro.ps.cryptoDanutX.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ps.cryptoDanutX.model.Cryptocurrency;

import java.util.UUID;
/**
 * Repository interface for managing Cryptocurrency entities.
 * Provides methods to interact with the database and perform CRUD operations on Cryptocurrency entities.
 */
@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, UUID> {
}
