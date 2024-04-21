package ro.ps.cryptoDanutX.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * This class represents a user in the system who is involved in cryptocurrency management.
 * It contains information about the user, including username, password, email, preferred currency, role, wallet, transactions, and trading orders.
 */
@Entity
@Table(name = "CRYPTO_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoUser {

    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The username of the user.
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * The password of the user.
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * The email of the user.
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * The wallet associated with the user.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WALLET_ID", referencedColumnName = "ID")
    private Wallet wallet;

    /**
     * The preferred cryptocurrency of the user.
     */
    @ManyToOne
    @JoinColumn(name = "PREFERRED_CURRENCY_ID")
    private Cryptocurrency preferredCurrency;

    /**
     * The role of the user.
     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The list of transactions made by the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    /**
     * The list of trading orders placed by the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TradingOrder> tradingOrders;
}
