package ro.ps.cryptoDanutX.repository;

import org.springframework.stereotype.Repository;
import ro.ps.cryptoDanutX.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;/**
 * Repository interface for managing Wallet entities.
 * Provides methods to interact with the database and perform CRUD operations on Wallet entities.
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    /**
     * Finds a wallet by its ID.
     *
     * @param userId The ID of the wallet.
     * @return An Optional containing the wallet if found, empty otherwise.
     */
    Optional<Wallet> findById(UUID userId);
}
