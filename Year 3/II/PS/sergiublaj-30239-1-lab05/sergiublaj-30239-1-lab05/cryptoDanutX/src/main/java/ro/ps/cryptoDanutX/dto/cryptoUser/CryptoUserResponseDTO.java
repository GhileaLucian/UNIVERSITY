package ro.ps.cryptoDanutX.dto.cryptoUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.cryptoDanutX.dto.wallet.WalletResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the response for CryptoUser.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoUserResponseDTO {
    private UUID id;
    private String username;
    private String email;
    private List<WalletResponseDTO> wallets; // Assume a user can have multiple wallets
    private String preferredCurrencyCode; // Added to reflect the preferred cryptocurrency of the user
}
