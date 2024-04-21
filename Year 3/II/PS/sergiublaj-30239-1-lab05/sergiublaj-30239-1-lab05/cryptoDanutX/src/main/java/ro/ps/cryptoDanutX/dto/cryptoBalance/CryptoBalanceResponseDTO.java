package ro.ps.cryptoDanutX.dto.cryptoBalance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the response for CryptoBalance.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoBalanceResponseDTO {
    private UUID id;
    private UUID walletId; // Assumes this already exists to link the balance to a wallet
    private UUID cryptoId; // Make sure this field is added to store the cryptocurrency ID
    private BigDecimal amount;
    // You can include other cryptocurrency details here if needed
}
