package ro.ps.cryptoDanutX.service.wallet;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ps.cryptoDanutX.dto.wallet.WalletRequestDTO;
import ro.ps.cryptoDanutX.dto.wallet.WalletResponseDTO;
import ro.ps.cryptoDanutX.exception.ExceptionCode;
import ro.ps.cryptoDanutX.exception.NotFoundException;
import ro.ps.cryptoDanutX.mapper.WalletMapper;
import ro.ps.cryptoDanutX.model.CryptoBalance;
import ro.ps.cryptoDanutX.model.Cryptocurrency;
import ro.ps.cryptoDanutX.model.Wallet;
import ro.ps.cryptoDanutX.repository.CryptoBalanceRepository;
import ro.ps.cryptoDanutX.repository.CryptocurrencyRepository;
import ro.ps.cryptoDanutX.repository.WalletRepository;
@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceBean implements WalletService {
    private final WalletRepository walletRepository;
    private final CryptoBalanceRepository cryptoBalanceRepository;
    private final CryptocurrencyRepository cryptocurrencyRepository; // Asigurați-vă că acesta este injectat corect
    private final WalletMapper walletMapper;
    @Override
    public WalletResponseDTO findById(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.ERR001_WALLET_NOT_FOUND.getMessage(walletId)));
        return walletMapper.toResponseDTO(wallet);
    }
    @Override
    public List<WalletResponseDTO> findAll() {
        List<Wallet> wallets = walletRepository.findAll();
        return wallets.stream().map(walletMapper::toResponseDTO).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public WalletResponseDTO create(WalletRequestDTO walletRequestDTO) {
        Wallet wallet = walletMapper.fromRequestDTO(walletRequestDTO);
        // Aici puteți adăuga orice logica suplimentară necesară înainte de salvare
        Wallet savedWallet = walletRepository.save(wallet);
        return walletMapper.toResponseDTO(savedWallet);
    }
    @Override
    @Transactional
    public WalletResponseDTO update(UUID walletId, WalletRequestDTO walletRequestDTO) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found for ID: " + walletId));
        wallet.setBalance(walletRequestDTO.getBalance());
        // Nu uitați să actualizați și CryptoBalances dacă este necesar
        Wallet updatedWallet = walletRepository.save(wallet);
        return walletMapper.toResponseDTO(updatedWallet);
    }
    @Override
    @Transactional
    public void delete(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found for ID: " + walletId));
        walletRepository.delete(wallet);
    }
    @Override
    @Transactional
    public WalletResponseDTO transfer(UUID fromWalletId, UUID toWalletId, BigDecimal amount, UUID cryptocurrencyId) {
        Cryptocurrency cryptocurrency = cryptocurrencyRepository.findById(cryptocurrencyId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.ERR002_CRYPTO_NOT_FOUND.getMessage(cryptocurrencyId)));
        Wallet fromWallet = walletRepository.findById(fromWalletId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.ERR003_SOURCE_WALLET_NOT_FOUND.getMessage(fromWalletId)));
        CryptoBalance fromBalance = fromWallet.getCryptoBalances().stream()
                .filter(cb -> cb.getCryptocurrency().equals(cryptocurrency))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ExceptionCode.ERR002_CRYPTO_NOT_FOUND.getMessage(cryptocurrencyId)));
        if (fromBalance.getAmount().compareTo(amount) < 0) {
            throw new IllegalArgumentException(ExceptionCode.ERR005_INSUFFICIENT_FUNDS.getMessage());
        }
        Wallet toWallet = walletRepository.findById(toWalletId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.ERR004_DESTINATION_WALLET_NOT_FOUND.getMessage(toWalletId)));
        CryptoBalance toBalance = toWallet.getCryptoBalances().stream()
                .filter(cb -> cb.getCryptocurrency().equals(cryptocurrency))
                .findFirst()
                .orElseGet(() -> {
                    CryptoBalance newBalance = new CryptoBalance();
                    newBalance.setWallet(toWallet);
                    newBalance.setCryptocurrency(cryptocurrency);
                    newBalance.setAmount(BigDecimal.ZERO);
                    toWallet.getCryptoBalances().add(newBalance);
                    return newBalance;
                });
        fromBalance.setAmount(fromBalance.getAmount().subtract(amount));
        toBalance.setAmount(toBalance.getAmount().add(amount));
        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);
        return walletMapper.toResponseDTO(fromWallet);
    }
}