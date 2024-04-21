package ro.ps.cryptoDanutX.service.transaction;

import ro.ps.cryptoDanutX.dto.transaction.TransactionRequestDTO;
import ro.ps.cryptoDanutX.dto.transaction.TransactionResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines the operations that can be performed on transactions.
 * It provides methods for retrieving, creating, updating, and deleting transactions.
 */
public interface TransactionService {

    /**
     * Retrieves a transaction by its unique identifier.
     *
     * @param id The unique identifier of the transaction to retrieve.
     * @return The transaction with the specified identifier, or null if not found.
     */
    TransactionResponseDTO findById(UUID id);

    /**
     * Retrieves all transactions.
     *
     * @return A list containing all transactions.
     */
    List<TransactionResponseDTO> findAll();

    /**
     * Saves a new transaction based on the provided data.
     *
     * @param transactionRequestDTO The data for the new transaction.
     * @return The created transaction.
     */
    TransactionResponseDTO save(TransactionRequestDTO transactionRequestDTO);

    /**
     * Updates an existing transaction with the specified identifier.
     *
     * @param id                     The unique identifier of the transaction to update.
     * @param transactionRequestDTO  The updated data for the transaction.
     * @return The updated transaction.
     */
    TransactionResponseDTO update(UUID id, TransactionRequestDTO transactionRequestDTO);

    /**
     * Deletes the transaction with the specified identifier.
     *
     * @param id The unique identifier of the transaction to delete.
     */
    void delete(UUID id);
}
