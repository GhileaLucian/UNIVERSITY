package ro.ps.cryptoDanutX.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.ps.cryptoDanutX.dto.tradingOrders.TradingOrderRequestDTO;
import ro.ps.cryptoDanutX.dto.tradingOrders.TradingOrderResponseDTO;
import ro.ps.cryptoDanutX.service.tradingOrder.TradingOrderService;

/**
 * Controller class for managing trading order-related HTTP requests.
 * Handles requests related to creating, updating, canceling, and retrieving trading orders.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/trading-orders")
@RequiredArgsConstructor
public class TradingOrderController {

    private final TradingOrderService tradingOrderService;

    /**
     * Creates a new trading order.
     *
     * @param orderRequestDTO TradingOrderRequestDTO containing the details of the order to be created
     * @return ResponseEntity containing the created TradingOrderResponseDTO
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TradingOrderResponseDTO> createOrder(@Valid @RequestBody TradingOrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(tradingOrderService.createOrder(orderRequestDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing trading order.
     *
     * @param orderId          UUID representing the ID of the order to be updated
     * @param orderRequestDTO TradingOrderRequestDTO containing the updated details of the order
     * @return ResponseEntity containing the updated TradingOrderResponseDTO
     */
    @PutMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TradingOrderResponseDTO> updateOrder(@PathVariable UUID orderId, @Valid @RequestBody TradingOrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(tradingOrderService.updateOrder(orderId, orderRequestDTO), HttpStatus.OK);
    }

    /**
     * Cancels a trading order.
     *
     * @param orderId UUID representing the ID of the order to be canceled
     * @return ResponseEntity indicating the success of the cancellation operation
     */
    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId) {
        tradingOrderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves all trading orders associated with a specific user.
     *
     * @param userId UUID representing the ID of the user
     * @return ResponseEntity containing a list of TradingOrderResponseDTOs
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TradingOrderResponseDTO>> findAllOrdersByUserId(@PathVariable UUID userId) {
        return new ResponseEntity<>(tradingOrderService.findAllOrdersByUserId(userId), HttpStatus.OK);
    }
}
