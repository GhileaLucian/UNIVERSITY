package ro.ps.cryptoDanutX.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptoBalance.CryptoBalanceResponseDTO;
import ro.ps.cryptoDanutX.model.CryptoBalance;

/**
 * This interface provides mapping methods for converting between CryptoBalance entities and their DTO representations.
 */
@Mapper(componentModel = "spring", uses = {CryptocurrencyMapper.class, WalletMapper.class})
public interface CryptoBalanceMapper {

    /**
     * Converts a CryptoBalance entity to its response DTO representation.
     *
     * @param cryptoBalance The CryptoBalance entity to convert.
     * @return The corresponding CryptoBalanceResponseDTO.
     */
    @Mapping(source = "wallet.id", target = "walletId")
    @Mapping(source = "cryptocurrency.id", target = "cryptoId")
    CryptoBalanceResponseDTO toResponseDTO(CryptoBalance cryptoBalance);

    /**
     * Converts a CryptoBalanceRequestDTO to a CryptoBalance entity.
     *
     * @param dto The CryptoBalanceRequestDTO to convert.
     * @return The corresponding CryptoBalance entity.
     */
    @Mapping(target = "wallet", ignore = true)
    @Mapping(target = "cryptocurrency", ignore = true)
    CryptoBalance fromRequestDTO(CryptoBalanceRequestDTO dto);
}
