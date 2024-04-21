package ro.ps.cryptoDanutX.dto.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceRequestDTO;

import java.math.BigDecimal;
import java.util.List;
/**
 * Data Transfer Object (DTO) representing the request for Wallet.
 */
@Getter
@Setter
@NoArgsConstructor
public class WalletRequestDTO {
    private BigDecimal balance;
    private List<CryptoBalanceRequestDTO> cryptoBalances;
}
