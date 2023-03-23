package com.Ecommerce.AmazOff.DTO.RequestDTO;

import com.Ecommerce.AmazOff.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CardRequestDTO {
    private int customerId;
    private String cardNo;
    private int cvv;
    private CardType cardType;
}
