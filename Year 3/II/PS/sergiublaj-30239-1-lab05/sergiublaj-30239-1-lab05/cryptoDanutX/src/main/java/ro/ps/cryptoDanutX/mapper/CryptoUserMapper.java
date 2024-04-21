package ro.ps.cryptoDanutX.mapper;

import org.mapstruct.*;
import ro.ps.cryptoDanutX.dto.cryptoUser.CryptoUserRequestDTO;
import ro.ps.cryptoDanutX.dto.cryptoUser.CryptoUserResponseDTO;
import ro.ps.cryptoDanutX.model.CryptoUser;

import java.util.List;

/**
 * This interface provides mapping methods for converting between CryptoUser entities and their DTO representations.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CryptoUserMapper {

    /**
     * Converts a CryptoUser entity to its response DTO representation.
     *
     * @param cryptoUser The CryptoUser entity to convert.
     * @return The corresponding CryptoUserResponseDTO.
     */
    @Mappings({
            @Mapping(target = "preferredCurrencyCode", source = "preferredCurrency.symbol"),
            @Mapping(target = "wallets", ignore = true) // Ignored for simplification, requires detailed implementation
    })
    CryptoUserResponseDTO toCryptoUserResponseDTO(CryptoUser cryptoUser);

    /**
     * Converts a list of CryptoUser entities to a list of CryptoUserResponseDTOs.
     *
     * @param cryptoUserList The list of CryptoUser entities to convert.
     * @return The corresponding list of CryptoUserResponseDTOs.
     */
    List<CryptoUserResponseDTO> cryptoUserListToCryptoUserResponseDTOList(List<CryptoUser> cryptoUserList);

    /**
     * Converts a CryptoUserRequestDTO to a CryptoUser entity.
     *
     * @param cryptoUserRequestDTO The CryptoUserRequestDTO to convert.
     * @return The corresponding CryptoUser entity.
     */
    @Mappings({
            @Mapping(target = "preferredCurrency.symbol", source = "preferredCurrencyCode"),
            @Mapping(target = "id", ignore = true), // Ignored ID because it's auto-generated
            @Mapping(target = "wallet", ignore = true), // Ignored wallet for simplification
            @Mapping(target = "transactions", ignore = true), // Ignored transactions for simplification
            @Mapping(target = "tradingOrders", ignore = true) // Ignored trading orders for simplification
    })
    CryptoUser cryptoUserRequestDTOToCryptoUser(CryptoUserRequestDTO cryptoUserRequestDTO);

    /**
     * Updates an existing CryptoUser entity with data from a CryptoUserRequestDTO.
     *
     * @param dto    The CryptoUserRequestDTO containing updated data.
     * @param entity The CryptoUser entity to be updated.
     */
    @Mapping(target = "preferredCurrency.symbol", source = "preferredCurrencyCode")
    void updateCryptoUserFromDto(CryptoUserRequestDTO dto, @MappingTarget CryptoUser entity);
}
