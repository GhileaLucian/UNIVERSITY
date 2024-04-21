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
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceResponseDTO;
import ro.ps.cryptoDanutX.exception.ExceptionBody;
import ro.ps.cryptoDanutX.service.cryptoBalance.CryptoBalanceService;

/**
 * Controller class for managing cryptocurrency balances.
 * Handles HTTP requests related to creating, updating, deleting, and retrieving cryptocurrency balances.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/crypto-balances")
@RequiredArgsConstructor
public class CryptoBalanceController {

    private final CryptoBalanceService cryptoBalanceService;

    /**
     * Retrieves a cryptocurrency balance by ID.
     *
     * @param balanceId UUID representing the ID of the cryptocurrency balance
     * @return ResponseEntity containing the retrieved CryptoBalanceResponseDTO
     */
    @GetMapping("/{balanceId}")
    @Operation(summary = "Retrieve a crypto balance", description = "Fetches a specific crypto balance by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crypto balance retrieved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CryptoBalanceResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Crypto balance not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<CryptoBalanceResponseDTO> getBalanceById(@PathVariable("balanceId") UUID balanceId) {
        return new ResponseEntity<>(cryptoBalanceService.findById(balanceId), HttpStatus.OK);
    }

    /**
     * Lists all cryptocurrency balances within a specific wallet.
     *
     * @param walletId UUID representing the ID of the wallet
     * @return ResponseEntity containing a list of CryptoBalanceResponseDTOs
     */
    @GetMapping("/wallet/{walletId}")
    @Operation(summary = "List all balances in a wallet", description = "Fetches all crypto balances within a specific wallet")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<CryptoBalanceResponseDTO>> getBalancesByWallet(@PathVariable("walletId") UUID walletId) {
        return new ResponseEntity<>(cryptoBalanceService.findByWalletId(walletId), HttpStatus.OK);
    }

    /**
     * Creates a new cryptocurrency balance entry for a wallet.
     *
     * @param cryptoBalanceRequestDTO CryptoBalanceRequestDTO containing the details of the new balance
     * @return ResponseEntity containing the created CryptoBalanceResponseDTO
     */
    @PostMapping
    @Operation(summary = "Create a new crypto balance", description = "Creates a new crypto balance entry for a wallet")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CryptoBalanceResponseDTO> createBalance(@Valid @RequestBody CryptoBalanceRequestDTO cryptoBalanceRequestDTO) {
        return new ResponseEntity<>(cryptoBalanceService.create(cryptoBalanceRequestDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing cryptocurrency balance.
     *
     * @param balanceId               UUID representing the ID of the balance to be updated
     * @param cryptoBalanceRequestDTO CryptoBalanceRequestDTO containing the updated details of the balance
     * @return ResponseEntity containing the updated CryptoBalanceResponseDTO
     */
    @PutMapping("/{balanceId}")
    @Operation(summary = "Update a crypto balance", description = "Updates an existing crypto balance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CryptoBalanceResponseDTO> updateBalance(@PathVariable("balanceId") UUID balanceId, @Valid @RequestBody CryptoBalanceRequestDTO cryptoBalanceRequestDTO) {
        return new ResponseEntity<>(cryptoBalanceService.update(balanceId, cryptoBalanceRequestDTO), HttpStatus.OK);
    }

    /**
     * Deletes a specific cryptocurrency balance by ID.
     *
     * @param balanceId UUID representing the ID of the balance to be deleted
     * @return ResponseEntity indicating the success of the deletion operation
     */
    @DeleteMapping("/{balanceId}")
    @Operation(summary = "Delete a crypto balance", description = "Deletes a specific crypto balance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBalance(@PathVariable("balanceId") UUID balanceId) {
        cryptoBalanceService.delete(balanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
