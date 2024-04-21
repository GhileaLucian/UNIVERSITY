package ro.ps.cryptoDanutX.dto.tradingOrders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
/**
 * Data Transfer Object (DTO) representing the request for TradingOrder.
 */
@Getter
@Setter
@NoArgsConstructor
public class TradingOrderRequestDTO {
    private String orderType;
    private BigDecimal amount;
    private BigDecimal price;
    private UUID cryptoId;
}