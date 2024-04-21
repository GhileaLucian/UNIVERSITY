package ro.ps.cryptoDanutX.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class represents a trading order made by a user in the system.
 * It contains information about the order type, status, amount, price, timestamp,
 * as well as the user who placed the order and the cryptocurrency involved.
 */
@Entity
@Table(name = "TRADING_ORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradingOrder {

    /**
     * The unique identifier of the trading order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The type of the trading order (e.g., "LIMIT", "MARKET").
     */
    @Column(name = "ORDER_TYPE", nullable = false)
    private String orderType;

    /**
     * The status of the trading order (e.g., "OPEN", "COMPLETED", "CANCELLED").
     */
    @Column(name = "STATUS", nullable = false)
    private String status;

    /**
     * The amount involved in the trading order.
     */
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    /**
     * The price at which the trading order is placed.
     */
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    /**
     * The timestamp when the trading order is placed.
     */
    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    /**
     * The user who placed the trading order.
     */
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private CryptoUser user;

    /**
     * The cryptocurrency involved in the trading order.
     */
    @ManyToOne
    @JoinColumn(name = "CRYPTO_ID", nullable = false)
    private Cryptocurrency cryptocurrency;
}
