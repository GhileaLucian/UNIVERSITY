package ro.ps.cryptoDanutX.dto.cryptoBalance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the request for CryptoBalance.
 */
@Getter
@Setter
@NoArgsConstructor
public class CryptoBalanceRequestDTO {
    private UUID walletId;
    private UUID cryptoId;
    private BigDecimal amount;
}
