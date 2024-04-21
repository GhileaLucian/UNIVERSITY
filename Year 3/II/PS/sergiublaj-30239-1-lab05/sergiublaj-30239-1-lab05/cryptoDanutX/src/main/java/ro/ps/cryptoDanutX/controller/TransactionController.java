package ro.ps.cryptoDanutX.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.ps.cryptoDanutX.dto.transaction.TransactionRequestDTO;
import ro.ps.cryptoDanutX.dto.transaction.TransactionResponseDTO;
import ro.ps.cryptoDanutX.service.transaction.TransactionService;

/**
 * Controller class for managing transaction-related HTTP requests.
 * Handles requests related to creating, retrieving, updating, and deleting transactions.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Creates a new transaction.
     *
     * @param transactionRequestDTO TransactionRequestDTO containing the details of the transaction to be created
     * @return ResponseEntity containing the created TransactionResponseDTO
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        return new ResponseEntity<>(transactionService.save(transactionRequestDTO), HttpStatus.CREATED);
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transactionId UUID representing the ID of the transaction
     * @return ResponseEntity containing the retrieved TransactionResponseDTO
     */
    @GetMapping("/{transactionId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable UUID transactionId) {
        return new ResponseEntity<>(transactionService.findById(transactionId), HttpStatus.OK);
    }

    /**
     * Retrieves all transactions.
     *
     * @return ResponseEntity containing a list of TransactionResponseDTOs
     */
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

    /**
     * Updates an existing transaction.
     *
     * @param transactionId         UUID representing the ID of the transaction to be updated
     * @param transactionRequestDTO TransactionRequestDTO containing the updated details of the transaction
     * @return ResponseEntity containing the updated TransactionResponseDTO
     */
    @PutMapping("/{transactionId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable UUID transactionId, @Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        return new ResponseEntity<>(transactionService.update(transactionId, transactionRequestDTO), HttpStatus.OK);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param transactionId UUID representing the ID of the transaction to be deleted
     * @return ResponseEntity indicating the success of the deletion operation
     */
    @DeleteMapping("/{transactionId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID transactionId) {
        transactionService.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
