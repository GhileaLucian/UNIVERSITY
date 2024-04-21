package ro.ps.cryptoDanutX.service.transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ps.cryptoDanutX.dto.transaction.TransactionRequestDTO;
import ro.ps.cryptoDanutX.dto.transaction.TransactionResponseDTO;
import ro.ps.cryptoDanutX.exception.NotFoundException;
import ro.ps.cryptoDanutX.mapper.TransactionMapper;
import ro.ps.cryptoDanutX.model.Transaction;
import ro.ps.cryptoDanutX.repository.CryptocurrencyRepository;
import ro.ps.cryptoDanutX.repository.CryptoUserRepository;
import ro.ps.cryptoDanutX.repository.TransactionRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TransactionServiceBean implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final CryptoUserRepository cryptoUserRepository;
    private final TransactionMapper transactionMapper;
    @Override
    public TransactionResponseDTO findById(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found with ID: " + id));
        return transactionMapper.toResponseDTO(transaction);
    }
    @Override
    public List<TransactionResponseDTO> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public TransactionResponseDTO save(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = transactionMapper.fromRequestDTO(transactionRequestDTO);
        transaction.setCryptocurrency(cryptocurrencyRepository.findById(transactionRequestDTO.getCryptoId())
                .orElseThrow(() -> new NotFoundException("Cryptocurrency not found with ID: " + transactionRequestDTO.getCryptoId())));
        // Presupunem că există un mecanism de autentificare și putem obține utilizatorul autentificat
        // transaction.setUser(authenticatedUser);
        return transactionMapper.toResponseDTO(transactionRepository.save(transaction));
    }
    @Override
    @Transactional
    public TransactionResponseDTO update(UUID id, TransactionRequestDTO transactionRequestDTO) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found with ID: " + id));
        // Actualizăm câmpurile necesare din tranzacția existentă
        existingTransaction.setAmount(transactionRequestDTO.getAmount());
        existingTransaction.setTransactionType(transactionRequestDTO.getTransactionType());
        existingTransaction.setRecipientWalletAddress(transactionRequestDTO.getRecipientWalletAddress());
        existingTransaction.setCryptocurrency(cryptocurrencyRepository.findById(transactionRequestDTO.getCryptoId())
                .orElseThrow(() -> new NotFoundException("Cryptocurrency not found with ID: " + transactionRequestDTO.getCryptoId())));
        return transactionMapper.toResponseDTO(transactionRepository.save(existingTransaction));
    }
    @Override
    @Transactional
    public void delete(UUID id) {
        if (!transactionRepository.existsById(id)) {
            throw new NotFoundException("Transaction not found with ID: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
