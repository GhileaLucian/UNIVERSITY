package ro.ps.cryptoDanutX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ps.cryptoDanutX.model.TradingOrder;

import java.util.List;
import java.util.UUID;
@Repository
/**
 * Repository interface for managing TradingOrder entities.
 * Provides methods to interact with the database and perform CRUD operations on TradingOrder entities.
 */
public interface TradingOrderRepository extends JpaRepository<TradingOrder, UUID> {

    /**
     * Finds all trading orders associated with a specific user ID.
     *
     * @param userId The ID of the user.
     * @return A list of trading orders associated with the specified user ID.
     */
    List<TradingOrder> findByUserId(UUID userId);

    /**
     * Finds all trading orders associated with a specific cryptocurrency ID and status.
     *
     * @param cryptocurrencyId The ID of the cryptocurrency.
     * @param status            The status of the trading order.
     * @return A list of trading orders associated with the specified cryptocurrency ID and status.
     */
    List<TradingOrder> findByCryptocurrencyIdAndStatus(UUID cryptocurrencyId, String status);
}
