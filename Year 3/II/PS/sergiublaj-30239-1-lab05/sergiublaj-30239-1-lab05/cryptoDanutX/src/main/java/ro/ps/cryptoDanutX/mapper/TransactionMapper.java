package ro.ps.cryptoDanutX.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.ps.cryptoDanutX.dto.transaction.TransactionRequestDTO;
import ro.ps.cryptoDanutX.dto.transaction.TransactionResponseDTO;
import ro.ps.cryptoDanutX.model.Transaction;

/**
 * This interface provides mapping methods for converting between Transaction entities and their DTO representations.
 */
@Mapper(componentModel = "spring", uses = {CryptocurrencyMapper.class, CryptoUserMapper.class})
public interface TransactionMapper {

    /**
     * Converts a Transaction entity to its response DTO representation.
     *
     * @param transaction The Transaction entity to convert.
     * @return The corresponding TransactionResponseDTO.
     */
    @Mapping(source = "user.id", target = "userId")
    TransactionResponseDTO toResponseDTO(Transaction transaction);

    /**
     * Converts a TransactionRequestDTO to a Transaction entity.
     *
     * @param dto The TransactionRequestDTO to convert.
     * @return The corresponding Transaction entity.
     */
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cryptocurrency", ignore = true)
    Transaction fromRequestDTO(TransactionRequestDTO dto);
}
