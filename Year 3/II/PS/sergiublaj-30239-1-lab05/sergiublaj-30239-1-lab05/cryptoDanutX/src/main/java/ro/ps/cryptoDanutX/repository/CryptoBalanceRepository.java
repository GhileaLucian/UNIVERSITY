package ro.ps.cryptoDanutX.repository;

import org.springframework.stereotype.Repository;
import ro.ps.cryptoDanutX.model.CryptoBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
/**
 * Repository interface for managing CryptoBalance entities.
 * Provides methods to interact with the database and perform CRUD operations on CryptoBalance entities.
 */
@Repository
public interface CryptoBalanceRepository extends JpaRepository<CryptoBalance, UUID> {

    /**
     * Finds all crypto balances associated with a specific wallet ID.
     *
     * @param walletId The ID of the wallet.
     * @return A list of crypto balances associated with the specified wallet ID.
     */
    List<CryptoBalance> findByWalletId(UUID walletId);

    /**
     * Finds all crypto balances associated with a specific cryptocurrency ID.
     *
     * @param cryptocurrencyId The ID of the cryptocurrency.
     * @return A list of crypto balances associated with the specified cryptocurrency ID.
     */
    List<CryptoBalance> findByCryptocurrencyId(UUID cryptocurrencyId);
}
