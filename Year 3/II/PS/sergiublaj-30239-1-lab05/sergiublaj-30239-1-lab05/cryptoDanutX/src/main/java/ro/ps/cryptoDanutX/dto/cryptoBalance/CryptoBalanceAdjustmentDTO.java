package ro.ps.cryptoDanutX.dto.cryptoBalance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the adjustment for CryptoBalance.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoBalanceAdjustmentDTO {
    private UUID walletId;
    private UUID cryptoId;
    private BigDecimal adjustmentAmount; // Can be positive (deposit) or negative (withdrawal)
}
