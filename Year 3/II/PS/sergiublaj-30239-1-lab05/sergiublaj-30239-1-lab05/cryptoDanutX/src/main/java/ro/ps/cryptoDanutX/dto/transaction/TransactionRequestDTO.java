package ro.ps.cryptoDanutX.dto.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
/**
 * Data Transfer Object (DTO) representing the request for Transaction.
 */
@Getter
@Setter
@NoArgsConstructor
public class TransactionRequestDTO {
    private BigDecimal amount;
    private String transactionType;
    private UUID cryptoId;
    private String recipientWalletAddress;
}
