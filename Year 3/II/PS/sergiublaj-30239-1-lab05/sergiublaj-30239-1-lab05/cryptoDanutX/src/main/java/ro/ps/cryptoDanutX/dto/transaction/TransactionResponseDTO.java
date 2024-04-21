package ro.ps.cryptoDanutX.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.cryptoDanutX.dto.cryptocurrency.CryptocurrencyResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
/**
 * Data Transfer Object (DTO) representing the response for Transaction.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private UUID id;
    private BigDecimal amount;
    private String transactionType;
    private LocalDateTime timestamp;
    private CryptocurrencyResponseDTO cryptocurrency; // Utilizarea CryptocurrencyResponseDTO
    private String recipientWalletAddress;
    private UUID userId; // Adăugat pentru a reprezenta ID-ul utilizatorului

}