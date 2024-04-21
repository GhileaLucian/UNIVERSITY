package ro.ps.cryptoDanutX.service.tradingOrder;

import ro.ps.cryptoDanutX.dto.tradingOrders.TradingOrderRequestDTO;
import ro.ps.cryptoDanutX.dto.tradingOrders.TradingOrderResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines the operations that can be performed on trading orders.
 * It provides methods for creating, updating, canceling, and retrieving trading orders,
 * as well as executing pending orders.
 */
public interface TradingOrderService {

    /**
     * Creates a new trading order based on the provided data.
     *
     * @param orderRequestDTO The data for the new trading order.
     * @return The created trading order.
     */
    TradingOrderResponseDTO createOrder(TradingOrderRequestDTO orderRequestDTO);

    /**
     * Updates an existing trading order with the specified identifier.
     *
     * @param orderId          The unique identifier of the trading order to update.
     * @param orderRequestDTO  The updated data for the trading order.
     * @return The updated trading order.
     */
    TradingOrderResponseDTO updateOrder(UUID orderId, TradingOrderRequestDTO orderRequestDTO);

    /**
     * Cancels the trading order with the specified identifier.
     *
     * @param orderId The unique identifier of the trading order to cancel.
     */
    void cancelOrder(UUID orderId);

    /**
     * Retrieves all trading orders associated with a specific user.
     *
     * @param userId The unique identifier of the user.
     * @return A list containing all trading orders associated with the user.
     */
    List<TradingOrderResponseDTO> findAllOrdersByUserId(UUID userId);

    /**
     * Executes pending trading orders.
     * This method is responsible for processing pending orders and executing them if conditions are met.
     */
    void executePendingOrders();
}
