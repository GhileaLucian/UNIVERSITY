package ro.ps.cryptoDanutX.dto.cryptocurrency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) representing the request for Cryptocurrency.
 */
@Getter
@Setter
@NoArgsConstructor
public class CryptocurrencyRequestDTO {
    private String name;
    private String symbol;
    private BigDecimal currentPrice;
}
