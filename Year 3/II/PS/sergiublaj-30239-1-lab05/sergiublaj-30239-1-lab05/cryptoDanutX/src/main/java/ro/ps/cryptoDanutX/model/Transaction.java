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
 * This class represents a transaction made by a user in the system.
 * It contains information about the amount, transaction type, timestamp,
 * as well as the user who made the transaction, the cryptocurrency involved,
 * and the recipient wallet address (if applicable).
 */
@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    /**
     * The unique identifier of the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The amount involved in the transaction.
     */
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    /**
     * The type of the transaction (e.g., "BUY", "SELL").
     */
    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType;

    /**
     * The timestamp when the transaction is made.
     */
    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    /**
     * The user who made the transaction.
     */
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private CryptoUser user;

    /**
     * The cryptocurrency involved in the transaction.
     */
    @ManyToOne
    @JoinColumn(name = "CRYPTO_ID", nullable = false)
    private Cryptocurrency cryptocurrency;

    /**
     * The recipient wallet address for transactions (if applicable).
     */
    @Column(name = "RECIPIENT_WALLET_ADDRESS")
    private String recipientWalletAddress;
}
