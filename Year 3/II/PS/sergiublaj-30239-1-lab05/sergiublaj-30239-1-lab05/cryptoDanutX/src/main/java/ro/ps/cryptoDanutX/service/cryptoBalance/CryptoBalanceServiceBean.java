package ro.ps.cryptoDanutX.service.cryptoBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceResponseDTO;
import ro.ps.cryptoDanutX.exception.NotFoundException;
import ro.ps.cryptoDanutX.mapper.CryptoBalanceMapper;
import ro.ps.cryptoDanutX.model.CryptoBalance;
import ro.ps.cryptoDanutX.repository.CryptoBalanceRepository;
import ro.ps.cryptoDanutX.repository.CryptocurrencyRepository;
import ro.ps.cryptoDanutX.repository.WalletRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CryptoBalanceServiceBean implements CryptoBalanceService {
    private final CryptoBalanceRepository cryptoBalanceRepository;
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final WalletRepository walletRepository;
    private final CryptoBalanceMapper cryptoBalanceMapper;
    @Override
    public CryptoBalanceResponseDTO findById(UUID id) {
        CryptoBalance cryptoBalance = cryptoBalanceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CryptoBalance not found for ID: " + id));
        return cryptoBalanceMapper.toResponseDTO(cryptoBalance);
    }
    @Override
    public List<CryptoBalanceResponseDTO> findByWalletId(UUID walletId) {
        List<CryptoBalance> cryptoBalances = cryptoBalanceRepository.findByWalletId(walletId);
        return cryptoBalances.stream().map(cryptoBalanceMapper::toResponseDTO).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public CryptoBalanceResponseDTO create(CryptoBalanceRequestDTO cryptoBalanceRequestDTO) {
        CryptoBalance cryptoBalance = cryptoBalanceMapper.fromRequestDTO(cryptoBalanceRequestDTO);
        cryptoBalance.setCryptocurrency(cryptocurrencyRepository.findById(cryptoBalanceRequestDTO.getCryptoId())
                .orElseThrow(() -> new NotFoundException("Cryptocurrency not found for ID: " + cryptoBalanceRequestDTO.getCryptoId())));
        cryptoBalance.setWallet(walletRepository.findById(cryptoBalanceRequestDTO.getWalletId())
                .orElseThrow(() -> new NotFoundException("Wallet not found for ID: " + cryptoBalanceRequestDTO.getWalletId())));
        CryptoBalance savedCryptoBalance = cryptoBalanceRepository.save(cryptoBalance);
        return cryptoBalanceMapper.toResponseDTO(savedCryptoBalance);
    }
    @Override
    @Transactional
    public CryptoBalanceResponseDTO update(UUID id, CryptoBalanceRequestDTO cryptoBalanceRequestDTO) {
        CryptoBalance existingCryptoBalance = cryptoBalanceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CryptoBalance not found for ID: " + id));
        existingCryptoBalance.setAmount(cryptoBalanceRequestDTO.getAmount());
        // Update cryptocurrency and wallet if necessary
        CryptoBalance updatedCryptoBalance = cryptoBalanceRepository.save(existingCryptoBalance);
        return cryptoBalanceMapper.toResponseDTO(updatedCryptoBalance);
    }
    @Override
    @Transactional
    public void delete(UUID id) {
        CryptoBalance cryptoBalance = cryptoBalanceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CryptoBalance not found for ID: " + id));
        cryptoBalanceRepository.delete(cryptoBalance);
    }
//    @Override
//    @Transactional
//    public void adjustBalance(CryptoBalanceAdjustmentDTO adjustmentDTO) {
//        // Verifică existența wallet-ului și a criptomonedei
//        walletRepository.findById(adjustmentDTO.getWalletId())
//                .orElseThrow(() -> new NotFoundException("Wallet not found for ID: " + adjustmentDTO.getWalletId()));
//        cryptocurrencyRepository.findById(adjustmentDTO.getCryptoId())
//                .orElseThrow(() -> new NotFoundException("Cryptocurrency not found for ID: " + adjustmentDTO.getCryptoId()));
//        // Caută balanța existentă sau creează una nouă
//        Optional<CryptoBalance> existingBalance = cryptoBalanceRepository
//                .findByWalletIdAndCryptocurrencyId(adjustmentDTO.getWalletId(), adjustmentDTO.getCryptoId());
//        CryptoBalance cryptoBalance = existingBalance.orElseGet(() -> new CryptoBalance());
//        if (!existingBalance.isPresent()) {
//            // Setează wallet-ul și criptomoneda pentru o nouă balanță
//            cryptoBalance.setWallet(walletRepository.getOne(adjustmentDTO.getWalletId()));
//            cryptoBalance.setCryptocurrency(cryptocurrencyRepository.getOne(adjustmentDTO.getCryptoId()));
//            cryptoBalance.setAmount(BigDecimal.ZERO);
//        }
//        // Ajustează balanța și salvează
//        cryptoBalance.setAmount(cryptoBalance.getAmount().add(adjustmentDTO.getAdjustmentAmount()));
//        cryptoBalanceRepository.save(cryptoBalance);
}