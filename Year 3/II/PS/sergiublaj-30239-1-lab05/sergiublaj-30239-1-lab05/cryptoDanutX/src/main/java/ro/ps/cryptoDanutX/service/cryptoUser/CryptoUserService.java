package ro.ps.cryptoDanutX.service.cryptoUser;

import java.util.List;
import java.util.UUID;
import ro.ps.cryptoDanutX.dto.cryptoUser.CryptoUserRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptoUser.CryptoUserResponseDTO;
import ro.ps.cryptoDanutX.dto.CollectionResponseDTO;
import ro.ps.cryptoDanutX.dto.PageRequestDTO;

/**
 * This interface defines the operations that can be performed on crypto users.
 * It provides methods for retrieving, creating, updating, and deleting crypto users,
 * as well as retrieving crypto users in a paged manner.
 */
public interface CryptoUserService {

    /**
     * Retrieves a crypto user by their unique identifier.
     *
     * @param userId The unique identifier of the crypto user to retrieve.
     * @return The crypto user with the specified identifier, or null if not found.
     */
    CryptoUserResponseDTO findById(UUID userId);

    /**
     * Retrieves all crypto users.
     *
     * @return A list containing all crypto users.
     */
    List<CryptoUserResponseDTO> findAll();

    /**
     * Retrieves a paged list of crypto users.
     *
     * @param page The page request information.
     * @return A collection response containing the requested page of crypto users.
     */
    CollectionResponseDTO<CryptoUserResponseDTO> findAllPaged(PageRequestDTO page);

    /**
     * Saves a new crypto user based on the provided data.
     *
     * @param userRequestDTO The data for the new crypto user.
     * @return The created crypto user.
     */
    CryptoUserResponseDTO save(CryptoUserRequestDTO userRequestDTO);

    /**
     * Updates an existing crypto user with the specified identifier.
     *
     * @param userId         The unique identifier of the crypto user to update.
     * @param userRequestDTO The updated data for the crypto user.
     * @return The updated crypto user.
     */
    CryptoUserResponseDTO update(UUID userId, CryptoUserRequestDTO userRequestDTO);

    /**
     * Deletes the crypto user with the specified identifier.
     *
     * @param userId The unique identifier of the crypto user to delete.
     */
    void delete(UUID userId);
}
