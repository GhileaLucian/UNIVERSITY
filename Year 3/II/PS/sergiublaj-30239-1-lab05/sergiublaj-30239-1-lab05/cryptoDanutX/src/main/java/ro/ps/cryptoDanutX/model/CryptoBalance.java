package ro.ps.cryptoDanutX.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * This class represents the balance of a specific cryptocurrency held in a wallet.
 * It links together a Wallet with a Cryptocurrency and specifies the amount held.
 */
@Entity
@Table(name = "CRYPTO_BALANCE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoBalance {

    /**
     * The unique identifier of the crypto balance.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The relationship between the balance and the wallet.
     */
    @ManyToOne
    @JoinColumn(name = "WALLET_ID", nullable = false)
    private Wallet wallet;

    /**
     * The relationship between the balance and the specific cryptocurrency.
     */
    @ManyToOne
    @JoinColumn(name = "CRYPTO_ID", nullable = false)
    private Cryptocurrency cryptocurrency;

    /**
     * The amount of cryptocurrency held in this balance.
     */
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount; // The amount of cryptocurrency held
}
