package ro.ps.cryptoDanutX.service.cryptocurrency;

import ro.ps.cryptoDanutX.dto.cryptocurrency.CryptocurrencyRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptocurrency.CryptocurrencyResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines the operations that can be performed on cryptocurrencies.
 * It provides methods for retrieving, creating, updating, and deleting cryptocurrencies.
 */
public interface CryptocurrencyService {

    /**
     * Retrieves a cryptocurrency by its unique identifier.
     *
     * @param id The unique identifier of the cryptocurrency to retrieve.
     * @return The cryptocurrency with the specified identifier, or null if not found.
     */
    CryptocurrencyResponseDTO findById(UUID id);

    /**
     * Retrieves all cryptocurrencies.
     *
     * @return A list containing all cryptocurrencies.
     */
    List<CryptocurrencyResponseDTO> findAll();

    /**
     * Saves a new cryptocurrency based on the provided data.
     *
     * @param cryptocurrencyRequestDTO The data for the new cryptocurrency.
     * @return The created cryptocurrency.
     */
    CryptocurrencyResponseDTO save(CryptocurrencyRequestDTO cryptocurrencyRequestDTO);

    /**
     * Updates an existing cryptocurrency with the specified identifier.
     *
     * @param id                         The unique identifier of the cryptocurrency to update.
     * @param cryptocurrencyRequestDTO  The updated data for the cryptocurrency.
     * @return The updated cryptocurrency.
     */
    CryptocurrencyResponseDTO update(UUID id, CryptocurrencyRequestDTO cryptocurrencyRequestDTO);

    /**
     * Deletes the cryptocurrency with the specified identifier.
     *
     * @param id The unique identifier of the cryptocurrency to delete.
     */
    void delete(UUID id);
}
