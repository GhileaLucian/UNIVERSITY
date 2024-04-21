package ro.ps.cryptoDanutX.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * This class represents a specific cryptocurrency, such as Bitcoin, Ethereum, etc.,
 * and contains basic information about it.
 */
@Entity
@Table(name = "CRYPTOCURRENCY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cryptocurrency {

    /**
     * The unique identifier of the cryptocurrency.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The name of the cryptocurrency.
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * The symbol of the cryptocurrency.
     */
    @Column(name = "SYMBOL", nullable = false)
    private String symbol;

    /**
     * The current price of the cryptocurrency.
     */
    @Column(name = "CURRENT_PRICE", nullable = false)
    private BigDecimal currentPrice;
}
