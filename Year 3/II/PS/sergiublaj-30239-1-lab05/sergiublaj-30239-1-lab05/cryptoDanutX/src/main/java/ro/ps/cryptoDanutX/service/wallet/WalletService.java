package ro.ps.cryptoDanutX.service.wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import ro.ps.cryptoDanutX.dto.wallet.WalletRequestDTO;
import ro.ps.cryptoDanutX.dto.wallet.WalletResponseDTO;

/**
 * This interface defines the operations that can be performed on wallets.
 * It provides methods for retrieving, creating, updating, and deleting wallets,
 * as well as transferring cryptocurrency between wallets.
 */
public interface WalletService {

    /**
     * Retrieves a wallet by its unique identifier.
     *
     * @param walletId The unique identifier of the wallet to retrieve.
     * @return The wallet with the specified identifier, or null if not found.
     */
    WalletResponseDTO findById(UUID walletId);

    /**
     * Retrieves all wallets.
     *
     * @return A list containing all wallets.
     */
    List<WalletResponseDTO> findAll();

    /**
     * Creates a new wallet based on the provided data.
     *
     * @param walletRequestDTO The data for the new wallet.
     * @return The created wallet.
     */
    WalletResponseDTO create(WalletRequestDTO walletRequestDTO);

    /**
     * Updates an existing wallet with the specified identifier.
     *
     * @param walletId          The unique identifier of the wallet to update.
     * @param walletRequestDTO  The updated data for the wallet.
     * @return The updated wallet.
     */
    WalletResponseDTO update(UUID walletId, WalletRequestDTO walletRequestDTO);

    /**
     * Deletes the wallet with the specified identifier.
     *
     * @param walletId The unique identifier of the wallet to delete.
     */
    void delete(UUID walletId);

    /**
     * Transfers cryptocurrency from one wallet to another.
     *
     * @param fromWalletId     The unique identifier of the wallet to transfer cryptocurrency from.
     * @param toWalletId       The unique identifier of the wallet to transfer cryptocurrency to.
     * @param amount           The amount of cryptocurrency to transfer.
     * @param cryptocurrencyId The unique identifier of the cryptocurrency to transfer.
     * @return The updated information of the wallets involved in the transfer.
     */
    WalletResponseDTO transfer(UUID fromWalletId, UUID toWalletId, BigDecimal amount, UUID cryptocurrencyId);
}
