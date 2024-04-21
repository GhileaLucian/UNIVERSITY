package ro.ps.cryptoDanutX.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.ps.cryptoDanutX.dto.tradingOrders.TradingOrderRequestDTO;
import ro.ps.cryptoDanutX.dto.tradingOrders.TradingOrderResponseDTO;
import ro.ps.cryptoDanutX.model.TradingOrder;

/**
 * This interface provides mapping methods for converting between TradingOrder entities and their DTO representations.
 */
@Mapper(componentModel = "spring", uses = {CryptocurrencyMapper.class, CryptoUserMapper.class})
public interface TradingOrderMapper {

    /**
     * Converts a TradingOrder entity to its response DTO representation.
     *
     * @param tradingOrder The TradingOrder entity to convert.
     * @return The corresponding TradingOrderResponseDTO.
     */
    // Note: The line for mapping userId has been removed
    //@Mapping(source = "cryptocurrency.id", target = "cryptoId")
    TradingOrderResponseDTO toResponseDTO(TradingOrder tradingOrder);

    /**
     * Converts a TradingOrderRequestDTO to a TradingOrder entity.
     *
     * @param dto The TradingOrderRequestDTO to convert.
     * @return The corresponding TradingOrder entity.
     */
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cryptocurrency", ignore = true)
    TradingOrder fromRequestDTO(TradingOrderRequestDTO dto);
}
