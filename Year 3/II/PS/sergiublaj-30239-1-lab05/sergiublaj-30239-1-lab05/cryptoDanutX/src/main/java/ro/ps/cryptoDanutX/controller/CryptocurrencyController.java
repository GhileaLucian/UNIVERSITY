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
import ro.ps.cryptoDanutX.dto.cryptocurrency.CryptocurrencyRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptocurrency.CryptocurrencyResponseDTO;
import ro.ps.cryptoDanutX.exception.ExceptionBody;
import ro.ps.cryptoDanutX.service.cryptocurrency.CryptocurrencyService;

/**
 * Controller class for managing cryptocurrency-related HTTP requests.
 * Handles requests related to creating, updating, deleting, and retrieving cryptocurrencies.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cryptocurrencies")
@RequiredArgsConstructor
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;

    /**
     * Retrieves cryptocurrency details by ID.
     *
     * @param id UUID representing the ID of the cryptocurrency
     * @return ResponseEntity containing the retrieved CryptocurrencyResponseDTO
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve Cryptocurrency Details", description = "Fetches details of a specific cryptocurrency by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cryptocurrency found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CryptocurrencyResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Cryptocurrency not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<CryptocurrencyResponseDTO> getCryptocurrencyById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(cryptocurrencyService.findById(id), HttpStatus.OK);
    }

    /**
     * Retrieves all cryptocurrencies.
     *
     * @return ResponseEntity containing a list of CryptocurrencyResponseDTOs
     */
    @GetMapping
    @Operation(summary = "List All Cryptocurrencies", description = "Lists all available cryptocurrencies")
    public ResponseEntity<List<CryptocurrencyResponseDTO>> getAllCryptocurrencies() {
        return new ResponseEntity<>(cryptocurrencyService.findAll(), HttpStatus.OK);
    }

    /**
     * Creates a new cryptocurrency entry.
     *
     * @param cryptocurrencyRequestDTO CryptocurrencyRequestDTO containing the details of the cryptocurrency to be created
     * @return ResponseEntity containing the created CryptocurrencyResponseDTO
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a New Cryptocurrency", description = "Creates a new cryptocurrency entry")
    public ResponseEntity<CryptocurrencyResponseDTO> createCryptocurrency(@Valid @RequestBody CryptocurrencyRequestDTO cryptocurrencyRequestDTO) {
        return new ResponseEntity<>(cryptocurrencyService.save(cryptocurrencyRequestDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing cryptocurrency.
     *
     * @param id                      UUID representing the ID of the cryptocurrency to be updated
     * @param cryptocurrencyRequestDTO CryptocurrencyRequestDTO containing the updated details of the cryptocurrency
     * @return ResponseEntity containing the updated CryptocurrencyResponseDTO
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update Cryptocurrency", description = "Updates an existing cryptocurrency")
    public ResponseEntity<CryptocurrencyResponseDTO> updateCryptocurrency(@PathVariable("id") UUID id, @Valid @RequestBody CryptocurrencyRequestDTO cryptocurrencyRequestDTO) {
        return new ResponseEntity<>(cryptocurrencyService.update(id, cryptocurrencyRequestDTO), HttpStatus.OK);
    }

    /**
     * Deletes a specific cryptocurrency by ID.
     *
     * @param id UUID representing the ID of the cryptocurrency to be deleted
     * @return ResponseEntity indicating the success of the deletion operation
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Cryptocurrency", description = "Deletes a specific cryptocurrency")
    public ResponseEntity<Void> deleteCryptocurrency(@PathVariable("id") UUID id) {
        cryptocurrencyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
