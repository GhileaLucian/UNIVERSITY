package ro.ps.cryptoDanutX.dto.cryptoUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing the request for CryptoUser.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoUserRequestDTO {
    private String username;
    private String password; // Note: In practice, a hashing mechanism should be used for password
    private String email;
    private String preferredCurrencyCode; // The code of the preferred cryptocurrency for the user
}
