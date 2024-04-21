package ro.ps.cryptoDanutX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ps.cryptoDanutX.model.CryptoUser;
import ro.ps.cryptoDanutX.model.Cryptocurrency;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing CryptoUser entities.
 * Provides methods to interact with the database and perform CRUD operations on CryptoUser entities.
 */
@Repository
public interface CryptoUserRepository extends JpaRepository<CryptoUser, UUID> {

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user.
     * @return An Optional containing the user if found, empty otherwise.
     */
    Optional<CryptoUser> findByUsername(String username);

    /**
     * Finds users by their email address.
     *
     * @param email The email address of the user.
     * @return A list of users with the specified email address.
     */
    List<CryptoUser> findByEmail(String email);

    /**
     * Finds all users who possess a certain cryptocurrency in their wallet.
     *
     * @param cryptocurrency The cryptocurrency to search for.
     * @return A list of users who possess the specified cryptocurrency.
     */
    List<CryptoUser> findAllByCryptocurrency(Cryptocurrency cryptocurrency);

    /**
     * Finds users who possess a certain cryptocurrency and have an amount greater than the specified value.
     *
     * @param cryptocurrency The cryptocurrency to search for.
     * @param amount         The minimum amount of the cryptocurrency.
     * @return A list of users who possess the specified cryptocurrency with an amount greater than the specified value.
     */
    List<CryptoUser> findByCryptocurrencyAndAmountGreaterThan(Cryptocurrency cryptocurrency, double amount);
}
