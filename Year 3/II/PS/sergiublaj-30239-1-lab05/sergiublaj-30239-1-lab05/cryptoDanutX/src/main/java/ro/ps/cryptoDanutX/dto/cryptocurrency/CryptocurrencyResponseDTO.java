package ro.ps.cryptoDanutX.dto.cryptocurrency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the response for Cryptocurrency.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptocurrencyResponseDTO {
    private UUID id;
    private String name;
    private String symbol;
    private BigDecimal currentPrice;
}
