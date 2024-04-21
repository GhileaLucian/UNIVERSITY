package ro.ps.cryptoDanutX.repository;

import org.springframework.stereotype.Repository;
import ro.ps.cryptoDanutX.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
/**
 * Repository interface for managing Transaction entities.
 * Provides methods to interact with the database and perform CRUD operations on Transaction entities.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    /**
     * Finds all transactions associated with a specific user ID.
     *
     * @param userId The ID of the user.
     * @return A list of transactions associated with the specified user ID.
     */
    List<Transaction> findByUserId(UUID userId);

    /**
     * Finds all transactions associated with a specific cryptocurrency ID.
     *
     * @param cryptocurrencyId The ID of the cryptocurrency.
     * @return A list of transactions associated with the specified cryptocurrency ID.
     */
    List<Transaction> findByCryptocurrencyId(UUID cryptocurrencyId);
}
