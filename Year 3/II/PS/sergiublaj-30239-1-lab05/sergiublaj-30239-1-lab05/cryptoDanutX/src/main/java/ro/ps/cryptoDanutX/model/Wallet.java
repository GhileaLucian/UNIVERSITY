package ro.ps.cryptoDanutX.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * This class represents a wallet that holds cryptocurrency balances.
 * It contains information about the wallet's balance, as well as relationships
 * with the cryptocurrency balances it holds and the user it belongs to.
 */
@Entity
@Table(name = "WALLET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    /**
     * The unique identifier of the wallet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The balance of the wallet.
     */
    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    /**
     * The list of cryptocurrency balances held in the wallet.
     */
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<CryptoBalance> cryptoBalances;

    /**
     * The user to whom the wallet belongs.
     */
    @OneToOne(mappedBy = "wallet")
    private CryptoUser user;
}
