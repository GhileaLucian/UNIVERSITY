package ro.ps.cryptoDanutX.service.cryptoBalance;

import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines the operations that can be performed on crypto balances.
 * It provides methods for retrieving, creating, updating, and deleting crypto balances,
 * as well as finding balances by wallet ID and adjusting balance.
 */
public interface CryptoBalanceService {

    /**
     * Retrieves a crypto balance by its unique identifier.
     *
     * @param id The unique identifier of the crypto balance to retrieve.
     * @return The crypto balance with the specified identifier, or null if not found.
     */
    CryptoBalanceResponseDTO findById(UUID id);

    /**
     * Retrieves all crypto balances associated with a specific wallet.
     *
     * @param walletId The unique identifier of the wallet.
     * @return A list containing all crypto balances associated with the wallet.
     */
    List<CryptoBalanceResponseDTO> findByWalletId(UUID walletId);

    /**
     * Creates a new crypto balance based on the provided data.
     *
     * @param cryptoBalanceRequestDTO The data for the new crypto balance.
     * @return The created crypto balance.
     */
    CryptoBalanceResponseDTO create(CryptoBalanceRequestDTO cryptoBalanceRequestDTO);

    /**
     * Updates an existing crypto balance with the specified identifier.
     *
     * @param id                         The unique identifier of the crypto balance to update.
     * @param cryptoBalanceRequestDTO    The updated data for the crypto balance.
     * @return The updated crypto balance.
     */
    CryptoBalanceResponseDTO update(UUID id, CryptoBalanceRequestDTO cryptoBalanceRequestDTO);

    /**
     * Deletes the crypto balance with the specified identifier.
     *
     * @param id The unique identifier of the crypto balance to delete.
     */
    void delete(UUID id);
}
