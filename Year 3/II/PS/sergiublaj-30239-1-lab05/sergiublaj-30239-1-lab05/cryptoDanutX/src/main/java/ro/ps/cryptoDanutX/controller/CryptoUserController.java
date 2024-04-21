package ro.ps.cryptoDanutX.controller;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.ps.cryptoDanutX.dto.cryptoUser.CryptoUserRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptoUser.CryptoUserResponseDTO;
import ro.ps.cryptoDanutX.dto.CollectionResponseDTO;
import ro.ps.cryptoDanutX.dto.PageRequestDTO;
import ro.ps.cryptoDanutX.exception.ExceptionBody;
import ro.ps.cryptoDanutX.service.cryptoUser.CryptoUserService;

/**
 * Controller class for managing crypto user-related HTTP requests.
 * Handles requests related to creating, updating, deleting, and retrieving crypto users.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/cryptoUser/v1")
@RequiredArgsConstructor
public class CryptoUserController {

    private final CryptoUserService cryptoUserService;

    /**
     * Retrieves a crypto user by ID.
     *
     * @param userId UUID representing the ID of the crypto user
     * @return ResponseEntity containing the retrieved CryptoUserResponseDTO
     */
    @GetMapping("/{id}")
    @Operation(summary = "Gets crypto user by ID", description = "Crypto user must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crypto user found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CryptoUserResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Crypto user not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<CryptoUserResponseDTO> findById(@PathVariable("id") UUID userId) {
        return new ResponseEntity<>(cryptoUserService.findById(userId), HttpStatus.OK);
    }

    /**
     * Retrieves all crypto users.
     *
     * @return ResponseEntity containing a list of CryptoUserResponseDTOs
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<CryptoUserResponseDTO>> findAll() {
        return new ResponseEntity<>(cryptoUserService.findAll(), HttpStatus.OK);
    }

    /**
     * Retrieves all crypto users with pagination.
     *
     * @param page PageRequestDTO containing pagination parameters
     * @return ResponseEntity containing a CollectionResponseDTO of CryptoUserResponseDTOs
     */
    @GetMapping("/all-paged")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CollectionResponseDTO<CryptoUserResponseDTO>> findAllPaged(@Valid PageRequestDTO page) {
        return new ResponseEntity<>(cryptoUserService.findAllPaged(page), HttpStatus.OK);
    }

    /**
     * Saves a new crypto user.
     *
     * @param cryptoUserRequestDTO CryptoUserRequestDTO containing the details of the user to be saved
     * @return ResponseEntity containing the saved CryptoUserResponseDTO
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CryptoUserResponseDTO> saveCryptoUser(@Valid @RequestBody CryptoUserRequestDTO cryptoUserRequestDTO) {
        return new ResponseEntity<>(cryptoUserService.save(cryptoUserRequestDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing crypto user.
     *
     * @param userId              UUID representing the ID of the user to be updated
     * @param cryptoUserRequestDTO CryptoUserRequestDTO containing the updated details of the user
     * @return ResponseEntity containing the updated CryptoUserResponseDTO
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CryptoUserResponseDTO> updateCryptoUser(
            @PathVariable("id") UUID userId,
            @Valid @RequestBody CryptoUserRequestDTO cryptoUserRequestDTO
    ) {
        return new ResponseEntity<>(cryptoUserService.update(userId, cryptoUserRequestDTO), HttpStatus.OK);
    }

    /**
     * Deletes a crypto user by ID.
     *
     * @param userId UUID representing the ID of the user to be deleted
     * @return ResponseEntity indicating the success of the deletion operation
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCryptoUser(@PathVariable("id") UUID userId) {
        cryptoUserService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
