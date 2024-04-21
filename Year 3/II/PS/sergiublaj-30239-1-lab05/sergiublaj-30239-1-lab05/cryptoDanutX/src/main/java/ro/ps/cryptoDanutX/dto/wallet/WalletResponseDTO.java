package ro.ps.cryptoDanutX.dto.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceResponseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
/**
 * Data Transfer Object (DTO) representing the response for Wallet.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponseDTO {
    private UUID id;
    private BigDecimal balance;
    private List<CryptoBalanceResponseDTO> cryptoBalances;
}