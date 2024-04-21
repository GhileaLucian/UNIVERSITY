package ro.ps.cryptoDanutX.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ps.cryptoDanutX.dto.wallet.WalletRequestDTO;
import ro.ps.cryptoDanutX.dto.wallet.WalletResponseDTO;
import ro.ps.cryptoDanutX.service.wallet.WalletService;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing wallet-related HTTP requests.
 * Handles requests related to creating, retrieving, updating, and deleting wallets.
 */
@RestController
@RequestMapping("/api/crypto/wallets")
public class WalletController {

    private final WalletService walletService;

    /**
     * Constructs a new WalletController with the given WalletService.
     *
     * @param walletService WalletService instance for managing wallet operations
     */
    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    /**
     * Retrieves a wallet by its ID.
     *
     * @param walletId UUID representing the ID of the wallet
     * @return ResponseEntity containing the retrieved WalletResponseDTO
     */
    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponseDTO> getWalletById(@PathVariable UUID walletId) {
        WalletResponseDTO walletResponseDTO = walletService.findById(walletId);
        return ResponseEntity.ok(walletResponseDTO);
    }

    /**
     * Retrieves all wallets.
     *
     * @return ResponseEntity containing a list of WalletResponseDTOs
     */
    @GetMapping
    public ResponseEntity<List<WalletResponseDTO>> getAllWallets() {
        List<WalletResponseDTO> wallets = walletService.findAll();
        return ResponseEntity.ok(wallets);
    }

    /**
     * Creates a new wallet.
     *
     * @param walletRequestDTO WalletRequestDTO containing the details of the wallet to be created
     * @return ResponseEntity containing the created WalletResponseDTO
     */
    @PostMapping
    public ResponseEntity<WalletResponseDTO> createWallet(@Valid @RequestBody WalletRequestDTO walletRequestDTO) {
        WalletResponseDTO createdWallet = walletService.create(walletRequestDTO);
        return ResponseEntity.ok(createdWallet);
    }

    /**
     * Updates an existing wallet.
     *
     * @param walletId         UUID representing the ID of the wallet to be updated
     * @param walletRequestDTO WalletRequestDTO containing the updated details of the wallet
     * @return ResponseEntity containing the updated WalletResponseDTO
     */
    @PutMapping("/{walletId}")
    public ResponseEntity<WalletResponseDTO> updateWallet(@PathVariable UUID walletId, @Valid @RequestBody WalletRequestDTO walletRequestDTO) {
        WalletResponseDTO updatedWallet = walletService.update(walletId, walletRequestDTO);
        return ResponseEntity.ok(updatedWallet);
    }

    /**
     * Deletes a wallet by its ID.
     *
     * @param walletId UUID representing the ID of the wallet to be deleted
     * @return ResponseEntity indicating the success of the deletion operation
     */
    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable UUID walletId) {
        walletService.delete(walletId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Transfers funds between two wallets.
     *
     * @param fromWalletId     UUID representing the ID of the source wallet
     * @param toWalletId       UUID representing the ID of the destination wallet
     * @param amount           BigDecimal representing the amount to be transferred
     * @param cryptocurrencyId UUID representing the ID of the cryptocurrency
     * @return ResponseEntity containing the updated WalletResponseDTO
     */
    @PostMapping("/transfer")
    public ResponseEntity<WalletResponseDTO> transferFunds(
            @RequestParam UUID fromWalletId,
            @RequestParam UUID toWalletId,
            @RequestParam BigDecimal amount,
            @RequestParam UUID cryptocurrencyId) {
        WalletResponseDTO result = walletService.transfer(fromWalletId, toWalletId, amount, cryptocurrencyId);
        return ResponseEntity.ok(result);
    }
}
