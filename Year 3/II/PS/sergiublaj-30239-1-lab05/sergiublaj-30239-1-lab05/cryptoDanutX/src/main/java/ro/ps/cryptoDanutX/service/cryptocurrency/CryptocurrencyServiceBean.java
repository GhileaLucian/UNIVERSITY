package ro.ps.cryptoDanutX.service.cryptocurrency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ps.cryptoDanutX.dto.cryptocurrency.CryptocurrencyRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptocurrency.CryptocurrencyResponseDTO;
import ro.ps.cryptoDanutX.exception.ExceptionCode;
import ro.ps.cryptoDanutX.exception.NotFoundException;
import ro.ps.cryptoDanutX.mapper.CryptocurrencyMapper;
import ro.ps.cryptoDanutX.model.Cryptocurrency;
import ro.ps.cryptoDanutX.repository.CryptocurrencyRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class CryptocurrencyServiceBean implements CryptocurrencyService {
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final CryptocurrencyMapper cryptocurrencyMapper;
    @Override
    public CryptocurrencyResponseDTO findById(UUID id) {
        return cryptocurrencyRepository.findById(id)
                .map(cryptocurrencyMapper::toResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR002_CRYPTO_NOT_FOUND.getMessage(),
                        id
                )));
    }
    @Override
    public List<CryptocurrencyResponseDTO> findAll() {
        return cryptocurrencyRepository.findAll().stream()
                .map(cryptocurrencyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public CryptocurrencyResponseDTO save(CryptocurrencyRequestDTO cryptocurrencyRequestDTO) {
        Cryptocurrency cryptocurrency = cryptocurrencyMapper.fromRequestDTO(cryptocurrencyRequestDTO);
        return cryptocurrencyMapper.toResponseDTO(cryptocurrencyRepository.save(cryptocurrency));
    }
    @Override
    @Transactional
    public CryptocurrencyResponseDTO update(UUID id, CryptocurrencyRequestDTO cryptocurrencyRequestDTO) {
        Cryptocurrency existingCryptocurrency = cryptocurrencyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR002_CRYPTO_NOT_FOUND.getMessage(),
                        id
                )));
        // Actualizarea datelor existente ale criptomonedei cu cele noi din DTO
        existingCryptocurrency.setName(cryptocurrencyRequestDTO.getName());
        existingCryptocurrency.setSymbol(cryptocurrencyRequestDTO.getSymbol());
        existingCryptocurrency.setCurrentPrice(cryptocurrencyRequestDTO.getCurrentPrice());
        return cryptocurrencyMapper.toResponseDTO(cryptocurrencyRepository.save(existingCryptocurrency));
    }
    @Override
    @Transactional
    public void delete(UUID id) {
        Cryptocurrency cryptocurrency = cryptocurrencyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR002_CRYPTO_NOT_FOUND.getMessage(),
                        id
                )));
        cryptocurrencyRepository.delete(cryptocurrency);
    }
}