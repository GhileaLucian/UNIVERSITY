package ro.ps.cryptoDanutX.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.ps.cryptoDanutX.dto.wallet.WalletRequestDTO;
import ro.ps.cryptoDanutX.dto.wallet.WalletResponseDTO;
import ro.ps.cryptoDanutX.model.Wallet;

/**
 * This interface provides mapping methods for converting between Wallet entities and their DTO representations.
 */
@Mapper(componentModel = "spring", uses = {CryptoBalanceMapper.class})
public interface WalletMapper {

    /**
     * Converts a Wallet entity to its response DTO representation.
     *
     * @param wallet The Wallet entity to convert.
     * @return The corresponding WalletResponseDTO.
     */
    WalletResponseDTO toResponseDTO(Wallet wallet);

    /**
     * Converts a WalletRequestDTO to a Wallet entity.
     *
     * @param dto The WalletRequestDTO to convert.
     * @return The corresponding Wallet entity.
     */
    @Mapping(target = "cryptoBalances", ignore = true)
    Wallet fromRequestDTO(WalletRequestDTO dto);
}
